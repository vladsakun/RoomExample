package com.example.roomexample.data;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users_table")
    LiveData<List<User>> getAllUsers();

    @Query("DELETE FROM users_table")
    void deleteAll();

    @Query("UPDATE users_table SET name=:name, phoneNumber=:phoneNumber, email = :email  WHERE id = :id ")
    void updateSpecific(String name, String email, int phoneNumber, int id);
}
