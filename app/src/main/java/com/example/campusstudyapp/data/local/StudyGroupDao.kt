package com.example.campusstudyapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: StudyGroupEntity)

    @Query("SELECT * FROM study_groups ORDER BY id DESC")
    fun getAllGroups(): Flow<List<StudyGroupEntity>>
}
