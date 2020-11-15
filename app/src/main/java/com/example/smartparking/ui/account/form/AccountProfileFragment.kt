package com.example.smartparking.ui.account.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smartparking.databinding.AccountProfileFragmentBinding
import com.example.smartparking.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountProfileFragment : Fragment(), AccountProfileView {
    private val viewModel: AccountProfileViewModel by viewModels()

    private lateinit var binding: AccountProfileFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            AccountProfileFragmentArgs.fromBundle(it).let {
                it.user?.let { u -> viewModel.setData(u) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AccountProfileFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewmodel = viewModel
        }
        listenFromView()
        return binding.root
    }

    private fun listenFromView() {
        viewModel.setView(this)
        binding.apply {
            edtName.setTextChangeListener { text ->
                viewModel.setProfileName(text)
            }
            edtPhone.setTextChangeListener { text ->
                viewModel.setProfilePhone(text)
            }
        }
    }

    override fun goBack() {
        findNavController().navigateUp()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

}