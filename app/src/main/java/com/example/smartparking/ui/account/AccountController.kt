package com.example.smartparking.ui.account

import com.airbnb.epoxy.TypedEpoxyController

class AccountController : TypedEpoxyController<List<AccountItem>() {
    override fun buildModels(data: List<AccountItem>?) {
        data?.mapIndexed {_, item ->
            when(item) {
                is AccountItem.LoggedInUser -> {}
                is AccountItem.AccountMenuList -> {

                }
            }
        }
    }

    var callbacks: AccountCallbacks? = null

    interface AccountCallbacks {
        fun onMenuItemClicked(
            index: Int,
            menuItem: AccountMenuItem
        )
    }
}