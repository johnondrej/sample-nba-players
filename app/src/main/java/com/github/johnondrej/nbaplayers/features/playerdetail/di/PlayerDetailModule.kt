package com.github.johnondrej.nbaplayers.features.playerdetail.di

import com.github.johnondrej.nbaplayers.features.playerdetail.presentation.PlayerDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val playerDetailModule = module {

    viewModelOf(::PlayerDetailViewModel)
}
