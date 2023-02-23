package com.github.johnondrej.nbaplayers.features.playerlist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.johnondrej.nbaplayers.shared.entities.model.api.ApiPlayer
import com.github.johnondrej.nbaplayers.shared.networking.ApiService

class PlayerListPagingSource(
    private val apiService: ApiService,
    private val pageSize: Int
) : PagingSource<Int, ApiPlayer>() {

    override fun getRefreshKey(state: PagingState<Int, ApiPlayer>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiPlayer> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getPlayers(
                page = page,
                perPage = pageSize
            )

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
