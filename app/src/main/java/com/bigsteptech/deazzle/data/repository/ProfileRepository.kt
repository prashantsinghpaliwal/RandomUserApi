package com.bigsteptech.deazzle.data.repository

import android.util.Log
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.data.local.ProfileDao
import com.bigsteptech.deazzle.network.ProfileService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileService,
    private val localDataSource: ProfileDao
) {


    fun getProfiles(page: Int) {
        remoteDataSource.getProfiles(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    localDataSource.deleteAll()
                    localDataSource.insertAll(it)
                }
            }, {
                Log.v("CameraLogs", "${it}")
            })
    }

    fun getCachedProfiles(): Flow<List<Profile>> =
        localDataSource.getAll()

    suspend fun updateStatus(profile: Profile, status: Int) =
        localDataSource.updateItem(profile.id, status)

}