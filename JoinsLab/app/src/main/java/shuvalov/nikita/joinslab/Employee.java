package shuvalov.nikita.joinslab;

/**
 * Created by NikitaShuvalov on 10/28/16.
 */

public class Employee {
    private String mSocial, mFirst, mLast, mCity;
    private int mYearOfBirth;

    public Employee(String social, String first, String last, int yearOfBirth, String city) {
        mSocial = social;
        mFirst = first;
        mLast = last;
        mCity = city;
        mYearOfBirth = yearOfBirth;
    }

    public String getSocial() {
        return mSocial;
    }

    public String getFirst() {
        return mFirst;
    }

    public String getLast() {
        return mLast;
    }

    public String getCity() {
        return mCity;
    }

    public int getYearOfBirth() {
        return mYearOfBirth;
    }

    public String getYearOfBirthAsString(){
        return Integer.toString(mYearOfBirth);
    }
}
