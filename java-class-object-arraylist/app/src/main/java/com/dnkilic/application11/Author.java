package com.dnkilic.application11;

public class Author {

    String mName;
    String mSurname;

    public Author(String name, String surname) {
        mName = name;
        mSurname = surname;
    }

    public Author(String name)
    {
        mName = name;
        mSurname = "";
    }

    public Author()
    {
        mSurname = "Unknown";
        mName = "Unknown";
    }

}
