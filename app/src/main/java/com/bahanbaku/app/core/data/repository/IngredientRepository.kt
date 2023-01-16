package com.bahanbaku.app.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.IngredientResults
import com.bahanbaku.app.core.data.remote.retrofit.ApiService
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getIngredient(token: String, search: String): LiveData<Resource<IngredientResults>> = liveData {
//        emit(Resource.Loading)
//        try {
//            val ingredients = apiService.getIngredient(token, search).results
//            emit(Resource.Success(ingredients))
//        } catch (e: Exception) {
//            emit(Resource.Error(e.message.toString()))
//        }
    }
}