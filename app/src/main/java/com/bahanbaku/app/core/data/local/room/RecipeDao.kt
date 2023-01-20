package com.bahanbaku.app.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bahanbaku.app.core.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE recipeId = :recipeId")
    fun getRecipeById(recipeId: String): Flow<RecipeEntity>

    @Query("DELETE FROM recipe")
    fun deleteAllRecipes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)
}