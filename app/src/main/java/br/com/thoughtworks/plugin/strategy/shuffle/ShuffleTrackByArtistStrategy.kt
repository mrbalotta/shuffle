package br.com.thoughtworks.plugin.strategy.shuffle

import br.com.thoughtworks.feature.playlist.business.interactor.ShuffleStrategy
import br.com.thoughtworks.model.Track

class ShuffleTrackByArtistStrategy: AbstractShuffleStrategy<Track>(), ShuffleStrategy {
    override fun groupBy(it: Track): Any {
        return it.artistId
    }

    override fun equalsByCriteria(left: Track, right: Track): Boolean {
        return left.artistId == right.artistId
    }
}