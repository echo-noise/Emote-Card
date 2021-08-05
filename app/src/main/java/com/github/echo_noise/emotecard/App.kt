package com.github.echo_noise.emotecard

import android.app.Application
import com.github.echo_noise.emotecard.data.AppDatabase
import com.github.echo_noise.emotecard.data.EmoteCardRepository

class App : Application() {
    // initializes database
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { EmoteCardRepository(database.emoteDao())}
}