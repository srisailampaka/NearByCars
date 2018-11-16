package com.nearbycars.data.repository

import com.nearbycars.data.model.PlaceMarkList
import io.reactivex.Single

interface CarsRepository {
    fun getCarList(): Single<PlaceMarkList>
}