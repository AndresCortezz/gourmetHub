package com.example.recipesapp.ui.shopping


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import java.net.URL
import kotlin.concurrent.thread

class ShoppingAdapter(
    private val recipes: List<ShoppingRecipe>,
    private val onStarClick: (ShoppingRecipe) -> Unit
) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe, onStarClick)
    }

    override fun getItemCount(): Int = recipes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.textTitle)
        private val description: TextView = itemView.findViewById(R.id.textDescription)
        private val image: ImageView = itemView.findViewById(R.id.imageRecipe)
        private val starButton: ImageButton = itemView.findViewById(R.id.btnStar)

        fun bind(recipe: ShoppingRecipe, onStarClick: (ShoppingRecipe) -> Unit) {
            title.text = recipe.title
            description.text = recipe.description

            // Cargar imagen desde URL
            thread {
                try {
                    val url = URL(recipe.imageUrl)
                    val bitmap: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    image.post {
                        image.setImageBitmap(bitmap)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            // Configurar el botón de estrella
            starButton.setOnClickListener {
                onStarClick(recipe)
            }
        }
    }
}
