package com.github.johnondrej.nbaplayers.shared.networking

import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiPlayer
import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiPlayerListResponse
import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiTeam
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("players")
    suspend fun getPlayers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiPlayerListResponse

    @GET("players/{playerId}")
    suspend fun getPlayer(
        @Path("playerId") playerId: Int
    ): ApiPlayer

    @GET("teams/{teamId}")
    suspend fun getTeam(
        @Path("teamId") teamId: Int
    ): ApiTeam
}
