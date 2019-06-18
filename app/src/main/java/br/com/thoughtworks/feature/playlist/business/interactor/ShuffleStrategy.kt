package br.com.thoughtworks.feature.playlist.business.interactor

import br.com.thoughtworks.model.Track

interface ShuffleStrategy {
    fun shuffle(list: MutableList<Track>)
}