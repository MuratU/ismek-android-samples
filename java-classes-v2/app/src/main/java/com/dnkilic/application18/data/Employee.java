package com.dnkilic.application18.data;

/**
 * Created by dnkilic on 27/03/16.
 */
public class Employee {

    private int number;
    private String name;
    private String address;

    public Employee(String name, int number, String adress) {
        this.name = name;
        this.number = number;
        this.address = adress;
    }

    private int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private String getName() {
        return name;
    }

    private String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void mailCheck()
    {
        System.out.println("Mailing a check to " + name + " " + address);
    }
}
