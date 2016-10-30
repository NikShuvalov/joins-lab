package shuvalov.nikita.joinslab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NikitaShuvalov on 10/28/16.
 */

public class EmployeeSQLOpenHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Staffing_resources.db";
    public static final int DATABASE_VERSION = 1;

    public static final String JOB_TABLE_NAME = "job_table";
    public static final String EMPLOYEE_TABLE_NAME = "employee_table";

    public static final String COL_JOB_EXP= "experience";
    public static final String COL_JOB_SSN = "social_security_number";
    public static final String COL_JOB_COMPANY = "company";
    public static final String COL_JOB_SALARY = "salary";

    public static final String COL_EMP_SSN = "social_security_number";
    public static final String COL_EMP_FORENAME = "first_name";
    public static final String COL_EMP_SURNAME = "last_name";
    public static final String COL_EMP_YOB = "year_of_birth";
    public static final String COL_EMP_CITY = "city";


    private static final String CREATE_JOB_TABLE = String.format("CREATE TABLE %s" +
            "(%s VARCHAR PRIMARY KEY, %s VARCHAR, %s INTEGER, %s INTEGER)",
            JOB_TABLE_NAME, COL_JOB_SSN, COL_JOB_COMPANY, COL_JOB_SALARY, COL_JOB_EXP);

    public static final String CREATE_EMPLOYEE_TABLE = String.format("CREATE TABLE %s" +
            "(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s INTEGER, %s TEXT)",
            EMPLOYEE_TABLE_NAME, COL_EMP_SSN, COL_EMP_FORENAME, COL_EMP_SURNAME, COL_EMP_YOB, COL_EMP_CITY);

    private static EmployeeSQLOpenHelper sInstance;

    public static EmployeeSQLOpenHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new EmployeeSQLOpenHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    private EmployeeSQLOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {sqLiteDatabase.execSQL(CREATE_JOB_TABLE); sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE);}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+EMPLOYEE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+JOB_TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public void insertJobRow(Job job){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_JOB_SSN, job.getSocial());
        values.put(COL_JOB_COMPANY, job.getCompany());
        values.put(COL_JOB_SALARY, job.getSalary());
        values.put(COL_JOB_EXP, job.getExperience());
        db.insertOrThrow(JOB_TABLE_NAME, null, values);
        db.close();
    }

    public void insertEmployeeRow(Employee employee){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_EMP_SSN, employee.getSocial());
        values.put(COL_EMP_FORENAME, employee.getFirst());
        values.put(COL_EMP_SURNAME, employee.getLast());
        values.put(COL_EMP_YOB, employee.getYearOfBirth());
        values.put(COL_EMP_CITY, employee.getCity());
        db.insertOrThrow(EMPLOYEE_TABLE_NAME, null, values);
        db.close();
    }

    public List<String> getHighestPaidEmployee(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT %s, %s, %s FROM %s INNER JOIN %s ON %s.%s = %s.%s ORDER BY %s DESC",
                COL_EMP_FORENAME, COL_EMP_SURNAME, COL_JOB_SALARY, EMPLOYEE_TABLE_NAME, JOB_TABLE_NAME, EMPLOYEE_TABLE_NAME,COL_EMP_SSN, JOB_TABLE_NAME, COL_JOB_SSN, COL_JOB_SALARY);
        Cursor cursor = db.rawQuery(query, null);

        List<String> highestPaidEmployees= new ArrayList<>();

        if(cursor.moveToFirst()){
            int highestSalary = 0;
            while(!cursor.isAfterLast()){
                if(highestPaidEmployees.isEmpty()){//Should be ordered so that highest paid is on top of query results, so if nothing if in table we can assume highest paid is first in.
                    highestPaidEmployees.add(cursor.getString(cursor.getColumnIndex(COL_EMP_FORENAME)) +" "+ cursor.getString(cursor.getColumnIndex(COL_EMP_SURNAME)));
                    highestSalary = cursor.getInt(cursor.getColumnIndex(COL_JOB_SALARY));
                    cursor.moveToNext();
                }else if (cursor.getInt(cursor.getColumnIndex(COL_JOB_SALARY)) == highestSalary){//In case there is a tie for highest salary, add the people with a tie.
                    highestPaidEmployees.add(cursor.getString(cursor.getColumnIndex(COL_EMP_FORENAME)) +" "+ cursor.getString(cursor.getColumnIndex(COL_EMP_SURNAME)));
                    cursor.moveToNext();
                } else { //Break out of loop if we're done.
                    break;
                }
            }

        }
        cursor.close();
        return highestPaidEmployees;
    }

    public List<String> getMacyEmployees(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT %s, %s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s = 'Macys'",
                COL_EMP_FORENAME, COL_EMP_SURNAME, JOB_TABLE_NAME, EMPLOYEE_TABLE_NAME, EMPLOYEE_TABLE_NAME, COL_EMP_SSN, JOB_TABLE_NAME, COL_JOB_SSN, COL_JOB_COMPANY);

        Cursor cursor = db.rawQuery(query, null);
        List<String> macysEmployees = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                macysEmployees.add(cursor.getString(cursor.getColumnIndex(COL_EMP_FORENAME))+ " "+ cursor.getString(cursor.getColumnIndex(COL_EMP_SURNAME)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return macysEmployees;
    }

    public List<String> getBostonComps(){
        SQLiteDatabase db =getReadableDatabase();
        String query = String.format("SELECT %s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s = 'Boston'",
                COL_JOB_COMPANY, JOB_TABLE_NAME, EMPLOYEE_TABLE_NAME, EMPLOYEE_TABLE_NAME, COL_EMP_SSN, JOB_TABLE_NAME, COL_JOB_SSN, COL_EMP_CITY);
        Cursor cursor = db.rawQuery(query, null);
        List<String > bostonComps = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                bostonComps.add(cursor.getString(cursor.getColumnIndex(COL_JOB_COMPANY)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return bostonComps;
    }

    public boolean dataAlreadyPopulated(){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("Select %s FROM %s", "*",JOB_TABLE_NAME);
        Cursor c = db.rawQuery(query, null);
        boolean alreadyPop = c.moveToFirst();
        c.close();
        return alreadyPop;
    }


}
