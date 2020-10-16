package com.example.smartparking.ui.account.paymentmethod.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartparking.databinding.PaymentMethodFormFragmentBinding
import com.example.smartparking.utils.extensions.findNavController

class PaymentMethodFormFragment : Fragment(), PaymentMethodFormView {

    private val viewModel: PaymentMethodFormViewModel by viewModels()

    private lateinit var binding: PaymentMethodFormFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentMethodFormFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setView(this)
        binding.edtCardNumber.setTextChangeListener { text ->
            viewModel.setCartNumberPaymentMethod(text)
        }
        binding.edtExpDate.setTextChangeListener { text ->
            viewModel.setExpDatePaymentMethod(text)
        }
        binding.edtCvv.setTextChangeListener { text ->
            viewModel.setCVVPaymentMethod(text)
        }
    }

    override fun goBack() {
        findNavController()?.navigateUp()
    }

}