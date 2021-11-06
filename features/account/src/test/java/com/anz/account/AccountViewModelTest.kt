package com.anz.account

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anz.account.repository.AccountRepository
import com.anz.core.repository.AccountProvider
import com.anz.core.repository.SettingProvider
import com.anz.home.HomeState
import com.anz.home.ui.HomeViewModel
import com.anz.testing.CoroutinesMainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
internal class AccountViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesMainDispatcherRule()


    @Test
    fun `empty name produces Error`() {
        // ARRANGE
        val accountRepository = mockk<AccountRepository> {
            coEvery { getAccountName() } coAnswers { "" }
        }
        val viewModel = AccountViewModel(accountRepository)
        val observer: Observer<AccountState> = mock()


        // ACT
        viewModel.run {
            accountName.observeForever(observer)
            // ASSERT
            assert(accountName.value is AccountState.GenericError)
        }
    }

    @Test
    fun `when get name success should callback as expected`() {
        // ARRANGE
        val expected = "alex"
        val accountRepository = mockk<AccountRepository>() {
            coEvery { getAccountName() } coAnswers { expected }
        }
        val observerSlot = slot<AccountState>()
        val mockObserver = mockk<Observer<AccountState>> {
            every { onChanged(capture(observerSlot)) } just Runs
        }

        // ACT
        val testObject = AccountViewModel(accountRepository)
        testObject.accountName.observeForever(mockObserver)

        // ASSERT
        val captured = observerSlot.captured
        Assert.assertTrue(observerSlot.isCaptured)
        check(captured is AccountState.Success)
        Assert.assertEquals(expected, captured.data)

    }

    @Test
    fun `empty balance produces Error`() {
        // ARRANGE
        val accountRepository = mockk<AccountRepository>() {
            coEvery { getAccountBalance() } coAnswers { "" }
        }
        val viewModel = AccountViewModel(accountRepository)
        val observer: Observer<AccountState> = mock()


        // ACT
        viewModel.run {
            accountBalence.observeForever(observer)
            // ASSERT
            assert(accountBalence.value is AccountState.GenericError)
        }
    }

    @Test
    fun `balance not empty should success and callback as expected`() {
        // ARRANGE
        val expected = "10000"
        val accountRepository = mockk<AccountRepository>() {
            coEvery { getAccountBalance() } coAnswers { expected }
        }
        val observerSlot = slot<AccountState>()
        val mockObserver = mockk<Observer<AccountState>> {
            every { onChanged(capture(observerSlot)) } just Runs
        }

        // ACT
        val testObject = AccountViewModel(accountRepository)
        testObject.accountBalence.observeForever(mockObserver)

        // ASSERT
        val captured = observerSlot.captured
        Assert.assertTrue(observerSlot.isCaptured)
        check(captured is AccountState.Success)
        Assert.assertEquals(expected, captured.data)

    }


}