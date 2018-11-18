package com.nearbycars.di

import android.content.Context
import com.nearbycars.data.CarsServices
import com.nearbycars.data.repository.CarsRepository
import com.nearbycars.data.repository.CarsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepo(carServices: CarsServices): CarsRepository = CarsRepositoryImpl(carServices)
}