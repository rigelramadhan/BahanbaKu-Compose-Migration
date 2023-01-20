package com.bahanbaku.app.core.data.local.datasource

import com.bahanbaku.app.core.data.local.entity.ProfileEntity
import com.bahanbaku.app.core.data.local.entity.RecipeEntity
import com.bahanbaku.app.core.data.local.room.ProfileDao
import com.bahanbaku.app.core.data.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(
    private val recipeDao: RecipeDao,
    private val profileDao: ProfileDao
) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(recipeDao: RecipeDao, profileDao: ProfileDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(recipeDao, profileDao)
            }
    }

    fun getAllRecipes(): Flow<List<RecipeEntity>> = recipeDao.getRecipes()

    suspend fun insertRecipes(recipeList: List<RecipeEntity>) = recipeDao.insertRecipes(recipeList)

    fun getRecipeById(id: String): Flow<RecipeEntity> = recipeDao.getRecipeById(id)

    fun getProfile(): Flow<List<ProfileEntity>> = profileDao.getProfile()

    suspend fun insertProfile(profile: ProfileEntity) = profileDao.insertProfile(profile)

    fun deleteProfile() = profileDao.deleteAllProfile()
}