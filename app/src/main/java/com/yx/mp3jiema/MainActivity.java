package com.yx.mp3jiema;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set();
        Log.i("onCreate","onCreate");
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                moveTaskToBack(true);
                ComponentName IndexActivityNormal = new ComponentName(getBaseContext(), "com.yx.mp3jiema.MainActivity");
                ComponentName IndexActivity11 = new ComponentName(getBaseContext(), "com.yx.mp3jiema.MainAliasActivity");
                disableComponent(IndexActivityNormal);
                enableComponent(IndexActivity11);

            }
        });
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int heightPixels = ScreenUtils.getScreenHeight(this);
        int widthPixels = ScreenUtils.getScreenWidth(this);
        float density = dm.density;
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
        float smallestWidthDP;
        if(widthDP < heightDP) {
            smallestWidthDP = widthDP;
        }else {
            smallestWidthDP = heightDP;
        }
        Log.i("最小宽度",widthDP+","+heightDP);



    }
    /**
     * 启动组件
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        PackageManager mPackageManager=getPackageManager();
        mPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        PackageManager mPackageManager=getPackageManager();
        mPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    private void set(){

    }

    @Override
    protected void onDestroy() {
        Log.i("onDestroy","onDestroy");
        super.onDestroy();
    }

    @Override
    public void finish() {
        Log.i("finish","finish");
//        moveTaskToBack(true);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            moveTaskToBack(true);
            //这里写需要重写的方法
//            Intent intent = new Intent();
//// 为Intent设置Action、Category属性
//            intent.setAction(Intent.ACTION_MAIN);// "android.intent.action.MAIN"
//
//            intent.addCategory(Intent.CATEGORY_HOME); //"android.intent.category.HOME"
//
//            startActivity(intent);
            return true;
        }
        return false;

    }





}
