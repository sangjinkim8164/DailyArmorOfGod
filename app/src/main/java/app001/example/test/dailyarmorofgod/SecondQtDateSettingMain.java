package app001.example.test.dailyarmorofgod;

//  DailyArmorOfGod / 2. 오늘의 본문 / 메인 화면

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class SecondQtDateSettingMain extends Activity {

    // 변수 선언

    // xml 태그
    private EditText mRefDateSettingEt;
    private EditText mRefBibleSettingEt;

    // 날짜 및 시간 보관 변수
    private Calendar mRefCalendar;

    private int mYear;
    private int mMonth;
    private int mDay;

    static EditText mShowET;

    // DB 매니저 클래스 참조 변수
    public DailyArmorOfGodDBManager mDBManager;

// ==============================================================

    // onCreate()

    @Override
    public void onCreate(Bundle savedInstanceState) {
        log("SecondQtDateSettingMain onCreate()");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.second_qt_date_setting);

        // 주소 가져오기
        this.mRefDateSettingEt = (EditText)this.findViewById(R.id.date_setting_second_et);
        this.mRefBibleSettingEt = (EditText)this.findViewById(R.id.bible_setting_second_et);

        this.mShowET = (EditText)this.findViewById(R.id.show_qt_table_et);

        //  시스템 날짜 가져와서 출력 (date_setting_second_et)
        String tempDate = getDate();
        mRefDateSettingEt.setText(tempDate);

        // DB 매니저 사용
        mDBManager = DailyArmorOfGodDBManager.getInstance(this);

    } // onCreate

// ==============================================================

    // 사용자 정의 함수

// --------------------------------------------------------------------------------------------------------

    // mShowET 에 내용을 출력하는 함수
    static void setMShowET(String text) {
        SecondQtDateSettingMain.mShowET.setText(text);
    }

// --------------------------------------------------------------------------------------------------------

    // Calendar 클래스에 접근하는 함수
    public void getCalendarInfo() {
        log("getCalendarInfo");
        mRefCalendar = Calendar.getInstance();
    }
// --------------------------------------------------------------------------------------------------------

    // 현재 날짜를 읽어오는 함수
    public String getDate() {
        log("getDate");
        getCalendarInfo();
        // 연도 가져오기
        mYear = mRefCalendar.get(Calendar.YEAR);
        // 월 가져오기
        mMonth = mRefCalendar.get(Calendar.MONTH) + 1;
        // 일 가져오기
        mDay = mRefCalendar.get(Calendar.DATE);

        String currentDate = mYear + "년 " + mMonth + "월 " + mDay + "일";
        return  currentDate;
    }
// --------------------------------------------------------------------------------------------------------

    // (추가) 월 , 일만 가져오는 함수

    public int getMnD() {
        log("getMnD");
        getCalendarInfo();

        String month = Integer.toString(mRefCalendar.get(Calendar.MONTH)+1);
        int day = mRefCalendar.get(Calendar.DATE);

        String tempDay = String.format("%02d", day);
        int currentMnD = Integer.parseInt(month + tempDay);
        log("오늘의 월+일은 " + currentMnD + "");

        return currentMnD;
    }
// --------------------------------------------------------------------------------------------------------
    // log
    public void log(String msg) {
        Log.v("mylog", msg);
    }

    // toast
    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
// --------------------------------------------------------------------------------------------------------

} // class SecondQtDateSettingMain
