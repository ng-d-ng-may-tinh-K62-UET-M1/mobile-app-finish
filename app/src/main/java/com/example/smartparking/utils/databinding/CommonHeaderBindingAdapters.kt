package com.example.smartparking.utils.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.smartparking.generated.callback.OnClickListener
import com.example.smartparking.utils.uiwidget.CommonHeader

object CommonHeaderBindingAdapters {

    @JvmStatic
    @BindingAdapter("onLeftButtonClick")
    fun setOnLeftIconClick(
        headerView: CommonHeader,
        listener: View.OnClickListener
    ) {
        headerView.setLeftButtonClickListener(listener)
    }

    @JvmStatic
    @BindingAdapter("onRightButtonClick")
    fun setOnRightTitleClick(
        headerView: CommonHeader,
        listener: View.OnClickListener
    ) {
        headerView.setRightButtonClickListener(listener)
    }

}