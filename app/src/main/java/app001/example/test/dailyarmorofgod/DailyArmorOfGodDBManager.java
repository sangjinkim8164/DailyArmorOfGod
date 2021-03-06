package app001.example.test.dailyarmorofgod;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static app001.example.test.dailyarmorofgod.SecondQtDateSettingMain.log;

public class DailyArmorOfGodDBManager {

    // --------------------------------------------------------------------------------------------------------

    private static DailyArmorOfGodDBManager mDbManager;
    /*private OpenHelper opener;  // DB opener*/
    private SQLiteDatabase mDatabase;

    // --------------------------------------------------------------------------------------------------------

    private Context mContext = null;
    private ArrayList<String> mReadArrayList;

    static final String DB_NAME = "DB_DailyArmorOfGod";
    static final String TABLE_NAME = "QtVerseTable";
    static final int DB_VERSION = 1;

    /*private final String PRIMARY_KEY = "_date";
    private final String CAND_KEY_1 = "day_verse";
    private final String CAND_KEY_2 = "night_verse";*/
    // --------------------------------------------------------------------------------------------------------

    public static DailyArmorOfGodDBManager getInstance(Context context) {
        log("DailyArmorOfGodDBManager getInstance 생성자 실행");

        if (mDbManager == null) {
            log("1");
            mDbManager = new DailyArmorOfGodDBManager(context);
            log("2");
        }
        return mDbManager;

    } // DailyArmorOfGodDBManager getInstance

    // --------------------------------------------------------------------------------------------------------
    DailyArmorOfGodDBManager(Context context) {
        log("3");
        mContext = context;
        log("4");

        // 1. DB Manager를 생성할 때 DB를 생성하고 열기
        mDatabase =
                context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        log("5");

        // *  테이블 조회
        searchTable();

        log("6");

// --------------------------------------------------------------------------------------------------------
        String creatTblSql =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(_date INTEGER PRIMARY KEY, day_verse TEXT, night_verse TEXT);";

        try {

            // 2. DB에 테이블이 존재하지 않으면 생성

            /*mDatabase.execSQL("BEGIN TRANSACTION;");*/
            /*mDatabase.execSQL("COMMIT;");*/

            mDatabase.execSQL("DROP TABLE QtVerseTable");
            log("drop table");

            // QtVerseTable 생성
            mDatabase.execSQL(creatTblSql);

            log("7");

            // insert_sql ArrayList에 넣기
            loadFile();

            log("8");

            // insert_sql 쿼리로 입력
            exeInsertSQL();

            log("9");

            /*query();*/

            log("10");

            setMrefBibleET();

        } catch (SQLException e) {
            log(e.getMessage() + "");
        }

    } // DailyArmorOfGodDBManager( )

// --------------------------------------------------------------------------------------------------------

    // 시스템 날짜 값과 QtVerseTable의 값을 비교해서 EditText에 값을 출력하는 함수

    public void setMrefBibleET() {
        log("setMrefBibleET() 실행");

        // getMnD
        int tempMnD = SecondQtDateSettingMain.getMnD();
        /*log("***" + tempMnD + "");*/

        // SELECT 조회 쿼리문
        String tempSelectSQL =
                "SELECT day_verse FROM " + TABLE_NAME +
                        " WHERE _date=" + " '" + tempMnD + "' " + ";";

        try {

            // SELECT rawQuery
            Cursor result = mDatabase.rawQuery(tempSelectSQL, null);
            log("mDatabase.rawQuery(tempSelectSQL, null); 실행 완료");

            if (result.moveToNext()) {
                log("해당 데이터 발견");

                String tempCursorResult = result.getString(0);
                log(tempCursorResult);

                // EditText에 내용 출력
                SecondQtDateSettingMain.mRefBibleSettingEt.setText(tempCursorResult);

            } else {
                log("해당 데이터가 없음");
            }

        } catch (SQLException e) {
            log(e.getMessage());
        }

    } // setMrefBibleET()

// --------------------------------------------------------------------------------------------------------
    // insert 쿼리문을 반복해서 실행하는 함수

    public void exeInsertSQL() {
        log("**********************************************************************************");
        log("exeInsertSQL() 실행");

        int size = mReadArrayList.size();

        log(size + "");
        log(mReadArrayList.get(365).toString());

        /*mDatabase.execSQL(mReadArrayList.get(1).toString());*/

        // mReadArrayList 의 각 배열의 내용을 mDatabase.exeSQL( ); 로 실행
        for (int i = 0; i < size; i++) {
            String temp = mReadArrayList.get(i).toString();

            log(temp);
            mDatabase.execSQL(temp);

        } // for

        log("exeInsertSQL() 실행 완료");
        log("**********************************************************************************");
    } // exeInsertSQL()

// --------------------------------------------------------------------------------------------------------

    // raw 폴더의 파일을 읽어오는 함수 ( mReadArrayList 에 저장 )

    public void loadFile() {
        log("**********************************************************************************");
        log("loadFile() 실행");

        try {

            InputStream in = mContext.getResources().openRawResource(R.raw.insert_sql);

            if (in != null) {
                InputStreamReader stream = new InputStreamReader(in, "utf-8");
                BufferedReader buffer = new BufferedReader(stream);

                String readString;
                /*StringBuilder sb = new StringBuilder("");*/

                mReadArrayList = new ArrayList<String>();
                int i = -1;

                while ((readString = buffer.readLine()) != null) {
                    log("loadFile() while 실행");

                    /*sb.append(readString);*/
                    i++;

                    // 한 줄씩 읽어온 내용을 mReadArrayList 에 저장
                    mReadArrayList.add(i, readString);
                    log("***" + readString);
                    log("readArrayList.add(" + i + ", readString");

                } // while( )

                in.close();
                log("in.close();");

                // 읽어온 문자열 출력
                /*SecondQtDateSettingMain.setMShowET(mReadArrayList.size() +"");*/

            } // if(in != null)

        } catch (Exception e) {
            log(e.getStackTrace() + "");
        }

        log("loadFile() 실행 완료");
        log("**********************************************************************************");
    } // loadFile()

// --------------------------------------------------------------------------------------------------------

    // 테이블 내용을 EditText에 출력하는 함수

    public Cursor query() {
        log("query( ) 실행");

        String[] columns = new String[]{"_date", "day_verse", "night_verse"};

        Cursor c = mDatabase.query(TABLE_NAME, columns, null, null, null, null, null);

        if (c != null) {

            SecondQtDateSettingMain.mShowET.setText("");

            log("while 실행 전");

            while (c.moveToNext()) {

                String _date = c.getString(0);
                log("String date = c.getString(0);");

                String day_verse = c.getString(1);
                log("String day_verse = c.getString(1);");

                String night_verse = c.getString(2);
                log("String night_verse = c.getString(2);");

                // EditText에 출력
                SecondQtDateSettingMain.mShowET.append(
                        "_date : " + _date + "\n" +
                                "day_verse : " + day_verse + "\n" +
                                "night_verse : " + night_verse + "\n" +
                                "---------------------------------------");

                SecondQtDateSettingMain.mShowET.append("\n Total : " + c.getCount());

            } // while
            log("while 실행 종료");

            c.close();

        } // if (c != null)

        return c;

    } // Cursor query()

// --------------------------------------------------------------------------------------------------------

    // 테이블 조회 쿼리 함수
    public Cursor searchTable() {

        Cursor c = mDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {

            for (; ; ) {
                log("table name : " + c.getString(0));
                if (!c.moveToNext())
                    break;
            }
        }
        log(c + "");

        return c;

    } // searchTable()
// --------------------------------------------------------------------------------------------------------

} // class QtDateAndVerseDBManager