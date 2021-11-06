package com.anz.home.ui


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.anz.core.repository.AccountProvider
import com.anz.core.repository.SettingProvider
import com.anz.home.HomeState
import com.anz.testing.CoroutinesMainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesMainDispatcherRule()


    @Test
    fun `empty name produces Error`() {
        // ARRANGE
        val mockAccountProvider = mockk<AccountProvider>(){
            coEvery { getAccountName() } coAnswers { ""}
        }
        val mockSettingProvider = mockk<SettingProvider>()
        val viewModel = HomeViewModel(
            mockAccountProvider,
            mockSettingProvider
        )
        val observer: Observer<HomeState> = mock()


        // ACT
        viewModel.run {
            accountName.observeForever(observer)
            // ASSERT
            assert(accountName.value is HomeState.GenericError)
        }
    }


    fun `name not empty should success and callback as expected`() {
        // ARRANGE
        val expected = "alex"
        val mockAccountProvider = mockk<AccountProvider>()
        val mockSettingProvider = mockk<SettingProvider>()
        val observerSlot = slot<HomeState>()
        val mockObserver = mockk<Observer<HomeState>> {
            every { onChanged(capture(observerSlot)) } just Runs
        }

        // ACT
        val testObject = HomeViewModel(mockAccountProvider, mockSettingProvider)
        testObject.accountName.observeForever(mockObserver)

        // ASSERT
        val captured = observerSlot.captured
        Assert.assertTrue(observerSlot.isCaptured)
        check(captured is HomeState.Success)
        Assert.assertEquals(expected, captured.name)

    }

    @Test
    fun `when homeViewModel call showTermsAndConditions mockSettingProvider should call showTermsAndConditions`() =
        runBlockingTest {
            // ARRANGE
            val mockAccountProvider = mockk<AccountProvider>()
            val mockSettingProvider = mockk<SettingProvider>()
            // ACT
            homeViewModel.showTermsAndConditions()

            // ASSERT
            verify(builder.mockSettingProvider, times(1)).showTermsAndConditions()
        }


}