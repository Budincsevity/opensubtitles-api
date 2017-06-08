package io.github.budincsevity.OpenSubtitlesAPI.controller

import org.hamcrest.Matchers
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.context.WebApplicationContext
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test


@RunWith(SpringRunner::class)
@SpringBootTest
@WebAppConfiguration
class OpenSubtitlesControllerTest : AbstractTestNGSpringContextTests() {

    @Autowired
    private var wac: WebApplicationContext? = null
    private var mockMvc: MockMvc? = null
    private var params = LinkedMultiValueMap<String, String>()

    companion object {
        val API_SEARCH = "/api/search"
        val ALL_FIELDS = "$.*"
        val DATA_FIELDS = "$.data[0].*"
        val DATA_MOVIE_NAME = "$.data[0].movieName"
        val DATA_QUERY_PARAMETERS = "$.data[0].queryParameters"
        val DATA_LANGUAGE_NAME = "$.data[0].languageName"
        val HUNGARIAN = "Hungarian"
        val ENGLISH = "English"
        val SAMPLE_QUERY_PARAMETERS = "{query=the.big.bang.theory.s10e10.720p.hdtv.x264-dimension[ettv], sublanguageid=hun}"
        val SAMPLE_QUERY = "The.big.bang.theory.s10e10.720p.hdtv.x264-dimension[ettv]"
        val SAMPLE_HASH_QUERY_PARAMETERS = "{sublanguageid=eng, moviehash=18379ac9af039390, moviebytesize=366876694}"
        val SAMPLE_HUNGARIAN_MOVIE_NAME = "\"The Big Bang Theory\" The Property Division Collision"
        val SAMPLE_ENGLISH_MOVIE_NAME = "\"Fringe\" Alone in the World"
        val MOVIE_HASH = "moviehash"
        val MOVIE_BYTE_SIZE = "moviebytesize"
        val QUERY = "query"
        val SUBLANGUAGEID = "sublanguageid"
        val HUN = "hun"
        val SAMPLE_MOVIE_HASH = "18379ac9af039390"
        val SAMPLE_MOVIE_BYTE_SIZE = "366876694"
    }

    @BeforeMethod
    fun setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.wac)
                .build()
    }

    @Test
    fun testSearchSubtitles_ShouldContainAllFieldsInJson() {
        givenAHungarianRequestParams()
        this.mockMvc?.perform(MockMvcRequestBuilders.get(API_SEARCH)
                .params(params)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                ?.andExpect(MockMvcResultMatchers.jsonPath(ALL_FIELDS, Matchers.hasSize<Any>(2)))
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_FIELDS, Matchers.hasSize<Any>(54)))
    }

    @Test
    fun testSearchSubtitles_ShouldReturnHungarianSubtitleWithCorrespondingQueryParameters() {
        givenAHungarianRequestParams()
        this.mockMvc?.perform(MockMvcRequestBuilders.get(API_SEARCH)
                .params(params)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_LANGUAGE_NAME).value(HUNGARIAN))
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_QUERY_PARAMETERS).value(SAMPLE_QUERY_PARAMETERS))
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_MOVIE_NAME).value(SAMPLE_HUNGARIAN_MOVIE_NAME))
    }

    @Test
    fun testSearchSubtitles_ShouldReturnSubtitleWithMovieHashMovieByteSizeQuery() {
        givenAMovieHashMovieByteSizeParams()
        this.mockMvc?.perform(MockMvcRequestBuilders.get(API_SEARCH)
                .params(params)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                ?.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                ?.andExpect(MockMvcResultMatchers.status().isOk)
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_MOVIE_NAME).value(SAMPLE_ENGLISH_MOVIE_NAME))
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_QUERY_PARAMETERS).value(SAMPLE_HASH_QUERY_PARAMETERS))
                ?.andExpect(MockMvcResultMatchers.jsonPath(DATA_LANGUAGE_NAME).value(ENGLISH))
    }

    private fun givenAHungarianRequestParams() {
        params = LinkedMultiValueMap<String, String>()
        params.add(QUERY, SAMPLE_QUERY)
        params.add(SUBLANGUAGEID, HUN)
    }

    private fun givenAMovieHashMovieByteSizeParams() {
        params = LinkedMultiValueMap<String, String>()
        params.add(MOVIE_HASH, SAMPLE_MOVIE_HASH)
        params.add(MOVIE_BYTE_SIZE, SAMPLE_MOVIE_BYTE_SIZE)
    }
}
