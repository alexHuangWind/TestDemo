package com.anz.core.repository

interface AccountProvider {
    fun getAccountBalance(): String
    fun getAccountName(): String
}
