package com.yx.mp3jiema;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set();
        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                ComponentName IndexActivityNormal = new ComponentName(getBaseContext(), "com.yx.mp3jiema.MainActivity");
                ComponentName IndexActivity11 = new ComponentName(getBaseContext(), "com.yx.mp3jiema.MainAliasActivity");
                disableComponent(IndexActivityNormal);
                enableComponent(IndexActivity11);
            }
        });

    }

    /**
     * 启动组件
     *
     * @param componentName 组件名
     */
    private void enableComponent(ComponentName componentName) {
        //此方法用以启用和禁用组件，会覆盖Androidmanifest文件下定义的属性
        PackageManager mPackageManager = getPackageManager();
        mPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName 组件名
     */
    private void disableComponent(ComponentName componentName) {
        PackageManager mPackageManager = getPackageManager();
        mPackageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    private void set() {

    }

    @Override
    protected void onDestroy() {
        Log.i("onDestroy", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void finish() {
        Log.i("finish", "finish");
//        moveTaskToBack(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    TransparentDialog dialog;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        dialog= new TransparentDialog(MainActivity.this,MainActivity.this);
//        dialog.show();
        moveTaskToBack(true);
        return true;
    }

    /**
     * 打开开发者模式界面
     */
    private void startDevelopmentActivity() {

        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
            startActivity(intent);
        } catch (Exception e) {
            try {
                ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.DevelopmentSettings");
                Intent intent = new Intent();
                intent.setComponent(componentName);
                intent.setAction("android.intent.action.View");
                startActivity(intent);
            } catch (Exception e1) {
                try {
                    Intent intent = new Intent("com.android.settings.APPLICATION_DEVELOPMENT_SETTINGS");
                    startActivity(intent);
                } catch (Exception e2) {

                }

            }
        } finally {
            finish();
        }
    }


}
