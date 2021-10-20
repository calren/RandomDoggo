package com.caren.doggo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caren.doggo.network.DogApi
import com.caren.doggo.network.RandomDogApiResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _lastApiResponse = MutableLiveData<RandomDogApiResponse>()
    val lastApiResponse: LiveData<RandomDogApiResponse> = _lastApiResponse

    init {
        getNewDog()
    }

    fun getNewDog() {
        viewModelScope.launch {
            // The response from https://zenquotes.io/api/random is a list of 1 quote, so we
            // are getting the first item in the list from the response.
            _lastApiResponse.value = DogApi.retrofitService.getRandomDogImage()
        }
    }
}