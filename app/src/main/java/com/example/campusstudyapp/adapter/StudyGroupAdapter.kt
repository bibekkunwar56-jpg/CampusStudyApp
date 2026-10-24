package com.example.campusstudyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.campusstudyapp.data.local.StudyGroupEntity
import com.example.campusstudyapp.databinding.ItemStudyGroupBinding

class StudyGroupAdapter : ListAdapter<StudyGroupEntity, StudyGroupAdapter.StudyGroupViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyGroupViewHolder {
        val binding = ItemStudyGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudyGroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudyGroupViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StudyGroupViewHolder(private val binding: ItemStudyGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(group: StudyGroupEntity) {
            binding.tvGroupTitle.text = group.title
            binding.tvGroupCategory.text = group.category
            binding.tvGroupCapacity.text = "Capacity: ${group.maxMembers}"
            binding.tvGroupDescription.text = group.description
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<StudyGroupEntity>() {
        override fun areItemsTheSame(oldItem: StudyGroupEntity, newItem: StudyGroupEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StudyGroupEntity, newItem: StudyGroupEntity): Boolean {
            return oldItem == newItem
        }
    }
}
