package com.example.smartparking.ui.myparking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.MyParkingFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyParkingFragment : Fragment() {
    private val viewModel: MyParkingViewModel by viewModels()
    private lateinit var binding: MyParkingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyParkingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}