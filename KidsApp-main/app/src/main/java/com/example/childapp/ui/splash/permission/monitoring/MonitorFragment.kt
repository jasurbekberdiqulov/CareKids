package com.example.childapp.ui.splash.permission.monitoring


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.databinding.FragmentMonitorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MonitorFragment : Fragment() {
    private var _binding: FragmentMonitorBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonitorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setMonitorBtn.setOnClickListener {
            findNavController().navigate(R.id.action_monitorFragment_to_monitorSettingsFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}