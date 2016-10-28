package shuvalov.nikita.joinslab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDatabaseInfo();
    }

    public void setUpDatabaseInfo(){
        EmployeeSQLOpenHelper openHelper = EmployeeSQLOpenHelper.getInstance(this);

//        openHelper.insertEmployeeRow(new Employee("123-04-5678", "John", "Smith",  1973, "NY"));
//        openHelper.insertEmployeeRow(new Employee("123-04-5679", "David", "McWill", 1982, "Seattle"));
//
//        openHelper.insertEmployeeRow(new Employee("123-04-5680", "Katerina", "Wise", 1973, "Boston"));
//        Employee don = new Employee()
//                David McWill 1982 Seattle
//                Katerine Wise 1973 Boston
//                Donald Lee 1992 London
//                Gary Henwood 1987 Las Vegas
//                Anthony Bright 1963 Seattle
//                William Newey 1995 Boston
//                Melony Smith 1970 Chicago
//
//
//                123-04-5678 Fuzz 60 1
//                GA 70 2
//                Little Place 120 5
//                Macy's 78 3
//                New Life 65 1
//                Blieve 158 6
//                Macy's 200 8
//                Stop 299 12
    }
}

/*The main purpose of the app is to provide information, which is present in two tables:

        show the full name of employees working at Macys
        show the companies located in Boston
        show the full name of the employee with the highest salary
        Your app doesn't need to look like the screenshot, but it needs to have the same functionality.
        */