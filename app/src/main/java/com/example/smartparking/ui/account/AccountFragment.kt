package com.example.smartparking.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartparking.databinding.AccountFragmentBinding
import com.example.smartparking.splash.SplashActivity
import com.example.smartparking.ui.account.models.AccountMenuModelGroup
import com.example.smartparking.utils.extensions.findNavController
import com.example.smartparking.utils.livedata.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment(), AccountController.AccountCallbacks {
    @Inject
    lateinit var accountController: AccountController

    private val viewModel: AccountViewModel by viewModels()

    private lateinit var binding: AccountFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountController.callbacks = this
        binding = AccountFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            accountViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
            accountItems.setController(accountController)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.signOutEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) startActivity(Intent(activity, SplashActivity::class.java))
        })
        viewModel.accountItems.observe(viewLifecycleOwner, Observer {
            accountController.setData(it)
        })
    }

    override fun onSignOutClicked() {
        viewModel.signOut()
    }

    override fun onMenuItemClicked(index: Int, menuItem: AccountMenuModelGroup.AccountMenuItem) {
        when(menuItem.destination) {
            AccountMenuModelGroup.AccountMenuDestination.EDIT_PROFILE -> goToProfile()
        }
    }

    private fun goToProfile() {
        findNavController()?.navigate(
            AccountFragmentDirections.actionAccountFragmentToAccountProfileFragment(viewModel.account.value)
        )
    }
}