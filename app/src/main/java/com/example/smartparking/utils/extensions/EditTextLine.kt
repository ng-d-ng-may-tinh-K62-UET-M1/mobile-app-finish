package com.example.smartparking.utils.extensions

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.InputFilter
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import com.example.smartparking.R

fun EditText.setMaxLength(maxLength: Int) {
    filters = arrayOf(InputFilter.LengthFilter(maxLength))
}

fun EditText.setOnRightIconClicked(onClickListener: View.OnClickListener) {
    val DRAWABLE_RIGHT = 2
    val extraOffsetPx = context.resources.getDimensionPixelOffset(R.dimen.margin_padding_12)

    setOnTouchListener { v, event ->
        val isClickOnRightIcon = compoundDrawables[DRAWABLE_RIGHT]?.let {
            event.x >= right - it.bounds.width() - extraOffsetPx
        } ?: false
        if ((event.action == MotionEvent.ACTION_UP) && isClickOnRightIcon) {
            onClickListener.onClick(v)
            true
        } else false
    }
}

fun EditText.setRightIcon(icon: Drawable) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            icon,
            null
        )
    } else {
        setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            icon,
            null
        )
    }
}