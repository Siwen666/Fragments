package com.siwen.myfragments.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.siwen.myfragments.R;
import com.siwen.myfragments.util.utilToast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentPageFive extends Fragment implements View.OnClickListener {

    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

    // page5
    private TextView mPhoneNumberText;
    private LinearLayout mQureRegister;

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
        View root = inflater.inflate(R.layout.layout_setp_five_new, container, false);
        initPageFiveViews(root);
        initPageFiveEvents();
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

    private void initPageFiveViews(View root) {
        mPhoneNumberText = (TextView) root.findViewById(R.id.phone_number_txt);
        mQureRegister = (LinearLayout) root.findViewById(R.id.qure_register);
    }

    private void initPageFiveEvents() {

        mQureRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qure_register:
                utilToast.showLongToastCenter(mActivity,"注册完成");
                mActivity.finish();
                break;
        }
    }
}
