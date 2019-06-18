package br.com.thoughtworks.feature.playlist.gateway.mvvm

import br.com.thoughtworks.base.business.interactor.ChainedUseCase
import br.com.thoughtworks.base.business.interactor.Output
import br.com.thoughtworks.base.business.interactor.ValueOutput
import br.com.thoughtworks.base.gateway.mvvm.BaseViewModel
import br.com.thoughtworks.feature.playlist.business.dto.ShuffledList
import br.com.thoughtworks.feature.playlist.business.dto.TrackList
import br.com.thoughtworks.feature.playlist.business.interactor.GetTracksUseCase
import br.com.thoughtworks.feature.playlist.business.interactor.ShufflerUseCase
import br.com.thoughtworks.feature.playlist.gateway.di.PlaylistInjector

class PlaylistViewModel(
    private val mockTracksUseCase: GetTracksUseCase? = null,
    private val mockShufflerUseCase: ShufflerUseCase? = null
): BaseViewModel() {
    companion object {
        const val PLAYLIST_CHANNEL = "playlist"
    }

    private val tracksUseCase by lazy { injectTracksUseCase() }

    private val shufflerUseCase by lazy { injectShufflerUseCase() }

    private var isShuffling = false
    private lateinit var shuffledList: TrackList
    var showingConnectionDialog = false
    override fun declareChannels() {
        availableChannels.add(PLAYLIST_CHANNEL)
    }
    fun getTracks() {
        dispatch(PLAYLIST_CHANNEL, ChainedUseCase(tracksUseCase, shufflerUseCase))
    }

    override fun postValue(channelName: String, output: Output<*>) {
        if(output.value is TrackList) shuffledList = output.value
        super.postValue(channelName, output)
    }

    fun shuffle() {
        if(!isShuffling && ::shuffledList.isInitialized) {
            isShuffling = true
            val job = shufflerUseCase.dispatch(shuffledList) { postShuffledList(it) }
            compositeJobDisposable.add(job)
        }
    }

    private fun postShuffledList(output: Output<TrackList>) {
        if(output.isSuccess()) {
            val shuffledList = ShuffledList(output.value?.results)
            postValue(PLAYLIST_CHANNEL, ValueOutput(shuffledList))
        } else {
            postValue(PLAYLIST_CHANNEL, output)
        }

        isShuffling = false
    }

    private fun injectTracksUseCase(): GetTracksUseCase {
        return mockTracksUseCase ?: PlaylistInjector.injector.tracksUseCase
    }

    private fun injectShufflerUseCase(): ShufflerUseCase {
        return mockShufflerUseCase ?: PlaylistInjector.injector.shufflerUseCase
    }
}
