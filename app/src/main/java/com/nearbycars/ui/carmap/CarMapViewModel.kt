package com.nearbycars.ui.carmap

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.nearbycars.data.model.PlaceMarkList
import com.nearbycars.data.repository.CarsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarMapViewModel @Inject constructor(private val repo: CarsRepository) : ViewModel() {
}