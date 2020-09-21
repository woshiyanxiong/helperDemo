package com.yx.mp3jiema;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;



import java.net.URL;
import java.util.concurrent.ExecutionException;


import jaydenxiao.com.expandabletextview.ExpandableTextView;

import static com.yx.mp3jiema.ScreenUtils.getScreenWidth;

/**
 * create by 2020/8/19
 *
 * @author yx
 */
public class HtmlUtils {
    /**
     * TextView HTML显示
     *
     * @param textView
     * @param html
     */
    public static void setHtml(TextView textView, String html) {
        setHtml(textView, html, null);
    }

    /**
     * TextView HTML显示
     *
     * @param textView
     * @param html
     */
    public static void setHtml(final TextView textView, String html, final FinishCallback callback) {
        if (TextUtils.isEmpty(html)) {
            textView.setText("");
            if (callback != null) {
                callback.finished();
            }
            return;
        }
        html = html.replaceAll("\n", "<br/>");
        final String finalHtml = html;
        Log.i("htmlString",finalHtml);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Spanned text = Html.fromHtml(finalHtml, new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        URL url = null;
                        Log.i("html图片",source);
//                        try {
//                            drawable= Glide.with(textView.getContext()).asDrawable().load(source).into(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).get();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                        try {
                            url = new URL(source);
                            drawable = Drawable.createFromStream(url.openStream(), "img");
                        } catch (Exception e) {
                            e.printStackTrace();
                            return null;
                        }
                        double widthHtml = drawable.getIntrinsicWidth();
                        double heightHtml = drawable.getIntrinsicHeight();
                        int screenWidth = textView.getMeasuredWidth();
                        if (screenWidth == 0) {
                            screenWidth = getScreenWidth(textView.getContext());
                        }
                        double width = screenWidth;
                        double height = 0;
                        //获取屏幕宽高
                        if (heightHtml <= 0) {
                            height = screenWidth * 3 / 4;
                        }
                        if (heightHtml > 0 && heightHtml > 0) {
                            height = heightHtml / (widthHtml / screenWidth);
                        }
                        drawable.setBounds(0, 0, 200, 200);

                        return drawable;
                    }
                }, null);
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.finished();
                        }
                        textView.setText(text);

                    }
                });
            }
        }).start();
    }
    public interface FinishCallback {
        void finished();
    }
}
