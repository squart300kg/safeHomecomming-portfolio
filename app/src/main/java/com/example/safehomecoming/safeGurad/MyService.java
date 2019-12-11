package com.example.safehomecoming.safeGurad;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

public class MyService extends Service {
    @Override
    public void onCreate(){

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Log.d("KeyUP Event", "뒤로 가기 키 down");
                return true;
        }
        return false;
    }

}
