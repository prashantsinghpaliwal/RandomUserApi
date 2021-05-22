package com.bigsteptech.deazzle.network

import com.bigsteptech.deazzle.common.Resource
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.data.remote.MainResponse
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

interface ProfileService {

    fun getProfiles(page: Int):  Observable<List<Profile>>


    class Impl @Inject constructor(
        private val profileApi: ProfileApi
    ) : ProfileService {

        override fun getProfiles(page: Int): Observable<List<Profile>> =
            profileApi.getProfiles()
                .map {
                    it?.results?.map {
                        Profile(
                            it.cell!!, it.dob?.age!!, it.email!!, it.gender!!, it.id?.value,
                            it.location?.city, it.location?.country, "${it.name?.title} " +
                                    "${it.name?.first} " +
                                    "${it.name?.last}", it.phone, it.picture?.large,
                            -1
                        )
                    }?.toList()!!
                }
                .toObservable()
                .subscribeOn(Schedulers.io())

    }
}