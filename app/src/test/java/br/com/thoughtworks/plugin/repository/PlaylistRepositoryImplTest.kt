package br.com.thoughtworks.plugin.repository

import br.com.thoughtworks.base.business.exception.HttpException
import com.nhaarman.mockitokotlin2.spy
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class PlaylistRepositoryImplTest {
    private val server = MockWebServer()
    private lateinit var repository: PlaylistRepositoryImpl

    @Before
    fun setup() {
        server.start()
        val baseUrl = "/"
        val httpUrl = server.url(baseUrl)
        repository = spy(PlaylistRepositoryImpl(httpUrl.toString()))
    }

    @Test(expected = HttpException::class)
    fun givenGetTracksCall_whenResponseHasHttpErrorCode_throwHttpException() {
        val ids = listOf(1L,2L,3L)
        val response = MockResponse().apply {setResponseCode(400)}
        server.enqueue(response)

        repository.getTracks(ids)
    }

    @After
    fun teardown() {
        server.shutdown()
    }
}