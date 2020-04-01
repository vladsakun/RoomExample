package com.example.roomexample.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    //MAGIC
    public abstract UserDao mDao();

    public static synchronized Database getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context, Database.class, "users_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
