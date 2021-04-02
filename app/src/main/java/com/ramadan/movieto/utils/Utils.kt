@file:Suppress("DEPRECATION")

package com.ramadan.movieto.utils

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.view.View
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar


class Utils(val context: Context) {}


const val debug_tag = "TOTO"


//fun showBrief(title: String, content: String, context: Context) {
//    val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
//    val view = View.inflate(context, R.layout.alert_dialog_read_marker, null)
//    dialogBuilder.setView(view)
//    val alertDialog = dialogBuilder.create()
//    alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    alertDialog.window!!.attributes.windowAnimations = R.style.ShrinkAnimation
//    alertDialog.show()
//    alertDialog.setCancelable(true)
//    view.findViewById<LinearLayout>(R.id.actionBar).visibility = View.GONE
//    view.findViewById<TextView>(R.id.alertTitle).text = title
//    val alertContent = view.findViewById<TextView>(R.id.alertContent)
//    alertContent.visibility = View.VISIBLE
//    alertContent.text = content
//}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
}


fun View.changeNavigation(direction: NavDirections) {
    Navigation.findNavController(this).navigate(direction)
}

fun View.snackBar(message: String) {
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    ).apply {
        setTextColor(Color.WHITE)
        show()
    }
}

