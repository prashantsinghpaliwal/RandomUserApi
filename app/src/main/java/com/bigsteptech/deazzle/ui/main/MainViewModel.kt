package com.bigsteptech.deazzle.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigsteptech.deazzle.network.ProfileService
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val profileService: ProfileService) :
    ViewModel() {


    fun fetchProfiles() {

        viewModelScope.launch {
           profileService.getProfiles(1).let {
               Log.v("ResponseLogs","response ${it.body()?.results}")
           }
        }

    }
}