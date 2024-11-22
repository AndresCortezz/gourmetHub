package com.example.recipesapp.ui.favorites

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesapp.R
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class FavoriteRecipesAdapter(
    private val favoriteRecipes: List<FavoriteRecipe>,
    private val onRemoveClick: (FavoriteRecipe) -> Unit
) : RecyclerView.Adapter<FavoriteRecipesAdapter.FavoriteRecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteRecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        val recipe = favoriteRecipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = favoriteRecipes.size

    inner class FavoriteRecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textDescription)
        private val recipeImageView: ImageView = itemView.findViewById(R.id.imageRecipe)
        private val removeFavoriteButton: ImageButton = itemView.findViewById(R.id.btnRemoveFavorite)

        fun bind(recipe: FavoriteRecipe) {
            titleTextView.text = recipe.title
            descriptionTextView.text = recipe.description
            loadImageFromUrl(recipe.imageUrl)

            removeFavoriteButton.setOnClickListener {
                onRemoveClick(recipe)
            }
        }

        private fun loadImageFromUrl(url: String) {
            // Realizamos la carga de la imagen desde la URL usando AsyncTask
            ImageLoadTask(recipeImageView).execute(url)
        }
    }

    // Clase AsyncTask para cargar la imagen desde una URL
    private class ImageLoadTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg params: String?): Bitmap? {
            val imageUrl = params[0]
            return try {
                val url = URL(imageUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.inputStream.use {
                    BitmapFactory.decodeStream(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if (result != null) {
                imageView.setImageBitmap(result)
            } else {
                imageView.setImageResource(R.drawable.placeholder_image) // Imagen por defecto en caso de error
            }
        }
    }
}
