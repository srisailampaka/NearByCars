package com.nearbycars.ui.carlist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.nearbycars.data.model.PlaceMarkList
import com.nearbycars.data.repository.CarsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarListViewModel @Inject constructor(private val repo: CarsRepository) : ViewModel() {
    val originalData = MutableLiveData<PlaceMarkList>()
    val errorData = MutableLiveData<String>()

    fun getCarList() {

        repo.getCarList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onCardListReceived, this::onError)
    }

    private fun onError(error: Throwable) {
        //Log.e { "error ${error.localizedMessage}" }
        Log.e("",""+error.localizedMessage)
        errorData.value = error.localizedMessage

    }

    private fun onCardListReceived(placeMarksList: PlaceMarkList) {
       // Log.d { "data received ${roomResponses.size}" }
        originalData.postValue(placeMarksList)


    }
}