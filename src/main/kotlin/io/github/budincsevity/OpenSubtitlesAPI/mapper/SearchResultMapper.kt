package io.github.budincsevity.OpenSubtitlesAPI.mapper

import io.github.budincsevity.OpenSubtitlesAPI.model.SearchResult
import io.github.budincsevity.OpenSubtitlesAPI.model.SearchResultData
import io.github.budincsevity.OpenSubtitlesAPI.model.builder.SearchResultDataBuilder
import io.github.budincsevity.OpenSubtitlesAPI.model.status.OpenSubtitlesStatus
import io.github.budincsevity.OpenSubtitlesAPI.util.ResponseParam.DATA
import io.github.budincsevity.OpenSubtitlesAPI.util.ResponseParam.STATUS

class SearchResultMapper {
    fun mapSearchResult(searchResultMap: Map<*, *>): SearchResult {
        val status: String = searchResultMap[STATUS.paramName] as String
        val data = searchResultMap[DATA.paramName] as Array<*>

        if (status != OpenSubtitlesStatus.OK.status) {
            return SearchResult(status, arrayListOf<SearchResultData>())
        }

        val searchResultData = mapSearchResultData(data)
        return SearchResult(status, searchResultData)
    }

    private fun mapSearchResultData(data: Array<*>): ArrayList<SearchResultData> {
        val searchResultData = arrayListOf<SearchResultData>()
        data.map { it as HashMap<*, *> }.mapTo(searchResultData) {
            buildSearchResultData(it)
        }
        return searchResultData
    }

    private fun buildSearchResultData(data: HashMap<*, *>): SearchResultData {
        return SearchResultDataBuilder()
                .withSubActualCD(data["SubActualCD"].toString())
                .withMovieName(data["MovieName"].toString())
                .withSubBad(data["SubBad"].toString())
                .withMovieHash(data["MovieHash"].toString())
                .withSubFileName(data["SubFileName"].toString())
                .withSubSumCD(data["SubSumCD"].toString())
                .withZipDownloadLink(data["ZipDownloadLink"].toString())
                .withMovieNameEng(data["MovieNameEng"].toString())
                .withSubSize(data["SubSize"].toString())
                .withIDSubtitleFile(data["IDSubtitleFile"].toString())
                .withSubHash(data["SubHash"].toString())
                .withSubFeatured(data["SubFeatured"].toString())
                .withSubAuthorComment(data["SubAuthorComment"].toString())
                .withSubDownloadsCnt(data["SubDownloadsCnt"].toString())
                .withSubAddDate(data["SubAddDate"].toString())
                .withSubLastTS(data["SubLastTS"].toString())
                .withSubAutoTranslation(data["SubAutoTranslation"].toString())
                .withMovieReleaseName(data["MovieReleaseName"].toString())
                .withSeriesIMDBParent(data["SeriesIMDBParent"].toString())
                .withScore(data["Score"].toString())
                .withUserNickName(data["UserNickName"].toString())
                .withSubHearingImpaired(data["SubHearingImpaired"].toString())
                .withSubTSGroup(data["SubTSGroup"].toString())
                .withQueryCached(data["QueryCached"].toString())
                .withSubLanguageID(data["SubLanguageID"].toString())
                .withSubFormat(data["SubFormat"].toString())
                .withLanguageName(data["LanguageName"].toString())
                .withSubTranslator(data["SubTranslator"].toString())
                .withSeriesEpisode(data["SeriesEpisode"].toString())
                .withUserRank(data["UserRank"].toString())
                .withMovieImdbRating(data["MovieImdbRating"].toString())
                .withMovieTimeMS(data["MovieTimeMS"].toString())
                .withMovieYear(data["MovieYear"].toString())
                .withSubEncoding(data["SubEncoding"].toString())
                .withQueryNumber(data["QueryNumber"].toString())
                .withSubHD(data["SubHD"].toString())
                .withUserID(data["UserID"].toString())
                .withMovieByteSize(data["MovieByteSize"].toString())
                .withMovieFPS(data["MovieFPS"].toString())
                .withSubtitlesLink(data["SubtitlesLink"].toString())
                .withIDSubMovieFile(data["IDSubMovieFile"].toString())
                .withISO639(data["ISO639"].toString())
                .withSeriesSeason(data["SeriesSeason"].toString())
                .withSubFromTrusted(data["SubFromTrusted"].toString())
                .withSubTSGroupHash(data["SubTSGroupHash"].toString())
                .withMatchedBy(data["MatchedBy"].toString())
                .withSubDownloadLink(data["SubDownloadLink"].toString())
                .withSubRating(data["SubRating"].toString())
                .withQueryParameters(data["QueryParameters"].toString())
                .withSubComments(data["SubComments"].toString())
                .withMovieKind(data["MovieKind"].toString())
                .withIDMovie(data["IDMovie"].toString())
                .withIDMovieImdb(data["IDMovieImdb"].toString())
                .withSubForeignPartsOnly(data["SubForeignPartsOnly"].toString())
                .withIDSubtitle(data["IDSubtitle"].toString())
                .build()
    }
}
