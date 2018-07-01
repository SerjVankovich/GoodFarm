package com.example.sergeyvankovich.goodfarm.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

public class DBSingleton {
    private static DBSingleton instance;

    private static AppDatabase appDatabase;

    private DBSingleton () {}


    public static DBSingleton getInstance(Context context) {
        if (instance == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "database")
                    .build();


            return new DBSingleton();
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return  appDatabase;
    }
}
