package com.example.smartparking.utils.extensions

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View

fun View.setBackgroundDrawableAndColor(
    drawable: Drawable? = null,
    color: Int? = null
) {
    drawable?.let {
        background = drawable
    }

    color?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            background.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}