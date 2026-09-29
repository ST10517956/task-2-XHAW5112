package com.pawsitive.petacademy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawsitive.petacademy.R
import com.pawsitive.petacademy.databinding.FragmentOverviewBinding
import com.pawsitive.petacademy.model.CourseRepository

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerOverview.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerOverview.adapter = CourseAdapter(CourseRepository.courses) { course ->
            val bundle = Bundle().apply { putString("courseId", course.id) }
            findNavController().navigate(R.id.courseDetailFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
