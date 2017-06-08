package io.github.budincsevity.OpenSubtitlesAPI.util

import org.apache.xmlrpc.client.XmlRpcClient
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import org.apache.xmlrpc.client.XmlRpcSunHttpTransportFactory
import org.springframework.stereotype.Component
import java.net.URL

@Component
class XmlRpcProvider {
    fun createXmlRpcClient(baseUrl: String): XmlRpcClient {
        val xmlRpcClientConfig = createXmlRpcClientConfig(baseUrl)
        val xmlRpcClient = XmlRpcClient()
        xmlRpcClient.setConfig(xmlRpcClientConfig)
        xmlRpcClient.transportFactory = XmlRpcSunHttpTransportFactory(xmlRpcClient)

        return xmlRpcClient
    }

    private fun createXmlRpcClientConfig(baseUrl: String): XmlRpcClientConfigImpl {
        val xmlRpcClientConfig = XmlRpcClientConfigImpl()
        xmlRpcClientConfig.serverURL = URL(baseUrl)

        return xmlRpcClientConfig
    }
}
