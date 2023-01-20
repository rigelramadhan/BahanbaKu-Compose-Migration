package com.bahanbaku.app.core.domain.usecase

import androidx.lifecycle.LiveData
import com.bahanbaku.app.core.data.Resource
import com.bahanbaku.app.core.data.remote.response.GetAddressByIdResponse
import com.bahanbaku.app.core.data.remote.response.GetAddressByUser
import com.bahanbaku.app.core.data.remote.response.PostAddUserAddress
import com.bahanbaku.app.core.data.remote.response.PostRegisterResponse
import com.bahanbaku.app.core.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.Flow
import java.io.File

class ProfileInteractor(private val profileRepository: IProfileRepository) : ProfileUseCase {
    override fun saveToken(token: String) = profileRepository.saveToken(token)

    override fun getToken() = profileRepository.getToken()

    override fun deleteToken() = profileRepository.deleteToken()

    override fun getProfile(token: String) = profileRepository.getProfile(token)

    override fun login(email: String, password: String) = profileRepository.login(email, password)

    override fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        phoneNumber: String
    ): Flow<Resource<PostRegisterResponse>> =
        profileRepository.register(firstName, lastName, email, password, phoneNumber)

    override fun updateUser(
        token: String,
        firstName: String,
        lastName: String
    ) = profileRepository.updateUser(token, firstName, lastName)

    override fun uploadPicture(
        token: String,
        file: File
    ) = profileRepository.uploadPicture(token, file)

    override fun updateLocation(
        token: String,
        lon: Double,
        lat: Double
    ) = profileRepository.updateLocation(token, lon, lat)

    override fun getAddress(token: String): Flow<Resource<GetAddressByUser>> =
        profileRepository.getAddress(token)

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
        profileRepository.addAddress(
            token,
            street,
            district,
            city,
            province,
            zipCode,
            label,
            receiverName, receiverNumber
        )

    override fun isFirstTime() = profileRepository.isFirstTime()

    override fun setFirstTime(firstTime: Boolean) = profileRepository.setFirstTime(firstTime)

    override fun setMainAddress(addressId: String) = profileRepository.setMainAddress(addressId)

    override fun getMainAddress(): LiveData<String> = profileRepository.getMainAddress()

    override fun getAddressById(
        token: String,
        id: String
    ): Flow<Resource<GetAddressByIdResponse>> = profileRepository.getAddressById(token, id)
}