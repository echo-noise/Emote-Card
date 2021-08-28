package com.github.echo_noise.emotecard.data

import androidx.lifecycle.LiveData
import androidx.room.*

// interface de chamadas ao banco
@Dao
interface EmoteCardDao {
    @Query("SELECT * FROM EmoteCard")
    fun getAll(): LiveData<List<EmoteCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(emoteCard: EmoteCard)

    @Delete
    fun deleteCard(emoteCard: EmoteCard)
}