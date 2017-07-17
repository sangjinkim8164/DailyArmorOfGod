package app001.example.test.dailyarmorofgod;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*import java.sql.SQLException;*/

public class DailyArmorOfGodDBManager {

    // --------------------------------------------------------------------------------------------------------
    static final String DB_DailyArmorOfGod = "DB_DailyArmorOfGod.db";
    static final String TABLE_QtDateAndVerse = "QtDateAndVerse";
    static final int DB_VERSION = 1;
    // --------------------------------------------------------------------------------------------------------
    Context mContext = null;

    private static DailyArmorOfGodDBManager mDbManager;
    private SQLiteDatabase mDatabase;

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
    private DailyArmorOfGodDBManager(Context context) {
        log("3");
        mContext = context;
        log("4");

        // 1. DB Manager를 생성할 때 DB를 생성하고 열기
        mDatabase =
                context.openOrCreateDatabase(DB_DailyArmorOfGod, Context.MODE_PRIVATE, null);

        log("5");

        // *  테이블 조회
        searchTable();

        log("6");

        String creatTblSql =
                "CREATE TABLE IF NOT EXISTS " + "QtVerseTable" + "(_date INTEGER PRIMARY KEY, day_verse TEXT, night_verse TEXT);";

        // 2. DB에 테이블이 존재하지 않으면 생성
        mDatabase.execSQL(creatTblSql);

        log("7");

        log("8");

        query();

        log("9");

    } // DailyArmorOfGodDBManager( )
// --------------------------------------------------------------------------------------------------------
    // 테이블 내용을 EditText에 출력하기(쿼리)

    public Cursor query() {
        log("query( ) 실행");

        String[] columns = new String[]{"_date", "day_verse", "night_verse"};

        Cursor c = mDatabase.query("QtVerseTable", columns, null, null, null, null, null);

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
        return c;

    } // searchTable()
// --------------------------------------------------------------------------------------------------------

    // 사용자 정의 함수
    // 1. Log
    public static void log(String msg) {
        Log.v("mylog", msg);
    }

} // class QtDateAndVerseDBManager

// --------------------------------------------------------------------------------------------------------

 /*// 데이터베이스가 존재하지만 버전이 다를 때 호출
 @Override
 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 }

 // 쿼리 함수
 public void insert(String _query) {
  SQLiteDatabase db = getWritableDatabase();
  db.execSQL(_query);
  db.close();
 }

 public void update(String _query) {
  SQLiteDatabase db = getWritableDatabase();
  db.execSQL(_query);
  db.close();
 }

 public void delete(String _query) {
  SQLiteDatabase db = getWritableDatabase();
  db.execSQL(_query);
  db.close();
 }

 public String PrintData() {
  SQLiteDatabase db = getReadableDatabase();
  String str = "";

  Cursor cursor = db.rawQuery("SELECT * FROM QtDateAndVerse", null);
  while(cursor.moveToNext()) {
str += cursor.getInt(0) + " :  date " + cursor.getString(1) + "\n"
  + " : day_verse " + cursor.getInt(2) + " : night_verse " + "\n";
  }
  return str;

 } // PrintData()*/
