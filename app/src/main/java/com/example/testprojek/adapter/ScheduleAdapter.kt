package com.example.testprojek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojek.data.ScheduleDao
import com.example.testprojek.data.ScheduleDatabase
import com.example.testprojek.databinding.ItemListScheduleBinding
import com.example.testprojek.model.Schedule
import com.example.testprojek.ui.schedule.EditScheduleFragment
import com.example.testprojek.ui.schedule.ScheduleActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleAdapter(
    application: Context,
    private val activity: ScheduleActivity,
): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    private val listOfSchedule = arrayListOf<Schedule>()
    private val dao: ScheduleDao

    init {
        val database = ScheduleDatabase.getInstance(application)
        dao = database.scheduleDao()
    }

    fun setAllData(data: List<Schedule>) {
        listOfSchedule.clear()
        listOfSchedule.addAll(data)
        notifyDataSetChanged()
    }

    inner class ScheduleViewHolder(private val view: ItemListScheduleBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(schedule: Schedule) {
            view.apply {
                tvTitle.text = schedule.title
                tvTime.text = schedule.rangeTime
                val editScheduleFragment = EditScheduleFragment()

                ivDelete.setOnClickListener {
                    CoroutineScope(Dispatchers.Main).launch {
                        dao.deleteSchedule(schedule)
                    }
                }

                ivEdit.setOnClickListener {
                    editScheduleFragment.show(activity.supportFragmentManager, editScheduleFragment::class.java.simpleName)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = ItemListScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(listOfSchedule[position])
    }

    override fun getItemCount(): Int = listOfSchedule.size
}