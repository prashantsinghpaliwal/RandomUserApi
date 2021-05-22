package com.bigsteptech.deazzle.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bigsteptech.deazzle.common.Status
import com.bigsteptech.deazzle.data.local.Profile
import com.bigsteptech.deazzle.data.repository.ProfileRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String>
        get() = _errorData

    init {
        fetchProfiles()
    }

    private fun fetchProfiles() {
        viewModelScope.launch {
            repository.getProfiles(10).collect {
                _errorData.postValue(it)
            }
        }
    }

    fun updateStatus(profile: Profile, status: Int) = viewModelScope.launch {
        repository.updateStatus(profile, status)
    }

    fun getCachedProfiles(): LiveData<List<Profile>> = repository.getCachedProfiles().asLiveData()
}