package com.example.smartparking.utils.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.smartparking.R

internal fun Fragment.findNavController() : NavController? {
    return if (activity != null) {
        Navigation.findNavController(activity!!, R.id.fragment_navigation_host)
    } else null
}

fun Fragment.hideKeyboard() {
    activity?.let {
        val imm = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(it.window.currentFocus?.windowToken, 0)
    }
}