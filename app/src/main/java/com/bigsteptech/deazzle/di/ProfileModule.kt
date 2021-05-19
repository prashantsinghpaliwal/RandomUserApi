package com.bigsteptech.deazzle.di

import com.bigsteptech.deazzle.BuildConfig
import com.bigsteptech.deazzle.network.ProfileApi
import com.bigsteptech.deazzle.network.ProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object ProfileModule {


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

}