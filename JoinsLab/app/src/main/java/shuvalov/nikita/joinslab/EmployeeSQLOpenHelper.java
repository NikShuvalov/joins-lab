package shuvalov.nikita.joinslab;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NikitaShuvalov on 10/28/16.
 */

public class EmployeeSQLOpenHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Staffing_resources.db";
    public static final int DATABASE_VERSION = 1;

    public static final String JOB_TABLE_NAME = "job_table";
    public static final String EMPLOYEE_TABLE_NAME = "employee_table";

    public static final String COL_JOB_EXP= "experience";
    public static final String COL_JOB_SSN = "social security number";
    public static final String COL_JOB_COMPANY = "company";
    public static final String COL_JOB_SALARY = "salary";

    public static final String COL_EMP_SSN = "social security number";
    public static final String COL_EMP_FORENAME = "first name";
    public static final String COL_EMP_SURNAME = "last name";
    public static final String COL_EMP_YOB = "year of birth";
    public static final String COL_EMP_CITY = "city";

    private static final String CREATE_JOB_TABLE = String.format("CREATE TABLE %s" +
            "(%s VARCHAR PRIMARY KEY, %s VARCHAR, %s INTEGER, %s INTEGER)",
            JOB_TABLE_NAME, COL_JOB_SSN, COL_JOB_COMPANY, COL_JOB_SALARY, COL_JOB_EXP);

    public static final String CREATE_EMPLOYEE_TABLE = String.format("CREATE TABLE %s" +
            "(%s TEXT PRIMARY KEY, %s TEXT, %s, TEXT, %s INTEGER, %s TEXT)",
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
    public void onCreate(SQLiteDatabase sqLiteDatabase) { sqLiteDatabase.execSQL(CREATE_JOB_TABLE); sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE);}

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
        db.close();

    }
}
