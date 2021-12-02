package com.example.testprojek.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import com.example.testprojek.R
import com.example.testprojek.databinding.ActivityMainBinding
import com.example.testprojek.ui.schedule.ScheduleActivity
import com.example.testprojek.ui.schedule.ScheduleActivity.Companion.KEY_OF_SELECTED_DAY
import com.example.testprojek.util.Day

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding<ActivityMainBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBinding.apply {
            val intent = Intent(this@MainActivity, ScheduleActivity::class.java)
            btnMonday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.MONDAY)
                startActivity(intent)
            }
            btnTuesday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.TUESDAY)
                startActivity(intent)
            }
            btnWednesday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.WEDNESDAY)
                startActivity(intent)
            }
            btnThursday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.THURSDAY)
                startActivity(intent)
            }
            btnFriday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.FRIDAY)
                startActivity(intent)
            }
            btnSaturday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.SATURDAY)
                startActivity(intent)
            }
            btnSunday.setOnClickListener {
                intent.putExtra(KEY_OF_SELECTED_DAY, Day.SUNDAY)
                startActivity(intent)
            }
        }
    }
}