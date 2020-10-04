package com.example.smartparking.utils.databinding

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.example.smartparking.ui.widget.EditTextLine

object EditTextLineBindingAdapters {
    @JvmStatic
    @BindingAdapter("label")
    fun setLabel(
        view: EditTextLine,
        label: String
    ) {
        view.setHint(label)
    }

    @JvmStatic
    @BindingAdapter("inputType")
    fun setInputType(
        view: EditTextLine,
        inputType: Int
    ) {
        view.setInputType(inputType)
    }

    @JvmStatic
    @BindingAdapter("imeOption")
    fun setImeOption(
        view: EditTextLine,
        imeOption: Int
    ) {
        view.setImeOption(imeOption)
    }

    @JvmStatic
    @BindingAdapter("text")
    fun setText(
        view: EditTextLine,
        text: CharSequence?
    ) {
        view.setTextAndCursor(text)
    }

    @JvmStatic
    @BindingAdapter("textColor")
    fun setTextColor(
        view: EditTextLine,
        @ColorInt textColor: Int
    ) {
        view.setTextColor(textColor)
    }

    @JvmStatic
    @BindingAdapter("onRightIconClicked")
    fun setOnRightIconClick(
        view: EditTextLine,
        listener: View.OnClickListener
    ) {
        view.setOnRightIconClicked {
            listener.onClick(view)
        }
    }

    @JvmStatic
    @BindingAdapter("rightIcon")
    fun setRightIcon(
        view: EditTextLine,
        icon: Drawable?
    ) {
        view.setRightIcon(icon)
    }

    @JvmStatic
    @BindingAdapter("onTextChanged")
    fun setOnTextChanged(
        view: EditTextLine,
        listener: (String) -> Unit
    ) {
        view.setTextChangeListener(listener)
    }

    @JvmStatic
    @BindingAdapter("disable")
    fun setDisable(
        view: EditTextLine,
        isDisable: Boolean
    ) {
        view.setDisable(isDisable)
    }
}