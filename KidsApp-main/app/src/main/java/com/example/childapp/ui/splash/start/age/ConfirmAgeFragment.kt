package com.example.childapp.ui.splash.start.age

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.*
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.databinding.FragmentConfirmAgeBinding
import com.example.childapp.db.room.AppDao
import com.example.childapp.db.room.Child
import com.example.childapp.common.utils.checkedEdittextIsNotEmpty
import com.example.childapp.common.utils.hideKeyBoard
import com.example.childapp.common.utils.showLog
import com.example.childapp.common.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ConfirmAgeFragment : Fragment(R.layout.fragment_confirm_age) {

    private var _binding: FragmentConfirmAgeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appDao: AppDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmAgeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mItext = SpannableString(getString(R.string.i_accept))
        val the_terms_of_use = SpannableString(getString(R.string.the_terms_of_use))
        val and = SpannableString(getString(R.string.and))
        val privacy_policy = SpannableString(getString(R.string.privacy_policy))

        val flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        the_terms_of_use.setSpan(URLSpan("www.google.com"), 0, the_terms_of_use.length, flag);
        privacy_policy.setSpan(URLSpan("www.google.com"), 0, privacy_policy.length, flag);
        val builder = SpannableStringBuilder()
        builder.append(mItext)
        builder.append(" ")
        builder.append(the_terms_of_use)
        builder.append(" ")
        builder.append(and)
        builder.append(" ")
        builder.append(privacy_policy)

        binding.acceptTermsText.setText(builder)

        binding.apply {
            ageChildren.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    showLog(s!!.length.toString())
                    if (s.length == 2) {
                        hideKeyBoard(ageChildren)
                    }
                }
            })

            acceptTermsText.setOnClickListener {
                val uri: Uri =
                    Uri.parse("http://www.google.com") // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }


            acceptTermsBtn.setOnClickListener {
                if (!checkedEdittextIsNotEmpty(ageChildren)
                ) {
                    showToast(getString(R.string.please_enter_your_childs_age))
                } else if (!checkboxTerms.isChecked) {
                    showToast(getString(R.string.please_accept_terms_use))
                } else {
                    appDao.addChild(Child(0, ageChildren.text.toString(), accept = true))
                    findNavController().navigate(R.id.action_confirmAgeFragment_to_geoLocationFragment)

                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}