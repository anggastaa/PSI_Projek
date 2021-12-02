package com.example.testprojek.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testprojek.util.Day

@Entity(tableName = "schedule_table")
data class Schedule(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "day")
    val day: Day,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "range")
    val rangeTime: String
)
