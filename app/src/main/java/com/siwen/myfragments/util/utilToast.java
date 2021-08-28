package com.siwen.myfragments.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * created by siwen on 2020/09/16
 */
public class utilToast {
    private static Toast toast; //实现不管我们触发多少次Toast调用，都只会持续一次Toast显示的时长

    public static void showShortToast(Context context, String msg){
        if(toast!=null) {
            toast.cancel();//注销之前显示的那条信息
            toast = null;//这里要注意上一步相当于隐藏了信息，mtoast并没有为空，我们强制是他为空
        }
        if(toast==null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public static void showLongToast(Context context, String msg){
        if (toast!=null) {
            toast.cancel(); //注销之前显示的那条信息
            toast = null; //这里要注意上一步相当于隐藏了信息，mtoast并没有为空，我们强制是他为空
        }
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public static void showShortToastLow(Context context, String msg){
        if (toast != null){
            toast.cancel();
            toast = null;
        }
        if (toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }
        toast.setGravity(Gravity.BOTTOM, 0,60);
        toast.show();
    }

    public static void showLongToastLow(Context context, String msg){
        if (toast != null){
            toast.cancel();
            toast = null;
        }
        if (toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }else{
            toast.setText(msg);
        }
        toast.setGravity(Gravity.BOTTOM, 0,60);
        toast.show();
    }

    public static void showLongToastCenter(Context context, String msg){
        if (toast != null){
            toast.cancel();
            toast = null;
        }
        if (toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }else{
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0,-30);
        toast.show();
    }

    public static void showLongToastTop(Context context, String msg){
        if (toast != null){
            toast.cancel();
            toast = null;
        }
        if (toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        }else{
            toast.setText(msg);
        }
        toast.setGravity(Gravity.TOP, 0,-30);
        toast.show();
    }
}
