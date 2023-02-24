package com.github.johnondrej.nbaplayers.features.teamdetail.di

import com.github.johnondrej.nbaplayers.features.teamdetail.data.TeamDetailRepositoryImpl
import com.github.johnondrej.nbaplayers.features.teamdetail.domain.GetTeamDetailUseCase
import com.github.johnondrej.nbaplayers.features.teamdetail.domain.GetTeamDetailUseCaseImpl
import com.github.johnondrej.nbaplayers.features.teamdetail.domain.TeamDetailRepository
import com.github.johnondrej.nbaplayers.features.teamdetail.presentation.TeamDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teamDetailModule = module {

    single<TeamDetailRepository> {
        TeamDetailRepositoryImpl(
            apiService = get()
        )
    }

    factory<GetTeamDetailUseCase> {
        GetTeamDetailUseCaseImpl(
            teamDetailRepository = get()
        )
    }

    viewModel { parameters ->
        TeamDetailViewModel(
            teamId = parameters.get(),
            getTeamDetail = get()
        )
    }
}
