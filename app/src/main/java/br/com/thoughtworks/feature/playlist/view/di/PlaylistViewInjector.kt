package br.com.thoughtworks.feature.playlist.view.di

import br.com.thoughtworks.base.view.dialog.SimpleDialogStrategy

interface PlaylistViewInjector {
    companion object {
        lateinit var injector: PlaylistViewInjector
    }

    val dialogStrategy: SimpleDialogStrategy
}