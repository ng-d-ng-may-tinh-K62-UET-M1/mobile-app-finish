package com.example.smartparking.ui.account.paymentmethod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.PaymentMethodFragmentBinding
import com.example.smartparking.utils.extensions.findNavController

class PaymentMethodFragment : Fragment(),
    PaymentMethodView {
    private val viewModel: PaymentMethodViewModel by viewModels()

    private lateinit var binding: PaymentMethodFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentMethodFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
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

    override fun goToPaymentMethodForm() {
        findNavController()?.navigate(
            PaymentMethodFragmentDirections.actionPaymentMethodFragmentToPaymentMethodFormFragment()
        )
    }

}