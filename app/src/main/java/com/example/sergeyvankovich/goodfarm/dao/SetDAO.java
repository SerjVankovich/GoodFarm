package com.example.sergeyvankovich.goodfarm.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.sergeyvankovich.goodfarm.entities.SetEntity;

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface SetDAO {

    @Query("SELECT * FROM SETENTITY")
    Flowable<List<SetEntity>> getAll();

    @Query("SELECT * FROM setentity WHERE id = :id")
    SetEntity getById(long id);

    @Insert(onConflict = REPLACE)
    void insert(SetEntity entity);

    @Update
    void update(SetEntity entity);

    @Delete
    void delete(SetEntity entity);
}
