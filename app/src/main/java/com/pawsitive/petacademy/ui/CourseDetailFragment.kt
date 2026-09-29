package com.pawsitive.petacademy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pawsitive.petacademy.databinding.FragmentCourseDetailBinding
import com.pawsitive.petacademy.model.CourseRepository

class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseId = arguments?.getString("courseId")
        val course = CourseRepository.byId(courseId ?: "") ?: CourseRepository.courses.first()

        binding.textCourseName.text = course.name
        binding.textCourseMeta.text = "Fee: R${course.fee} · ${course.duration}"
        binding.textIncludes.text = course.includes.joinToString("\n") { "• $it" }
        binding.imageCourseDetail.setImageResource(course.imageRes)

        binding.btnAddBooking.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "${course.name} added to booking",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
