package com.yx.mp3jiema;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * create by 2020/8/19
 *
 * @author yx
 */
public class WTEXTView extends android.support.v7.widget.AppCompatTextView {
    private Boolean unfold=true;
    public WTEXTView(Context context) {
        super(context);
    }

    public WTEXTView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private void init(){

    }
    public void doAction(){
        if (unfold){
            in();
        }else {
            an();
        }
        unfold=!unfold;
    }


    public void an(){
        setEllipsize(null);
        setMaxLines(Integer.MAX_VALUE);
    }

    public void in(){
       setEllipsize(TextUtils.TruncateAt.END);
       setMaxLines(3);
    }
}
