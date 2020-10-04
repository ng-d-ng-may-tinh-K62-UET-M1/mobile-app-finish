package com.example.smartparking.ui.account.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.smartparking.R
import com.example.smartparking.databinding.AccountProfileFragmentBinding
import com.example.smartparking.utils.extensions.findNavController
import com.example.smartparking.utils.extensions.hideKeyboard
import com.example.smartparking.utils.livedata.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
        findNavController()?.navigateUp()
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

}