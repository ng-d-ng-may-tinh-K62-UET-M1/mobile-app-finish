package com.example.smartparking.ui.account

import com.example.smartparking.data.model.User
import com.example.smartparking.ui.account.models.AccountMenuModelGroup

sealed class AccountItem (val id: String) {
    data class LoggedInUser(var user: User) : AccountItem("account_logged_in_user")
    data class AccountMenuList(val items: List<AccountMenuModelGroup.AccountMenuItem>) : AccountItem("account_menu_list")
}