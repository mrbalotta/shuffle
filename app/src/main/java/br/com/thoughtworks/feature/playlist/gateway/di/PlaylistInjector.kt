package br.com.thoughtworks.feature.playlist.gateway.di

import br.com.thoughtworks.feature.playlist.business.interactor.GetTracksUseCase
import br.com.thoughtworks.feature.playlist.business.interactor.ShufflerUseCase

interface PlaylistInjector {
    companion object {
        lateinit var injector: PlaylistInjector
    }

    val shufflerUseCase: ShufflerUseCase
    val tracksUseCase: GetTracksUseCase
}