package com.example.testprojek.ui.schedule

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import com.example.testprojek.databinding.NewScheduleFragmentBinding
import com.example.testprojek.model.Schedule
import com.example.testprojek.util.Day
import com.example.testprojek.util.Format
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class NewScheduleFragment : BottomSheetDialogFragment() {

    private var _binding: NewScheduleFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModels<NewScheduleViewModel>()

    private var startHour = 0
    private var startMinute = 0
    private var endHour = 0
    private var endMinute = 0
    private var rangeTime = "00.00 - 00.00"
    private var day: Day? = null

    companion object {
        private const val KEY_BUNDLE = "DAY"

        fun getInstance(day: Day): BottomSheetDialogFragment {
            return NewScheduleFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_BUNDLE, day)
                }
            }
        }

        const val SPINNER_MODE = 1
        const val CLOCK_MODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            day = it.getSerializable(KEY_BUNDLE) as Day?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewScheduleFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)

        binding?.apply {
            btnSetStartTime.setOnClickListener {
                val startTimeSetTime = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    startHour = hour
                    startMinute = minute

                    val startTime = Format.timeFormat(startHour, startMinute)
                    val endTime = Format.timeFormat(endHour, endMinute)
                    rangeTime = String.format("$startTime - $endTime")
                    binding?.tvRangeTime?.text = rangeTime
                }
                val timePickerDialog = TimePickerDialog(
                    activity,
                    CLOCK_MODE,
                    startTimeSetTime,
                    hour,
                    minute,
                    DateFormat.is24HourFormat(activity)
                )
                timePickerDialog.show()
            }

            btnSetEndTime.setOnClickListener {
                val endTimeSetTime = TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    endHour = hour
                    endMinute = minute

                    val startTime = Format.timeFormat(startHour, startMinute)
                    val endTime = Format.timeFormat(endHour, endMinute)
                    rangeTime = String.format("$startTime - $endTime")
                    binding?.tvRangeTime?.text = rangeTime
                }
                val timePickerDialog = TimePickerDialog(
                    activity,
                    CLOCK_MODE,
                    endTimeSetTime,
                    hour,
                    minute,
                    DateFormat.is24HourFormat(activity)
                )
                timePickerDialog.show()
            }

            btnAdd.setOnClickListener {
                val rangeTime = rangeTime

                if (!TextUtils.isEmpty(edtName.text)) {
                    val title = edtName.text

                    viewModel.insertSchedule(
                        Schedule(
                            NanoIdUtils.randomNanoId(),
                            day!!,
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