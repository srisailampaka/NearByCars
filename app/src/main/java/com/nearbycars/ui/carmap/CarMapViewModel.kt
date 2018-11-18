package com.nearbycars.ui.carmap

import android.arch.lifecycle.ViewModel
import com.nearbycars.data.repository.CarsRepository
import javax.inject.Inject

class CarMapViewModel @Inject constructor(private val repo: CarsRepository) : ViewModel()