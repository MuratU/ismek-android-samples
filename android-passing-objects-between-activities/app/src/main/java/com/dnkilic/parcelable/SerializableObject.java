package com.dnkilic.parcelable;

import java.io.Serializable;

/**
 * Created by dogan.kilic on 6.06.2016.
 */
public class SerializableObject implements Serializable {
    private String name;
    private String surname;
    private int id;

    public SerializableObject(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
