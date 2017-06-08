package io.github.budincsevity.OpenSubtitlesAPI.service

import io.github.budincsevity.OpenSubtitlesAPI.exception.UnknownUserAgentException
import io.github.budincsevity.OpenSubtitlesAPI.model.status.OpenSubtitlesStatus
import io.github.budincsevity.OpenSubtitlesAPI.util.OpenSubtitlesMethod
import io.github.budincsevity.OpenSubtitlesAPI.util.OpenSubtitlesMethod.*
import io.github.budincsevity.OpenSubtitlesAPI.util.ResponseParam
import io.github.budincsevity.OpenSubtitlesAPI.util.ResponseParam.*
import io.github.budincsevity.OpenSubtitlesAPI.util.XmlRpcProvider
import org.apache.xmlrpc.client.XmlRpcClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.HashMap

@Service
open class OpenSubtitlesServiceImpl : OpenSubtitlesService {

    final val xmlRpcClient: XmlRpcClient

    @Autowired
    constructor(@Value("\${opensubtitles.baseUrl:base-url}") baseUrl: String, xmlRpcProvider: XmlRpcProvider) {
        xmlRpcClient = xmlRpcProvider.createXmlRpcClient(baseUrl)
    }

    override fun serverInfo(): Map<*, *> {
        return xmlRpcClient.execute(SERVERINFO.methodName, arrayOf<Any>()) as HashMap<*, *>
    }

    override fun <T> login(params: Array<T>): String {
        val loginResponse = xmlRpcClient.execute(LOGIN.methodName, params) as HashMap<*, *>
        val status = loginResponse[STATUS.paramName].toString()

        if (status == OpenSubtitlesStatus.UNKNOWN_USER_AGENT.status) {
            throw UnknownUserAgentException()
        }

        return loginResponse[TOKEN.paramName].toString()
    }

    override fun logout(token: String): String {
        val loginResponse = xmlRpcClient.execute(LOGOUT.methodName, arrayOf(token)) as HashMap<*, *>
        return loginResponse[STATUS.paramName].toString()
    }

    override fun <T> searchSubtitles(token: String, params: Array<T>): Map<*, *> {
        return xmlRpcClient.execute(SEARCHSUBTITLES.methodName, params) as HashMap<*, *>
    }
}
