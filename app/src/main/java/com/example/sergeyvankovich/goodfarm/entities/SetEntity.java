package com.example.sergeyvankovich.goodfarm.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class SetEntity {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String description;
    public double price;
    public String imgUrl;

    public SetEntity(String name, String description, double price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }
}
