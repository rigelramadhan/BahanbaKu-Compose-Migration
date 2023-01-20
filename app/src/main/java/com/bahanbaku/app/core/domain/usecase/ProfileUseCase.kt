package com.bahanbaku.app.core.domain.usecase

import androidx.lifecycle.LiveData
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import java.io.File

interface ProfileUseCase {
    fun saveToken(token: String)
    fun getToken(): Flow<String>
    fun deleteToken()
    fun getProfile(token: String): Flow<Resource<Profile>>
    fun login(email: String, password: String): Flow<Resource<LoginResponse>>
    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Flow<Resource<PostRegisterResponse>>

    fun updateUser(
        token: String,
        firstName: String,
        lastName: String
    ): Flow<Resource<UpdateProfileResponse>>

    fun uploadPicture(token: String, file: File): Flow<Resource<UploadPictureResponse>>
    fun updateLocation(
        token: String,
        lon: Double,
        lat: Double
    ): Flow<Resource<UpdateLocationResponse>>

    fun getAddress(
        token: String
    ): Flow<Resource<GetAddressByUser>>
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
    ): Flow<Resource<PostAddUserAddress>>

//    fun getBookmarks(token: String): Flowable<Resource<List<RecipeItem>>>
//    fun addBookmark(token: String, id: String): Flowable<Resource<AddBookmarkResponse>>
//    fun deleteBookmarkByPosition(
//        token: String,
//        position: Int
//    ): Flowable<Resource<DeleteBookmarkResponse>>
//
//    fun deleteBookmark(token: String, id: String): Flowable<Resource<DeleteBookmarkResponse>>
//    fun checkIfRecipeBookmarked(token: String, id: String): Flowable<Boolean>
    fun isFirstTime(): LiveData<Boolean>
    fun setFirstTime(firstTime: Boolean)
    fun setMainAddress(addressId: String)
    fun getMainAddress(): LiveData<String>
    fun getAddressById(token: String, id: String): Flow<Resource<GetAddressByIdResponse>>
}