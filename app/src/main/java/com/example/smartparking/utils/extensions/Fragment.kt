package com.example.smartparking.utils.extensions

import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.smartparking.R
import java.time.Duration

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


fun Fragment.showDialog(
    title: Int,
    message: String,
    positiveButtonTitle: Int,
    positiveButtonAction: (DialogInterface, Int) -> Unit,
    negativeButtonTitle: Int,
    negativeButtonAction: (DialogInterface, Int) -> Unit,
    cancelable: Boolean = true
) : AlertDialog? {
    return if (context != null) {
        AlertDialog.Builder(context!!)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonTitle, positiveButtonAction)
            .setNegativeButton(negativeButtonTitle, negativeButtonAction)
            .setCancelable(cancelable)
            .show()
    } else null
}