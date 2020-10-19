package com.example.smartparking.ui.account.paymentmethod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartparking.data.model.PaymentMethod
import com.example.smartparking.databinding.PaymentMethodFragmentBinding
import com.example.smartparking.utils.extensions.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentMethodFragment : Fragment(),
    PaymentMethodView, PaymentMethodController.PaymentMethodListCallback {
    private val viewModel: PaymentMethodViewModel by viewModels()

    private lateinit var binding: PaymentMethodFragmentBinding

    @Inject
    lateinit var paymentMethodController: PaymentMethodController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentMethodFragmentBinding.inflate(layoutInflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            paymentMethodItems.setController(paymentMethodController)
        }
        listenViewModel()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setView(this)
    }

    private fun listenViewModel() {
        viewModel.paymentMethods.observe(viewLifecycleOwner, Observer {
            paymentMethodController.setData(it)
        })
    }

    override fun goBack() {
        findNavController()?.navigateUp()
    }

    override fun goToPaymentMethodForm() {
        findNavController()?.navigate(
            PaymentMethodFragmentDirections.actionPaymentMethodFragmentToPaymentMethodFormFragment()
        )
    }

    override fun onClickPaymentMethod(index: Int, paymentMethod: PaymentMethod) {
        TODO("Not yet implemented")
    }

}