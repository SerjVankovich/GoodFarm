package com.example.sergeyvankovich.goodfarm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.sergeyvankovich.goodfarm.dao.SetDAO;
import com.example.sergeyvankovich.goodfarm.entities.SetEntity;

@Database(entities = {SetEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SetDAO setDAO();
}
