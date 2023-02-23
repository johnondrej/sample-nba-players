package com.github.johnondrej.nbaplayers.shared.entities.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ApiPlayerListResponse(
    val data: List<ApiPlayer>
)
