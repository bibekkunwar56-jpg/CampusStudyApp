package com.example.campusstudyapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campusstudyapp.adapter.StudyGroupAdapter
import com.example.campusstudyapp.data.local.StudyGroupEntity
import com.example.campusstudyapp.databinding.ActivityStudyGroupsBinding

class StudyGroupsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudyGroupsBinding
    private val viewModel: CampusViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudyGroupsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = StudyGroupAdapter()
        binding.rvStudyGroups.layoutManager = LinearLayoutManager(this)
        binding.rvStudyGroups.adapter = adapter

        viewModel.allGroups.observe(this) { groups ->
            adapter.submitList(groups)
        }

        binding.btnAddGroup.setOnClickListener {
            val newGroup = StudyGroupEntity(
                title = "Android Study Session",
                category = "Mobile Development",
                maxMembers = 5,
                description = "Preparing for the midterm project."
            )
            viewModel.insertGroup(newGroup)
            Toast.makeText(this, "New Study Group Added", Toast.LENGTH_SHORT).show()
        }
    }
}
