package com.nearbycars.data

import com.nearbycars.data.model.PlaceMarkList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface CarsServices {
    @Headers("Content-type: application/json")
    @GET("locations.json")
    fun getCarList(): Single<PlaceMarkList>

}