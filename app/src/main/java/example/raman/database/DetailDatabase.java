package example.raman.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 6/5/14
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DetailDatabase {

    private DatabaseHelper dbHelper = null;

    public  final String TABLE_NAME = "DETAIL_TABLE";
    public  final String ID = "ID";
    public  final String ADDRESS = "ADDRESS";
    public  final String PHONE = "PHONE";
    public  final String DESIGNATION = "DESIGNATION";
    public  final String DEPARTMENT = "DEPARTMENT";
    public  final String INCOME = "INCOME";

    /**
     * Constructor
     */
    public DetailDatabase(Context context) {
        dbHelper =  DatabaseHelper.getinstance(context);
      //  getConnection();
    }

    /**
     * Get the database connection.
     */
    public SQLiteDatabase getConnection() {
        return dbHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void closeConnection() {
        dbHelper.close();
    }

    public boolean addInfo(DetailDto dto) {
        boolean flag = false;

            String sql = "insert into " + TABLE_NAME + "(" + ID
                    + ", " + ADDRESS  + ", " + PHONE + ", " + DESIGNATION + ", " + DEPARTMENT + ", " + INCOME + ") "
                    + "values('" + dto.getId() + "','"+ dto.getAddress() + "','"+ dto.getPhone() + "','"
                    + dto.getDesignation() +  "','"+ dto.getDepartment() + "','" + dto.getIncome() + "')";
            flag= dbHelper.insert(sql);

        return flag;
    }



    public Cursor getInfo(int id) {
        String sql = "select * from " + TABLE_NAME+" where ID="+id;

        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);

        cursor.moveToFirst();
        return cursor;
    }


    public ArrayList<DetailDto> getListInfo() {

        DetailDto cartInfo;
        ArrayList<DetailDto> cartList = new ArrayList<DetailDto>();
        String sql = "select * from " + TABLE_NAME;

        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);

        int count = cursor.getCount();

        if (count > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                cartInfo = new DetailDto(cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(PHONE)),
                        cursor.getString(cursor.getColumnIndex(DESIGNATION)),
                        cursor.getString(cursor.getColumnIndex(DEPARTMENT)),
                        cursor.getString(cursor.getColumnIndex(INCOME)));
                cartList.add(cartInfo);
            }
        }
        cursor.close();

        return cartList;
    }


    public void removeInfo(String id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID
                + "='" + id + "'";
        dbHelper.delete(sql);
    }


    public long updateInfo(DetailDto detailDto) {

        ContentValues values = new ContentValues();
        values.put(ID, detailDto.getId());
        values.put(ADDRESS, detailDto.getAddress());
        values.put(PHONE, detailDto.getPhone());
        values.put(DESIGNATION, detailDto.getDesignation());
        values.put(DEPARTMENT, detailDto.getDepartment());
        values.put(INCOME, detailDto.getIncome());


        long result = dbHelper.getWritableDatabase().update(TABLE_NAME, values,
                ID+" =?",
                new String[]{String.valueOf(detailDto.getId())});

        return result;


    }




}

