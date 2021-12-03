package com.example.testprojek.data

import androidx.room.*
import com.example.testprojek.model.Schedule
import com.example.testprojek.util.Day
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule_table WHERE day = :selectedDay")
    fun getAllSchedule(selectedDay: Day): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule_table WHERE id = :selectedId")
    fun getSelectedSchedule(selectedId: String): Flow<Schedule>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchedule(schedule: Schedule)

    @Delete
    suspend fun deleteSchedule(schedule: Schedule)

    @Update
    suspend fun updateSchedule(schedule: Schedule)
}