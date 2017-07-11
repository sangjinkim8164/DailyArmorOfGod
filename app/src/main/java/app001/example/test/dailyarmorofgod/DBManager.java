package app001.example.test.dailyarmorofgod;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


// SQLite DB 관리자 클래스
public class DBManager extends SQLiteOpenHelper {
/*
    private Context mRefContext;
*/
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory,
                     int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    // 생성자에서 넘겨받은 이름과 데이터베이스가 존재하지 않을 때 한 번 호출
    // ( 새로운 DB를 생성할 때)
    @Override
    public void onCreate(SQLiteDatabase db) {
        log("DBManeger.onCreate( )");
        db.execSQL
                ("CREATE TABLE QtDateAndVerse( _date INTEGER PRIMARY KEY, day_verse TEXT, night_verse TEXT");
    } // onCreate

    // 데이터베이스가 존재하지만 버전이 다를 때 호출
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

    } // PrintData()




// ---------------------------------------------------------------------------------------------------------------------------

    // 사용자 정의 함수

    // 1. Log
    public void log(String msg) {
        Log.v("mylog", msg);
    }

// ---------------------------------------------------------------------------------------------------------------------------




















} // class DBManager
