package br.com.thoughtworks.plugin.main

import android.app.Application
import br.com.thoughtworks.BuildConfig
import br.com.thoughtworks.base.business.interactor.ConnectivityStrategy
import br.com.thoughtworks.feature.playlist.business.interactor.GetTracksUseCase
import br.com.thoughtworks.feature.playlist.business.interactor.PlaylistRepository
import br.com.thoughtworks.plugin.strategy.shuffle.ShuffleTrackByArtistStrategy
import br.com.thoughtworks.feature.playlist.business.interactor.ShufflerUseCase
import br.com.thoughtworks.feature.playlist.gateway.di.PlaylistInjector
import br.com.thoughtworks.base.gateway.di.ConnectivityInjector
import br.com.thoughtworks.base.view.dialog.SimpleDialogStrategy
import br.com.thoughtworks.feature.playlist.view.di.PlaylistViewInjector
import br.com.thoughtworks.plugin.repository.PlaylistRepositoryImpl
import br.com.thoughtworks.plugin.strategy.connectivity.ConnectivityStrategyImpl
import br.com.thoughtworks.plugin.view.dialog.DialogStrategyImpl

class BaseApplication: Application() {
    private val connectivityStrategy by lazy {
        ConnectivityStrategyImpl(
            context = this
        )
    }
    private val playlistRepository by lazy { createPlaylistRepository() }
    private val baseApiUrl = BuildConfig.ENDPOINT

    override fun onCreate() {
        super.onCreate()
        injectFeaturePlaylist()
        injectFeatureSplash()
    }

    private fun injectFeatureSplash() {
        ConnectivityInjector.injector = object:
            ConnectivityInjector {
            override val connectivityStrategy: ConnectivityStrategy
                get() = this@BaseApplication.connectivityStrategy
        }
    }

    private fun injectFeaturePlaylist() {
        PlaylistInjector.injector = object: PlaylistInjector {
            override val tracksUseCase: GetTracksUseCase
                get() = GetTracksUseCase(playlistRepository)
            override val shufflerUseCase: ShufflerUseCase
                get() = ShufflerUseCase(ShuffleTrackByArtistStrategy())
        }

        PlaylistViewInjector.injector = object: PlaylistViewInjector {
            override val dialogStrategy: SimpleDialogStrategy
                get() = DialogStrategyImpl()
        }
    }

    private fun createPlaylistRepository(): PlaylistRepository {
        return PlaylistRepositoryImpl(baseApiUrl)
    }
}