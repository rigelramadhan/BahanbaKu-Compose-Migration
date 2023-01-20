package com.bahanbaku.app.core.data.remote.retrofit

import com.bahanbaku.app.core.data.remote.response.*
import com.bahanbaku.app.core.domain.model.ProductsData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phoneNumber") phoneNumber: String,
    ): PostRegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("users/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): GetProfileResponse

    @FormUrlEncoded
    @POST("users/update/profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String
    ): UpdateProfileResponse

    @PUT("user/update-location")
    suspend fun updateLocation(
        @Header("Authorization") token: String,
        @Body location: RequestBody
    ): UpdateLocationResponse

    @Multipart
    @POST("users/update/picture")
    suspend fun uploadPicture(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): UploadPictureResponse

    @GET("favorites")
    suspend fun getFavorites(
        @Header("Authorization") token: String,
    ): GetFavoriteResponse

    @POST("favorites/{id}")
    suspend fun addFavorites(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): PostAddFavoriteResponse

    @DELETE("favorites/{id}")
    suspend fun deleteFavorites(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DeleteFavoriteResponse

    @GET("/recipes")
    suspend fun getRecipe(
        @Header("Authorization") token: String
    ): GetAllRecipesResponse

    @GET("recipes/search")
    suspend fun searchRecipe(
        @Header("Authorization") token: String,
        @Query("title") name: String? = ""
    ): GetAllRecipesResponse

    @GET("/recipes/{id}")
    suspend fun getRecipeById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): GetRecipeByIdResponse

    @GET("/tags/search")
    suspend fun getRecipeByTag(
        @Header("Authorization") token: String,
        @Query("tag") tag: String = ""
    ): GetRecipesByTagResponse

    @GET("users/address")
    suspend fun getUserAddress(
        @Header("Authorization") token: String
    ): GetAddressByUser

    @FormUrlEncoded
    @POST("users/address")
    suspend fun addUserAddress(
        @Header("Authorization") token: String,
        @Field("street") street: String,
        @Field("district") district: String,
        @Field("city") city: String,
        @Field("province") province: String,
        @Field("zipCode") zipCode: Int,
        @Field("label") label: String,
        @Field("receiverName") receiverName: String,
        @Field("receiverPhoneNumber") receiverNumber: String,
    ): PostAddUserAddress

    @GET("/transaction/payment-method")
    suspend fun getPaymentMethod(
        @Header("Authorization") token: String
    ): GetPaymentMethodResponse

    @GET("users/address/{id}")
    suspend fun getUserAddressById(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): GetAddressByIdResponse

    @POST("direct-pay/{id}")
    suspend fun createDirectPayment(
        @Header("Authorization") token: String,
        @Body productsData: ProductsData,
        @Path("id") id: String,
    ): PostCreateDirectPaymentResponse

    @Multipart
    @POST("direct-pay/submit-payment/{id}")
    suspend fun submitPaymentProof(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Path("id") id: String
    ): PostSubmitProofResponse

    @GET("direct-pay/payment-method")
    suspend fun getDirectPaymentInfo(
        @Header("Authorization") token: String
    ): GetDirectPaymentInfoResponse

    @GET("direct-pay")
    suspend fun getDirectOrderHistory(
        @Header("Authorization") token: String
    ): GetOrderHistoryResponse

    @GET("direct-pay/{id}")
    suspend fun getDirectOrderDetail(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    ): GetOrderDetailResponse
}

