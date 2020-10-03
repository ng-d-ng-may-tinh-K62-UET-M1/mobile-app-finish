package com.example.smartparking.utils.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter

object ImageBindingAdapters {
    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(
        imageView: ImageView,
        src: Drawable
    ) {
        imageView.setImageDrawable(src)
    }

    @JvmStatic
    @BindingAdapter("srcCompat")
    fun setSrcCompat(
        imageView: ImageView,
        srcRes: Int
    ) {
        AppCompatResources.getDrawable(imageView.context, srcRes)?.let { src ->
            setSrcCompat(imageView, src)
        }
    }
}