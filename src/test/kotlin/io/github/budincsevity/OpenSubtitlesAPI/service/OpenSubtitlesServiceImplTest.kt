package io.github.budincsevity.OpenSubtitlesAPI.service

import io.github.budincsevity.OpenSubtitlesAPI.util.OpenSubtitlesMethod
import io.github.budincsevity.OpenSubtitlesAPI.util.XmlRpcProvider
import org.apache.xmlrpc.client.XmlRpcClient
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test


@RunWith(SpringRunner::class)
@SpringBootTest
class OpenSubtitlesServiceImplTest {
    @Mock
    private lateinit var xmlRpcClient: XmlRpcClient
    @Mock
    private lateinit var xmlRpcProvider: XmlRpcProvider
    private lateinit var openSubtitlesService: OpenSubtitlesServiceImpl
    private lateinit var serverInfo: Map<*, *>
    private lateinit var subtitle: Map<*, *>
    private lateinit var token: String

    companion object {
        val STATUS = "status"
        val TOKEN = "token"
        val OK = "OK"
        val TEST_TOKEN = "123"
        val SRC = "src"
        val TEST_BASE_URL = "www.base-url.com"
    }

    @BeforeMethod
    fun setup() {
        MockitoAnnotations.initMocks(this)

        givenAXmlRpcProvider()
        givenAnOpenSubtitlesService()
    }

    @Test
    fun testServerInfo_ShouldCallExecute() {
        whenServerInfoIsCalled()
        thenOpenSubtitlesServerInfoShouldBeCalledAndStatusShouldBeOk()
    }

    @Test
    fun testLogin_ShouldCallExecute() {
        whenLoginIsCalled()
        thenOpenSubtitlesLoginShouldBeCalledAndShouldReturnToken()
    }

    @Test
    fun testSearchSubtitles_ShouldCallExecute() {
        whenSearchSubtitlesIsCalled()
        thenOpenSubtitlesSearchSubtitlesShouldBeCalledAndResultShouldBeAValidSubtitle()
    }

    private fun givenAXmlRpcProvider() {
        Mockito.`when`(xmlRpcClient.execute(ArgumentMatchers.anyString(), ArgumentMatchers.eq(arrayOf<Any>()))).thenReturn(hashMapOf(STATUS to OK, TOKEN to TEST_TOKEN, SRC to SRC))
        Mockito.`when`(xmlRpcProvider.createXmlRpcClient(ArgumentMatchers.anyString())).thenReturn(xmlRpcClient)
    }

    private fun givenAnOpenSubtitlesService() {
        openSubtitlesService = OpenSubtitlesServiceImpl(TEST_BASE_URL, xmlRpcProvider)
    }

    private fun whenServerInfoIsCalled() {
        serverInfo = openSubtitlesService.serverInfo()
    }

    private fun whenLoginIsCalled() {
        token = openSubtitlesService.login(arrayOf<Any>())
    }

    private fun whenSearchSubtitlesIsCalled() {
        subtitle = openSubtitlesService.searchSubtitles(TEST_TOKEN, arrayOf<Any>())
    }

    private fun thenOpenSubtitlesServerInfoShouldBeCalledAndStatusShouldBeOk() {
        Mockito.verify(xmlRpcClient, Mockito.only()).execute(ArgumentMatchers.eq(OpenSubtitlesMethod.SERVERINFO.methodName), ArgumentMatchers.eq(arrayOf<Any>()))
        assertEquals(serverInfo[STATUS], OK)
    }

    private fun thenOpenSubtitlesLoginShouldBeCalledAndShouldReturnToken() {
        Mockito.verify(xmlRpcClient, Mockito.only()).execute(ArgumentMatchers.eq(OpenSubtitlesMethod.LOGIN.methodName), ArgumentMatchers.eq(arrayOf<Any>()))
        assertEquals(token, TEST_TOKEN)
    }

    private fun thenOpenSubtitlesSearchSubtitlesShouldBeCalledAndResultShouldBeAValidSubtitle() {
        Mockito.verify(xmlRpcClient, Mockito.only()).execute(ArgumentMatchers.eq(OpenSubtitlesMethod.SEARCHSUBTITLES.methodName), ArgumentMatchers.eq(arrayOf<Any>()))
        assertEquals(subtitle[SRC], SRC)
    }
}
