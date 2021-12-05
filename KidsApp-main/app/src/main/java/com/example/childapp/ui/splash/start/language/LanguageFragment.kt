package com.example.childapp.ui.splash.start.language

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ContentFrameLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.childapp.R
import com.example.childapp.databinding.FragmentLanguageBinding
import com.example.childapp.db.preferences.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.reword.Reword


@AndroidEntryPoint
class LanguageFragment : Fragment() {
    private var preferencesManager: PreferencesManager? = null
    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesManager = PreferencesManager(requireContext())
        navController = Navigation.findNavController(requireView())
        onViewsClick()
    }


    private fun onViewsClick() {
        with(binding) {
            languageEnglish.setOnClickListener {
                AppLocale.desiredLocale = AppLocale.supportedLocales[0]
                changeLanguage("en")
            }
            languageRus.setOnClickListener {
                AppLocale.desiredLocale = AppLocale.supportedLocales[1]
                changeLanguage("ru")
            }
            languageUzb.setOnClickListener {
                AppLocale.desiredLocale = AppLocale.supportedLocales[2]
                changeLanguage("uz")
            }
            nextBtn.setOnClickListener {
            navController!!.navigate(R.id.action_languageFragment_to_confirmCodeFragment)
            }
        }
    }

    private fun changeLanguage(language: String) {
        val rootView = requireActivity()
            .window
            .decorView
            .findViewById<ContentFrameLayout>(android.R.id.content)
        Reword.reword(rootView)
        preferencesManager?.language = language
    }


}