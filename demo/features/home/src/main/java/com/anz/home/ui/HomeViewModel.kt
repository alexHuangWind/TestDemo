package com.anz.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anz.core.repository.AccountProvider
import com.anz.core.repository.SettingProvider
import com.anz.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountProvider: AccountProvider,
    private val settingProvider: SettingProvider
) : ViewModel() {

    fun showTermsAndConditions() {
        settingProvider.showTermsAndConditions()
    }

    /**
     * Get the Account Name
     */
    val accountName: LiveData<HomeState>
        get() = _name

    private val _name by lazy {
        val name = MutableLiveData<HomeState>()
        name.postValue(HomeState.Loading)
        viewModelScope.launch {
            val result = accountProvider.getAccountName()
            if (result.isNotEmpty()) {
                name.postValue(HomeState.Success("  $result$"))
            } else {
                name.postValue(HomeState.GenericError)
            }
        }
        return@lazy name

    }


}