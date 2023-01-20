package com.bahanbaku.app.core.di

import com.bahanbaku.app.core.data.local.datasource.LocalDataSource
import com.bahanbaku.app.core.data.local.datastore.UserPreferences
import com.bahanbaku.app.core.data.remote.datasource.RemoteDataSource
import com.bahanbaku.app.core.data.repository.PaymentRepository
import com.bahanbaku.app.core.data.repository.ProfileRepository
import com.bahanbaku.app.core.data.repository.RecipeRepository
import com.bahanbaku.app.core.data.repository.UtilRepository
import com.bahanbaku.app.core.domain.repository.IPaymentRepository
import com.bahanbaku.app.core.domain.repository.IProfileRepository
import com.bahanbaku.app.core.domain.repository.IRecipeRepository
import com.bahanbaku.app.core.domain.repository.IUtilRepository
import com.bahanbaku.app.core.domain.usecase.*
import com.bahanbaku.app.core.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        appExecutors: AppExecutors
    ): IRecipeRepository {
        return RecipeRepository(localDataSource, remoteDataSource, appExecutors)
    }

    @Provides
    fun provideRecipeUseCase(repository: IRecipeRepository): RecipeUseCase {
        return RecipeInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        userPreferences: UserPreferences,
        appExecutors: AppExecutors
    ): IProfileRepository {
        return ProfileRepository(remoteDataSource, userPreferences)
    }

    @Provides
    fun provideProfileUseCase(repository: IProfileRepository): ProfileUseCase {
        return ProfileInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideUtilRepository(
        remoteDataSource: RemoteDataSource
    ): IUtilRepository {
        return UtilRepository(remoteDataSource)
    }

    @Provides
    fun provideUtilUseCase(repository: IUtilRepository): UtilUseCase {
        return UtilInteractor(repository)
    }

    @Provides
    @Singleton
    fun providePaymentRepository(
        remoteDataSource: RemoteDataSource
    ): IPaymentRepository {
        return PaymentRepository(remoteDataSource)
    }

    @Provides
    fun providePaymentUseCase(repository: IPaymentRepository): PaymentUseCase {
        return PaymentInteractor(repository)
    }

}