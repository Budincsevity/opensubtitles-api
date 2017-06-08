package io.github.budincsevity.OpenSubtitlesAPI.controller

import io.github.budincsevity.OpenSubtitlesAPI.exception.SearchParamMissingException
import io.github.budincsevity.OpenSubtitlesAPI.exception.UnknownUserAgentException
import io.github.budincsevity.OpenSubtitlesAPI.mapper.SearchResultMapper
import io.github.budincsevity.OpenSubtitlesAPI.model.SearchResult
import io.github.budincsevity.OpenSubtitlesAPI.model.builder.OpenSubtitlesLoginParamsBuilder
import io.github.budincsevity.OpenSubtitlesAPI.model.builder.OpenSubtitlesSearchParamsBuilder
import io.github.budincsevity.OpenSubtitlesAPI.service.OpenSubtitlesService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.text.MessageFormat
import java.util.*

@Api(value = "/api", description = "OpenSubtitles REST API")
@RestController
@RequestMapping("/api")
class OpenSubtitlesController @Autowired constructor(val openSubtitlesService: OpenSubtitlesService,
                                                     @Value("\${opensubtitles.userAgent}") val userAgent: String,
                                                     @Value("\${opensubtitles.username}") val username: String,
                                                     @Value("\${opensubtitles.password}") val password: String) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
        val SUCCESSFUL_RESPONSE = "Successful subtitle response. SearchResult: {0}"
    }

    @ApiOperation(value = "Search", notes = """"Gets Subtitles for a given movie. If sublanguageid is empty, or have value 'all' - it search in every sublanguage,
    you can set it to multiple languages (e.g. 'eng,dut,cze'). Tag is index of movie filename or subtitle file name, or release name
    If you define moviehash and moviebytesize, then imdbid and query in same array are ignored. If you define imdbid, then moviehash,
    moviebytesize and query is ignored. If you define query, then moviehash, moviebytesize and imdbid is ignored."""", response = SearchResult::class)
    @GetMapping(value = "/search", produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun searchSubtitles(@RequestParam(value = "sublanguageid", required = false, defaultValue = "eng") sublanguageid: String,
                        @RequestParam(value = "tag", required = false) tag: String?,
                        @RequestParam(value = "moviehash", required = false) movieHash: String?,
                        @RequestParam(value = "moviebytesize", required = false) movieByteSize: String?,
                        @RequestParam(value = "imdbId", required = false) imdbId: String?,
                        @RequestParam(value = "query", required = false) query: String?): ResponseEntity<SearchResult> {

        try {
            val token = loginUser()
            val searchResult = searchSubtitles(token, Optional.of(sublanguageid), Optional.ofNullable(tag), Optional.ofNullable(movieHash),
                    Optional.ofNullable(movieByteSize), Optional.ofNullable(imdbId), Optional.ofNullable(query))
            val mappedSearchResult = SearchResultMapper().mapSearchResult(searchResult)
            logoutUser(token)

            log.info(MessageFormat.format(SUCCESSFUL_RESPONSE, mappedSearchResult))
            return ResponseEntity.ok().body(mappedSearchResult)
        } catch (exception: SearchParamMissingException) {
            log.error(exception.message, exception)
            return ResponseEntity.badRequest().body(SearchResult.badRequest())
        } catch (exception: UnknownUserAgentException) {
            log.error(exception.message, exception)
            return ResponseEntity.badRequest().body(SearchResult.unknownUserAgent())
        }
    }

    private fun loginUser(): String {
        val loginParams = OpenSubtitlesLoginParamsBuilder()
                .withUsername(username)
                .withPassword(password)
                .withUserAgent(userAgent)
                .build()
        return openSubtitlesService.login(loginParams)
    }

    private fun searchSubtitles(token: String, sublanguageid: Optional<String>, tag: Optional<String>, movieHash: Optional<String>,
                                movieByteSize: Optional<String>, imdbId: Optional<String>, query: Optional<String>): Map<*, *> {
        val params = OpenSubtitlesSearchParamsBuilder()
                .withSubLanguageId(sublanguageid)
                .withTag(tag)
                .withMovieHash(movieHash)
                .withMovieByteSize(movieByteSize)
                .withImdbId(imdbId)
                .withQuery(query)
                .build(token)

        return openSubtitlesService.searchSubtitles(token, params)
    }

    private fun logoutUser(token: String) {
        openSubtitlesService.logout(token)
    }
}
