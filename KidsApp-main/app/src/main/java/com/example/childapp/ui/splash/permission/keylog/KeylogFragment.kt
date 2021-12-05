package com.example.childapp.ui.splash.permission.keylog

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.databinding.FragmentKeylogBinding
import com.example.childapp.function.keylog.KeyLoggerService


class KeylogFragment : Fragment(R.layout.fragment_keylog) {

    private var _binding: FragmentKeylogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeylogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        binding.allowKeylogService.setOnClickListener {
            if (KeyLoggerService.isRunService) {

            } else {
                getAccessibilityPermission()
            }
        }

    }

    private fun getAccessibilityPermission() {
        val openSettings = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        openSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(openSettings)
    }

    override fun onResume() {
        super.onResume()
        if (KeyLoggerService.isRunService) {
            binding.allowKeylogService.text = resources.getString(R.string.next)
        }
    }

}