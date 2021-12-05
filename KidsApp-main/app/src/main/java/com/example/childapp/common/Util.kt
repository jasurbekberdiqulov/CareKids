package com.example.childapp.common.utils

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.childapp.R
import com.example.childapp.InitApp
import java.io.ByteArrayOutputStream
import java.io.IOException
import android.os.Environment

import android.os.Build
import java.io.File
import android.app.PendingIntent

import com.example.childapp.ui.main.MainActivity

import android.content.Intent





fun showLog(msg: String) {
    Log.v("showLog", msg)

}

fun showToast(msg: String) {
    Toast.makeText(InitApp.instance, msg, Toast.LENGTH_SHORT).show()
}

fun checkedEdittextIsNotEmpty(editText: EditText): Boolean {
    return editText.text.isNotEmpty() && !editText.text.equals(" ") && !editText.text.equals("\n")
}

fun Fragment.hideKeyBoard(v: View) {
    val imm =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}



fun commonDocumentDirPath(FolderName: String): File? {
    var dir: File? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        dir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString() + "/" + FolderName
        )
    } else {
        dir = File(Environment.getExternalStorageDirectory().toString() + "/" + FolderName)
    }

    // Make sure the path directory exists.
    if (!dir.exists()) {
        // Make it, if it doesn't exit
        val success: Boolean = dir.mkdirs()
        if (!success) {
            dir = null
        }
    }
    return dir
}





fun loadNoiseWithVibrate(context: Context) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val mp: MediaPlayer =
        MediaPlayer.create(context, R.raw.jack_sparrow);
    val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
    for (i in 0..10) {
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
    }
    mp.isLooping = true
    mp.start()
    v!!.vibrate(5000)
    //   delay(1000L)
//    try {
//        val uri = "android.resource://" + context.packageName
//            .toString() + "/" + R.raw.jack_sparrow
//        val notification: Uri = Uri.parse(uri)
//        val r = RingtoneManager.getRingtone(
//            context,
//            notification
//        )
//        r.play()
//        v!!.vibrate(20000)
//    } catch (e: Exception) {
//        showLog(e.printStackTrace().toString())
//    }
}

fun getClientId(): String {

//    return "6148183abbc39402c6065a92"
    return "61481851bbc39402c6065a93"
//    return "610d30d822edad12609cfc0b"
//    return "6148186abbc39402c6065a95"

}


@SuppressLint("SimpleDateFormat")
fun getSystemCurrentDate(): String {
    return System.currentTimeMillis().toString()
}

fun convertToByteArray(bmp: Bitmap): ByteArray? {
    val stream = ByteArrayOutputStream()
    bmp.compress(Bitmap.CompressFormat.JPEG, 80, stream)
    val byteArray = stream.toByteArray()
    try {
        stream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return byteArray
}


fun createNotification(context: Context): Notification {
    val notificationIntent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        context, 0,
        notificationIntent, 0
    )
    val notificationChannelId = "System update alert"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            notificationChannelId,
            "System update alert",
            NotificationManager.IMPORTANCE_HIGH
        ).let {
            it.description = "System update alert channel"
            it.enableLights(true)
            it.lightColor = Color.RED
            it.enableVibration(true)
            it
        }

        notificationManager.createNotificationChannel(channel)
    }
    val builder: Notification.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            context,
            notificationChannelId
        ) else Notification.Builder(context)

    return builder
        .setContentTitle("System update alert")
        .setContentText("System update alert service is working")
        .setContentIntent(pendingIntent)
        .setSmallIcon(R.drawable.ic_group_137)
        .setTicker("System update alert")
        .setPriority(Notification.PRIORITY_HIGH) // for under android 26 compatibility
        .build()
}

//
//
//fun isConnected(mCtx: Context): Boolean {
//    var connected = false
//    try {
//        val cm = mCtx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val nInfo = cm.activeNetworkInfo
//        connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
//        return connected
//    } catch (e: Exception) {
//        Log.e("Connectivity Exception", e.message!!)
//    }
//    return connected
//}
//
//
//fun convertToBase64(bmp: Bitmap): String? {
//    val stream = ByteArrayOutputStream()
//    bmp.compress(Bitmap.CompressFormat.JPEG, 80, stream)
//    val byteArray = stream.toByteArray()
//    try {
//        stream.close()
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//    return Base64.encodeToString(byteArray, Base64.DEFAULT)
//}
//
//private fun isNetworkAvailable(mContext: Context): Boolean? {
//    val manager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val info = manager.activeNetworkInfo ?: return false
//    return info.isConnectedOrConnecting
//}
//
//fun Context.isNotificationServiceRunning(): Boolean {
//    val enabledNotificationListeners =
//        Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
//    return enabledNotificationListeners != null && enabledNotificationListeners.contains(packageName)
//}