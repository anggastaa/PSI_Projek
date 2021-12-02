package com.example.testprojek.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.bottomsheetdialogfragment.viewBinding
import androidx.fragment.app.viewModels
import com.example.testprojek.R
import com.example.testprojek.databinding.NewScheduleFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewScheduleFragment : BottomSheetDialogFragment() {

    private val viewBinding by viewBinding<NewScheduleFragmentBinding>()
    private val viewModel by viewModels<NewScheduleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}