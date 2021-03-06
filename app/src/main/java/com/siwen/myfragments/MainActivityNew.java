package com.siwen.myfragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.siwen.myfragments.fragments.fragmentPageFive;
import com.siwen.myfragments.fragments.fragmentPageFour;
import com.siwen.myfragments.fragments.fragmentPageOne;
import com.siwen.myfragments.fragments.fragmentPageThree;
import com.siwen.myfragments.fragments.fragmentPageTwo;
import com.siwen.myfragments.util.CatchUtils;
import com.siwen.myfragments.util.Constant;
import com.siwen.myfragments.util.uitlTool;
import com.siwen.myfragments.util.utilToast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivityNew extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnPre;
    private Button mBtnNext;

    private LinearLayout mRegisterStep1;
    private LinearLayout mRegisterStep2;
    private LinearLayout mRegisterStep3;
    private LinearLayout mRegisterStep4;
    private LinearLayout mRegisterStep5;
    private ImageView mRegisterNext1;
    private ImageView mRegisterNext2;
    private ImageView mRegisterNext3;
    private ImageView mRegisterNext4;

    private int mCurrentStepIndex = 1;

    private fragmentPageOne mFragmentPageOne;
    private fragmentPageTwo mFragmentPageTwo;
    private fragmentPageThree mFragmentPageThree;
    private fragmentPageFour mFragmentPageFour;
    private fragmentPageFive mFragmentPageFive;
    private FragmentManager mSupportFragmentManager;
    private FragmentTransaction mTransaction;

    // time
    private TextView DateText;
    private TextView TimeText;
    private TextView WeekText;
    private String dateNow;
    private String timeNow;

    // ******************* show current system time *******************
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //???????????????
            //????????????????????????
            setTimeText();
            //Log.e("siwen","sdfsdfsdf = "+dateNow+"; ="+timeNow);
            handler.postDelayed(runnable, 1000);//?????????????????????runnable
        }
    };
    public void showTime(){
        handler.postDelayed(runnable, 0);//??????????????????runnable
    }
    //????????????????????????
    public void setTimeText(){
        dateNow = uitlTool.getNowDay("yyyy???MM???dd???");
        timeNow = uitlTool.getNowDay("HH:mm:ss");

        DateText.setText(dateNow);
        TimeText.setText(timeNow);
        WeekText.setText(uitlTool.getWeeks());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_btn_previous:
                if (mCurrentStepIndex <= 1) {
                    finish();
                } else {
                    doPrevious();
                }
                break;
            case R.id.reg_btn_next:
                switch (mCurrentStepIndex){
                    case 1:
                        doNext();
                        break;
                    case 2:
                        doNext();
//                        if (doPageTwoNext()){
//                            doNext();
//                        }
                        break;
                    case 3:
                        doNext();
                        break;
                    case 4:
                        doNext();
                        break;
                    case 5:
                        doNext();
                        break;
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        initViews();

        initEvents();

        showTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(runnable);
        handler.removeCallbacksAndMessages(null);
    }

    private void initViews() {
        DateText = (TextView) findViewById(R.id.main_date_text);
        TimeText = (TextView) findViewById(R.id.main_time_text);
        WeekText = (TextView) findViewById(R.id.main_week_text);

        mBtnNext = (Button) findViewById(R.id.reg_btn_next);
        mBtnPre = (Button) findViewById(R.id.reg_btn_previous);
        mBtnPre.setText("??????");

        intiPages();
        initFragmentData();
    }
    private void intiPages(){

        mRegisterStep1 = (LinearLayout) findViewById(R.id.register_step1);
        mRegisterStep2 = (LinearLayout) findViewById(R.id.register_step2);
        mRegisterStep3 = (LinearLayout) findViewById(R.id.register_step3);
        mRegisterStep4 = (LinearLayout) findViewById(R.id.register_step4);
        mRegisterStep5 = (LinearLayout) findViewById(R.id.register_step5);
        mRegisterNext1 = (ImageView) findViewById(R.id.register_next1);
        mRegisterNext2 = (ImageView) findViewById(R.id.register_next2);
        mRegisterNext3 = (ImageView) findViewById(R.id.register_next3);
        mRegisterNext4 = (ImageView) findViewById(R.id.register_next4);
        mRegisterStep1.setBackgroundResource(R.drawable.register_blue);
    }
    protected void initFragmentData() {
        mFragmentPageOne = new fragmentPageOne();
        mFragmentPageTwo = new fragmentPageTwo();
        mFragmentPageThree = new fragmentPageThree();
        mFragmentPageFour = new fragmentPageFour();
        mFragmentPageFive = new fragmentPageFive();

        Bundle bundle = new Bundle();
        bundle.putSerializable("param_key","111111");
        mFragmentPageOne.setArguments(bundle);
        mFragmentPageTwo.setArguments(bundle);
        mFragmentPageThree.setArguments(bundle);
        mFragmentPageFour.setArguments(bundle);
        mFragmentPageFive.setArguments(bundle);

        // ?????????fragment container?????????fragmentPageOne
        mSupportFragmentManager = getSupportFragmentManager();
        mTransaction = mSupportFragmentManager.beginTransaction();
        mTransaction.add(R.id.register_container, mFragmentPageOne)
                .commit();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.register_container, mFragmentPageOne)
//                .commit();
    }

    private void initEvents() {
        mBtnNext.setOnClickListener(this);
        mBtnPre.setOnClickListener(this);
    }

    private void doPrevious() {
        mCurrentStepIndex--;
        switch (mCurrentStepIndex){
            case 1:
                mSupportFragmentManager.popBackStack();
                mRegisterStep2.setBackgroundResource(R.drawable.register_gray);
                mRegisterNext1.setBackgroundResource(R.drawable.register_next);
                mBtnPre.setText("??????");
                break;
            case 2:
                mSupportFragmentManager.popBackStack();
                mRegisterStep3.setBackgroundResource(R.drawable.register_gray);
                mRegisterNext2.setBackgroundResource(R.drawable.register_next);
                mBtnPre.setText("?????????");
                break;
            case 3:
                mSupportFragmentManager.popBackStack();
                mRegisterStep4.setBackgroundResource(R.drawable.register_gray);
                mRegisterNext3.setBackgroundResource(R.drawable.register_next);
                break;
            case 4:
                mSupportFragmentManager.popBackStack();
                mRegisterStep5.setBackgroundResource(R.drawable.register_qure_gray);
                mRegisterNext4.setBackgroundResource(R.drawable.register_next);
                mBtnPre.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
                break;
            case 5:
                mSupportFragmentManager.popBackStack();
                mBtnPre.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    private void doNext() {
        mCurrentStepIndex++;
        switch (mCurrentStepIndex){
            case 2:
                mTransaction = mSupportFragmentManager.beginTransaction();
                mTransaction.setCustomAnimations(
                                R.animator.card_flip_right_in_new,
                                R.animator.card_flip_left_out_new,
                                R.animator.card_flip_left_in_new,
                                R.animator.card_flip_right_out_new)
                        .replace(R.id.register_container, mFragmentPageTwo)
                        .addToBackStack(null)
                        .commit();
                mRegisterStep2.setBackgroundResource(R.drawable.register_blue);
                mRegisterNext1.setBackgroundResource(R.drawable.register_next_blue);
                mBtnPre.setText("?????????");
                break;
            case 3:
                mTransaction = mSupportFragmentManager.beginTransaction();
                mTransaction.setCustomAnimations(
                                R.animator.card_flip_right_in_new,
                                R.animator.card_flip_left_out_new,
                                R.animator.card_flip_left_in_new,
                                R.animator.card_flip_right_out_new)
                        .replace(R.id.register_container, mFragmentPageThree)
                        .addToBackStack(null)
                        .commit();
                mRegisterStep3.setBackgroundResource(R.drawable.register_blue);
                mRegisterNext2.setBackgroundResource(R.drawable.register_next_blue);
                break;
            case 4:
                mTransaction = mSupportFragmentManager.beginTransaction();
                mTransaction.setCustomAnimations(
                                R.animator.card_flip_right_in_new,
                                R.animator.card_flip_left_out_new,
                                R.animator.card_flip_left_in_new,
                                R.animator.card_flip_right_out_new)
                        .replace(R.id.register_container, mFragmentPageFour)
                        .addToBackStack(null)
                        .commit();
                mRegisterStep4.setBackgroundResource(R.drawable.register_blue);
                mRegisterNext3.setBackgroundResource(R.drawable.register_next_blue);
                break;
            case 5:
                mTransaction = mSupportFragmentManager.beginTransaction();
                mTransaction.setCustomAnimations(
                                R.animator.card_flip_right_in_new,
                                R.animator.card_flip_left_out_new,
                                R.animator.card_flip_left_in_new,
                                R.animator.card_flip_right_out_new)
                        .replace(R.id.register_container, mFragmentPageFive)
                        .addToBackStack(null)
                        .commit();
                mRegisterStep5.setBackgroundResource(R.drawable.register_qure);
                mRegisterNext4.setBackgroundResource(R.drawable.register_next_blue);
                mBtnPre.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

}