package com.anz.setting.ui

import androidx.lifecycle.ViewModel
import com.anz.core.repository.SettingProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingProvider: SettingProvider
) : ViewModel() {
    fun showTermsAndConditions() {
        settingProvider.showTermsAndConditions()
    }
}