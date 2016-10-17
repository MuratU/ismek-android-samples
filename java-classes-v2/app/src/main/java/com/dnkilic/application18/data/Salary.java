package com.dnkilic.application18.data;

/**
 * Created by dnkilic on 27/03/16.
 */
public class Salary extends Employee {

    String mName;
    int mNumber;
    String mAddress;
    int salary;


    public Salary(String name, int number, String adress, int salary) {
        super(name, number, adress);
        this.mName = name;
        this.mNumber = number;
        this.mAddress = adress;
        this.salary = salary;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void mailCheck() {

        System.out.println("Within Salary mailcheck");
        System.out.println("MailCheck to  " + mName + "with Salary" + getSalary());
    }

    public double computePay()
    {
        System.out.println("Computing salary for  :" + mName);
        return salary/52;
    }
}
