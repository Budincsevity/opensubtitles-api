package io.github.budincsevity.OpenSubtitlesAPI.model.status

enum class OpenSubtitlesStatus(val status: String) {
    OK("200 OK"),
    PARTIAL_CONTENT("206 Partial content"),

    MOVED("301 Moved (host)"),

    UNAUTHORIZED("401 Unauthorized"),
    INVALID_FORMAT("402 Subtitles has invalid format"),
    INVALID_HASH("403 SubHashes (content and sent subhash) are not same!"),
    INVALID_LANGUAGE("404 Subtitles has invalid language!"),
    MISSING_MANDATORY_PARAMETERS("405 Not all mandatory parameters was specified"),
    NO_SESSION("406 No session"),
    DOWNLOAD_LIMIT_REACHED("407 Download limit reached"),
    INVALID_PARAMETERS("408 Invalid parameters"),
    METHOD_NOT_FOUND("409 Method not found"),
    UNKNOWN_ERROR("410 Other or unknown error"),
    EMPTY_OR_INVALID_USER_AGENT("411 Empty or invalid useragent"),
    INVALID_FORMAT_S("412 %s has invalid format (reason)"),
    INVALID_IMDBID("413 Invalid ImdbID"),
    UNKNOWN_USER_AGENT("414 Unknown User Agent"),
    DISABLED_USER_AGENT("415 Disabled user agent"),
    SUBTITLE_VALIDATION_FAILED("416 Internal subtitle validation failed"),

    SERVICE_UNAVAILABLE("503 Service Unavailable"),
    SERVER_UNDER_MAINTENANCE("506 Server under maintenance")
}
