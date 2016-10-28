package shuvalov.nikita.joinslab;

/**
 * Created by NikitaShuvalov on 10/28/16.
 */

public class Job {
    private String mSocial, mCompany;
    private int mSalary, mExperience;

    public Job(String ssn, String company, int salary, int experience) {
        mSocial = ssn;
        mCompany = company;
        mSalary = salary;
        mExperience = experience;
    }

    public String getSocial() {
        return mSocial;
    }

    public String getCompany() {
        return mCompany;
    }

    public int getSalary() {
        return mSalary;
    }

    public String getSalaryAsString(){
        return Integer.toString(mSalary);
    }

    public int getExperience() {
        return mExperience;
    }

    public String getExperienceAsString(){
        return Integer.toString(mExperience);
    }
}
