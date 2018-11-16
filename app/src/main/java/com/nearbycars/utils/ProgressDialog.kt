package com.nearbycars.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.nearbycars.R
/**
 * This class to handle ProgressDialog
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-11-16
 */
class ProgressDialog {
    companion object {
        lateinit var dialog: Dialog
        fun showProgressDialog(context: Context) {
            dialog = Dialog(context)
            val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
            dialog.setContentView(inflate)
            dialog.setCancelable(true)
            dialog.window!!.setBackgroundDrawable(
                    ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }

        fun dismissProgressDialog() {
            dialog.dismiss()
        }


    }
}