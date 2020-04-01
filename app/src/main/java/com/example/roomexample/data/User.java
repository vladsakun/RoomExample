package com.example.roomexample.data;

import androidx.room.PrimaryKey;

@androidx.room.Entity(tableName = "users_table")
public class User {

    //Поля в таблице

    //Id который создается автоматически
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String email;
    private int phoneNumber;

    //Конструктор без id
    public User(String name, String email, int phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
