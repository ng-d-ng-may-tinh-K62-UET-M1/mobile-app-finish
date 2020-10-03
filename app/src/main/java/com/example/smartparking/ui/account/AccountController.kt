package com.example.smartparking.ui.account

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import com.example.smartparking.ui.account.models.AccountMenuModelGroup
import com.example.smartparking.ui.account.models.LoggedInItemModel_

class AccountController : TypedEpoxyController<List<AccountItem>>() {
    override fun buildModels(data: List<AccountItem>?) {
        data?.mapIndexed { _, item ->
            when (item) {
                is AccountItem.LoggedInUser -> {
                    LoggedInItemModel_()
                        .id(item.id)
                        .user(item.user)
                        .clickListener(View.OnClickListener {
                            callbacks?.onSignOutClicked()
                        })
                        .addTo(this)
                }
                is AccountItem.AccountMenuList -> {
                    AccountMenuModelGroup(item, object : AccountMenuModelGroup.Callbacks {
                        override fun onMenuItemClicked(
                            index: Int,
                            menuItem: AccountMenuModelGroup.AccountMenuItem
                        ) {
                            callbacks?.onMenuItemClicked(index, menuItem)
                        }
                    })
                        .id(item.id)
                        .addTo(this)
                }
            }
        }
    }

    var callbacks: AccountCallbacks? = null

    interface AccountCallbacks {
        fun onSignOutClicked()

        fun onMenuItemClicked(
            index: Int,
            menuItem: AccountMenuModelGroup.AccountMenuItem
        )
    }
}