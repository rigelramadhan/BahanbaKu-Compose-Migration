package com.bahanbaku.app.core.domain.repository

import androidx.lifecycle.LiveData
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IProfileRepository {
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
    fun isFirstTime(): LiveData<Boolean>
    fun setFirstTime(firstTime: Boolean)
    fun getAddress(token: String): Flow<Resource<GetAddressByUser>>
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

    fun setMainAddress(addressId: String)
    fun getMainAddress(): LiveData<String>
    fun getAddressById(token: String, id: String): Flow<Resource<GetAddressByIdResponse>>
}