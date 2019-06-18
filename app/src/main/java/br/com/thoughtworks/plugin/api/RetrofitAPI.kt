package br.com.thoughtworks.plugin.api

import br.com.thoughtworks.feature.playlist.business.dto.TrackList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {
    @GET("lookup")
    fun lookup(@Query("id", encoded = true) ids: String,
               @Query("limit") limit: Int = 5): Call<TrackList>
}