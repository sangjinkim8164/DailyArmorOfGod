package app001.example.test.dailyarmorofgod;
// ---------------------------------------------------------------------------------------------------------------------------
    // SecondQtDate 화면으로 이동
    // (추가)
// ---------------------------------------------------------------------------------------------------------------------------

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainLayout_MainActivity extends Activity {

// ==========================================================================

    // 변수 선언
    private Intent mRefMainIntent;
    private Button mRefNextToSeondQTBtn;
    private Context mRefContext;

// ---------------------------------------------------------------------------------------------------------------------------

    // 재정의 함수
    @Override
    public void onCreate(Bundle savedInstanceState) {
        log("MainLayout onCreate()");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_layout);

        // 주소 가져오기
        this.mRefNextToSeondQTBtn = (Button)findViewById(R.id.main_next_to_second_qt_btn);
        mRefContext = this;

        // 버튼 클릭
        mRefNextToSeondQTBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log("mRefNextToSeondQTBtn 클릭");
                startActivity(new Intent(mRefContext, SecondQtDateSettingMain.class));
                log("startActivity");
            }
        });

    } // onCreate

// ---------------------------------------------------------------------------------------------------------------------------

    // 사용자 정의 함수

    // 1. Log
    public void log(String msg) {
        Log.v("mylog", msg);
    }

    // 2. Toast
    public void toast(String msg) {
        Toast.makeText(mRefContext, msg, Toast.LENGTH_SHORT).show();
    }

















} // class MainLayout_MainActivity
