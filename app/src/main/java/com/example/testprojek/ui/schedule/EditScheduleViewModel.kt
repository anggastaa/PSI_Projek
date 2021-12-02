package com.example.testprojek.ui.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojek.data.ScheduleDao
import com.example.testprojek.data.ScheduleDatabase
import com.example.testprojek.model.Schedule
import kotlinx.coroutines.launch

class EditScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: ScheduleDao

    init {
        val database = ScheduleDatabase.getInstance(application)
        dao = database.scheduleDao()
    }

    fun editSchedule(schedule: Schedule) = viewModelScope.launch {
        dao.updateSchedule(schedule)
    }
}