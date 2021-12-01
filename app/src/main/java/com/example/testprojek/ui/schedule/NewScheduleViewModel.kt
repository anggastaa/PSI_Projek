package com.example.testprojek.ui.schedule

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.testprojek.data.ScheduleDao
import com.example.testprojek.data.ScheduleDatabase

class NewScheduleViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: ScheduleDao

    init {
        val database = ScheduleDatabase.getInstance(application)
        dao = database.scheduleDao()
    }
}