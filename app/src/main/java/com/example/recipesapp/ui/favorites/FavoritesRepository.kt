package com.example.recipesapp.ui.favorites

import FavoritesDatabaseHelper
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log

class FavoritesRepository(context: Context) {

    private val dbHelper = FavoritesDatabaseHelper(context) // Cambiado a FavoritesDatabaseHelper

    // Agregar una receta favorita
    fun addFavorite(favoriteRecipe: FavoriteRecipe) {
        val db = dbHelper.writableDatabase // Usamos writableDatabase para escribir en la base de datos
        val contentValues = ContentValues().apply {
            put(FavoritesDatabaseHelper.COLUMN_TITLE, favoriteRecipe.title)
            put(FavoritesDatabaseHelper.COLUMN_DESCRIPTION, favoriteRecipe.description)
            put(FavoritesDatabaseHelper.COLUMN_IMAGE_URL, favoriteRecipe.imageUrl)
        }

        // Insertamos el nuevo favorito
        val result = db.insert(FavoritesDatabaseHelper.TABLE_NAME, null, contentValues)

        // Verificamos si la inserción fue exitosa
        if (result == -1L) {
            Log.e("FavoritesRepository", "Error al guardar el favorito")
        } else {
            Log.d("FavoritesRepository", "Favorito guardado con éxito")
        }

        db.close() // No olvides cerrar la base de datos
    }


    // Obtener todos los favoritos
    @SuppressLint("Range")
    fun getAllFavorites(): List<FavoriteRecipe> {
        val favoriteRecipes = mutableListOf<FavoriteRecipe>()
        val db = dbHelper.readableDatabase // Usamos readableDatabase aquí
        val cursor: Cursor = db.query(
            FavoritesDatabaseHelper.TABLE_NAME,
            null, null, null, null, null, null
        )

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(FavoritesDatabaseHelper.COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndex(FavoritesDatabaseHelper.COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(FavoritesDatabaseHelper.COLUMN_DESCRIPTION))
            val imageUrl = cursor.getString(cursor.getColumnIndex(FavoritesDatabaseHelper.COLUMN_IMAGE_URL))

            favoriteRecipes.add(FavoriteRecipe( title, description, imageUrl))
        }
        cursor.close()
        db.close() // Cerrar la base de datos
        return favoriteRecipes
    }

    // Eliminar un favorito
    fun removeFavorite(id: String) {
        val db = dbHelper.writableDatabase
        db.delete(
            FavoritesDatabaseHelper.TABLE_NAME,
            "${FavoritesDatabaseHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        db.close() // No olvides cerrar la base de datos
    }
}
