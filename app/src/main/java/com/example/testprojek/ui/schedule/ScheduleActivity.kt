package com.example.testprojek.ui.schedule

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojek.R
import com.example.testprojek.adapter.ScheduleAdapter
import com.example.testprojek.databinding.ActivityScheduleBinding
import com.example.testprojek.util.Day

class ScheduleActivity : AppCompatActivity() {

    companion object {
        const val KEY_OF_SELECTED_DAY = "KEY_OF_SELECTED_DAY"
    }

    private val viewBinding by viewBinding<ActivityScheduleBinding>()
    private val viewModel by viewModels<ScheduleViewModel>()
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        scheduleAdapter = ScheduleAdapter(applicationContext, this)

        val day = intent.getSerializableExtra(KEY_OF_SELECTED_DAY) as Day
        val newScheduleFragment = NewScheduleFragment.getInstance(day)

        viewBinding.rvSchedule.apply {
            adapter = scheduleAdapter
            layoutManager = LinearLayoutManager(this@ScheduleActivity, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getAllSchedule(day).observe(this, {
            if (it.isNullOrEmpty()) {
                viewBinding.tvError.visibility = View.VISIBLE
                viewBinding.rvSchedule.visibility = View.INVISIBLE
            } else {
                viewBinding.tvError.visibility = View.INVISIBLE
                viewBinding.rvSchedule.visibility = View.VISIBLE
                scheduleAdapter.setAllData(it)
            }
        })

        viewBinding.btnAdd.setOnClickListener {
            newScheduleFragment.show(supportFragmentManager, newScheduleFragment::class.java.simpleName)
        }

    }
}