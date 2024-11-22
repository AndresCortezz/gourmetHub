package com.example.recipesapp.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesapp.databinding.FragmentShoppingBinding

class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val shoppingRecipes = listOf(
            ShoppingRecipe("Pizza Margherita", "Delicious classic pizza with tomatoes and mozzarella", "https://example.com/image1.jpg"),
            ShoppingRecipe("Pasta Carbonara", "Creamy pasta with bacon and parmesan", "https://example.com/image2.jpg"),
            ShoppingRecipe("Caesar Salad", "Crisp lettuce with Caesar dressing and croutons", "https://example.com/image3.jpg")
        )

        val adapter = ShoppingAdapter(shoppingRecipes) { recipe ->
            // Acción al hacer clic en el botón de favoritos
            // Ejemplo: imprimir el título de la receta
            println("Clicked on favorite: ${recipe.title}")
        }

        binding.recyclerViewShopping.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        return root
    }


    private fun onStarClick(recipe: ShoppingRecipe) {
        Toast.makeText(context, "${recipe.title} added to favorites!", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
