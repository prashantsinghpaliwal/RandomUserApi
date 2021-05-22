package com.bigsteptech.deazzle.network


import com.bigsteptech.deazzle.data.remote.MainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("api")
    suspend fun getProfiles(@Query("results") results: Int = 10): Response<MainResponse>

}