package com.example.smartparking.ui.account

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.smartparking.R
import com.example.smartparking.data.model.User
import com.example.smartparking.data.preference.PreferenceDataSource
import com.example.smartparking.repositories.users.UserRepository
import com.example.smartparking.ui.account.models.AccountMenuModelGroup
import com.example.smartparking.utils.auth.AuthenticationManager
import com.example.smartparking.utils.livedata.Event

class AccountViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val preferenceDataSource: PreferenceDataSource
) : ViewModel() {

    private var _userUID: MutableLiveData<String?> =
        MutableLiveData(authenticationManager.getCurrentUserId())
    private val accountItemsInternal = mutableListOf<AccountItem>()
    val accountItems = MutableLiveData<List<AccountItem>>()

    val account = Transformations.switchMap(_userUID) {
        it?.let {
            userRepository.getUser(it).asLiveData().switchMap {
                it.getLiveDataIfSuccess()
            }
        }
    }

    private var accountObserver: Observer<User?>? = null

    private val _signOutEvent = MutableLiveData<Event<Boolean>>()
    val signOutEvent: LiveData<Event<Boolean>>
        get() = _signOutEvent


    init {
        initAccountItems()
        accountObserver = Observer { userObserver ->
            userObserver?.let { user ->
                accountItemsInternal.filterIsInstance<AccountItem.LoggedInUser>().map {
                    it.user = user
                }
            }
            accountItems.postValue(accountItemsInternal)
        }
        account.observeForever(accountObserver!!)
    }

    private fun initAccountItems() {
        accountItemsInternal.add(AccountItem.LoggedInUser(user = User(null, null, null)))
        accountItemsInternal.add(
            AccountItem.AccountMenuList(
                listOf(
                    AccountMenuModelGroup.AccountMenuItem(
                        name = R.string.account_menu_item_profile,
                        icon = R.drawable.navigation_menu_icon_account,
                        destination = AccountMenuModelGroup.AccountMenuDestination.EDIT_PROFILE
                    ),
                    AccountMenuModelGroup.AccountMenuItem(
                        name = R.string.account_menu_item_payment_method,
                        icon = R.drawable.navigation_menu_icon_payment,
                        destination = AccountMenuModelGroup.AccountMenuDestination.PAYMENT_METHODS
                    ),
                    AccountMenuModelGroup.AccountMenuItem(
                        name = R.string.account_menu_item_vehicle,
                        icon = R.drawable.navigation_menu_icon_car,
                        destination = AccountMenuModelGroup.AccountMenuDestination.VEHICLES
                    )
                )
            )
        )
        accountItems.postValue(accountItemsInternal)
    }

    override fun onCleared() {
        super.onCleared()
        accountObserver?.let {
            account.removeObserver(it)
        }
    }


    fun signOut() {
        authenticationManager.signOut()
        preferenceDataSource.setIsUserOnboarded(false)
        _signOutEvent.postValue(Event(true))
    }


}