package app001.example.test.dailyarmorofgod;

//  DailyArmorOfGod / 2. 오늘의 본문 / 메인 화면

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import java.util.Arrays;
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

    int [] janMnD = new int  [31];
    int [] febMnD = new int  [29];
    int [] marMnD = new int  [31];
    int [] aprMnD = new int  [30];
    int [] mayMnD = new int  [31];
    int [] junMnD = new int  [30];
    int [] julMnD = new int  [31];
    int [] augMnD = new int  [31];
    int [] sepMnD = new int  [30];
    int [] nobMnD = new int  [30];
    int [] octMnD = new int  [31];
    int [] decMnD = new int  [31];

    // DB 매니저 클래스 참조 변수
    public DailyArmorOfGodDBManager mDBManager;

// ==============================================================

        // 재정의 함수

        // onCreate()
        @Override
        public void onCreate(Bundle savedInstanceState) {
            log("SecondQtDateSettingMain onCreate()");
            super.onCreate(savedInstanceState);
            this.setContentView(R.layout.second_qt_date_setting);

            // 주소 가져오기
            this.mRefDateSettingEt = (EditText)this.findViewById(R.id.date_setting_second_et);
            this.mRefBibleSettingEt = (EditText)this.findViewById(R.id.bible_setting_second_et);

            //  시스템 날짜 가져와서 출력 (date_setting_second_et)
            String tempDate = getDate();
            mRefDateSettingEt.setText(tempDate);

            // setArrayMnDver2
            setArrayMnDver2();
            log("setArrayMnDver2()");

            // DB 매니저 사용
            mDBManager = DailyArmorOfGodDBManager.getInstance(this);

//            // 월 , 일만 가져오는 함수(로그 확인)
//            getMnD();

        } // onCreate

// ==============================================================

        // 사용자 정의 함수

        // Calendar 클래스에 접근하는 함수
        public void getCalendarInfo() {
                log("getCalendarInfo");
                mRefCalendar = Calendar.getInstance();
        }

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

    // 월, 날짜를 배열로 만들어주는 함수 만들기(2)

    public void setArrayMnDver2() {
        log("setArrayMnDver2 실행");

        int tempForMon;

            try {

                for(int i = 1; i <= 30; i ++ ) {
                    String temp = String.format("%02d", i);

                    tempForMon = Integer.parseInt("4" + temp);
                    aprMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("6" + temp);
                    junMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("9" + temp);
                    sepMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("11" + temp);
                    nobMnD [i-1] = tempForMon;

                } // for

                for(int i = 1; i <= 29; i ++ ) {
                    String temp = String.format("%02d", i);

                    tempForMon = Integer.parseInt("2" + temp);
                    febMnD [i-1] = tempForMon;
                }

                for(int i = 1; i <= 31; i ++ ) {
                    String temp = String.format("%02d", i);

                    tempForMon = Integer.parseInt("1" + temp);
                    janMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("3" + temp);
                    marMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("5" + temp);
                    mayMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("7" + temp);
                    julMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("8" + temp);
                    augMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("10" + temp);
                    octMnD [i-1] = tempForMon;

                    tempForMon = Integer.parseInt("12" + temp);
                    decMnD [i-1] = tempForMon;

                } // for

                // 배열 출력해서 확인
                logPrintArrayMnD();
                log("logPrintArrayMnD 실행");

            } catch(Exception e) {
                e.printStackTrace();
            }

    } // setArrayMnDver2

// --------------------------------------------------------------------------------------------------------
    // 월&일 배열의 값을 확인하는 함수

    public void logPrintArrayMnD() {
        log("logPrintArrayMnD 실행");

        log(Arrays.toString(janMnD));
        log(Arrays.toString(febMnD));
        log(Arrays.toString(marMnD));
        log(Arrays.toString(aprMnD));
        log(Arrays.toString(mayMnD));
        log(Arrays.toString(junMnD));
        log(Arrays.toString(julMnD));
        log(Arrays.toString(augMnD));
        log(Arrays.toString(sepMnD));
        log(Arrays.toString(octMnD));
        log(Arrays.toString(nobMnD));
        log(Arrays.toString(decMnD));
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

//    // 월, 날짜를 배열로 만들어주는 함수 만들기
//
//    public void setArrayMnD(String mon) {
//        log("setArrayMnD");
//
//        int tempForMon;
//
//        // 1. 월을 입력 받기 (int mon)
//
//        // 2. 조건 나누기
//        // (1, 3, 5, 7, 8, 10, 12) -> 31일까지
//        // (4, 6, 9, 11) -> 30일까지
//        // (2월은 29일까지)
//        // 월 별로 배열 반환
//
//        if( mon.equals("04") || mon.equals("06") || mon.equals("09") || mon.equals("11") == true ) {
//
//            for(int i = 0; i <= 30; i ++ ) {
//                tempForMon = Integer.parseInt(String.format("%02d", mon+ i));
//
//                switch(mon) {
//                    case "04" :
//                        aprMnD [i] = tempForMon;
//                        break;
//                    case "06" :
//                        junMnD [i] = tempForMon;
//                        break;
//                    case "09" :
//                        sepMnD [i] = tempForMon;
//                        break;
//                    case "11" :
//                        nobMnD [i] = tempForMon;
//                        break;
//                } // switch
//
//            } // for
//
//        }
//        else if(mon.equals(02) == true) {
//            for(int i = 0; i <= 29; i ++ ) {
//                tempForMon = Integer.parseInt(String.format("%02d", mon+ i));
//
//                febMnD [i] = tempForMon;
//            }
//        }
//        else {
//            for(int i = 0; i <= 31; i ++ ) {
//                tempForMon = Integer.parseInt(String.format("%02d", mon+ i));
//
//                switch(mon) {
//                    case "01" :
//                        janMnD [i] = tempForMon;
//                        break;
//                    case "03" :
//                        marMnD [i] = tempForMon;
//                        break;
//                    case "05" :
//                        mayMnD [i] = tempForMon;
//                        break;
//                    case "07" :
//                        julMnD [i] = tempForMon;
//                        break;
//                    case "08" :
//                        augMnD [i] = tempForMon;
//                        break;
//                    case "10" :
//                        octMnD [i] = tempForMon;
//                        break;
//                    case "12" :
//                        decMnD [i] = tempForMon;
//                        break;
//                    default :
//                        log("01~12 사이의 값을 입력하세요");
//                        break;
//                } // switch
//
//            } // for
//
//        } // else
//
//    } // setArrayMnD
// --------------------------------------------------------------------------------------------------------


} // class SecondQtDateSettingMain
