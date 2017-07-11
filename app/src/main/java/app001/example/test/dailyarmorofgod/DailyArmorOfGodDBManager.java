package app001.example.test.dailyarmorofgod;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DailyArmorOfGodDBManager {

// --------------------------------------------------------------------------------------------------------
    static final String DB_DailyArmorOfGod = "DB_DailyArmorOfGod.db";
    static final String TABLE_QtDateAndVerse = "QtDateAndVerse";
    static  final int DB_VERSION = 1;
// --------------------------------------------------------------------------------------------------------
    Context mContext = null;

    private static DailyArmorOfGodDBManager mDbManager;
    private SQLiteDatabase mDatabase;

// --------------------------------------------------------------------------------------------------------
    public static  DailyArmorOfGodDBManager getInstance(Context context) {
        log("DailyArmorOfGodDBManager getInstance 생성자 실행");

        if(mDbManager == null) {
            log("1");
            mDbManager = new DailyArmorOfGodDBManager(context);
            log("2");
        }
        return mDbManager;

    } // DailyArmorOfGodDBManager getInstance

// --------------------------------------------------------------------------------------------------------
    private DailyArmorOfGodDBManager(Context context) {
        log("3");
        mContext = context;
        log("4");

        // 1. DB Manager를 생성할 때 DB를 생성하고 열기
        mDatabase =
                context.openOrCreateDatabase(DB_DailyArmorOfGod, Context.MODE_PRIVATE, null);
        log("5");
        // 2. DB에 테이블이 존재하지 않으면 생성
        mDatabase.execSQL
                ("CREATE TABLE QtDateAndVerse( " +
                        "_date INTEGER PRIMARY KEY, day_verse TEXT, night_verse TEXT)");
        log("6");

    } // DailyArmorOfGodDBManager( )
// --------------------------------------------------------------------------------------------------------

// --------------------------------------------------------------------------------------------------------

    // 사용자 정의 함수
    // 1. Log
    public static void log(String msg) {
        Log.v("mylog", msg);
    }

} // class QtDateAndVerseDBManager
