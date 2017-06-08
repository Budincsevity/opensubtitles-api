package io.github.budincsevity.OpenSubtitlesAPI.service


/**
 *  Official OpenSubtitles API documentation:
 *  http://trac.opensubtitles.org/projects/opensubtitles/wiki/XMLRPC
 *
 * **/
interface OpenSubtitlesService {

    /**
     * This simple function returns basic server info, it could be used for ping or telling server info to client.
     * No valid UserAgent is needed.
     *
     * @return server info Map<*,*>
     *
     * **/
    fun serverInfo(): Map<*, *>

    /**
     * This will login user. This function should be called always when starting talking with server.
     * It returns token, which must be used in later communication. If user has no account, blank username and password
     * should be OK.
     *
     * Important: for security improvement, you can use HTTPS and/or send $password as MD5($password) hash.
     *
     * @return token String
     *
     * **/
    fun <T> login(params: Array<T>): String

    /**
     * This will logout user (ends session id). Good call this function is before ending (closing) clients program.
     *
     * @return status String
     *
     * **/
    fun logout(token: String): String

    /**
     *
     * Returns information about found subtitles. When nothing is found, 'data' is empty. If sublanguageid is empty,
     * or have value 'all' - it search in every sublanguage, you can set it to multiple languages (e.g. 'eng,dut,cze').
     * Tag is index of movie filename or subtitle file name, or release name - currently we index more than 53.000.000 of tags.
     *    *
     * If you define moviehash and moviebytesize, then imdbid and query in same array are ignored.
     * If you define imdbid, then moviehash, moviebytesize and query is ignored. If you define query,
     * then moviehash, moviebytesize and imdbid is ignored.
     *
     * @return Map<*,*> it can be mapped to SearchResult data, with SearchResultMapper.mapSearchResult
     *
     * **/
    fun <T> searchSubtitles(token: String, params: Array<T>): Map<*, *>
}
