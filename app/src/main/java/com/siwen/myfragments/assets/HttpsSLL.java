package com.siwen.myfragments.assets;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class HttpsSLL {

    /**
     * 创建信任所有证书的套接字工厂
     * 调用方法： .sslSocketFactory(SSLUtils.createTrustAllSSLSocketFactory()).hostnameVerifier(new SSLUtils.TrustXHostnameVerifier())
     * @return
     */
    @SuppressLint("TrulyRandom")
    public static SSLSocketFactory createTrustAllSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
        }
        return sSLSocketFactory;
    }

    /**
     * 信任所有的证书
     */
    public static class TrustAllManager implements X509TrustManager {

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            //            //检查所有证书
//            try {
//                TrustManagerFactory factory = TrustManagerFactory.getInstance("X509");
//                factory.init((KeyStore) null);
//                for (TrustManager trustManager : factory.getTrustManagers()) {
//                    ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
//                }
//                //获取网络中的证书信息
//                X509Certificate certificate = chain[0];
//                // 证书拥有者
//                String subject = certificate.getSubjectDN().getName();
//                // 证书颁发者
//                String issuer = certificate.getIssuerDN().getName();
//
//                Log.e("HHHTEST：-->", "证书拥有者：" + subject);
//                Log.e("HHHTEST：-->", "证书颁发者：" + issuer);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            Log.e("HHHTEST", "Exception：" + e.getMessage());
//                Log.e("HHHTEST：-->", "Exception：" + e.getMessage());
//            }
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static class TrustXHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //BASE_URL 域名
//            boolean verify = HttpsURLConnection.getDefaultHostnameVerifier().verify(Contanct.BaseUrl, session);
//            Log.e("HHHTEST", "hostname：" + hostname);
//            Log.e("HHHTEST", "verify：" + verify);
            return true; // verify
        }
    }


    /* *
     * 校验自定义的ca证书
     */
    // 调用方法：.sslSocketFactory(HttpsSLL.getSLLContext(context).getSocketFactory())
    public static SSLContext getSLLContext(Context context) {
        SSLContext sslContext = null;
        try {
//            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//            InputStream certificate = context.getAssets().open("https_crt.keystore");
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            keyStore.load(null);
//            String certificateAlias = Integer.toString(0);
//            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));
//            sslContext = SSLContext.getInstance("TLS");
//            final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //把证书打包在asset文件夹中 BuildConfig.AUTH_CERT：证书名称
            InputStream caInput = new BufferedInputStream(context.getAssets().open("https_crt.keystore"));
            Certificate ca;
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                caInput.close();
            }

            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            // Create an SSLContext that uses our TrustManager
            SSLContext s = SSLContext.getInstance("TLSv1", "AndroidOpenSSL"); // SSLContext.getInstance("TLS");
            s.init(null, tmf.getTrustManagers(), null);

        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return sslContext;
    }


    /**
     * 信任所有的证书，方法二
     */
    public static OkHttpClient getHttpsClient(OkHttpClient okhttpClient) {
        //OkHttpClient.Builder okhttpClient = new OkHttpClient().newBuilder();
        //信任所有服务器地址
        okhttpClient.newBuilder().hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                //设置为true
                return true;
            }
        });
        //创建管理器
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] x509Certificates,
                    String s) throws java.security.cert.CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
        } };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            //为OkHttpClient设置sslSocketFactory
            okhttpClient.newBuilder().sslSocketFactory(sslContext.getSocketFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return okhttpClient.newBuilder().build();
    }
}
