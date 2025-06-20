package com.ryvk.headline

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater

class AlertUtils {
    companion object{
        private var loaderDialog : AlertDialog? = null
        fun showLoader(context: Context?) {
            if (context is Activity && !context.isFinishing) {
                val builder = android.app.AlertDialog.Builder(context)
                val inflater = LayoutInflater.from(context)
                builder.setView(inflater.inflate(R.layout.dialog_loader, null))
                builder.setCancelable(false)

                if (loaderDialog != null && loaderDialog!!.isShowing) {
                    loaderDialog!!.dismiss()
                    loaderDialog = null
                }
                loaderDialog = builder.create()
                loaderDialog?.show()
            }
        }
        fun hideLoader() {
            if (loaderDialog != null && loaderDialog!!.isShowing) {
                loaderDialog!!.dismiss()
                loaderDialog = null
            }
        }
    }
}