package com.github.echo_noise.emotecard.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// interface de chamadas ao banco
@Dao
interface EmoteCardDao {
    @Query("SELECT * FROM EmoteCard")
    fun getAll(): LiveData<List<EmoteCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(emoteCard: EmoteCard)
}