package com.example.testprojek.ui.schedule

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testprojek.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditScheduleFragment :BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = EditScheduleFragment()
    }

    private lateinit var viewModel: EditScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditScheduleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}