package com.siwen.myfragments.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.siwen.myfragments.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentPageThree extends Fragment implements View.OnClickListener {

    // page3
    private Button mFenKong;
    private Button mZiKong;
    private Button mJianCang;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_setp_three_new, container, false);
        initPageThreeViews(root);
        initPageThreeEvents();
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


    private void initPageThreeViews(View root) {

        mFenKong = (Button) root.findViewById(R.id.fenkong_btn);
        mZiKong = (Button)  root.findViewById(R.id.zikong_btn);
        mJianCang =  (Button)  root.findViewById(R.id.jiancang_btn);
    }

    private void initPageThreeEvents() {

        mFenKong.setOnClickListener(this);
        mZiKong.setOnClickListener(this);
        mJianCang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fenkong_btn:
                mFenKong.setBackgroundResource(R.drawable.fenkong_blue);
                mZiKong.setBackgroundResource(R.drawable.zikong_gray);
                mJianCang.setBackgroundResource(R.drawable.jiancang_gray);
                break;
            case R.id.zikong_btn:
                mFenKong.setBackgroundResource(R.drawable.fenkong_gray);
                mZiKong.setBackgroundResource(R.drawable.zikong_blue);
                mJianCang.setBackgroundResource(R.drawable.jiancang_gray);
                break;
            case R.id.jiancang_btn:
                mFenKong.setBackgroundResource(R.drawable.fenkong_gray);
                mZiKong.setBackgroundResource(R.drawable.zikong_gray);
                mJianCang.setBackgroundResource(R.drawable.jiancang_blue);
                break;
            default:
                break;
        }
    }
}
