package com.example.safehomecoming.citizen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.safehomecoming.R;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class Activity_Wait_for_request_guard_response extends AppCompatActivity
{
    private String TAG = "Activity_Wait_for_request_guard_response";

    private TextView
            button_cancel_request_safe_guard // 요청 취소 버튼
            ;

    long elapsed;
    final static long INTERVAL = 1000; // 1초 에 1씩 증가
    final static long TIMEOUT = 11000; // 종료 할 시간 설정 (10초)

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_for_request_guard_response);

        // View Find
        button_cancel_request_safe_guard = findViewById(R.id.button_cancel_request_safe_guard);


        // todo: 요청 취소 버튼
        button_cancel_request_safe_guard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                // todo: 매칭 성공 액티비티로 이동 (임시)
                Intent intent = new Intent(
                        Activity_Wait_for_request_guard_response.this,
                        Activity_success_guard_request.class);

                startActivity(intent);
            }
        });

        // todo: 타이머 1초부터 시작
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                elapsed += INTERVAL;
                if (elapsed >= TIMEOUT)
                {
                    this.cancel(); // 설정한 시간이 되면 타이머 중단
                    return;
                }

                // 현재 초를 displayText 메소드로 전달
                displayText(String.valueOf(elapsed / 1000));
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(task, INTERVAL, INTERVAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        timer.cancel(); // 타이머 중단
        finish();
    }

    // 시간 증가 메소드 (초당 1씩 값 증가)
    private void displayText(final String text)
    {
        this.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Log.e(TAG, "run: text: " + text);

                // todo: 10초간 도우미가 응답 없으면 매칭 실패 화면으로 이동
                if (Integer.parseInt(text) == 10)
                {
                    Intent intent = new Intent(
                            Activity_Wait_for_request_guard_response.this,
                            Activity_fail_request_guard.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
