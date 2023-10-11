package com.katoklizm.myprojectsearchmoviecleanarchitecture.data.movie

import android.content.SharedPreferences

class LocalStorage(private val sharedPreferences: SharedPreferences) {
    private companion object {
        const val FAVORITES_KEY = "FAVORITES_KEY"
    }

    fun addToFavorites(moviesId: String) {
        changeFavorites(moviesId = moviesId, false)
    }

    fun removeFromFavorites(moviesId: String) {
        changeFavorites(moviesId = moviesId, true)
    }

    fun getSavedFavorites(): Set<String> {
        return sharedPreferences.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
    }

    private fun changeFavorites(moviesId: String, remove: Boolean) {
        val mutableSet = getSavedFavorites().toMutableSet()
        val modified = if (remove) mutableSet.remove(moviesId) else mutableSet.add(moviesId)
        if (modified) sharedPreferences.edit().putStringSet(FAVORITES_KEY, mutableSet).apply()
    }
}