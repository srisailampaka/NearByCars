package com.nearbycars.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.nearbycars.R
import com.nearbycars.utils.SimpleCountingIdlingResource
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Class for implement common functionalities to all the activitys
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var dialog: Dialog
    var espressoTestIdlingResource = SimpleCountingIdlingResource(SimpleCountingIdlingResource::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = Dialog(this)
    }


    /*
      This method for replace the fragment to container
     */
    fun replaceTheFragment(fragment: Fragment, backStackText: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(backStackText)
                .commitAllowingStateLoss()
    }


    /*
     This methode helps to add font to app
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    fun setBackButton(status: Boolean) {
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(status)
    }

    fun setTitle(title: String) {
        getSupportActionBar()!!.title = title
    }

    fun showProgressDialog() {
        val inflate = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null)
        dialog.setContentView(inflate)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT))
        dialog.show()

    }

    fun dismissProgressDialog() {
        dialog.cancel()
    }
}
