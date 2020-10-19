package com.example.smartparking.ui.account.paymentmethod.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.smartparking.R
import com.example.smartparking.databinding.PaymentMethodDetailFragmentBinding
import com.example.smartparking.utils.extensions.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentMethodDetailFragment : Fragment(), PaymentMethodDetailView {

    private val viewModel: PaymentMethodDetailViewModel by viewModels()

    private lateinit var binding: PaymentMethodDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentMethodDetailFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setView(this)
    }

    override fun goBack() {
        findNavController()?.navigateUp()
    }

}