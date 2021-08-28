package com.github.echo_noise.emotecard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch

// repositorio que faz a chamada do dao
class EmoteCardRepository(private val dao: EmoteCardDao) {

    fun insert(emoteCard: EmoteCard) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(emoteCard)
        }
    }

    fun getAll() = dao.getAll()
    fun deleteCard(emoteCard: EmoteCard) = dao.deleteCard(emoteCard)
}