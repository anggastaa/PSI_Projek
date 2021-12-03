package com.example.testprojek.ui.schedule

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.example.testprojek.databinding.EditScheduleFragmentBinding
import com.example.testprojek.model.Schedule
import com.example.testprojek.util.Day
import com.example.testprojek.util.Format
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class EditScheduleFragment :BottomSheetDialogFragment() {

    private var _binding: EditScheduleFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<EditScheduleViewModel>()
    private var selectedScheduleId: String = ""

    private var startHour = 0
    private var startMinute = 0
    private var endHour = 0
    private var endMinute = 0
    private var rangeTime = "00.00 - 00.00"

    companion object {
        private const val KEY_BUNDLE = "ID"

        fun getInstance(scheduleId: String): BottomSheetDialogFragment {
            return EditScheduleFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE, scheduleId)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedScheduleId = it.getString(KEY_BUNDLE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EditScheduleFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)

        viewModel.getSelectedSchedule(selectedScheduleId).observe(viewLifecycleOwner, { schedule ->

            startHour = schedule.rangeTime.substring(0, 2).toInt()
            startMinute = schedule.rangeTime.substring(3, 5).toInt()
            endHour = schedule.rangeTime.substring(8, 10).toInt()
            endMinute = schedule.rangeTime.substring(11, 13).toInt()

            binding?.apply {
                edtName.setText(schedule.title, TextView.BufferType.EDITABLE)
                tvRangeTime.text = schedule.rangeTime

                btnSetStartTime.setOnClickListener {
                    val startTimeSetTime = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        startHour = hour
                        startMinute = minute

                        val startTime = Format.timeFormat(startHour, startMinute)
                        val endTime = Format.timeFormat(endHour, endMinute)
                        rangeTime = String.format("$startTime - $endTime")
                        binding?.tvRangeTime?.text = rangeTime
                    }
                    val timePickerDialog = TimePickerDialog(
                        activity,
                        NewScheduleFragment.CLOCK_MODE,
                        startTimeSetTime,
                        hour,
                        minute,
                        DateFormat.is24HourFormat(activity)
                    )
                    timePickerDialog.show()
                }

                btnSetEndTime.setOnClickListener {
                    val endTimeSetTime = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                        endHour = hour
                        endMinute = minute

                        val startTime = Format.timeFormat(startHour, startMinute)
                        val endTime = Format.timeFormat(endHour, endMinute)
                        rangeTime = String.format("$startTime - $endTime")
                        binding?.tvRangeTime?.text = rangeTime
                    }
                    val timePickerDialog = TimePickerDialog(
                        activity,
                        NewScheduleFragment.CLOCK_MODE,
                        endTimeSetTime,
                        hour,
                        minute,
                        DateFormat.is24HourFormat(activity)
                    )
                    timePickerDialog.show()
                }

                btnUpdate.setOnClickListener {
                    val rangeTime = rangeTime

                    if (!TextUtils.isEmpty(edtName.text)) {
                        val title = edtName.text

                        viewModel.editSchedule(
                            Schedule(
                                schedule?.id.toString(),
                                schedule?.day!!,
                                title.toString(),
                                rangeTime
                            )
                        )

                        dismiss()
                    } else {
                        Toast.makeText(activity, "Nama jadwal tidak boleh kosong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun onDismiss(dialog: DialogInterface) {
        binding?.edtName?.text?.clear()
        _binding = null
        startHour = 0
        startMinute = 0
        endHour = 0
        endMinute = 0
        rangeTime = "00.00 - 00.00"
        super.onDismiss(dialog)
    }
}