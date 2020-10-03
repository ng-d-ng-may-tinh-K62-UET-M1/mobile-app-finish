package com.example.smartparking.ui.account.models

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.databinding.BR
import com.example.smartparking.R

@EpoxyModelClass(layout = R.layout.item_account_menu_item)
abstract class AccountMenuItemModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    lateinit var menuItem: AccountMenuModelGroup.AccountMenuItem

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.let {
            it.setVariable(BR.menuItem, menuItem)
            it.setVariable(BR.clickListener, clickListener)
        }
    }
}