package com.anz.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anz.account.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {

    /**
     * Get the current balance
     */
    val accountBalence: LiveData<AccountState>
        get() = _balance

    /**
     * Get the current Account Name
     */
    val accountName: LiveData<AccountState>
        get() = _name


    private val _balance by lazy {
        val balance = MutableLiveData<AccountState>()
        balance.postValue(AccountState.Loading)
        viewModelScope.launch {
            val result = accountRepository.getAccountBalance()
            if (result.isNotEmpty()) {
                balance.postValue(AccountState.Success(result))
            } else {
                balance.postValue(AccountState.GenericError)
            }
        }
        return@lazy balance
    }

    private val _name by lazy {
        val name = MutableLiveData<AccountState>()
        name.postValue(AccountState.Loading)
        viewModelScope.launch {
            val result = accountRepository.getAccountName()
            if (result.isNotEmpty()) {
                name.postValue(AccountState.Success("  $result$"))
            } else {
                name.postValue(AccountState.GenericError)
            }
        }
        return@lazy name

    }

}