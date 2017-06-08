package io.github.budincsevity.OpenSubtitlesAPI.model.builder

import java.util.*

class OpenSubtitlesLoginParamsBuilder {
    var username: String = ""
    var password: String = ""
    var locale = Locale.US.isO3Language
    var userAgent: String = ""

    fun withUsername(username: String): OpenSubtitlesLoginParamsBuilder {
        this.username = username
        return this
    }

    fun withPassword(password: String): OpenSubtitlesLoginParamsBuilder {
        this.password = password
        return this
    }

    fun withUserAgent(userAgent: String): OpenSubtitlesLoginParamsBuilder {
        this.userAgent = userAgent
        return this
    }

    fun build(): Array<Any> = arrayOf(username, password, locale, userAgent)
}
