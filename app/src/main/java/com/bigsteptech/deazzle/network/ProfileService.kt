package com.bigsteptech.deazzle.network

import com.bigsteptech.deazzle.common.Resource
import com.bigsteptech.deazzle.data.remote.MainResponse
import javax.inject.Inject

interface ProfileService {

    suspend fun getProfiles(page: Int): Resource<MainResponse>


    class Impl @Inject constructor(
        private val profileApi: ProfileApi
    ) : ProfileService, BaseDataSource() {

        override suspend fun getProfiles(page: Int) =
            getResult {
                profileApi.getProfiles()
            }


    }
}