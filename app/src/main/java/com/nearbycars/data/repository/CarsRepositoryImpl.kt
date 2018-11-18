package com.nearbycars.data.repository

import android.content.Context
import com.nearbycars.data.CarsServices
import com.nearbycars.data.model.PlaceMarkList
import io.reactivex.Single

/**
 * Implementation class for Cars Repository
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
class CarsRepositoryImpl(private val service: CarsServices) : CarsRepository {

    override fun getCarList(): Single<PlaceMarkList> {

        return service.getCarList()


    }

}