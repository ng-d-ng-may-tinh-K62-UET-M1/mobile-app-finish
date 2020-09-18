package com.example.smartparking.data.preference

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.smartparking.R

class PreferenceDataSource {
    fun getIsUserOnboarded(context: Context): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val key = getIsUserOnboardedKey(context)
        return preferences.getBoolean(key, false)
    }

    fun setIsUserOnboarded(context: Context, isUserOnBoarded: Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val key = getIsUserOnboardedKey(context)
        preferences.edit().putBoolean(key, isUserOnBoarded).apply()
    }

    fun getIsUserOnboardedKey(context: Context) = context.getString(R.string.key_is_user_onboarded)
}