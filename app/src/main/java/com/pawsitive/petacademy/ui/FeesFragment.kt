package com.pawsitive.petacademy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pawsitive.petacademy.R
import com.pawsitive.petacademy.databinding.FragmentFeesBinding
import com.pawsitive.petacademy.databinding.ItemCourseCheckboxBinding
import com.pawsitive.petacademy.model.CourseRepository
import java.util.Locale

class FeesFragment : Fragment() {

    private var _binding: FragmentFeesBinding? = null
    private val binding get() = _binding!!

    // course.id -> checkbox binding, so we can read state on Calculate
    private val checkboxes = mutableMapOf<String, ItemCourseCheckboxBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CourseRepository.courses.forEach { course ->
            val row = ItemCourseCheckboxBinding.inflate(
                layoutInflater, binding.checkboxContainer, false
            )
            row.textLabel.text = "${course.name} - R${course.fee}"
            binding.checkboxContainer.addView(row.root)
            checkboxes[course.id] = row
        }

        binding.btnCalculate.setOnClickListener { calculateTotal() }
    }

    private fun calculateTotal() {
        val selected = CourseRepository.courses.filter { course ->
            checkboxes[course.id]?.checkboxCourse?.isChecked == true
        }

        if (selected.isEmpty()) {
            binding.textTotal.text = getString(R.string.select_course_error)
            return
        }

        val subtotal = selected.sumOf { it.fee }
        val rate = CourseRepository.discountRate(selected.size)
        val discount = subtotal * rate
        val total = subtotal - discount

        binding.textTotal.text = String.format(
            Locale.getDefault(),
            "Courses: %d\nSubtotal: R%.2f\nDiscount (%.0f%%): -R%.2f\nTotal: R%.2f",
            selected.size, subtotal.toDouble(), rate * 100, discount, total
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        checkboxes.clear()
        _binding = null
    }
}
