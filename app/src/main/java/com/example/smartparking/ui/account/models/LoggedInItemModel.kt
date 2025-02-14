package com.example.smartparking.ui.account.models

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.smartparking.BR
import com.example.smartparking.R
import com.example.smartparking.data.model.User

@EpoxyModelClass(layout = R.layout.item_account_logged_in)
abstract class LoggedInItemModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    lateinit var user: User

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.let {
            it.setVariable(BR.user, user)
            it.setVariable(BR.clickListener, clickListener)
        }
    }
}