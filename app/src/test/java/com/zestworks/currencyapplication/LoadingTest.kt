package com.zestworks.currencyapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zestworks.currencyapplication.model.Result
import com.zestworks.currencyapplication.repository.network.Repository
import com.zestworks.currencyapplication.viewModel.CurrencyViewModel
import com.zestworks.currencyapplication.viewModel.RepositoryCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class LoadingTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRepo:Repository

    lateinit var currencyViewModel: CurrencyViewModel

    @Captor
    lateinit var currencyCallbackCaptor : ArgumentCaptor<RepositoryCallback>

    @Before
    fun setupTestingStuff() {
        currencyViewModel = CurrencyViewModel(mockRepo)
    }

    @Test
    fun `Does network failure trigger error`() {
        currencyViewModel.onLoading()
        Mockito.verify(mockRepo).load(capture(currencyCallbackCaptor))
        assert(currencyViewModel.currentState.value!! == CurrencyViewModel.State.Loading)
        currencyCallbackCaptor.value.onError("Network failed")
        assert(currencyViewModel.currentState.value!! is CurrencyViewModel.State.Error)
    }

    @Test
    fun `Does network success trigger success`(){
        currencyViewModel.onLoading()
        Mockito.verify(mockRepo).load(capture(currencyCallbackCaptor))
        assert(currencyViewModel.currentState.value!! == CurrencyViewModel.State.Loading)
        val results = listOf<Result>()
        currencyCallbackCaptor.value.onSuccess(results)
        assert(currencyViewModel.currentState.value!! is CurrencyViewModel.State.Success)
    }
}
