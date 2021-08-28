package com.siwen.myfragments.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import com.siwen.myfragments.R;
import com.siwen.myfragments.assets.HttpsSLL;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

/**
 * created by siwen on 2020/09/12
 * My helper methods
 */
public class uitlTool {

    public static final String pattern = "yyyy-MM-dd HH:mm:ss";

    /**
     *获取当前时间
     * @param timeFormat 时间格式
     * @return 时间文本
     */
    public static String getNowDay(String timeFormat){
        /**
         * SimpleDateFormat 是一个以与语言环境有关的方式来格式化和解析日期的具体类（java.text.SimpleDateFormat)。
         * 它允许进行格式化（日期 -> 文本）、解析（文本 -> 日期）和规范化。
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        String dateString = simpleDateFormat.format(new Date()); //将给定的 Date 格式化为日期/时间字符串
        return dateString;
    }

    /**
     * 设置星期几
     */
    public static String getWeeks(){
        String results = new String("");
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(uitlTool.getCurTimeLong());
        int day = instance.get(Calendar.DAY_OF_WEEK);
        int offDay = day;
        switch (offDay % 7) {
            case 0:
                results = "周六";
                break;
            case 1:
                results = "周日";
                break;
            case 2:
                results = "周一";
                break;
            case 3:
                results = "周二";
                break;
            case 4:
                results = "周三";
                break;
            case 5:
                results = "周四";
                break;
            case 6:
                results = "周五";
                break;
        }
        return results;
    }

    // 获取系统时间戳
    public static long getCurTimeLong(){
        long time=System.currentTimeMillis();
        //long time = getStringToDate(dataString, pattern);
        return time;
    }

    // 将字符串转为时间戳
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    //时间戳转字符串
    public static String getStrTime(long timeStamp, String pattern){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long  l = timeStamp;
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    // 展示剩余时、分、秒：
    public static String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue()/1000 ;
        if (second > 60) {
            minute = second / 60;   //取整
            second = second % 60;   //取余
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = ""+hour+"小时"+minute+"分"+second+"秒";
        return strtime;
    }

    /**
     * 简单的封装一个http下载类
     */
    public static Bitmap downLoadImg(String ul) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        //   File file = null;
        try {
            URL url = new URL(ul);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int fileLength = connection.getContentLength();


            input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            // output = new FileOutputStream(file.getAbsolutePath());
            // bitmap.compress(Bitmap.CompressFormat.JPEG, 100,output);

        } catch(Exception e){
            System.out.println(e.toString());
            // return e.toString();
            return null;
        } finally{
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
            if (connection != null)
                connection.disconnect();
        }
        return bitmap;
    }

    /**
     * 简单的封装一个https下载类
     */
    public static Bitmap downLoadImg(String ul, Context context) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //
        HttpsURLConnection.setDefaultSSLSocketFactory(HttpsSLL.createTrustAllSSLSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HttpsSLL.TrustXHostnameVerifier());

        InputStream input = null;
        OutputStream output = null;
        HttpsURLConnection connection = null;
        Bitmap bitmap = null;
        //   File file = null;
        try {
            URL url = new URL(ul);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                return null;
            }

            int fileLength = connection.getContentLength();


            input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
            // output = new FileOutputStream(file.getAbsolutePath());
            // bitmap.compress(Bitmap.CompressFormat.JPEG, 100,output);

        } catch(Exception e){
            System.out.println(e.toString());
            // return e.toString();
            return null;
        } finally{
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }
            if (connection != null)
                connection.disconnect();
        }
        return bitmap;
    }

    public static String getBaseUrl(Activity context)
    {
        String url = CatchUtils.getParam(context, "BaseUrl","").toString();
        if (url.isEmpty()){
            // url = Contanct.BaseUrl;
            url = "";
        }
        return url;
    }
    public static String getSocketUrl(Activity context)
    {
        String socket = CatchUtils.getParam(context, "SocketUrl","").toString();
        if (socket.isEmpty()){
            // url = Contanct.SocketUrl;
            socket = "";
        }
        return socket;
    }
}
