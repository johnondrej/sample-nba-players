package com.github.johnondrej.nbaplayers.shared.networking

import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiPlayerListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("players")
    suspend fun getPlayers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiPlayerListResponse
}
