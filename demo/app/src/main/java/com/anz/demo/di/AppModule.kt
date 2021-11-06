package com.anz.demo.di


import android.content.Context
import com.anz.account.repository.AccountRepository
import com.anz.core.repository.AccountProvider
import com.anz.core.repository.SettingProvider
import com.anz.demo.repository.AccountProviderImpl
import com.anz.demo.repository.SettingProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAccountProvider(accountRepository: AccountRepository): AccountProvider {
        return AccountProviderImpl(accountRepository)
    }

    @Provides
    @Singleton
    fun provideSettingProvider(@ApplicationContext context: Context): SettingProvider {
        return SettingProviderImpl(context)
    }

}
