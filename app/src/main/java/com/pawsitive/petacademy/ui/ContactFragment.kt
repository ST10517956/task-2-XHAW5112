package com.pawsitive.petacademy.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pawsitive.petacademy.R
import com.pawsitive.petacademy.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSend.setOnClickListener {
            val name = binding.inputName.text?.toString()?.trim().orEmpty()
            val email = binding.inputEmail.text?.toString()?.trim().orEmpty()
            val message = binding.inputMessage.text?.toString()?.trim().orEmpty()

            val valid = name.isNotEmpty() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                message.isNotEmpty()

            if (valid) {
                binding.textStatus.text = getString(R.string.contact_success)
                binding.textStatus.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.teal)
                )
                binding.inputName.text?.clear()
                binding.inputEmail.text?.clear()
                binding.inputPhone.text?.clear()
                binding.inputMessage.text?.clear()
            } else {
                binding.textStatus.text = getString(R.string.contact_error)
                binding.textStatus.setTextColor(0xFFB00020.toInt())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
