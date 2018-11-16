package com.nearbycars

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.nearbycars.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

/**
 * Class for implement app related features
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */

class CarApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this)
                .build().inject(this)

        Stetho.initializeWithDefaults(this)

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("font/RobotoCondensed-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        var activity: Activity? = null
        fun setTheActivity(activityInstance: Activity) {
            activity = activityInstance
        }
    }
}