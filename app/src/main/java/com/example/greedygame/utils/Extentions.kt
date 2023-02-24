package com.example.productlist.utils


import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Context.makeToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun getConnectivityStatusString(context: Context): String? {
    var status: String? = null
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    if (activeNetwork != null) {
        if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
            //    status = "Wifi enabled"
            status = ""
            return status
        } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
            //status = "Mobile data enabled"
            status = ""
            return status
        }
    } else {
        status = "No internet is available"
        return status
    }
    return status
}


fun Activity.isInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED

}

@RequiresApi(Build.VERSION_CODES.M)
fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    capabilities.also {
        if (it != null) {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true
            else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            }
        }
    }
    return false
}


fun Activity.hideKeyboard() {
    var view = this.currentFocus
    view?.let { v ->
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun Fragment.hideKeyboard() {
    val activity = context as AppCompatActivity
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


fun EditText.makeEmpty() {
    setText("")
}
