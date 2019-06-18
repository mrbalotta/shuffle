package br.com.thoughtworks.feature.playlist.business.interactor

import br.com.thoughtworks.base.business.interactor.Output
import br.com.thoughtworks.base.business.interactor.UseCase
import br.com.thoughtworks.base.business.interactor.ValueOutput
import br.com.thoughtworks.feature.playlist.business.dto.TrackList

class GetTracksUseCase(private val repository: PlaylistRepository): UseCase<Nothing, TrackList>() {

    override fun execute(param: Nothing?): Output<TrackList> {
        val artistIds = repository.getPreferredArtists()
        val tracks = repository.getTracks(artistIds)
        return ValueOutput(tracks)
    }
}