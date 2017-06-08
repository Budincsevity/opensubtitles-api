package io.github.budincsevity.OpenSubtitlesAPI.mapper

import io.github.budincsevity.OpenSubtitlesAPI.util.TestDataProvider
import io.github.budincsevity.OpenSubtitlesAPI.model.SearchResult
import io.github.budincsevity.OpenSubtitlesAPI.model.status.OpenSubtitlesStatus
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test

class SearchResultMapperTest {

    val searchResultMapper = SearchResultMapper()
    val testDataProvider = TestDataProvider()
    val expectedSearchResult = testDataProvider.createTestSearchResult()
    lateinit var searchResultMap: Map<*, *>
    lateinit var searchResult: SearchResult

    @Test
    fun testMapSearchResult_SearchResultShouldBeEqualToTest() {
        givenASearchResultMap()
        whenMapSearchResultIsCalled()

        thenSearchResultShouldBeAsExpected()
    }

    @Test
    fun testMapSearchResult_SearchResultShouldBeEqualToErrorExpected() {
        givenASearchResultMapWithError()
        whenMapSearchResultIsCalled()

        thenSearchResultDataShouldBeEmptyWithProperErrorStatus()
    }

    private fun givenASearchResultMap() {
        searchResultMap = testDataProvider.createSearchResultMap()
    }

    private fun givenASearchResultMapWithError() {
        searchResultMap = testDataProvider.createErrorSearchResultMap()
    }

    private fun whenMapSearchResultIsCalled() {
        searchResult = searchResultMapper.mapSearchResult(searchResultMap)
    }

    private fun thenSearchResultShouldBeAsExpected() {
        assertTrue(searchResult == expectedSearchResult)
    }

    private fun thenSearchResultDataShouldBeEmptyWithProperErrorStatus() {
        assertEquals(searchResult.status, OpenSubtitlesStatus.UNKNOWN_ERROR.status)
        assertTrue(searchResult.data.isEmpty())
    }
}
