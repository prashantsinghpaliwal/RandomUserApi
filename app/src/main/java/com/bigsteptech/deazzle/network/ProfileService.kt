package com.bigsteptech.deazzle.network

import com.bigsteptech.deazzle.data.MainResponse
import retrofit2.Response
import javax.inject.Inject

interface ProfileService {

   suspend fun getProfiles(page : Int): Response<MainResponse>


    class Impl @Inject constructor(
        private val profileApi: ProfileApi
    ) : ProfileService {

        override suspend fun getProfiles(page: Int): Response<MainResponse> =
            profileApi.getProfiles()


    }
}