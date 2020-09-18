package com.example.smartparking.splash

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.smartparking.R
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.ui.MainActivity
import com.example.smartparking.ui.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var preferenceDataSource: PreferenceDataSource

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)
        Log.d("SplashActivity", "start")
        if (preferenceDataSource.getIsUserOnboarded(this)) {
            MainActivity.start(this)
        } else {
            OnboardingActivity.start(this)
        }
        finish()
    }
}