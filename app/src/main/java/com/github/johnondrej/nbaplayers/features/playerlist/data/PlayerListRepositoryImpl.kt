package com.github.johnondrej.nbaplayers.features.playerlist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.github.johnondrej.nbaplayers.features.playerlist.domain.PlayerListRepository
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.Player
import com.github.johnondrej.nbaplayers.shared.entities.model.domain.toDomain
import com.github.johnondrej.nbaplayers.shared.networking.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerListRepositoryImpl(
    private val apiService: ApiService,
) : PlayerListRepository {

    override fun observePagedPlayerList(): Flow<PagingData<Player>> {
        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                PlayerListPagingSource(
                    apiService = apiService,
                    pageSize = PAGE_SIZE
                )
            }
        )

        return pager.flow.map { pagingData ->
            pagingData.map { apiPlayer ->
                apiPlayer.toDomain()
            }
        }
    }

    companion object {

        private const val PAGE_SIZE = 35
    }
}
