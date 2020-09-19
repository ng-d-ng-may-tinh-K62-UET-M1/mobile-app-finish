package com.example.smartparking.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.databinding.OnboardingFragmentBinding
import com.example.smartparking.ui.MainActivity
import com.example.smartparking.utils.AuthenticationManager
import com.example.smartparking.utils.livedata.EventObserver
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private val viewModel: OnboardingViewModel by viewModels()
    private lateinit var binding: OnboardingFragmentBinding

    @Inject
    lateinit var authenticationManager: AuthenticationManager

    @Inject
    lateinit var preferenceDataSource: PreferenceDataSource

    var auth = Firebase.auth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OnboardingFragmentBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle ${account.id} ${account}")
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                throw e
            }
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    preferenceDataSource.setIsUserOnboarded(true)
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun signIn() {
        val signInIntent = authenticationManager.getGoogleSignInRequest()
        startActivityForResult(signInIntent, REQUEST_CODE_SIGN_IN)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listenViewModel()
    }

    private fun listenViewModel() {
        viewModel.performSignInEvent.observe(viewLifecycleOwner, EventObserver {request ->
            if (request) {
                signIn()
            }
        })
    }

    companion object {
        const val REQUEST_CODE_SIGN_IN = 42
        const val TAG = "ONBOARDINGFRAGMENT"
    }

}