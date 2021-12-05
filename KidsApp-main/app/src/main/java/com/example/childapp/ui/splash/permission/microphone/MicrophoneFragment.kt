package com.example.childapp.ui.splash.permission.microphone

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.databinding.FragmentMicrophoneBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MicrophoneFragment : Fragment(R.layout.fragment_microphone) {
    private var _binding: FragmentMicrophoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMicrophoneBinding.inflate(inflater, container, false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allowMicrophoneBtn.setOnClickListener {
            requestMicrophonePermission()
        }

    }


    private fun requestMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS
                ),
                1
            );
        } else {
            findNavController().navigate(R.id.action_microphoneFragment_to_monitorFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            AlertDialog.Builder(requireContext())
                .setTitle("Microphone Permission Needed")
                .setMessage("This app needs the Microphone permission, please accept to use Microphone functionality")
                .setPositiveButton(
                    "OK"
                ) { _, _ ->
                    //Prompt the user once explanation has been shown
                    requestMicrophonePermission()
                }
                .create()
                .show()
        } else {
            binding.allowMicrophoneBtn.text=getString(R.string.continue_text)
        }
    }
}