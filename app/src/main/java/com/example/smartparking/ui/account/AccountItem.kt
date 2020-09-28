package com.example.smartparking.ui.account

import com.example.smartparking.data.model.User

sealed class AccountItem (val id: String) {
    data class LoggedInUser(val user: User) : AccountItem("account_logged_in_user")
    data class AccountMenuList(val items: List<AccountMenuItem>) : AccountItem("account_menu_list")
}