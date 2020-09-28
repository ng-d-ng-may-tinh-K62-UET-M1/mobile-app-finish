package com.example.smartparking.utils.databinding

import androidx.databinding.BindingAdapter
import com.example.smartparking.generated.callback.OnClickListener
import com.example.smartparking.utils.uiwidget.CommonHeader

object CommonHeaderBindingAdapters {
    @JvmStatic
    @BindingAdapter("onLeftButtonClick")
    fun setLeftButtonClick(
        header: CommonHeader,
        listener: OnClickListener
    ) {
        header.setLeftButtonClickListener(listener)
    }

    @JvmStatic
    @BindingAdapter("onRightButtonClick")
    fun setRightButtonClick(
        header: CommonHeader,
        listener: OnClickListener
    ) {
        header.setRightButtonClickListener(listener)
    }
}