package com.example.itemmanagement;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "quantity")
    public String quantity;

    public Item(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}