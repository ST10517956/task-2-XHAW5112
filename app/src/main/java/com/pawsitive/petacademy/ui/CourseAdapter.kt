package com.pawsitive.petacademy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pawsitive.petacademy.databinding.ItemCourseBinding
import com.pawsitive.petacademy.model.Course

class CourseAdapter(
    private val courses: List<Course>,
    private val onClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.binding.textName.text = course.name
        holder.binding.textMeta.text = "R${course.fee} · ${course.duration}"
        holder.binding.imageCourse.setImageResource(course.imageRes)
        holder.itemView.setOnClickListener { onClick(course) }
    }

    override fun getItemCount(): Int = courses.size
}
