package com.bahanbaku.app.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bahanbaku.app.core.data.local.entity.ProfileEntity
import io.reactivex.Completable
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile LIMIT 1")
    fun getProfile(): Flow<List<ProfileEntity>>

    @Query("DELETE FROM profile")
    fun deleteAllProfile()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity): Completable
}