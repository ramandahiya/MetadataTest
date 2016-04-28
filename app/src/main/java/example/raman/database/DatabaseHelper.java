package example.raman.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import example.raman.R;


/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 6/5/14
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my.db3";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
   private  static DatabaseHelper instance;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String[] sql = context.getString(R.string.Database_onCreate).split("\n");
        db.beginTransaction();
        try {
            execMultipleSQL(db, sql);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){

            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public static synchronized DatabaseHelper getinstance(Context context){
      if(instance==null){
          instance=new DatabaseHelper(context);
      }
        return instance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String[] sql = context.getString(R.string.Database_onUpgrade)
                .split("\n");
        db.beginTransaction();
        try {
            execMultipleSQL(db, sql);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        onCreate(db);
    }


    private void execMultipleSQL(SQLiteDatabase db, String[] sql) {
        for (String s : sql)
            if (s.trim().length() > 0) {
                db.execSQL(s);
            }
    }


    public boolean insert(String sql) {
        try {

            getWritableDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(String sql) {
        getWritableDatabase().execSQL(sql);
    }

    public void delete(String sql) {
        getWritableDatabase().execSQL(sql);
    }


}
