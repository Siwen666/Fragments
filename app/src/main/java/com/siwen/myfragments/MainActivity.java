package com.siwen.myfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.siwen.myfragments.util.CatchUtils;
import com.siwen.myfragments.util.Constant;
import com.siwen.myfragments.util.uitlTool;
import com.siwen.myfragments.util.utilToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FrameLayout mStepFlipper;
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


    private LinearLayout mPage1;
    private LinearLayout mPage2;
    private LinearLayout mPage3;
    private LinearLayout mPage4;
    private LinearLayout mPage5;
    private int mCurrentStepIndex = 1;

    // page2
    private EditText mHostUrlEdit;
    private EditText mSipUrlEdit;
    private EditText mSocketUrlEdit;

    // page3
    private Button mFenKong;
    private Button mZiKong;
    private Button mJianCang;

    // page4
    private Spinner mSpinnerYuanQu;
    private List<String> mYuanQuList = new ArrayList<>();
    private ArrayAdapter<String> YuanQuAdapter;
    private Spinner mSpinnerBuilding;
    private List<String> mBuildingList = new ArrayList<>();
    private ArrayAdapter<String> BuildingAdapter;
    private Spinner mSpinnerLouDong;
    private List<String> mLouDongList = new ArrayList<>();
    private ArrayAdapter<String> LouDongAdapter;
    private Spinner mSpinnerDanYuan;
    private List<String> mDanYuanList = new ArrayList<>();
    private ArrayAdapter<String> DanYuanAdapter;
    private Spinner mSpinnerFloor;
    private List<String> mFloorList = new ArrayList<>();
    private ArrayAdapter<String> FloorAdapter;
    private Spinner mSpinnerRoom;
    private List<String> mRoomList = new ArrayList<>();
    private ArrayAdapter<String> RoomAdapter;

    // page5
    private TextView mPhoneNumberText;
    private LinearLayout mQureRegister;

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
                        if (doPageTwoNext()){
                            doNext();
                        }
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
            case R.id.qure_register:
                utilToast.showLongToastCenter(this,"????????????");
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        mStepFlipper = (FrameLayout) findViewById(R.id.reg_vf_viewflipper);
        mBtnNext = (Button) findViewById(R.id.reg_btn_next);
        mBtnPre = (Button) findViewById(R.id.reg_btn_previous);
        mBtnPre.setText("??????");

        intiPages();
        initPageTwoViews();
        initPageThreeViews();
        initPageFourViews();
        initPageFiveViews();
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

        mPage1 = (LinearLayout) findViewById(R.id.page1);
        mPage2 = (LinearLayout) findViewById(R.id.page2);
        mPage3 = (LinearLayout) findViewById(R.id.page3);
        mPage4 = (LinearLayout) findViewById(R.id.page4);
        mPage5 = (LinearLayout) findViewById(R.id.page5);
    }
    private void initPageTwoViews() {

        mHostUrlEdit = (EditText) findViewById(R.id.base_url);
        mSipUrlEdit = (EditText) findViewById(R.id.sip_url);
        mSocketUrlEdit = (EditText) findViewById(R.id.socket_url);

        String baseurl = (String) CatchUtils.getParam(this, "BaseUrl", "");
        String sipip = (String) CatchUtils.getParam(this, "SipIP", "");
        String socketip = (String) CatchUtils.getParam(this, "SocketIp", "");

        mHostUrlEdit.setText(baseurl);
        mSipUrlEdit.setText(sipip);
        mSocketUrlEdit.setText(socketip);
    }
    private void initPageThreeViews() {

        mFenKong = (Button) findViewById(R.id.fenkong_btn);
        mZiKong = (Button)  findViewById(R.id.zikong_btn);
        mJianCang =  (Button)  findViewById(R.id.jiancang_btn);
    }
    private void initPageFourViews() {

        mSpinnerYuanQu = (Spinner) findViewById(R.id.spinner_yuanqu);
        mSpinnerBuilding = (Spinner) findViewById(R.id.spinner_building);
        mSpinnerLouDong = (Spinner) findViewById(R.id.spinner_loudong);
        mSpinnerDanYuan = (Spinner) findViewById(R.id.spinner_danyuan);
        mSpinnerFloor = (Spinner) findViewById(R.id.spinner_floor);
        mSpinnerRoom = (Spinner) findViewById(R.id.spinner_room);
    }
    private void initPageFiveViews() {
        mPhoneNumberText = (TextView) findViewById(R.id.phone_number_txt);
        mQureRegister = (LinearLayout) findViewById(R.id.qure_register);
    }

    private void initEvents() {
        mBtnNext.setOnClickListener(this);
        mBtnPre.setOnClickListener(this);

        initPageThreeEvents();
        initPageFourEvents();
        initPageFiveEvents();
    }
    private void initPageThreeEvents() {

        mFenKong.setOnClickListener(this);
        mZiKong.setOnClickListener(this);
        mJianCang.setOnClickListener(this);
    }
    private void initPageFourEvents(){
        boolean isHave = false; // ????????????????????????????????????
        int idx = 0;

        //????????????????????????????????????
        if (mYuanQuList!=null && mYuanQuList.isEmpty()) {
            mYuanQuList.add("??????");
        }
        String YuanQuStr  = (String) CatchUtils.getParam(this, "DeviceYuanQu","");
        for (int i=0; i<mYuanQuList.size(); i++){
            if (YuanQuStr.equals(mYuanQuList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //????????????????????????????????????????????????
        YuanQuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mYuanQuList);
        //??????????????????????????????????????????????????????
        YuanQuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //????????????????????????????????????????????????
        mSpinnerYuanQu.setAdapter(YuanQuAdapter);
        //??????????????????????????????????????????????????????????????????
        mSpinnerYuanQu.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerYuanQu.setSelection(idx); // ??????????????????
        }

        //????????????????????????????????????
        if (mBuildingList!=null && mBuildingList.isEmpty()) {
            mBuildingList.add(getResources().getString(R.string.please_select_building));
        }
        String BuildStr  = (String) CatchUtils.getParam(this, "DeviceBuild","");
        for (int i=0; i<mBuildingList.size(); i++){
            if (BuildStr.equals(mBuildingList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //????????????????????????????????????????????????
        BuildingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mBuildingList);
        //??????????????????????????????????????????????????????
        BuildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //????????????????????????????????????????????????
        mSpinnerBuilding.setAdapter(BuildingAdapter);
        //??????????????????????????????????????????????????????????????????
        mSpinnerBuilding.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerBuilding.setSelection(idx); // ??????????????????
        }

        //????????????????????????????????????
        if (mLouDongList!=null && mLouDongList.isEmpty()) {
            mLouDongList.add(getResources().getString(R.string.please_select_loudong));
        }
        String LouDongStr  = (String) CatchUtils.getParam(this, "DeviceLouDong","");
        for (int i=0; i<mLouDongList.size(); i++){
            if (LouDongStr.equals(mLouDongList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //????????????????????????????????????????????????
        LouDongAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mLouDongList);
        //??????????????????????????????????????????????????????
        LouDongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //????????????????????????????????????????????????
        mSpinnerLouDong.setAdapter(LouDongAdapter);
        //??????????????????????????????????????????????????????????????????
        mSpinnerLouDong.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerLouDong.setSelection(idx); // ??????????????????
        }

        //????????????????????????????????????
        if (mDanYuanList!=null && mDanYuanList.isEmpty()) {
            mDanYuanList.add(getResources().getString(R.string.please_select_danyuan));
        }
        String DanYuanStr  = (String) CatchUtils.getParam(this, "DeviceDanYuan","");
        for (int i=0; i<mDanYuanList.size(); i++){
            if (DanYuanStr.equals(mDanYuanList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //????????????????????????????????????????????????
        DanYuanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mDanYuanList);
        //??????????????????????????????????????????????????????
        DanYuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //????????????????????????????????????????????????
        mSpinnerDanYuan.setAdapter(DanYuanAdapter);
        //??????????????????????????????????????????????????????????????????
        mSpinnerDanYuan.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerDanYuan.setSelection(idx); // ??????????????????
        }

        //????????????????????????????????????
        if (mFloorList!=null && mFloorList.isEmpty()) {
            mFloorList.add(getResources().getString(R.string.please_select_floor));
        }
        String FloorStr  = (String) CatchUtils.getParam(this, "DeviceFloor","");
        for (int i=0; i<mFloorList.size(); i++){
            if (FloorStr.equals(mFloorList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //????????????????????????????????????????????????
        FloorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mFloorList);
        //??????????????????????????????????????????????????????
        FloorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //????????????????????????????????????????????????
        mSpinnerFloor.setAdapter(FloorAdapter);
        //??????????????????????????????????????????????????????????????????
        mSpinnerFloor.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerFloor.setSelection(idx); // ??????????????????
        }

        //????????????????????????????????????
        if (mRoomList!=null && mRoomList.isEmpty()) {
            mRoomList.add(getResources().getString(R.string.please_select_room));
        }
        String RoomStr  = (String) CatchUtils.getParam(this, "DeviceRoom","");
        for (int i=0; i<mRoomList.size(); i++){
            if (RoomStr.equals(mRoomList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //????????????????????????????????????????????????
        RoomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mRoomList);
        //??????????????????????????????????????????????????????
        RoomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //????????????????????????????????????????????????
        mSpinnerRoom.setAdapter(RoomAdapter);
        //??????????????????????????????????????????????????????????????????
        mSpinnerRoom.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerRoom.setSelection(idx); // ??????????????????
        }
    }
    private void initPageFiveEvents() {

        mQureRegister.setOnClickListener(this);
    }

    private void doPrevious() {
        mCurrentStepIndex--;
        switch (mCurrentStepIndex){
            case 1:
                mRegisterStep2.setBackgroundResource(R.drawable.register_gray);
                mRegisterNext1.setBackgroundResource(R.drawable.register_next);
                mPage1.setVisibility(View.VISIBLE);
                mPage2.setVisibility(View.GONE);
                mBtnPre.setText("??????");
                break;
            case 2:
                mRegisterStep3.setBackgroundResource(R.drawable.register_gray);
                mRegisterNext2.setBackgroundResource(R.drawable.register_next);
                mPage2.setVisibility(View.VISIBLE);
                mPage3.setVisibility(View.GONE);
                mBtnPre.setText("?????????");
                break;
            case 3:
                mRegisterStep4.setBackgroundResource(R.drawable.register_gray);
                mRegisterNext3.setBackgroundResource(R.drawable.register_next);
                mPage3.setVisibility(View.VISIBLE);
                mPage4.setVisibility(View.GONE);
                break;
            case 4:
                mRegisterStep5.setBackgroundResource(R.drawable.register_qure_gray);
                mRegisterNext4.setBackgroundResource(R.drawable.register_next);
                mPage4.setVisibility(View.VISIBLE);
                mPage5.setVisibility(View.GONE);
                mBtnPre.setVisibility(View.VISIBLE);
                mBtnNext.setVisibility(View.VISIBLE);
                break;
            case 5:
                mPage5.setVisibility(View.VISIBLE);
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
            case 1:
                mPage1.setVisibility(View.VISIBLE);
                mBtnPre.setText("??????");
                break;
            case 2:
                mRegisterStep2.setBackgroundResource(R.drawable.register_blue);
                mRegisterNext1.setBackgroundResource(R.drawable.register_next_blue);
                mPage1.setVisibility(View.GONE);
                mPage2.setVisibility(View.VISIBLE);
                mBtnPre.setText("?????????");
                break;
            case 3:
                mRegisterStep3.setBackgroundResource(R.drawable.register_blue);
                mRegisterNext2.setBackgroundResource(R.drawable.register_next_blue);
                mPage2.setVisibility(View.GONE);
                mPage3.setVisibility(View.VISIBLE);
                break;
            case 4:
                mRegisterStep4.setBackgroundResource(R.drawable.register_blue);
                mRegisterNext3.setBackgroundResource(R.drawable.register_next_blue);
                mPage3.setVisibility(View.GONE);
                mPage4.setVisibility(View.VISIBLE);
                break;
            case 5:
                mRegisterStep5.setBackgroundResource(R.drawable.register_qure);
                mRegisterNext4.setBackgroundResource(R.drawable.register_next_blue);
                mPage4.setVisibility(View.GONE);
                mPage5.setVisibility(View.VISIBLE);
                mBtnPre.setVisibility(View.GONE);
                mBtnNext.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    // Spinner select Listener
    private Spinner.OnItemSelectedListener selectedListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (view.getId()){
                case R.id.spinner_yuanqu:
                    CatchUtils.setParam(MainActivity.this, "DeviceYuanQu", mYuanQuList.get(position).toString()); // ???????????????
                    Constant.mDeviceYuanQu = mYuanQuList.get(position).toString(); // ?????????????????????
                    break;
                case R.id.spinner_building:
                    CatchUtils.setParam(MainActivity.this, "DeviceBuild", mBuildingList.get(position).toString()); // ???????????????
                    Constant.mDeviceBuild = mBuildingList.get(position).toString(); // ?????????????????????
                    break;
                case R.id.spinner_loudong:
                    CatchUtils.setParam(MainActivity.this, "DeviceLouDong", mLouDongList.get(position).toString()); // ???????????????
                    Constant.mDeviceLouDong = mLouDongList.get(position).toString(); // ?????????????????????
                    break;
                case R.id.spinner_danyuan:
                    CatchUtils.setParam(MainActivity.this, "DeviceDanYuan", mDanYuanList.get(position).toString()); // ???????????????
                    Constant.mDeviceDanYuan = mDanYuanList.get(position).toString(); // ?????????????????????
                    break;
                case R.id.spinner_floor:
                    CatchUtils.setParam(MainActivity.this, "DeviceFloor", mFloorList.get(position).toString()); // ???????????????
                    Constant.mDeviceFloor = mFloorList.get(position).toString(); // ?????????????????????
                    break;
                case R.id.spinner_room:
                    CatchUtils.setParam(MainActivity.this, "DeviceRoom", mRoomList.get(position).toString()); // ???????????????
                    Constant.mDeviceRoom = mRoomList.get(position).toString(); // ?????????????????????
                    break;
                default:
                    break;
            }
            parent.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            parent.setVisibility(View.VISIBLE);
        }
    };

    // page2 ??????????????????
    public boolean doPageTwoNext() {

        if (mHostUrlEdit.getText().toString().isEmpty()){
            utilToast.showLongToastCenter(this,"BASE IP???????????????");
        }else if (mSipUrlEdit.getText().toString().isEmpty()){
            utilToast.showLongToastCenter(this,"SIP IP???????????????");
        }else if (mSocketUrlEdit.getText().toString().isEmpty()){
            utilToast.showLongToastCenter(this,"SOCKET IP???????????????");
        }else{
            CatchUtils.setParam(this, "BaseUrl", mHostUrlEdit.getText().toString());
            CatchUtils.setParam(this, "SipIP", mSipUrlEdit.getText().toString());
            CatchUtils.setParam(this, "SocketIp", mSocketUrlEdit.getText().toString());

            Constant.BASE_URL =  mHostUrlEdit.getText().toString();
            Constant.SIP_IP = mSipUrlEdit.getText().toString();
            Constant.SOCKET_URL = mSocketUrlEdit.getText().toString();

            return true;
        }
        return false;
    }
}