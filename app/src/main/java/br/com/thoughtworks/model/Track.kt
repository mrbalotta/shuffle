package br.com.thoughtworks.model

import java.io.Serializable

data class Track(
    val id: Long,
    val artistName: String = "",
    val wrapperType: String = "",
    val primaryGenreName: String = "",
    val artistId: Int = 0,
    val artworkUrl: String = "",
    val collectionId: Int = 0,
    val collectionName: String = "",
    val country: String = "",
    val releaseDate: String = "",
    val trackCensoredName: String = "",
    val trackExplicitness: String = "",
    val trackName: String = "",
    val trackTimeMillis: Int = 0
): Serializable

