package com.github.echo_noise.emotecard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.echo_noise.emotecard.data.EmoteCard
import com.github.echo_noise.emotecard.data.EmoteCardRepository

class MainViewModel(private val emoteCardRepository: EmoteCardRepository) : ViewModel() {

    fun insert (emoteCard: EmoteCard) {
        emoteCardRepository.insert(emoteCard)
    }

    fun getAll(): LiveData<List<EmoteCard>> {
        return emoteCardRepository.getAll()
    }

    fun deleteCard(emoteCard: EmoteCard) {
        return emoteCardRepository.deleteCard(emoteCard)
    }
}

class MainViewModelFactory(private val repository: EmoteCardRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}