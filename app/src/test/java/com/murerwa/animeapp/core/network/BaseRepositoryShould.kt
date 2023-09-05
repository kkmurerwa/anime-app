package com.murerwa.animeapp.core.network

import com.murerwa.animeapp.utils.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseRepositoryShould: BaseUnitTest() {
    private val baseRepository = BaseRepository()

    @Test
    fun returnNetworkResultSuccessWhenApiCallIsSuccessful() = runTest {
        val result = baseRepository.safeApiCall { "Success" }
        assertTrue(result is DataSourceState.Success)
    }

    @Test
    fun returnNetworkResultFailureWhenApiCallIsUnsuccessful() = runTest {
        val result = baseRepository.safeApiCall { throw Exception("Error") }
        assertTrue(result is DataSourceState.Failure)
    }
}