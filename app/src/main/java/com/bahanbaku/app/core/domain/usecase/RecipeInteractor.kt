package com.bahanbaku.app.core.domain.usecase

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.DeleteFavoriteResponse
import com.bahanbaku.app.core.data.remote.response.FavoriteItem
import com.bahanbaku.app.core.data.remote.response.PostAddFavoriteResponse
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeInteractor(private val recipeRepository: IRecipeRepository) : RecipeUseCase {
    override fun getNewRecipes(token: String) = recipeRepository.getNewRecipes(token)

    override fun searchRecipe(token: String, query: String) =
        recipeRepository.searchRecipe(token, query)

    override fun getRecipeById(token: String, id: String) =
        recipeRepository.getRecipeById(token, id)

    override fun getRecipesByTag(token: String, tag: String): Flow<Resource<List<Recipe>>> =
        recipeRepository.getRecipeByTag(token, tag)

    override fun getFavorites(token: String): Flow<Resource<List<FavoriteItem>>> =
        recipeRepository.getFavorites(token)

    override fun addFavorites(
        token: String,
        id: String
    ): Flow<Resource<PostAddFavoriteResponse>> =
        recipeRepository.addFavorites(token, id)

    override fun deleteFavorites(
        token: String,
        position: Int
    ): Flow<Resource<DeleteFavoriteResponse>> =
        recipeRepository.deleteFavorites(token, position)

    override fun deleteFavorites(
        token: String,
        id: String
    ): Flow<Resource<DeleteFavoriteResponse>> = recipeRepository.deleteFavorites(token, id)

    override fun checkIfRecipeBookmarked(token: String, id: String): Flow<Boolean> =
        recipeRepository.checkIfRecipeFavorited(token, id)
}