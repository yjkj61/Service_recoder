package com.yjkj.service_recoder.java.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;
import com.yjkj.service_recoder.R;
import com.yjkj.service_recoder.java.utils.ToastUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T viewBinding;
    protected Activity activity;

    private FrameLayout eyeCareView;

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        try {
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            viewBinding = (T) inflate.invoke(null, getLayoutInflater());
            ImmersionBar.with(this)
                    .statusBarColor(R.color.status_bar_color)
                    .statusBarDarkFont(true)
                    .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();
            setContentView(viewBinding.getRoot());
            activity = this;


            //hideSystemUI();
            initView();
            initData();
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        initEye();
    }


    public void initView() {
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("GestureDetector", e2.getX() + "");
                Log.i("GestureDetector1", e1.getX() + "");
                if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > Math.abs(velocityY) && e1.getX() < 300) {
                    finish(); // 右滑手势，结束当前Activity
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void initData() {

    }

    public void showToast(String msg) {
        ToastUtil.showToast(msg, activity);
    }


    public void go(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    public void go(Class clazz,String msg) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("msg",msg);
        startActivity(intent);
    }

    public void go(Class clazz,String msg,String msg2) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("msg",msg);
        intent.putExtra("msg2",msg2);
        startActivity(intent);
    }

    public void go(Class clazz,Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // 隐藏导航栏

    private void hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().getInsetsController().hide(
                    android.view.WindowInsets.Type.systemBars()
            );
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    protected void hideStatusBar(){
        //隐藏状态栏
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_STATUS_BAR).init();
    }


    public void updateMoney(Handler handler){
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }

    public void updateToken(String token,String userName,String header,String userId,String phoneNumber){


    }

    /**
     * 添加护眼模式浮层
     */
    private void initEye() {
        eyeCareView = new FrameLayout(this);
//        if (EyeCareHelper.getEyeCare()){
//            openEye();
//        }else {
//            closeEye();
//        }
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().addContentView(eyeCareView, params);
    }

    @Override
    protected void onResume() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onResume();
    }

//    /**
//     * 开启护眼模式
//     */
//    public void openEye() {
//        if (eyeCareView != null) {
//            eyeCareView.setBackgroundColor(EyeCareColorUtil.getFilterColor(30));
//        }
//    }
//
//    /**
//     * 关闭护眼模式
//     */
//    public void closeEye() {
//        if (eyeCareView != null) {
//            eyeCareView.setBackgroundColor(Color.TRANSPARENT);
//        }
//    }

}
