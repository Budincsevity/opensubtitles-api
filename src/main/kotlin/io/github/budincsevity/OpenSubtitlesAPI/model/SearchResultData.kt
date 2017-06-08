package io.github.budincsevity.OpenSubtitlesAPI.model

import io.github.budincsevity.OpenSubtitlesAPI.model.builder.SearchResultDataBuilder
import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.apache.commons.lang3.builder.ToStringBuilder

class SearchResultData(builder: SearchResultDataBuilder) {
    val subActualCD: String = builder.subActualCD
    val movieName: String = builder.movieName
    val subBad: String = builder.subBad
    val movieHash: String = builder.movieHash
    val subFileName: String = builder.subFileName
    val subSumCD: String = builder.subSumCD
    val zipDownloadLink: String = builder.zipDownloadLink
    val movieNameEng: String = builder.movieNameEng
    val subSize: String = builder.subSize
    val iDSubtitleFile: String = builder.iDSubtitleFile
    val subHash: String = builder.subHash
    val subFeatured: String = builder.subFeatured
    val subAuthorComment = builder.subAuthorComment
    val subDownloadsCnt = builder.subDownloadsCnt
    val subAddDate = builder.subAddDate
    val subLastTS = builder.subLastTS
    val subAutoTranslation = builder.subAutoTranslation
    val movieReleaseName = builder.movieReleaseName
    val seriesIMDBParent = builder.seriesIMDBParent
    val score = builder.score
    val userNickName = builder.userNickName
    val subHearingImpaired = builder.subHearingImpaired
    val subTSGroup = builder.subTSGroup
    val queryCached = builder.queryCached
    val subLanguageID = builder.subLanguageID
    val subFormat = builder.subFormat
    val languageName = builder.languageName
    val subTranslator = builder.subTranslator
    val seriesEpisode = builder.seriesEpisode
    val userRank = builder.userRank
    val movieImdbRating = builder.movieImdbRating
    val movieTimeMS = builder.movieTimeMS
    val movieYear = builder.movieYear
    val subEncoding = builder.subEncoding
    val queryNumber = builder.queryNumber
    val subHD = builder.subHD
    val userID = builder.userID
    val movieByteSize = builder.movieByteSize
    val movieFPS = builder.movieFPS
    val subtitlesLink = builder.subtitlesLink
    val iDSubMovieFile = builder.iDSubMovieFile
    val isO639 = builder.ISO639
    val seriesSeason = builder.seriesSeason
    val subFromTrusted = builder.subFromTrusted
    val subTSGroupHash = builder.subTSGroupHash
    val matchedBy = builder.matchedBy
    val subDownloadLink = builder.subDownloadLink
    val subRating = builder.subRating
    val queryParameters = builder.queryParameters
    val subComments = builder.subComments
    val movieKind = builder.movieKind
    val iDMovie = builder.iDMovie
    val iDMovieImdb = builder.iDMovieImdb
    val subForeignPartsOnly = builder.subForeignPartsOnly
    val iDSubtitle = builder.iDSubtitle

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
