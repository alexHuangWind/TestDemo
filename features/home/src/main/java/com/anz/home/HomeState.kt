package com.anz.home

/**
 * Different states that can occur from get account or get name.
 */
sealed class HomeState {

    /**
     * Async request is currently loading.
     */
    object Loading : HomeState()

    /**
     * get name was successful, and responded with [name].
     */
    data class Success(val name: String) : HomeState()

    /**
     * An error has occurred with the search request.
     */
    object GenericError : HomeState()
}
