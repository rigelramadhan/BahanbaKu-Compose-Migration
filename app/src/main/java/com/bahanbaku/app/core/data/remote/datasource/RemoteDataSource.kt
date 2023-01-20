package com.bahanbaku.app.core.data.remote.datasource

import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.ApiResponse
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.data.remote.retrofit.ApiService
import com.bahanbaku.app.core.domain.model.ProductsData
import com.bahanbaku.app.core.domain.model.Profile
import com.bahanbaku.app.core.domain.model.Recipe
import com.bahanbaku.app.core.utils.DataMapper
import com.bahanbaku.app.core.utils.ERROR_DEFAULT_MESSAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File

class RemoteDataSource private constructor(private val apiService: ApiService) {

    //    RECIPES data sources
    fun getNewRecipes(token: String): Flow<ApiResponse<List<RecipeItem>>> {
        return flow {
            try {
                val response = apiService.getRecipe(token)
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    fun getRecipesByTag(token: String, tag: String): Flow<Resource<List<Recipe>>> {
        return flow {
            try {
                val response = apiService.getRecipeByTag(token, tag)
                val data = DataMapper.mapRecipeResponseToRecipeEntity(response.results.recipes)
                val domainData = DataMapper.mapRecipeEntitiesToRecipeDomain(data)
                if (data.isNotEmpty()) {
                    emit(Resource.Success(domainData))
                } else {
                    emit(Resource.Error(ERROR_DEFAULT_MESSAGE))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun searchRecipe(token: String, query: String): Flow<Resource<List<Recipe>>> {
        return flow {
            try {
                val response = apiService.searchRecipe(token, query)
                val data = DataMapper.mapRecipeResponseToRecipeEntity(response.results)
                val domainData = DataMapper.mapRecipeEntitiesToRecipeDomain(data)
                if (data.isNotEmpty()) {
                    emit(Resource.Success(domainData))
                } else {
                    emit(Resource.Error(ERROR_DEFAULT_MESSAGE))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getRecipeById(token: String, id: String): Flow<Resource<RecipeDetailItem>> {
        return flow {
            try {
                val response = apiService.getRecipeById(token, id)
                val data = response.results
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    //    PROFILE data sources
    fun getProfile(token: String): Flow<Resource<Profile>> {
        return flow {
            try {
                val response = apiService.getProfile(token)
                val data = DataMapper.mapProfileResponseToProfileEntity(response.results)
                val domainData = DataMapper.mapProfileEntityToProfileDomain(data)
                emit(Resource.Success(domainData))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun login(email: String, password: String): Flow<Resource<LoginResponse>> {
        return flow {
            try {
                val response = apiService.login(email, password)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        phoneNumber: String,
    ): Flow<Resource<PostRegisterResponse>> {
        return flow {
            try {
                val response =
                    apiService.register(firstName, lastName, email, password, phoneNumber)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun updateUser(
        token: String,
        firstName: String,
        lastName: String
    ): Flow<Resource<UpdateProfileResponse>> {
        return flow {
            try {
                val response =
                    apiService.updateProfile(token, firstName, lastName)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun uploadPicture(
        token: String,
        file: File
    ): Flow<Resource<UploadPictureResponse>> {
        val mediaType = "image".toMediaTypeOrNull()
        val multipartBody =
            MultipartBody.Part.createFormData("image", file.name, file.asRequestBody(mediaType))

        return flow {
            try {
                val response =
                    apiService.uploadPicture(token, multipartBody)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun updateLocation(
        token: String,
        lon: Double,
        lat: Double
    ): Flow<Resource<UpdateLocationResponse>> {
        val location = JSONObject()
        location.put("lat", lat)
        location.put("lng", lon)

        val bodyObject = JSONObject()
        bodyObject.put("location", location)

        val requestBody = bodyObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        return flow {
            try {
                val response =
                    apiService.updateLocation(token, requestBody)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getFavorites(token: String): Flow<Resource<List<FavoriteItem>>> {
        return flow {
            try {
                val response =
                    apiService.getFavorites(token)
                val data = response.results.favorite
                if (data.isNotEmpty()) {
                    emit(Resource.Success(data))
                } else {
                    emit(Resource.Error(response.message))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun addFavorites(token: String, id: String): Flow<Resource<PostAddFavoriteResponse>> {
        return flow {
            try {
                val response =
                    apiService.addFavorites(token, id)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun deleteBookmarkByPosition(
        token: String,
        position: Int
    ): Flow<Resource<DeleteFavoriteResponse>> {
        return flow {
            try {
                val favorites =
                    apiService.getFavorites(token)
                deleteFavorites(token, favorites.results.favorite[position].recipeId).collect {
                    emit(it)
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }


    fun deleteFavorites(token: String, id: String): Flow<Resource<DeleteFavoriteResponse>> {
        return flow {
            try {
                val response =
                    apiService.deleteFavorites(token, id)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun checkIfRecipeBookmarked(token: String, id: String): Flow<Boolean> {
        return flow {
            try {
                val response = apiService.getFavorites(token)

                var isAvailable = false
                response.results.favorite.forEach {
                    if (it.recipeId == id) {
                        isAvailable = true
                    }
                }

                emit(isAvailable)
            } catch (e: Exception) {
                emit(false)
            }
        }
    }

    fun getAddress(token: String): Flow<Resource<GetAddressByUser>> {
        return flow {
            try {
                val response = apiService.getUserAddress(token)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getAddressById(token: String, id: String): Flow<Resource<GetAddressByIdResponse>> {
        return flow {
            try {
                val response = apiService.getUserAddressById(token, id)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun addAddress(
        token: String,
        street: String,
        district: String,
        city: String,
        province: String,
        zipCode: Int,
        label: String,
        receiverName: String,
        receiverNumber: String,
    ): Flow<Resource<PostAddUserAddress>> {
        return flow {
            try {
                val response = apiService.addUserAddress(
                    token,
                    street,
                    district,
                    city,
                    province,
                    zipCode,
                    label,
                    receiverName,
                    receiverNumber
                )
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getPaymentMethods(token: String): Flow<Resource<List<PaymentItem>>> {
        return flow {
            try {
                val response = apiService.getPaymentMethod(token)
                val data = response.results
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun createDirectPayment(
        token: String,
        products: ProductsData,
        id: String
    ): Flow<Resource<PostCreateDirectPaymentResponse>> {
        return flow {
            try {
                val response = apiService.createDirectPayment(token, products, id)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun submitPaymentProof(
        token: String,
        file: File,
        id: String
    ): Flow<Resource<PostSubmitProofResponse>> {
        val mediaType = "image".toMediaTypeOrNull()
        val multipartBody =
            MultipartBody.Part.createFormData("image", file.name, file.asRequestBody(mediaType))

        return flow {
            try {
                val response = apiService.submitPaymentProof(token, multipartBody, id)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getDirectPaymentInfo(token: String): Flow<Resource<GetDirectPaymentInfoResponse>> {
        return flow {
            try {
                val response = apiService.getDirectPaymentInfo(token)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getDirectOrderHistory(token: String): Flow<Resource<List<OrderHistoryItem>>> {
        return flow {
            try {
                val response = apiService.getDirectOrderHistory(token)
                val data = response.results
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun getDirectOrderDetail(token: String, id: String): Flow<Resource<DirectPaymentDetailResult>> {
        return flow {
            try {
                val response = apiService.getDirectOrderDetail(token, id)
                val data = response.results
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    companion object {
        private const val TAG = "REMOTE_DATA_SOURCE"

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RemoteDataSource(apiService)
            }
    }
}