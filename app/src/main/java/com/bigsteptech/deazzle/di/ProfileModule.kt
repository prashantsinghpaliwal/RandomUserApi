package com.bigsteptech.deazzle.di

import android.content.Context
import com.bigsteptech.deazzle.BuildConfig
import com.bigsteptech.deazzle.data.local.AppDatabase
import com.bigsteptech.deazzle.data.local.ProfileDao
import com.bigsteptech.deazzle.data.repository.ProfileRepository
import com.bigsteptech.deazzle.network.ProfileApi
import com.bigsteptech.deazzle.network.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ProfileModule {

    @Singleton
    @Provides
    fun profileApi(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): ProfileApi {
        return retrofit
            .baseUrl(BuildConfig.BASE_ENDPOINT)
            .client(okHttpClient)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    fun profileService(
        api: ProfileApi
    ): ProfileService {
        return ProfileService.Impl(
            api
        )
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideProfileDao(db: AppDatabase) = db.profileDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ProfileService,
        localDataSource: ProfileDao
    ) =
        ProfileRepository(remoteDataSource, localDataSource)

}