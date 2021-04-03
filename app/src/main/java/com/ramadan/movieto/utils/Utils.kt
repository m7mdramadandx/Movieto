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


val genresNamesList = hashMapOf(
    28 to "Action", 12 to "Adventure", 16 to "Animation",
    35 to "Comedy", 80 to "Crime", 99 to "Documentary",
    18 to "Drama", 10751 to "Family", 14 to "Fantasy",
    36 to "History", 27 to "Horror", 10402 to "Music",
    9648 to "Mystery", 10749 to "Romance", 878 to "Science Fiction",
    10770 to "TV Movie", 53 to "Thriller", 10752 to "War",
    37 to "Western"
)