package io.github.budincsevity.OpenSubtitlesAPI.model.builder

import io.github.budincsevity.OpenSubtitlesAPI.exception.SearchParamMissingException
import io.github.budincsevity.OpenSubtitlesAPI.util.SearchParam.*
import java.util.*

class OpenSubtitlesSearchParamsBuilder {

    var subLanguageId: Optional<String> = Optional.empty()
    var tag: Optional<String> = Optional.empty()
    var movieHash: Optional<String> = Optional.empty()
    var movieByteSize: Optional<String> = Optional.empty()
    var imdbId: Optional<String> = Optional.empty()
    var query: Optional<String> = Optional.empty()

    fun withSubLanguageId(subLanguageId: Optional<String>): OpenSubtitlesSearchParamsBuilder {
        this.subLanguageId = subLanguageId
        return this
    }

    fun withTag(tag: Optional<String>): OpenSubtitlesSearchParamsBuilder {
        this.tag = tag
        return this
    }

    fun withMovieHash(movieHash: Optional<String>): OpenSubtitlesSearchParamsBuilder {
        this.movieHash = movieHash
        return this
    }

    fun withMovieByteSize(movieByteSize: Optional<String>): OpenSubtitlesSearchParamsBuilder {
        this.movieByteSize = movieByteSize
        return this
    }

    fun withImdbId(imdbId: Optional<String>): OpenSubtitlesSearchParamsBuilder {
        this.imdbId = imdbId
        return this
    }

    fun withQuery(query: Optional<String>): OpenSubtitlesSearchParamsBuilder {
        this.query = query
        return this
    }

    fun build(token: String): Array<Any> {

        val FIRST = "1"
        if (movieHash.isPresent && movieByteSize.isPresent) {
            return arrayOf(token, hashMapOf(FIRST to hashMapOf(MOVIE_HASH.paramName to movieHash.get(), MOVIE_BYTE_SIZE.paramName to movieByteSize.get(),
                    SUBLANGUAGEID.paramName to subLanguageId.get())))
        } else if (tag.isPresent) {
            return arrayOf(token, hashMapOf(FIRST to hashMapOf(TAG.paramName to tag.get(), SUBLANGUAGEID.paramName to subLanguageId.get())))
        } else if (imdbId.isPresent) {
            return arrayOf(token, hashMapOf(FIRST to hashMapOf(IMDBID.paramName to imdbId.get(), SUBLANGUAGEID.paramName to subLanguageId.get())))
        } else if (query.isPresent) {
            return arrayOf(token, hashMapOf(FIRST to hashMapOf(QUERY.paramName to query.get(), SUBLANGUAGEID.paramName to subLanguageId.get())))
        } else {
            throw SearchParamMissingException("At least one search parameter is required.")
        }
    }
}
