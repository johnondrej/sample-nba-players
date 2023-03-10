package com.github.johnondrej.nbaplayers.features.playerlist.di

import com.github.johnondrej.nbaplayers.features.playerlist.data.PlayerListRepositoryImpl
import com.github.johnondrej.nbaplayers.features.playerlist.domain.ObservePlayerListUseCase
import com.github.johnondrej.nbaplayers.features.playerlist.domain.ObservePlayerListUseCaseImpl
import com.github.johnondrej.nbaplayers.features.playerlist.domain.PlayerListRepository
import com.github.johnondrej.nbaplayers.features.playerlist.presentation.PlayerListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val playerListModule = module {

    single<PlayerListRepository> {
        PlayerListRepositoryImpl(
            apiService = get()
        )
    }

    factory<ObservePlayerListUseCase> {
        ObservePlayerListUseCaseImpl(
            playerListRepository = get()
        )
    }

    viewModelOf(::PlayerListViewModel)
}
