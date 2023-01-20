package com.bahanbaku.app.core.data.repository

import androidx.lifecycle.asLiveData
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.local.datastore.UserPreferences
import com.bahanbaku.app.core.data.remote.datasource.RemoteDataSource
import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.Profile
import com.bahanbaku.app.core.domain.repository.IProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class ProfileRepository(
    private val remoteDataSource: RemoteDataSource,
    private val userPreferences: UserPreferences
) : IProfileRepository, CoroutineScope {

    override fun setMainAddress(addressId: String) {
        launch(Dispatchers.IO) {
            userPreferences.setMainAddress(addressId)
        }
    }

    override fun getMainAddress() = userPreferences.getMainAddress().asLiveData(coroutineContext)

    override fun saveToken(token: String) {
        launch(Dispatchers.IO) {
            userPreferences.saveToken(token)
        }
    }

    override fun getToken() = userPreferences.getToken()

    override fun deleteToken() {
        launch(Dispatchers.IO) {
            userPreferences.deleteToken()
        }
    }

    override fun getProfile(token: String): Flow<Resource<Profile>> =
        remoteDataSource.getProfile(token)

    override fun login(email: String, password: String): Flow<Resource<LoginResponse>> =
        remoteDataSource.login(email, password)

    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Flow<Resource<PostRegisterResponse>> =
        remoteDataSource.register(firstName, lastName, email, password, phoneNumber)

    override fun updateUser(
        token: String,
        firstName: String,
        lastName: String
    ): Flow<Resource<UpdateProfileResponse>> =
        remoteDataSource.updateUser(token, firstName, lastName)

    override fun uploadPicture(
        token: String,
        file: File
    ): Flow<Resource<UploadPictureResponse>> = remoteDataSource.uploadPicture(token, file)

    override fun updateLocation(
        token: String,
        lon: Double,
        lat: Double
    ): Flow<Resource<UpdateLocationResponse>> = remoteDataSource.updateLocation(token, lon, lat)

    override fun isFirstTime() = userPreferences.isFirstTime().asLiveData(coroutineContext)

    override fun setFirstTime(firstTime: Boolean) {
        launch(Dispatchers.IO) {
            userPreferences.setFirstTime(firstTime)
        }
    }

    override fun getAddress(token: String): Flow<Resource<GetAddressByUser>> =
        remoteDataSource.getAddress(token)

    override fun getAddressById(
        token: String,
        id: String
    ): Flow<Resource<GetAddressByIdResponse>> = remoteDataSource.getAddressById(token, id)

    override fun addAddress(
        token: String,
        street: String,
        district: String,
        city: String,
        province: String,
        zipCode: Int,
        label: String,
        receiverName: String,
        receiverNumber: String,
    ): Flow<Resource<PostAddUserAddress>> =
        remoteDataSource.addAddress(
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

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}