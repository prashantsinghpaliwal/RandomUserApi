package com.bigsteptech.deazzle.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    private val _data = MutableLiveData<Any>()
    val data: LiveData<Any>
        get() = _data

    val someData = MutableLiveData<List<Profile>>()

    init {
        fetchProfiles()
    }

    private fun fetchProfiles() {
        repository.getProfiles(10)
    }

    fun updateStatus(profile: Profile, status: Int) = viewModelScope.launch {
        repository.updateStatus(profile, status)
    }

    fun getCachedProfiles():LiveData<List<Profile>> = repository.getCachedProfiles().asLiveData()
}