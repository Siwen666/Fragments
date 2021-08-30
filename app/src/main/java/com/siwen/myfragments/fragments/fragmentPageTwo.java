package com.siwen.myfragments.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.siwen.myfragments.R;
import com.siwen.myfragments.util.CatchUtils;
import com.siwen.myfragments.util.Constant;
import com.siwen.myfragments.util.utilToast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentPageTwo extends Fragment {

    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

    // page2
    private EditText mHostUrlEdit;
    private EditText mSipUrlEdit;
    private EditText mSocketUrlEdit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mActivity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);  //获取参数
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.layout_setp_two_new, container, false);
        initPageTwoViews(root);

        return root;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initPageTwoViews(View root) {

        mHostUrlEdit = (EditText) root.findViewById(R.id.base_url);
        mSipUrlEdit = (EditText) root.findViewById(R.id.sip_url);
        mSocketUrlEdit = (EditText) root.findViewById(R.id.socket_url);

        String baseurl = (String) CatchUtils.getParam(mActivity, "BaseUrl", "");
        String sipip = (String) CatchUtils.getParam(mActivity, "SipIP", "");
        String socketip = (String) CatchUtils.getParam(mActivity, "SocketIp", "");

        mHostUrlEdit.setText(baseurl);
        mSipUrlEdit.setText(sipip);
        mSocketUrlEdit.setText(socketip);
    }

    // page2 下一页的校验
    public boolean doPageTwoNext() {

        if (mHostUrlEdit.getText().toString().isEmpty()){
            utilToast.showLongToastCenter(mActivity,"BASE IP不能为空！");
        }else if (mSipUrlEdit.getText().toString().isEmpty()){
            utilToast.showLongToastCenter(mActivity,"SIP IP不能为空！");
        }else if (mSocketUrlEdit.getText().toString().isEmpty()){
            utilToast.showLongToastCenter(mActivity,"SOCKET IP不能为空！");
        }else{
            CatchUtils.setParam(mActivity, "BaseUrl", mHostUrlEdit.getText().toString());
            CatchUtils.setParam(mActivity, "SipIP", mSipUrlEdit.getText().toString());
            CatchUtils.setParam(mActivity, "SocketIp", mSocketUrlEdit.getText().toString());

            Constant.BASE_URL =  mHostUrlEdit.getText().toString();
            Constant.SIP_IP = mSipUrlEdit.getText().toString();
            Constant.SOCKET_URL = mSocketUrlEdit.getText().toString();

            return true;
        }
        return false;
    }
}
