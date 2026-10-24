package com.example.campusstudyapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study_groups")
data class StudyGroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val category: String,
    val maxMembers: Int,
    val description: String
)
