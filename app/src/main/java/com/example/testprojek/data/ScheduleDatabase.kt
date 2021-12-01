package com.example.testprojek.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testprojek.model.Schedule

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase: RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: ScheduleDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): ScheduleDatabase {
            if (INSTANCE == null) {
                synchronized(ScheduleDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ScheduleDatabase::class.java,
                        "Schedule.db"
                    ).build()
                }
            }
            return INSTANCE as ScheduleDatabase
        }
    }
}