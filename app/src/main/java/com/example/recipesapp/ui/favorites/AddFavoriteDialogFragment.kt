package com.example.recipesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.recipesapp.databinding.FragmentAddFavoriteDialogBinding


class AddFavoriteDialogFragment : DialogFragment() {

    private var _binding: FragmentAddFavoriteDialogBinding? = null
    private val binding get() = _binding!!

    var onFavoriteAdded: ((FavoriteRecipe) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFavoriteDialogBinding.inflate(inflater, container, false)

        binding.btnSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val imageUrl = binding.editTextImageUrl.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && imageUrl.isNotEmpty()) {
                val favoriteRecipe = FavoriteRecipe(title, description, imageUrl)
                onFavoriteAdded?.invoke(favoriteRecipe)
                dismiss()  // Cerrar el diálogo
            } else {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
