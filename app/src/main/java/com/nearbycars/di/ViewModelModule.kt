package com.nearbycars.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.nearbycars.ui.carlist.CarListViewModel
import com.nearbycars.ui.carmap.CarMapViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CarListViewModel::class)
    abstract fun bindCarsListViewModel(viewModel: CarListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CarMapViewModel::class)
    abstract fun bindCarMapViewModel(viewModel: CarMapViewModel): ViewModel


}