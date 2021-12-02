package com.example.testprojek.ui.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testprojek.data.ScheduleDao
import com.example.testprojek.data.ScheduleDatabase
import com.example.testprojek.model.Schedule
import com.example.testprojek.util.Day
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleViewModel(application: Application): AndroidViewModel(application) {

    private val dao: ScheduleDao

    init {
        val database = ScheduleDatabase.getInstance(application)
        dao = database.scheduleDao()
    }

    fun getAllSchedule(day: Day) = dao.getAllSchedule(day).asLiveData(Dispatchers.IO)
}