package io.github.budincsevity.OpenSubtitlesAPI.util

import io.github.budincsevity.OpenSubtitlesAPI.model.SearchResult
import io.github.budincsevity.OpenSubtitlesAPI.model.SearchResultData
import io.github.budincsevity.OpenSubtitlesAPI.model.builder.OpenSubtitlesSearchParamsBuilder
import io.github.budincsevity.OpenSubtitlesAPI.model.builder.SearchResultDataBuilder
import io.github.budincsevity.OpenSubtitlesAPI.model.status.OpenSubtitlesStatus
import java.util.*

class TestDataProvider {
    fun createTestSearchResult(): SearchResult {
        val status = OpenSubtitlesStatus.OK.status
        val data = createSearchResultData()

        return SearchResult(status, arrayListOf(data))
    }

    fun createTestErrorSearchResult(): SearchResult {
        val status = OpenSubtitlesStatus.UNKNOWN_ERROR.status
        val data = createSearchResultData()

        return SearchResult(status, arrayListOf(data))
    }

    private fun createSearchResultData(): SearchResultData {
        return SearchResultDataBuilder()
                .withSubActualCD("SubActualCD")
                .withMovieName("MovieName")
                .withSubBad("SubBad")
                .withMovieHash("MovieHash")
                .withSubFileName("SubFileName")
                .withSubSumCD("SubSumCD")
                .withZipDownloadLink("ZipDownloadLink")
                .withMovieNameEng("MovieNameEng")
                .withSubSize("SubSize")
                .withIDSubtitleFile("IDSubtitleFile")
                .withSubHash("SubHash")
                .withSubFeatured("SubFeatured")
                .withSubAuthorComment("SubAuthorComment")
                .withSubDownloadsCnt("SubDownloadsCnt")
                .withSubAddDate("SubAddDate")
                .withSubLastTS("SubLastTS")
                .withSubAutoTranslation("SubAutoTranslation")
                .withMovieReleaseName("MovieReleaseName")
                .withSeriesIMDBParent("SeriesIMDBParent")
                .withScore("Score")
                .withUserNickName("UserNickName")
                .withSubHearingImpaired("SubHearingImpaired")
                .withSubTSGroup("SubTSGroup")
                .withQueryCached("QueryCached")
                .withSubLanguageID("SubLanguageID")
                .withSubFormat("SubFormat")
                .withLanguageName("LanguageName")
                .withSubTranslator("SubTranslator")
                .withSeriesEpisode("SeriesEpisode")
                .withUserRank("UserRank")
                .withMovieImdbRating("MovieImdbRating")
                .withMovieTimeMS("MovieTimeMS")
                .withMovieYear("MovieYear")
                .withSubEncoding("SubEncoding")
                .withQueryNumber("QueryNumber")
                .withSubHD("SubHD")
                .withUserID("UserID")
                .withMovieByteSize("MovieByteSize")
                .withMovieFPS("MovieFPS")
                .withSubtitlesLink("SubtitlesLink")
                .withIDSubMovieFile("IDSubMovieFile")
                .withISO639("ISO639")
                .withSeriesSeason("SeriesSeason")
                .withSubFromTrusted("SubFromTrusted")
                .withSubTSGroupHash("SubTSGroupHash")
                .withMatchedBy("MatchedBy")
                .withSubDownloadLink("SubDownloadLink")
                .withSubRating("SubRating")
                .withQueryParameters("QueryParameters")
                .withSubComments("SubComments")
                .withMovieKind("MovieKind")
                .withIDMovie("IDMovie")
                .withIDMovieImdb("IDMovieImdb")
                .withSubForeignPartsOnly("SubForeignPartsOnly")
                .withIDSubtitle("IDSubtitle")
                .build()
    }


    fun createSearchResultMap(): Map<*, *> {
        val status = OpenSubtitlesStatus.OK.status
        val data = arrayOf<Any>(hashMapOf(
                "SubActualCD" to "SubActualCD",
                "MovieName" to "MovieName",
                "SubBad" to "SubBad",
                "MovieHash" to "MovieHash",
                "SubFileName" to "SubFileName",
                "SubSumCD" to "SubSumCD",
                "ZipDownloadLink" to "ZipDownloadLink",
                "MovieNameEng" to "MovieNameEng",
                "SubSize" to "SubSize",
                "IDSubtitleFile" to "IDSubtitleFile",
                "SubHash" to "SubHash",
                "SubFeatured" to "SubFeatured",
                "SubAuthorComment" to "SubAuthorComment",
                "SubDownloadsCnt" to "SubDownloadsCnt",
                "SubAddDate" to "SubAddDate",
                "SubLastTS" to "SubLastTS",
                "SubAutoTranslation" to "SubAutoTranslation",
                "MovieReleaseName" to "MovieReleaseName",
                "SeriesIMDBParent" to "SeriesIMDBParent",
                "Score" to "Score",
                "UserNickName" to "UserNickName",
                "SubHearingImpaired" to "SubHearingImpaired",
                "SubTSGroup" to "SubTSGroup",
                "QueryCached" to "QueryCached",
                "SubLanguageID" to "SubLanguageID",
                "SubFormat" to "SubFormat",
                "LanguageName" to "LanguageName",
                "SubTranslator" to "SubTranslator",
                "SeriesEpisode" to "SeriesEpisode",
                "UserRank" to "UserRank",
                "MovieImdbRating" to "MovieImdbRating",
                "MovieTimeMS" to "MovieTimeMS",
                "MovieYear" to "MovieYear",
                "SubEncoding" to "SubEncoding",
                "QueryNumber" to "QueryNumber",
                "SubHD" to "SubHD",
                "UserID" to "UserID",
                "MovieByteSize" to "MovieByteSize",
                "MovieFPS" to "MovieFPS",
                "SubtitlesLink" to "SubtitlesLink",
                "IDSubMovieFile" to "IDSubMovieFile",
                "ISO639" to "ISO639",
                "SeriesSeason" to "SeriesSeason",
                "SubFromTrusted" to "SubFromTrusted",
                "SubTSGroupHash" to "SubTSGroupHash",
                "MatchedBy" to "MatchedBy",
                "SubDownloadLink" to "SubDownloadLink",
                "SubRating" to "SubRating",
                "QueryParameters" to "QueryParameters",
                "SubComments" to "SubComments",
                "MovieKind" to "MovieKind",
                "IDMovie" to "IDMovie",
                "IDMovieImdb" to "IDMovieImdb",
                "SubForeignPartsOnly" to "SubForeignPartsOnly",
                "IDSubtitle" to "IDSubtitle"))

        return hashMapOf("data" to data, "status" to status)
    }

    fun createErrorSearchResultMap(): Map<*, *> {
        return hashMapOf("status" to OpenSubtitlesStatus.UNKNOWN_ERROR.status, "data" to arrayOf(hashMapOf<Any, Any>()))
    }

    fun createOpenSubtitlesSearchParamBuilderWithMovieHashMovieBytes(): OpenSubtitlesSearchParamsBuilder {
        return OpenSubtitlesSearchParamsBuilder()
                .withSubLanguageId(Optional.of("eng"))
                .withMovieHash(Optional.of("HASH"))
                .withMovieByteSize(Optional.of("12345"))
                .withQuery(Optional.of("The.Big.Bang.Theory.S10E10.720p.HDTV.X264-DIMENSION[ettv]"))
    }

    fun createOpenSubtitlesSearchParamBuilderWithQuery(): OpenSubtitlesSearchParamsBuilder {
        return OpenSubtitlesSearchParamsBuilder()
                .withSubLanguageId(Optional.of("eng"))
                .withMovieByteSize(Optional.empty())
                .withMovieHash(Optional.empty())
                .withTag(Optional.empty())
                .withQuery(Optional.of("The.Big.Bang.Theory.S10E10.720p.HDTV.X264-DIMENSION[ettv]"))
    }

    fun createOpenSubtitlesSearchParamBuilderWithTag(): OpenSubtitlesSearchParamsBuilder {
        return OpenSubtitlesSearchParamsBuilder()
                .withSubLanguageId(Optional.of("eng"))
                .withMovieByteSize(Optional.empty())
                .withMovieHash(Optional.empty())
                .withTag(Optional.of("Alien"))
                .withQuery(Optional.of("The.Big.Bang.Theory.S10E10.720p.HDTV.X264-DIMENSION[ettv]"))
    }

    fun createOpenSubtitlesSearchParamBuilderWithImdbId(): OpenSubtitlesSearchParamsBuilder {
        return OpenSubtitlesSearchParamsBuilder()
                .withSubLanguageId(Optional.of("eng"))
                .withMovieByteSize(Optional.empty())
                .withMovieHash(Optional.empty())
                .withTag(Optional.empty())
                .withImdbId(Optional.of("555555"))
                .withQuery(Optional.of("The.Big.Bang.Theory.S10E10.720p.HDTV.X264-DIMENSION[ettv]"))
    }

    fun createEmptyOpenSubtitlesSearchParamBuilder(): OpenSubtitlesSearchParamsBuilder {
        return OpenSubtitlesSearchParamsBuilder()
                .withSubLanguageId(Optional.of("eng"))
                .withMovieByteSize(Optional.empty())
                .withMovieHash(Optional.empty())
                .withTag(Optional.empty())
                .withImdbId(Optional.empty())
                .withQuery(Optional.empty())
    }
}
