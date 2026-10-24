package com.example.campusstudyapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusstudyapp.adapter.TaskAdapter
import com.example.campusstudyapp.data.local.TaskEntity
import com.example.campusstudyapp.databinding.ActivityTasksBinding

class TasksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTasksBinding
    private val viewModel: CampusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TaskAdapter()
        binding.rvTasks.layoutManager = LinearLayoutManager(this)
        binding.rvTasks.adapter = adapter

        viewModel.allTasks.observe(this) { tasks ->
            adapter.submitList(tasks)
        }

        binding.btnAddTask.setOnClickListener {
            val newTask = TaskEntity(
                groupId = 1,
                userId = "2025621097",
                taskTitle = "New Assignment",
                dueDate = "2026-11-15",
                status = "Pending"
            )
            viewModel.insertTask(newTask)
            Toast.makeText(this, "New Task Added", Toast.LENGTH_SHORT).show()
        }
    }
}
