package com.example.smartparking.ui.account.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.example.smartparking.R
import com.example.smartparking.shared.models.GridCarouselModel_
import com.example.smartparking.ui.account.AccountItem

class AccountMenuModelGroup(
    data: AccountItem.AccountMenuList,
    callbacks: Callbacks
) : EpoxyModelGroup(
    R.layout.section_account_menu_list,
    buildModels(data, callbacks)
) {
    interface Callbacks {
        fun onMenuItemClicked(
            index: Int,
            menuItem: AccountMenuItem
        )
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

    companion object {
        fun buildModels(
            data: AccountItem.AccountMenuList,
            callbacks: Callbacks
        ): List<EpoxyModel<*>> {
            val result = mutableListOf<EpoxyModel<*>>()

            val menuItemModels = data.items.mapIndexed { index, accountMenuItem ->
                AccountMenuItemModel_()
                    .id("account_menu_item_${index}")
                    .menuItem(accountMenuItem)
                    .clickListener { _ -> callbacks.onMenuItemClicked(index, accountMenuItem) }
            }

            result.add(
                GridCarouselModel_()
                    .id("account_menu_item")
                    .models(menuItemModels)
                    .spanCount(1)
                    .padding(Carousel.Padding.dp(0, 0, 0, 0, 0))
                    .orientation(GridLayoutManager.VERTICAL)
            )
            return result
        }
    }

    data class AccountMenuItem(
        @StringRes val name: Int,
        @DrawableRes val icon: Int,
        val destination: AccountMenuDestination
    )

    enum class AccountMenuDestination {
        EDIT_PROFILE,
        PAYMENT_METHODS,
        VEHICLES
    }
}