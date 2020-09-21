package com.yx.mp3jiema;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

/**
 * create by 2020/8/3
 *
 * @author yx
 */
public class TransparentDialog extends Dialog {
    private Activity activity;
    public TransparentDialog(@NonNull Context context,Activity activity) {
        super(context);
        setContentView(R.layout.dialog_a);
        this.activity=activity;
    }

    public TransparentDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void show() {
        super.show();
        activity.moveTaskToBack(true);
    }


}
