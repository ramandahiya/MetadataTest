package example.raman.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 6/5/14
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthDatabase {
    private DatabaseHelper dbHelper = null;
    public  final String TABLE_NAME = "AUTH_TABLE";
    public  final String ID = "ID";
    public  final String FIRSTNAME = "FIRSTNAME";
    public  final String LASTNAME = "LASTNAME";
    public  final String EMAIL = "EMAIL";


    /**
     * Constructor
     */
    public AuthDatabase(Context context) {
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

    public int addInfo(AuthDto dto) {

            String sql = "insert into " + TABLE_NAME + "(" + FIRSTNAME  + ", " + LASTNAME + ", " + EMAIL + ") "
                    + "values('" + dto.getFirstname() + "','"+ dto.getLastname() + "','"
                    + dto.getEmail() + "')";
         boolean bool=   dbHelper.insert(sql);


      return  getInfo();
    }


    public int getInfo() {

        String sql = "select last_insert_rowid() AS rowid from " + TABLE_NAME +" LIMIT 1";
        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);


         cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public Cursor getInfo(int id) {
        String sql = "select * from " + TABLE_NAME+" where ID="+id;

        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);
         cursor.moveToFirst();

        return cursor;
    }


    public ArrayList<AuthDto> getListInfo() {

        AuthDto cartInfo;
        ArrayList<AuthDto> cartList = new ArrayList<AuthDto>();
        String sql = "select * from " + TABLE_NAME;

        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(sql, null);

        int count = cursor.getCount();

        if (count > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                cartInfo = new AuthDto(cursor.getInt(cursor.getColumnIndex(ID)),
                                cursor.getString(cursor.getColumnIndex(FIRSTNAME)),
                                cursor.getString(cursor.getColumnIndex(LASTNAME)),
                                cursor.getString(cursor.getColumnIndex(EMAIL)));
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


    public long updateInfo(AuthDto authDto) {

            ContentValues values = new ContentValues();
             values.put(ID, authDto.getId());
             values.put(FIRSTNAME, authDto.getFirstname());
             values.put(LASTNAME, authDto.getLastname());
             values.put(EMAIL, authDto.getEmail());


        long result = dbHelper.getWritableDatabase().update(TABLE_NAME, values,
                    ID+" =?",
                    new String[]{String.valueOf(authDto.getId())});

            return result;


    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
//        String query = "SELECT FIRSTNAME,LASTNAME,EMAIL,ADDRESS," +
//                "PHONE,DESIGNATION,DEPARTMENT,INCOME FROM AUTH_TABLE auth, DETAIL_TABLE dept WHERE auth.ID = dept.ID";


        String query = "SELECT FIRSTNAME,LASTNAME,EMAIL,ADDRESS," +
                "PHONE,DESIGNATION,DEPARTMENT,INCOME FROM AUTH_TABLE auth INNER JOIN DETAIL_TABLE dept ON auth.ID = dept.ID";


        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            Employee employee = new Employee();
            employee.setId(cursor.getInt(0));
            employee.setFirstname(cursor.getString(1));
            employee.setLastname(cursor.getString(2));
            employee.setEmail(cursor.getString(3));
            employee.setAddress(cursor.getString(4));
            employee.setPhone(cursor.getString(5));
            employee.setDesignation(cursor.getString(6));
            employee.setDepartment(cursor.getString(7));
            employee.setIncome(cursor.getString(2));


            employees.add(employee);
        }
        return employees;
    }



//
//  public ArrayList<Employee> getEmployees() {
//		ArrayList<Employee> employees = new ArrayList<Employee>();
//		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//		queryBuilder
//				.setTables("DETAIL_TABLE"
//						+ " INNER JOIN "
//						+ "AUTH_TABLE"
//						+ " ON "
//						+ DataBaseHelper.EMPLOYEE_DEPARTMENT_ID
//						+ " = "
//						+ ( "AUTH_TABLE." + DataBaseHelper.ID_COLUMN));
//
//		// Get cursor
//		Cursor cursor = queryBuilder.query(database, new String[] {
//				EMPLOYEE_ID_WITH_PREFIX,
//				DataBaseHelper.EMPLOYEE_TABLE + "."
//						+ DataBaseHelper.NAME_COLUMN,
//				DataBaseHelper.EMPLOYEE_DOB,
//				DataBaseHelper.EMPLOYEE_SALARY,
//				DataBaseHelper.EMPLOYEE_DEPARTMENT_ID,
//				DataBaseHelper.DEPARTMENT_TABLE + "."
//						+ DataBaseHelper.NAME_COLUMN }, null, null, null, null,
//				null);
//
//		while (cursor.moveToNext()) {
//			Employee employee = new Employee();
//			employee.setId(cursor.getInt(0));
//			employee.setName(cursor.getString(1));
//			try {
//				employee.setDateOfBirth(formatter.parse(cursor.getString(2)));
//			} catch (ParseException e) {
//				//employee.setDateOfBirth(null);
//			}
//			employee.setSalary(cursor.getDouble(3));
//
//			Department department = new Department();
//			department.setId(cursor.getInt(4));
//			department.setName(cursor.getString(5));
//
//			employee.setDepartment(department);
//
//			employees.add(employee);
//		}
//		return employees;
//	}

}
