package com.example.recipesapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Crear lista de notificaciones de ejemplo
        val notificationsList = listOf(
            "New Recipe Available - Check out the new recipes available in the app!",
            "Update Available - There's a new update for the app, don't miss it!",
            "Promo Code - Use code 'SAVE10' to get a 10% discount on your next order."
        )

        // Contenedor donde se agregarán las notificaciones
        val notificationsContainer: LinearLayout = binding.notificationsContainer

        // Agregar notificaciones al contenedor
        for (notification in notificationsList) {
            val textView = TextView(requireContext())
            textView.text = notification
            textView.textSize = 16f
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            textView.setPadding(16, 16, 16, 16)
            textView.setBackgroundResource(R.drawable.notification_background)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 16, 0, 16) // Margen entre notificaciones
            textView.layoutParams = params
            notificationsContainer.addView(textView)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
