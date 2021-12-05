package com.example.childapp.ui.lock

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.childapp.databinding.ActivityLockScreenBinding
import com.example.childapp.db.room.AppDao
import com.example.childapp.db.room.AppDatabase
import com.example.childapp.function.keylog.KeyLoggerService
import com.example.childapp.common.utils.showToast
import com.example.childapp.ui.BaseActivity
import com.itsxtt.patternlock.PatternLockView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class LockScreenActivity : BaseActivity() {
    lateinit var binding: ActivityLockScreenBinding
    @Inject
    lateinit var appDao: AppDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLockScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val packName = intent.getStringExtra("pack")
        val mId = intent.getStringExtra("appId")

        val codeList = ArrayList<Int>()
        codeList.add(0)
        codeList.add(1)
        codeList.add(4)
        codeList.add(5)
        binding.patternLockView.setOnPatternListener(object : PatternLockView.OnPatternListener {
            override fun onStarted() {
                super.onStarted()
            }

            override fun onProgress(ids: ArrayList<Int>) {
                super.onProgress(ids)


            }

            override fun onComplete(ids: ArrayList<Int>): Boolean {
                if (codeList == ids) {
                    showToast("Great correct password")
//                    finish()
                    KeyLoggerService.statePackage = packName.toString()
                    appDao.updateAppsUpdateField(mId!!, true)
                    startOtherApp(packName.toString())
                    return true
                } else {
                    showToast("Incorrect password")
                    return false
                }
            }
        })
    }

    private fun startOtherApp(packageName: String) {

        var intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        } else {
            // Bring user to the market or let them choose an app?
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse("market://details?id=$packageName")
            startActivity(intent)
        }
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}