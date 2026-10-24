package com.example.campusstudyapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.campusstudyapp.data.local.AppDatabase
import com.example.campusstudyapp.data.local.TaskEntity
import com.example.campusstudyapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Room Database
        database = AppDatabase.getDatabase(this)

        // Navigation
        binding.btnViewGroups.setOnClickListener {
            startActivity(Intent(this, StudyGroupsActivity::class.java))
        }

        binding.btnViewTasks.setOnClickListener {
            startActivity(Intent(this, TasksActivity::class.java))
        }

        // Test inserting and reading from Room database
        addSampleTaskAndVerify()
    }

    private fun addSampleTaskAndVerify() {
        lifecycleScope.launch(Dispatchers.IO) {
            val sampleTask = TaskEntity(
                groupId = 1,
                userId = "2025621097",
                taskTitle = "Midterm Android Project Submission",
                dueDate = "2026-10-30",
                status = "In Progress"
            )

            try {
                database.taskDao().insertTask(sampleTask)

                withContext(Dispatchers.Main) {
                    binding.tvDbStatus.text = "Database Status: Connected & Sample Task Saved Successfully!"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.tvDbStatus.text = "Database Error: ${e.localizedMessage}"
                }
            }
        }
    }
}
