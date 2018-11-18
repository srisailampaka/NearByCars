package com.nearbycarapp

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nearbycars.R
import com.nearbycars.ui.MainActivity
import com.nearbycars.ui.carlist.CarListAdapter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.nearbycarapp", appContext.packageName)
    }


    @Rule
    @JvmField
    public var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
            MainActivity::class.java)


    @Before
    fun setUp() {
    }

    @Test
    fun showTheCarListScreen() {
        IdlingRegistry.getInstance().register(mActivityRule.activity.espressoTestIdlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        IdlingRegistry.getInstance().unregister(mActivityRule.activity.espressoTestIdlingResource)
    }

    @Test
    fun showTheCarMapScreen() {
        IdlingRegistry.getInstance().register(mActivityRule.activity.espressoTestIdlingResource)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<CarListAdapter.ViewHolder>(1, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.map)).check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        IdlingRegistry.getInstance().unregister(mActivityRule.activity.espressoTestIdlingResource)
    }
}
