package com.github.johnondrej.nbaplayers.features.playerdetail.di

import com.github.johnondrej.nbaplayers.features.playerdetail.data.PlayerDetailRepositoryImpl
import com.github.johnondrej.nbaplayers.features.playerdetail.domain.GetPlayerDetailUseCase
import com.github.johnondrej.nbaplayers.features.playerdetail.domain.GetPlayerDetailUseCaseImpl
import com.github.johnondrej.nbaplayers.features.playerdetail.domain.PlayerDetailRepository
import com.github.johnondrej.nbaplayers.features.playerdetail.presentation.PlayerDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerDetailModule = module {

    single<PlayerDetailRepository> {
        PlayerDetailRepositoryImpl(
            apiService = get()
        )
    }

    factory<GetPlayerDetailUseCase> {
        GetPlayerDetailUseCaseImpl(
            playerDetailRepository = get()
        )
    }

    viewModel { parameters ->
        PlayerDetailViewModel(
            playerId = parameters.get(),
            getPlayerDetail = get()
        )
    }
}
