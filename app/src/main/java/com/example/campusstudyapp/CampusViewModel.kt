package com.example.campusstudyapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.campusstudyapp.data.local.AppDatabase
import com.example.campusstudyapp.data.local.StudyGroupEntity
import com.example.campusstudyapp.data.local.TaskEntity
import kotlinx.coroutines.launch

class CampusViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CampusRepository = AppDatabase.getDatabase(application).let { db ->
        CampusRepository(db.taskDao(), db.studyGroupDao())
    }
    
    val allTasks = repository.allTasks.asLiveData()
    val allGroups = repository.allGroups.asLiveData()

    fun insertTask(task: TaskEntity) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun insertGroup(group: StudyGroupEntity) = viewModelScope.launch {
        repository.insertGroup(group)
    }
}
