package com.example.recipesapp.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recipesapp.databinding.FragmentFeedbackBinding

class FeedbackFragment : Fragment() {

    // Declarar la variable para el binding
    private var _binding: FragmentFeedbackBinding? = null
    private val binding get() = _binding!!

    private lateinit var feedbackEditText: EditText
    private lateinit var submitFeedbackButton: Button
    private lateinit var confirmationMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout usando el ViewBinding generado
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        val root: View = binding.root

        feedbackEditText = binding.feedbackEditText
        submitFeedbackButton = binding.submitFeedbackButton
        confirmationMessage = binding.confirmationMessage

        submitFeedbackButton.setOnClickListener {
            val feedbackText = feedbackEditText.text.toString()
            if (feedbackText.isNotEmpty()) {
                // Simula el envío del feedback (puedes conectarlo con una API)
                confirmationMessage.text = "Thank you for your feedback!"
                confirmationMessage.setTextColor(resources.getColor(android.R.color.holo_red_dark))
                feedbackEditText.setText("")
            } else {
                confirmationMessage.text = "Please enter your feedback before submitting."
                confirmationMessage.setTextColor(resources.getColor(android.R.color.holo_red_light))
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Limpiar la referencia del binding para evitar fugas de memoria
    }
}
