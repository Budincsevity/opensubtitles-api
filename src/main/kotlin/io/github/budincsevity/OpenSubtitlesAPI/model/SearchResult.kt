package io.github.budincsevity.OpenSubtitlesAPI.model

import io.github.budincsevity.OpenSubtitlesAPI.model.status.OpenSubtitlesStatus
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class SearchResult(val status: String, val data: List<SearchResultData>) {
    companion object {
        fun badRequest(): SearchResult = SearchResult(OpenSubtitlesStatus.INVALID_PARAMETERS.status, emptyList())
        fun unknownUserAgent(): SearchResult = SearchResult(OpenSubtitlesStatus.UNKNOWN_USER_AGENT.status, emptyList())
    }

    override fun equals(other: Any?): Boolean {
        return EqualsBuilder.reflectionEquals(this, other)
    }

    override fun hashCode(): Int {
        return HashCodeBuilder.reflectionHashCode(this)
    }

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}
