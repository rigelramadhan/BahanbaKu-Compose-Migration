package com.bahanbaku.app.core.data.repository

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.local.datasource.LocalDataSource
import com.bahanbaku.app.core.data.remote.datasource.RemoteDataSource
import com.bahanbaku.app.core.data.remote.response.DeleteFavoriteResponse
import com.bahanbaku.app.core.data.remote.response.FavoriteItem
import com.bahanbaku.app.core.data.remote.response.PostAddFavoriteResponse
import com.bahanbaku.app.core.data.remote.response.RecipeDetailItem
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.repository.IRecipeRepository
import com.bahanbaku.app.core.utils.AppExecutors
import io.reactivex.Flowable

class RecipeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRepository {
    override fun getNewRecipes(token: String): Flowable<Resource<List<Recipe>>> =
        remoteDataSource.getNewRecipes(token)

    override fun searchRecipe(token: String, query: String): Flowable<Resource<List<Recipe>>> =
        remoteDataSource.searchRecipe(token, query)

    override fun getRecipeById(token: String, id: String): Flowable<Resource<RecipeDetailItem>> =
        remoteDataSource.getRecipeById(token, id)

    override fun getRecipeByTag(token: String, tag: String): Flowable<Resource<List<Recipe>>> =
        remoteDataSource.getRecipesByTag(token, tag)

    override fun getFavorites(token: String): Flowable<Resource<List<FavoriteItem>>> =
        remoteDataSource.getFavorites(token)

    override fun addFavorites(
        token: String,
        id: String
    ): Flowable<Resource<PostAddFavoriteResponse>> = remoteDataSource.addFavorites(token, id)

    override fun deleteFavorites(
        token: String,
        position: Int
    ): Flowable<Resource<DeleteFavoriteResponse>> =
        remoteDataSource.deleteBookmarkByPosition(token, position)

    override fun deleteFavorites(
        token: String,
        id: String
    ): Flowable<Resource<DeleteFavoriteResponse>> =
        remoteDataSource.deleteFavorites(token, id)

    override fun checkIfRecipeFavorited(
        token: String,
        id: String
    ): Flowable<Boolean> = remoteDataSource.checkIfRecipeBookmarked(token, id)
}