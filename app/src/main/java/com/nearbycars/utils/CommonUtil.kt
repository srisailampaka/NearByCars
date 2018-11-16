package com.nearbycars.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * This class to implement the common functions for the app
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
class CommonUtil {
    companion object {
        fun isNetworkStatusAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            connectivityManager?.let {
                it.activeNetworkInfo?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }


    }


}