package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.DeleteFavoriteResponse
import com.bahanbaku.app.core.data.remote.response.FavoriteItem
import com.bahanbaku.app.core.data.remote.response.PostAddFavoriteResponse
import com.bahanbaku.app.core.data.remote.response.RecipeDetailItem
import com.bahanbaku.app.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {
    fun getNewRecipes(token: String): Flow<Resource<List<Recipe>>>
    fun searchRecipe(token: String, query: String): Flow<Resource<List<Recipe>>>
    fun getRecipeById(token: String, id: String): Flow<Resource<RecipeDetailItem>>
    fun getRecipesByTag(token: String, tag: String): Flow<Resource<List<Recipe>>>
    fun getFavorites(token: String): Flow<Resource<List<FavoriteItem>>>
    fun addFavorites(token: String, id: String): Flow<Resource<PostAddFavoriteResponse>>
    fun deleteFavorites(token: String, position: Int): Flow<Resource<DeleteFavoriteResponse>>
    fun deleteFavorites(token: String, id: String): Flow<Resource<DeleteFavoriteResponse>>
    fun checkIfRecipeBookmarked(token: String, id: String): Flow<Boolean>
}