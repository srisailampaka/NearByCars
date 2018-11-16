package com.nearbycars.di

import com.nearbycars.ui.carmap.CarMapFragment
import com.nearbycars.ui.carlist.CarListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {
    @ContributesAndroidInjector
    internal abstract fun contributeCarListFragmentInjector(): CarListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeCarMapFragmentInjector(): CarMapFragment


}