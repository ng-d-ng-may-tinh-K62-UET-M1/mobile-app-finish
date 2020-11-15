package com.example.smartparking.utils.extensions

import android.content.Context
import android.content.DialogInterface
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

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
): AlertDialog? {
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