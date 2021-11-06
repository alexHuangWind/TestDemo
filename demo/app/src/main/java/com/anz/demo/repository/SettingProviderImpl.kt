package com.anz.demo.repository

import android.content.Context
import com.anz.core.repository.SettingProvider
import android.content.Intent
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingProviderImpl @Inject constructor(@ApplicationContext val context: Context) :
    SettingProvider {

    override fun showTermsAndConditions() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.anz.co.nz/institutional/markets/terms-conditions/"))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}
