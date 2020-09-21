package com.yx.mp3jiema;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


/**
 * create by 2020/3/16
 *
 * @author yx
 */
public class SecretCodeReceiver extends BroadcastReceiver {
    public SecretCodeReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("重启",intent.getAction());
        if (intent != null && "android.provider.Telephony.SECRET_CODE".equals(intent.getAction())){
            Toast.makeText(context,"22222222",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, TextActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);
        }


    }

}

