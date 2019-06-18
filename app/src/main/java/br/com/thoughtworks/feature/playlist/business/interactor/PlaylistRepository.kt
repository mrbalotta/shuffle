package br.com.thoughtworks.feature.playlist.business.interactor

import br.com.thoughtworks.feature.playlist.business.dto.TrackList

interface PlaylistRepository {
    fun getTracks(ids: List<Long>): TrackList
    fun getPreferredArtists(): List<Long>
}