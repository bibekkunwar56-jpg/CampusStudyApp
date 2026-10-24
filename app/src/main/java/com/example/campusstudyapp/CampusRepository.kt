package com.example.campusstudyapp

import com.example.campusstudyapp.data.local.StudyGroupDao
import com.example.campusstudyapp.data.local.StudyGroupEntity
import com.example.campusstudyapp.data.local.TaskDao
import com.example.campusstudyapp.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class CampusRepository(private val taskDao: TaskDao, private val studyGroupDao: StudyGroupDao) {

    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()
    val allGroups: Flow<List<StudyGroupEntity>> = studyGroupDao.getAllGroups()

    suspend fun insertTask(task: TaskEntity) {
        taskDao.insertTask(task)
    }

    suspend fun insertGroup(group: StudyGroupEntity) {
        studyGroupDao.insertGroup(group)
    }
}
