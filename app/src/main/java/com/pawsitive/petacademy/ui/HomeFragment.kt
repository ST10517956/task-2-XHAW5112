package com.pawsitive.petacademy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pawsitive.petacademy.R
import com.pawsitive.petacademy.databinding.FragmentHomeBinding
import com.pawsitive.petacademy.model.CourseRepository

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Wireframe shows the first four courses as "Featured" on the home page.
        val featured = CourseRepository.courses.take(4)
        binding.recyclerFeatured.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerFeatured.adapter = CourseAdapter(featured) { course ->
            val bundle = Bundle().apply { putString("courseId", course.id) }
            findNavController().navigate(R.id.courseDetailFragment, bundle)
        }

        binding.btnBookNow.setOnClickListener {
            findNavController().navigate(R.id.feesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
