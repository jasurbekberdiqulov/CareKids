package com.example.childapp.ui.splash.permission.battery

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.content.Context.POWER_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.databinding.FragmentBatterySaverBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BatterySaverFragment : Fragment(R.layout.fragment_battery_saver) {
    private var _binding: FragmentBatterySaverBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBatterySaverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allowSwitchBatterBtn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val intent = Intent()
                val packageName: String = requireActivity().packageName
                val pm = requireActivity().getSystemService(POWER_SERVICE) as PowerManager?
                if (!pm!!.isIgnoringBatteryOptimizations(packageName)) {
                    intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                    intent.data = Uri.parse("package:$packageName")
                    startActivity(intent)
                } else {
                    findNavController().navigate(R.id.action_batterySaverFragment_to_microphoneFragment)
                }
            } else {
                findNavController().navigate(R.id.action_batterySaverFragment_to_microphoneFragment)
            }
        }
    }


    fun getBatteryPercentage(context: Context): Int {
        return if (Build.VERSION.SDK_INT >= 21) {
            val bm = context.getSystemService(BATTERY_SERVICE) as BatteryManager
            bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        } else {
            val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            val batteryStatus: Intent = context.registerReceiver(null, iFilter)!!
            val level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPct = level / scale.toDouble()
            (batteryPct * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}