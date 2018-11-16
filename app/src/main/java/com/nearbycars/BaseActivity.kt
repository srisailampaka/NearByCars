package com.nearbycars.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.nearbycars.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * Class for implement common functionalities to all the activitys
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    /*
      This method for replace the fragment to container
     */
    fun replaceTheFragment(fragment: Fragment, backStackText: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(backStackText)
                .commit()
    }


    /*
     This methode helps to add font to app
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }



}