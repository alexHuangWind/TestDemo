package com.anz.account.di


import com.anz.account.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AccountModule {
    @Provides
    fun provideAccountRepository(): AccountRepository = AccountRepository();

}