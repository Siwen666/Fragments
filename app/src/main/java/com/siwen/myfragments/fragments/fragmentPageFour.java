package com.siwen.myfragments.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.siwen.myfragments.MainActivityNew;
import com.siwen.myfragments.R;
import com.siwen.myfragments.util.CatchUtils;
import com.siwen.myfragments.util.Constant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentPageFour extends Fragment {

    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

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
        View root = inflater.inflate(R.layout.layout_setp_four_new, container, false);
        initPageFourViews(root);
        initPageFourEvents();
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

    private void initPageFourViews(View root) {

        mSpinnerYuanQu = (Spinner) root.findViewById(R.id.spinner_yuanqu);
        mSpinnerBuilding = (Spinner) root.findViewById(R.id.spinner_building);
        mSpinnerLouDong = (Spinner) root.findViewById(R.id.spinner_loudong);
        mSpinnerDanYuan = (Spinner) root.findViewById(R.id.spinner_danyuan);
        mSpinnerFloor = (Spinner) root.findViewById(R.id.spinner_floor);
        mSpinnerRoom = (Spinner) root.findViewById(R.id.spinner_room);
    }

    private void initPageFourEvents(){
        boolean isHave = false; // 如果参数在列表中已经存在
        int idx = 0;

        //第一步：定义下拉列表内容
        if (mYuanQuList!=null && mYuanQuList.isEmpty()) {
            mYuanQuList.add("园区");
        }
        String YuanQuStr  = (String) CatchUtils.getParam(mActivity, "DeviceYuanQu","");
        for (int i=0; i<mYuanQuList.size(); i++){
            if (YuanQuStr.equals(mYuanQuList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //第二步：为下拉列表定义一个适配器
        YuanQuAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, mYuanQuList);
        //第三步：设置下拉列表下拉时的菜单样式
        YuanQuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpinnerYuanQu.setAdapter(YuanQuAdapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        mSpinnerYuanQu.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerYuanQu.setSelection(idx); // 设置默认选项
        }

        //第一步：定义下拉列表内容
        if (mBuildingList!=null && mBuildingList.isEmpty()) {
            mBuildingList.add(getResources().getString(R.string.please_select_building));
        }
        String BuildStr  = (String) CatchUtils.getParam(mActivity, "DeviceBuild","");
        for (int i=0; i<mBuildingList.size(); i++){
            if (BuildStr.equals(mBuildingList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //第二步：为下拉列表定义一个适配器
        BuildingAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, mBuildingList);
        //第三步：设置下拉列表下拉时的菜单样式
        BuildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpinnerBuilding.setAdapter(BuildingAdapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        mSpinnerBuilding.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerBuilding.setSelection(idx); // 设置默认选项
        }

        //第一步：定义下拉列表内容
        if (mLouDongList!=null && mLouDongList.isEmpty()) {
            mLouDongList.add(getResources().getString(R.string.please_select_loudong));
        }
        String LouDongStr  = (String) CatchUtils.getParam(mActivity, "DeviceLouDong","");
        for (int i=0; i<mLouDongList.size(); i++){
            if (LouDongStr.equals(mLouDongList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //第二步：为下拉列表定义一个适配器
        LouDongAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, mLouDongList);
        //第三步：设置下拉列表下拉时的菜单样式
        LouDongAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpinnerLouDong.setAdapter(LouDongAdapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        mSpinnerLouDong.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerLouDong.setSelection(idx); // 设置默认选项
        }

        //第一步：定义下拉列表内容
        if (mDanYuanList!=null && mDanYuanList.isEmpty()) {
            mDanYuanList.add(getResources().getString(R.string.please_select_danyuan));
        }
        String DanYuanStr  = (String) CatchUtils.getParam(mActivity, "DeviceDanYuan","");
        for (int i=0; i<mDanYuanList.size(); i++){
            if (DanYuanStr.equals(mDanYuanList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //第二步：为下拉列表定义一个适配器
        DanYuanAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, mDanYuanList);
        //第三步：设置下拉列表下拉时的菜单样式
        DanYuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpinnerDanYuan.setAdapter(DanYuanAdapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        mSpinnerDanYuan.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerDanYuan.setSelection(idx); // 设置默认选项
        }

        //第一步：定义下拉列表内容
        if (mFloorList!=null && mFloorList.isEmpty()) {
            mFloorList.add(getResources().getString(R.string.please_select_floor));
        }
        String FloorStr  = (String) CatchUtils.getParam(mActivity, "DeviceFloor","");
        for (int i=0; i<mFloorList.size(); i++){
            if (FloorStr.equals(mFloorList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //第二步：为下拉列表定义一个适配器
        FloorAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, mFloorList);
        //第三步：设置下拉列表下拉时的菜单样式
        FloorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpinnerFloor.setAdapter(FloorAdapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        mSpinnerFloor.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerFloor.setSelection(idx); // 设置默认选项
        }

        //第一步：定义下拉列表内容
        if (mRoomList!=null && mRoomList.isEmpty()) {
            mRoomList.add(getResources().getString(R.string.please_select_room));
        }
        String RoomStr  = (String) CatchUtils.getParam(mActivity, "DeviceRoom","");
        for (int i=0; i<mRoomList.size(); i++){
            if (RoomStr.equals(mRoomList.get(i))){
                isHave = true;
                idx = i;
            }
        }
        //第二步：为下拉列表定义一个适配器
        RoomAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, mRoomList);
        //第三步：设置下拉列表下拉时的菜单样式
        RoomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //第四步：将适配器添加到下拉列表上
        mSpinnerRoom.setAdapter(RoomAdapter);
        //第五步：添加监听器，为下拉列表设置事件的响应
        mSpinnerRoom.setOnItemSelectedListener(selectedListener);
        if (isHave){
            mSpinnerRoom.setSelection(idx); // 设置默认选项
        }
    }

    // Spinner select Listener
    private Spinner.OnItemSelectedListener selectedListener = new Spinner.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (view.getId()){
                case R.id.spinner_yuanqu:
                    CatchUtils.setParam(mActivity, "DeviceYuanQu", mYuanQuList.get(position).toString()); // 选择后保存
                    Constant.mDeviceYuanQu = mYuanQuList.get(position).toString(); // 选择后直接赋值
                    break;
                case R.id.spinner_building:
                    CatchUtils.setParam(mActivity, "DeviceBuild", mBuildingList.get(position).toString()); // 选择后保存
                    Constant.mDeviceBuild = mBuildingList.get(position).toString(); // 选择后直接赋值
                    break;
                case R.id.spinner_loudong:
                    CatchUtils.setParam(mActivity, "DeviceLouDong", mLouDongList.get(position).toString()); // 选择后保存
                    Constant.mDeviceLouDong = mLouDongList.get(position).toString(); // 选择后直接赋值
                    break;
                case R.id.spinner_danyuan:
                    CatchUtils.setParam(mActivity, "DeviceDanYuan", mDanYuanList.get(position).toString()); // 选择后保存
                    Constant.mDeviceDanYuan = mDanYuanList.get(position).toString(); // 选择后直接赋值
                    break;
                case R.id.spinner_floor:
                    CatchUtils.setParam(mActivity, "DeviceFloor", mFloorList.get(position).toString()); // 选择后保存
                    Constant.mDeviceFloor = mFloorList.get(position).toString(); // 选择后直接赋值
                    break;
                case R.id.spinner_room:
                    CatchUtils.setParam(mActivity, "DeviceRoom", mRoomList.get(position).toString()); // 选择后保存
                    Constant.mDeviceRoom = mRoomList.get(position).toString(); // 选择后直接赋值
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
}
