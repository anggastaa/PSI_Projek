package com.example.testprojek.ui.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojek.data.ScheduleDao
import com.example.testprojek.data.ScheduleDatabase
import com.example.testprojek.model.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: ScheduleDao

    init {
        val database = ScheduleDatabase.getInstance(application)
        dao = database.scheduleDao()
    }

    fun insertSchedule(schedule: Schedule) = CoroutineScope(Dispatchers.IO).launch {
        dao.insertSchedule(schedule)
    }
}