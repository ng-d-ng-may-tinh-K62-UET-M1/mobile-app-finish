package com.example.smartparking.ui.myparking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartparking.databinding.MyParkingFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyParkingFragment : Fragment(), MyParkingView {
    private val viewModel: MyParkingViewModel by viewModels()
    private lateinit var binding: MyParkingFragmentBinding

    @Inject
    lateinit var myParkingController: MyParkingController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.myParkingView = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MyParkingFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            items.setController(myParkingController)
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenViewModel()
    }

    override fun onDestroyView() {
        binding.items.clear()
        super.onDestroyView()
    }

    private fun listenViewModel(){
        viewModel.parkingListsDataStore.observe(viewLifecycleOwner, Observer {
            myParkingController.setData(it)
        })
    }

}