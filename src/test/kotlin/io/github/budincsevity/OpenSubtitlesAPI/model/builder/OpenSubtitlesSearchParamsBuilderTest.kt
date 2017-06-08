package io.github.budincsevity.OpenSubtitlesAPI.model.builder

import io.github.budincsevity.OpenSubtitlesAPI.exception.SearchParamMissingException
import io.github.budincsevity.OpenSubtitlesAPI.util.TestDataProvider
import io.github.budincsevity.OpenSubtitlesAPI.util.SearchParam
import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class OpenSubtitlesSearchParamsBuilderTest {

    val openSubtitlesSearchParamsBuilderWithMovieHashMovieBytes = TestDataProvider().createOpenSubtitlesSearchParamBuilderWithMovieHashMovieBytes()
    val openSubtitlesSearchParamsBuilderWithTag = TestDataProvider().createOpenSubtitlesSearchParamBuilderWithTag()
    val openSubtitlesSearchParamsBuilderWithQuery = TestDataProvider().createOpenSubtitlesSearchParamBuilderWithQuery()
    val openSubtitlesSearchParamsBuilderWithImdbId = TestDataProvider().createOpenSubtitlesSearchParamBuilderWithImdbId()
    val openSubtitlesEmptySearchParamsBuilder = TestDataProvider().createEmptyOpenSubtitlesSearchParamBuilder()
    lateinit var paramsMap: HashMap<*, *>

    companion object {
        val TOKEN = "token"
        val FIRST = "1"
        val ALIEN = "Alien"
        val HASH = "HASH"
        val BYTESIZE = "12345"
        val IMDBID = "555555"
        val QUERY = "The.Big.Bang.Theory.S10E10.720p.HDTV.X264-DIMENSION[ettv]"
    }

    @DataProvider
    fun testData(): Array<Array<out Any?>> = arrayOf(
            arrayOf(openSubtitlesSearchParamsBuilderWithMovieHashMovieBytes, HASH, BYTESIZE, null, null, null),
            arrayOf(openSubtitlesSearchParamsBuilderWithTag, null, null, ALIEN, null, null),
            arrayOf(openSubtitlesSearchParamsBuilderWithImdbId, null, null, null, IMDBID, null),
            arrayOf(openSubtitlesSearchParamsBuilderWithQuery, null, null, null, null, QUERY)
    )

    @Test(dataProvider = "testData")
    fun testBuild_ShouldBuildSearchResultAsExpected(searchParamsBuilder: OpenSubtitlesSearchParamsBuilder, movieHash: String?,
                                                    movieBytes: String?, tag: String?, imdbId: String?, query: String?) {
        whenBuildMethodIsCalled(searchParamsBuilder)

        thenBuiltSearchParamsShouldBeAsExpected(movieHash, movieBytes, query, tag, imdbId)
    }

    @Test(expectedExceptions = arrayOf(SearchParamMissingException::class))
    fun testBuild_ShouldThrowSearchParamMissingException() {
        openSubtitlesEmptySearchParamsBuilder.build(TOKEN)
    }

    private fun whenBuildMethodIsCalled(searchParamsBuilder: OpenSubtitlesSearchParamsBuilder) {
        val openSubtitlesSearchParams = searchParamsBuilder.build(TOKEN)
        val containerMap = openSubtitlesSearchParams[1] as HashMap<*, *>
        paramsMap = containerMap[FIRST] as HashMap<*, *>
    }

    private fun thenBuiltSearchParamsShouldBeAsExpected(movieHash: String?, movieBytes: String?, query: String?, tag: String?, imdbId: String?) {
        assertEquals(paramsMap[SearchParam.MOVIE_HASH.paramName], movieHash)
        assertEquals(paramsMap[SearchParam.MOVIE_BYTE_SIZE.paramName], movieBytes)
        assertEquals(paramsMap[SearchParam.QUERY.paramName], query)
        assertEquals(paramsMap[SearchParam.TAG.paramName], tag)
        assertEquals(paramsMap[SearchParam.IMDBID.paramName], imdbId)
    }
}
