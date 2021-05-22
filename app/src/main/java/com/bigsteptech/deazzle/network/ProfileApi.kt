package com.bigsteptech.deazzle.network


import com.bigsteptech.deazzle.data.remote.MainResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("api")
    fun getProfiles(@Query("results") results: Int = 10): Single<MainResponse>

}