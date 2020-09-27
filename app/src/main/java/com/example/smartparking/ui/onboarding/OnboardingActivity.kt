package com.example.smartparking.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.smartparking.R
import com.example.smartparking.data.model.User
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.databinding.ActivityOnboardingBinding
import com.example.smartparking.ui.MainActivity
import com.example.smartparking.utils.auth.AuthenticationManager
import com.example.smartparking.utils.livedata.EventObserver
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var preferenceDataSource: PreferenceDataSource

    @Inject
    lateinit var authenticationManager: AuthenticationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        binding.viewModel = viewModel
    }


    override fun onStart() {
        super.onStart()
        viewModel.performSignInEvent.observe(this, EventObserver {
            if (it) {
                binding.buttonContinueWithGoogle.visibility = View.GONE
                signIn()
            }
        })
    }

    private fun signIn() {
        startActivityForResult(authenticationManager.getGoogleSignInRequest(), RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "data: $data")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                throw e
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    preferenceDataSource.setIsUserOnboarded(true)
                    if (task.result?.additionalUserInfo?.isNewUser!!) {
                        auth.currentUser?.let { viewModel.createFirebaseUser(User.createFromFirebaseAuth(it)) }
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }

    companion object {
        const val RC_SIGN_IN = 42
        const val TAG = "OnboardingActivity"
    }

}