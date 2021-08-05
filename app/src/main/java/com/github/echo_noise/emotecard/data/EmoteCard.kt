package com.github.echo_noise.emotecard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// classe que passa para o banco de dados as informações a serem gravadas
@Entity
data class EmoteCard (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val bgColor: Int,
    val icon: Int
)