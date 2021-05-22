package com.bigsteptech.deazzle.data.repository

import com.bigsteptech.deazzle.common.Status
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.data.local.ProfileDao
import com.bigsteptech.deazzle.network.ProfileService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val remoteDataSource: ProfileService,
    private val localDataSource: ProfileDao
) {


    suspend fun getProfiles(page: Int) = flow {
        val result = remoteDataSource.getProfiles(page)

        when (result.status) {

            //Cache to database if response is successful
            Status.SUCCESS -> {
                localDataSource.deleteAll()
                localDataSource.insertAll(result.data?.results?.map {
                    Profile(
                        it.cell!!, it.dob?.age!!, it.email!!, it.gender!!, it.id?.value,
                        it.location?.city, it.location?.country, "${it.name?.title} " +
                                "${it.name?.first} " +
                                "${it.name?.last}", it.phone, it.picture?.large,
                        -1
                    )
                }?.toList()!!)
            }

            Status.ERROR -> {
                emit(result.message)
            }

        }
    }

    fun getCachedProfiles(): Flow<List<Profile>> =
        localDataSource.getAll()

    suspend fun updateStatus(profile: Profile, status: Int) =
        localDataSource.updateItem(profile.id, status)

}