package com.bahanbaku.app.core.data.repository

import com.bahanbaku.app.core.data.NetworkBoundResource
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.local.datasource.LocalDataSource
import com.bahanbaku.app.core.data.remote.ApiResponse
import com.bahanbaku.app.core.data.remote.datasource.RemoteDataSource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.domain.repository.IRecipeRepository
import com.bahanbaku.app.core.utils.AppExecutors
import com.bahanbaku.app.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRepository {
    override fun getNewRecipes(token: String): Flow<Resource<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<RecipeItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Recipe>> {
                return localDataSource.getAllRecipes()
                    .map { DataMapper.mapRecipeEntitiesToRecipeDomain(it) }
            }

            override fun shouldFetch(data: List<Recipe>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<RecipeItem>>> {
                return remoteDataSource.getNewRecipes(token)
            }

            override suspend fun saveCallResult(data: List<RecipeItem>) {
                val newRecipes = DataMapper.mapRecipeResponseToRecipeEntity(data)
                localDataSource.insertRecipes(newRecipes)
            }

        }.asFlow()

    override fun searchRecipe(token: String, query: String): Flow<Resource<List<Recipe>>> =
        remoteDataSource.searchRecipe(token, query)

    override fun getRecipeById(token: String, id: String): Flow<Resource<RecipeDetailItem>> =
        remoteDataSource.getRecipeById(token, id)

    override fun getRecipeByTag(token: String, tag: String): Flow<Resource<List<Recipe>>> =
        remoteDataSource.getRecipesByTag(token, tag)

    override fun getFavorites(token: String): Flow<Resource<List<FavoriteItem>>> =
        remoteDataSource.getFavorites(token)

    override fun addFavorites(
        token: String,
        id: String
    ): Flow<Resource<PostAddFavoriteResponse>> = remoteDataSource.addFavorites(token, id)

    override fun deleteFavorites(
        token: String,
        position: Int
    ): Flow<Resource<DeleteFavoriteResponse>> =
        remoteDataSource.deleteBookmarkByPosition(token, position)

    override fun deleteFavorites(
        token: String,
        id: String
    ): Flow<Resource<DeleteFavoriteResponse>> =
        remoteDataSource.deleteFavorites(token, id)

    override fun checkIfRecipeFavorited(
        token: String,
        id: String
    ): Flow<Boolean> = remoteDataSource.checkIfRecipeBookmarked(token, id)
}