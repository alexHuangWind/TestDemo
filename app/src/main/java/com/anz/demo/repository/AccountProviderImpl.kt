package com.anz.demo.repository

import com.anz.account.repository.AccountRepository
import com.anz.core.repository.AccountProvider


internal class AccountProviderImpl(private val accountRepository: AccountRepository): AccountProvider {


    override fun getAccountBalance(): String {
        return accountRepository.getAccountBalance()
    }
    override fun getAccountName(): String {
        return accountRepository.getAccountName()
    }
}
