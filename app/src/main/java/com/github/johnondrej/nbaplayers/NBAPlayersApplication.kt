package com.github.johnondrej.nbaplayers

import android.app.Application
import com.github.johnondrej.nbaplayers.features.playerdetail.di.playerDetailModule
import com.github.johnondrej.nbaplayers.features.playerlist.di.playerListModule
import com.github.johnondrej.nbaplayers.features.teamdetail.di.teamDetailModule
import com.github.johnondrej.nbaplayers.shared.networking.di.networkingModule
import org.koin.core.context.startKoin

class NBAPlayersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                networkingModule,
                playerListModule,
                playerDetailModule,
                teamDetailModule
            )
        }
    }
}
