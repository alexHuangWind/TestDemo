package com.anz.account

/**
 * Different states that can occur from get account or get name.
 */
sealed class AccountState {

    /**
     * Async request is currently loading.
     */
    object Loading : AccountState()

    /**
     * successful, and responded with [data].
     */
    data class Success(val data: String) : AccountState()

    /**
     * An error has occurred with the search request.
     */
    object GenericError : AccountState()
}
