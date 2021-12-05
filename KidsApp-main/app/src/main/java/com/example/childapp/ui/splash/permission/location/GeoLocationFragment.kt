package com.example.childapp.ui.splash.permission.location

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.common.utils.showLog
import com.example.childapp.common.utils.showToast
import com.example.childapp.databinding.FragmentGeoLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeoLocationFragment : Fragment() {
    private var _binding: FragmentGeoLocationBinding? = null
    private val binding get() = _binding!!
    private val MY_PERMISSIONS_REQUEST_LOCATION = 12
    private val MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeoLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allowLocationBtn.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {

                AlertDialog.Builder(requireContext())
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        //Prompt the user once explanation has been shown
                        requestLocationPermission()
                    }
                    .create()
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                requestLocationPermission()
            }
        } else {
            findNavController().navigate(R.id.action_geoLocationFragment_to_batterySaverFragment)

        }

    }

/*
    private fun checkBackgroundLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            AlertDialog.Builder(requireContext())
                .setTitle("All the time")
                .setMessage("This app needs the Location permission, please accept to use location functionality")
                .setPositiveButton(
                    "OK"
                ) { _, _ ->
                    requestBackgroundLocationPermission()
                }
                .create()
                .show()

        } else {
            findNavController().navigate(R.id.action_geoLocationFragment_to_batterySaverFragment)

        }
    }
*/

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }

    /* private fun requestBackgroundLocationPermission() {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
             ActivityCompat.requestPermissions(
                 requireActivity(),
                 arrayOf(
                     Manifest.permission.ACCESS_BACKGROUND_LOCATION
                 ),
                 MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION
             )
         } else {
             ActivityCompat.requestPermissions(
                 requireActivity(),
                 arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                 MY_PERMISSIONS_REQUEST_LOCATION
             )
         }
     }*/
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            binding.allowLocationBtn.text = getString(R.string.continue_text)
            showToast("granted")
        }
        else{
            showToast("Denied")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}