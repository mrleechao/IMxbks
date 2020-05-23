package com.example.imxbkslibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentActivity;
import android.support.v4.graphics.ColorUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;




/**
 * Created by Administrator on 2017/10/16/016.
 */
public abstract class BaseActivity extends FragmentActivity {

    private String mMsgContent;
    public boolean isVoice=false;//是否语音提示
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //6.0以下的一律改成透明的  要在setcontentview前面写 不然 有些机型状态栏高度显示不全
//        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
//            StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.transparent));
//        }else{
//            StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.black));
//            StatusBarUtil.StatusBarLightMode(this);//设置状态栏白底黑子
//        }



        setContentView(getLayoutID());


        initView();
        initData();
        listener();

    }


//----------------------------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            hideKeyboard();
        }
        return super.onTouchEvent(event);
    }
    /**
     * 隐藏系统键盘………………
     */
    public void hideKeyboard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getWindow().getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getWindow().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * @return 返回当前布局id
     */
    protected abstract int getLayoutID();
    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 数据填充
     */
    protected abstract void initData();

    /**
     * 注册点击事件
     */
    protected abstract void listener();

    /**
     * @return 返回当前实例，代替  xxxxxActivity.this
     */
    protected  BaseActivity getLActivity() {
        return this;
    }



    // 封装跳转
    public void openActivity(Class<?> c) {
        openActivity(c, null);
    }

    // 跳转 传递数据 bundel
    public void openActivity(Class<?> c, Bundle b) {
        openActivity(c, b, null);
    }

    public void openActivity(Class<?> c, Bundle b, Uri uri) {
        Intent intent = new Intent(this, c);
        if (b != null) {
            intent.putExtras(b);
        }
        if (uri != null) {
            intent.setData(uri);
        }
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    /**
     * Android 6.0 以上设置状态栏颜色
     */
    protected void setStatusBar(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 设置状态栏底色颜色
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(color);

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(color)) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }

    /**
     * 判断颜色是不是亮色
     *
     * @param color
     * @return
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    private boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    /**
     * 获取StatusBar颜色，默认白色
     *
     * @return
     */
    protected @ColorInt int getStatusBarColor() {
        return Color.WHITE;
    }


}
