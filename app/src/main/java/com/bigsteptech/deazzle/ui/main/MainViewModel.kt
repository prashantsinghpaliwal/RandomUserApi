package com.bigsteptech.deazzle.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

        viewModelScope.launch {
            repository.getProfiles(10)
        }
    }

    fun updateStatus(profile: Profile, status: Int) = viewModelScope.launch {
        repository.updateStatus(profile, status)
    }

    fun getCachedProfiles() {
        viewModelScope.launch {
            repository.getCachedProfiles().value?.let {
                someData.postValue(it)
            }
        }
    }
}