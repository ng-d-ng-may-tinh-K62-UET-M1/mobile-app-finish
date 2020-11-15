package com.example.smartparking.ui.booking.models

import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.R
import com.example.smartparking.BR

@EpoxyModelClass(layout = R.layout.section_title_model)
abstract class SectionTitleModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    @StringRes
    var titleRes: Int? = null

    @EpoxyAttribute
    var canViewMore: Boolean = false

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.apply {
            setVariable(BR.title, title)
            setVariable(BR.titleRes, titleRes)
            setVariable(BR.canViewMore, canViewMore)
            setVariable(BR.clickListener, clickListener)
        }
    }

    override fun unbind(holder: DataBindingHolder) {
        holder.dataBinding.setVariable(BR.clickListener, null)
        super.unbind(holder)
    }
}