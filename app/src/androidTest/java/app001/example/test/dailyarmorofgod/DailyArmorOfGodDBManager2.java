package app001.example.test.dailyarmorofgod;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static app001.example.test.dailyarmorofgod.SecondQtDateSettingMain.log;

//  내부 OpenHelper 클래스를 가지는 DBManager 2 클래스 ( 기존 Manager 클래스 대체)
public class DailyArmorOfGodDBManager2 {

    // --------------------------------------------------------------------------------------------------------

    private OpenHelper opener;  // DB opener
    private SQLiteDatabase mDatabase;

    // --------------------------------------------------------------------------------------------------------

    private Context mContext;
    private ArrayList<String> mReadArrayList;

    static final String DB_NAME = "DB_DailyArmorOfGod";
    static final String TABLE_NAME= "QtVerseTable";
    static final int DB_VERSION = 1;

    // --------------------------------------------------------------------------------------------------------

    // 생성자
    public DailyArmorOfGodDBManager2(Context context) {
        log("DailyArmorOfGodDBManager2() 생성자 실행");

        this.mContext = context;

        this.opener = new OpenHelper(mContext, DB_NAME, null, DB_VERSION);

        mDatabase = opener.getWritableDatabase();
        log("mDatabase = opener.getWritableDatabase();");
    }
    // --------------------------------------------------------------------------------------------------------

    // DB and Table opener  클래스
    private class OpenHelper extends SQLiteOpenHelper {

        // OpenHelper 클래스 생성자
        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            log("OpenHelper() 생성자 실행");
        }

        /*public OpenHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, null, version);
        }*/
        // --------------------------------------------------------------------------------------------------------

        // ***** 생성된 DB 가 없을 때 한 번만 호출됨
        @Override
        public void onCreate(SQLiteDatabase mDatabase) {
            log("onCreate(SQLiteDatabase mDatabase) 실행");

            // 1. DB Manager를 생성할 때 DB를 생성하고 열기

            if(mContext == null) {
                log("mContext == null");
            }
            else {
                log("mContext != null");
            }

            mDatabase =
                    mContext.openOrCreateDatabase(DB_NAME , Context.MODE_PRIVATE, null);

            String creatTblSql =
                    "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                            "(_date INTEGER PRIMARY KEY, day_verse TEXT, night_verse TEXT);";

            try {
                 /*mDatabase.execSQL("DROP TABLE QtVerseTable");
                log("drop table");*/

                // QtVerseTable 생성
                mDatabase.execSQL(creatTblSql);
                log("mDatabase.execSQL(creatTblSql);");

                // insert_sql ArrayList에 넣기
                loadFile();
                log("loadFile();");

                // INSERT 쿼리로 데이터 삽입
                exeInsertSQL();
                log("exeInsertSQL();");

            } catch(SQLException e) {
                log(e.getMessage());
            }
        } // onCreate

        // --------------------------------------------------------------------------------------------------------
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    } // class OpenHelper

// --------------------------------------------------------------------------------------------------------

    // 시스템 날짜 값과 QtVerseTable의 값을 비교해서 EditText에 값을 출력하는 함수

    public void setMrefBibleET() {
        log("setMrefBibleET()");

        // getMnD
        int tempMnD = SecondQtDateSettingMain.getMnD();
        log("***" + tempMnD + "");

        // SELECT 조회 쿼리문
        String tempSelectSQL = "SELECT day_verse FROM " + TABLE_NAME + " WHERE _date=" + " '" + tempMnD + "' "+";";

        try {

            // SELECT rawquery
            Cursor result = mDatabase.rawQuery(tempSelectSQL, null);
            log("mDatabase.rawQuery(tempSelectSQL, null); 실행 완료");

            if(result.moveToNext()) {
                log("맞는 데이터 발견");

                String tempCursorResult = result.getString(0);
                log(tempCursorResult);

                // mRefBibleSettingEt.setText( );
                SecondQtDateSettingMain.mRefBibleSettingEt.setText(tempCursorResult);
            }
            else {
                log("맞는 데이터가 없음");
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
        for(int i = 0; i < size; i ++) {
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

            if(in != null) {
                InputStreamReader stream = new InputStreamReader(in, "utf-8");
                BufferedReader buffer = new BufferedReader(stream);

                String readString;
                /*StringBuilder sb = new StringBuilder("");*/

                mReadArrayList= new ArrayList<String>();
                int i = -1;

                while( (readString=buffer.readLine() ) != null ) {
                    log("loadFile() while 실행");

                    /*sb.append(readString);*/
                    i ++;

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

        } catch (Exception e){
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

        if(c.moveToFirst()) {

            for(;;) {
                log("table name : " + c.getString(0));
                if(!c.moveToNext())
                    break;
            }
        }
        log(c + "");

        return c;

    } // searchTable()

// --------------------------------------------------------------------------------------------------------

    /*mDatabase.execSQL("BEGIN TRANSACTION;");*/
                /*mDatabase.execSQL("COMMIT;");*/
                /*query();*/
                /*  테이블 조회
                searchTable();*/
                /*setMrefBibleET();*/

} // class DailyArmorOfGodDBManager2





