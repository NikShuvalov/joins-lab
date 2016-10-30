package shuvalov.nikita.joinslab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button highestPaidButton, macysEmpButton, bostonCompButton;
    RecyclerView mRecyclerView;
    List<String> displayedNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!EmployeeSQLOpenHelper.getInstance(this).dataAlreadyPopulated()){ //keeps tables from populating if they aren't empty.
            setUpDatabaseInfo();
        }

        startFunctioning();



    }


    public void setUpDatabaseInfo(){
        EmployeeSQLOpenHelper openHelper = EmployeeSQLOpenHelper.getInstance(this);


        openHelper.insertEmployeeRow(new Employee("123-04-5678", "John", "Smith",  1973, "NY"));
        openHelper.insertEmployeeRow(new Employee("123-04-5679", "David", "McWill", 1982, "Seattle"));
        openHelper.insertEmployeeRow(new Employee("123-04-5680", "Katerina", "Wise", 1973, "Boston"));
        openHelper.insertEmployeeRow(new Employee("123-04-5681", "Donald", "Lee", 1992, "London"));
        openHelper.insertEmployeeRow(new Employee("123-04-5682", "Gary", "Henwood", 1987, "Las Vegas"));
        openHelper.insertEmployeeRow(new Employee("123-04-5683", "Anthony", "Bright", 1963, "Seattle"));
        openHelper.insertEmployeeRow(new Employee("123-04-5684", "William", "Newey", 1995, "Boston"));
        openHelper.insertEmployeeRow(new Employee("123-04-5685", "Melony", "Smith", 1970, "Chicago"));

        openHelper.insertJobRow(new Job("123-04-5678","Fuzz", 60, 1));
        openHelper.insertJobRow(new Job("123-04-5679","GA", 70, 2));
        openHelper.insertJobRow(new Job("123-04-5680","Little Place", 120, 5));
        openHelper.insertJobRow(new Job("123-04-5681","Macys", 78, 3));
        openHelper.insertJobRow(new Job("123-04-5682","New Life", 65, 1));
        openHelper.insertJobRow(new Job("123-04-5683","Believe", 158, 6));
        openHelper.insertJobRow(new Job("123-04-5684","Macys", 200, 8));
        openHelper.insertJobRow(new Job("123-04-5685","Stop", 299, 12));
    }



    public void startFunctioning(){
        highestPaidButton = (Button)findViewById(R.id.highest_paid_butt);
        macysEmpButton = (Button)findViewById(R.id.macys_emp_butt);
        bostonCompButton = (Button)findViewById(R.id.boston_comp_butt);

        displayedNames = new ArrayList<>();

        mRecyclerView = (RecyclerView)findViewById(R.id.results_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        final ResultRecyclerAdapter recyclerAdapter = new ResultRecyclerAdapter(displayedNames);


        highestPaidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayedNames = EmployeeSQLOpenHelper.getInstance(MainActivity.this).getHighestPaidEmployee();
                recyclerAdapter.replaceData(displayedNames);
            }
        });

        macysEmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayedNames = EmployeeSQLOpenHelper.getInstance(MainActivity.this).getMacyEmployees();
                recyclerAdapter.replaceData(displayedNames);
            }
        });

        bostonCompButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayedNames = EmployeeSQLOpenHelper.getInstance(MainActivity.this).getBostonComps();
                recyclerAdapter.replaceData(displayedNames);
            }
        });
        mRecyclerView.setAdapter(recyclerAdapter);
    }
}

/*The main purpose of the app is to provide information, which is present in two tables:

        show the full name of employees working at Macys
        show the companies located in Boston
        show the full name of the employee with the highest salary
        Your app doesn't need to look like the screenshot, but it needs to have the same functionality.
        */