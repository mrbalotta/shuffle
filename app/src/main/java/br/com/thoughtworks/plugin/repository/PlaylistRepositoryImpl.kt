package br.com.thoughtworks.plugin.repository

import br.com.thoughtworks.feature.playlist.business.dto.TrackList
import br.com.thoughtworks.feature.playlist.business.interactor.PlaylistRepository
import br.com.thoughtworks.plugin.api.RetrofitAPI
import br.com.thoughtworks.plugin.api.RetrofitAPIBuilder
import okhttp3.logging.HttpLoggingInterceptor

class PlaylistRepositoryImpl(private val baseUrl: String): BaseRepository(),
    PlaylistRepository {
    override fun getPreferredArtists(): List<Long> {
        return listOf(909253L, 1171421960L, 358714030L, 1419227L, 264111789L)
    }

    override fun getTracks(ids: List<Long>): TrackList {
        val call = getService().lookup(ids.joinToString(","))
        val trackList = getBodyOrThrow(call)
        val tracks =  trackList.results.filter { it.wrapperType.toLowerCase() == "track" }

        return trackList.copy(results = tracks)
    }

    override fun getService(): RetrofitAPI {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return RetrofitAPIBuilder(baseUrl, listOf(interceptor)).build()
    }
}