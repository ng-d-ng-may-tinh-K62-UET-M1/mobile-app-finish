package com.example.smartparking.ui.selecttime

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.smartparking.data.request.FindParkingDateTime
import com.example.smartparking.data.request.FindParkingRequest
import com.example.smartparking.data.response.FindParkingResponse
import com.example.smartparking.databinding.SelectTimeFragmentBinding
import com.example.smartparking.utils.extensions.clickEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min

@AndroidEntryPoint
class SelectTimeFragment : Fragment(), SelectTimeView {
    private val selectTimeViewModel: SelectTimeViewModel by viewModels()

    private lateinit var binding: SelectTimeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SelectTimeFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = selectTimeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectTimeViewModel.selectTimeView = this
        initDatePicker()
    }

    private fun initDatePicker() {
        binding.layoutArriveAfter
            .clickEvent()
            .onEach {
                selectDate(
                    selectTimeViewModel.startDate,
                    true
                )
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.layoutExitBefore
            .clickEvent()
            .onEach {
                selectDate(
                    selectTimeViewModel.endDate
                )
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun selectDate(
        calendar: Calendar?,
        isStartDateTime: Boolean = false
    ) {
        val currentCalendar: Calendar = calendar ?: Calendar.getInstance()
        val year = currentCalendar.get(Calendar.YEAR)
        val month = currentCalendar.get(Calendar.MONTH)
        val dayOfMonth = currentCalendar.get(Calendar.DAY_OF_MONTH)
        val hour = currentCalendar.get(Calendar.HOUR)
        val minutes = currentCalendar.get(Calendar.MINUTE)

        val expectedDate = Calendar.getInstance()

        DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, y, m, d ->
                TimePickerDialog(
                    requireActivity(),
                    TimePickerDialog.OnTimeSetListener { _, h, min ->
                        expectedDate.set(y, m, d, h, min)
                        if (isStartDateTime) selectTimeViewModel.updateStartDate(expectedDate) else selectTimeViewModel.updateEndDate(expectedDate)
                    }, hour, minutes, true
                ).show()
            },
            year, month, dayOfMonth
        ).show()
    }

    override fun goToLocationList(findParkingRequest: FindParkingRequest?, findParkingDateTime: FindParkingDateTime?) {
        findNavController().navigate(SelectTimeFragmentDirections.actionSelectTimeFragmentToLocationListFragment(findParkingRequest, findParkingDateTime))
    }
}