package com.example.recipesapp.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import com.example.recipesapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    // Simulamos un ViewModel para obtener las recetas favoritas
    private lateinit var favoriteRecipesAdapter: FavoriteRecipesAdapter

    // Datos de ejemplo, deberías obtenerlos de un ViewModel o repositorio
    private val favoriteRecipes = listOf(
        FavoriteRecipe("Recipe 1", "Delicious recipe 1", "https://example.com/image1.jpg"),
        FavoriteRecipe("Recipe 2", "Delicious recipe 2", "https://example.com/image2.jpg"),
        FavoriteRecipe("Recipe 3", "Delicious recipe 3", "https://example.com/image3.jpg")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Configuramos el RecyclerView
        favoriteRecipesAdapter = FavoriteRecipesAdapter(favoriteRecipes) { recipe ->
            removeFavorite(recipe)
        }

        binding.recyclerViewFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoriteRecipesAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun removeFavorite(recipe: FavoriteRecipe) {
        // Lógica para quitar la receta de los favoritos (ej. eliminar de la base de datos o lista)
        Toast.makeText(context, "${recipe.title} removed from favorites", Toast.LENGTH_SHORT).show()
    }
}
