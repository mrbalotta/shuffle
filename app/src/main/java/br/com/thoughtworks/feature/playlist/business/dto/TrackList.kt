package br.com.thoughtworks.feature.playlist.business.dto

import br.com.thoughtworks.model.Track
import java.io.Serializable

data class TrackList(val resultCount: Int, val results: List<Track>): Serializable

data class ShuffledList(val results: List<Track>?)