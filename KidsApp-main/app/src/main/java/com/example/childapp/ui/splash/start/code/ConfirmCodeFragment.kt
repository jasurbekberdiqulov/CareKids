package com.example.childapp.ui.splash.start.code

import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.childapp.R
import com.example.childapp.databinding.FragmentConfirmCodeBinding
import com.example.childapp.db.room.AppDao
import com.example.childapp.db.room.Code
import com.example.childapp.common.utils.checkedEdittextIsNotEmpty
import com.example.childapp.common.utils.hideKeyBoard
import com.example.childapp.common.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ConfirmCodeFragment : Fragment(R.layout.fragment_confirm_code) {

    private var _binding: FragmentConfirmCodeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var appDao: AppDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewClick()
    }

    private fun onViewClick() {
        val mCannotCode = SpannableString(getString(R.string.cannot_find_code))
        mCannotCode.setSpan(
            UnderlineSpan(),
            0,
            mCannotCode.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.apply {
            cantFindCode.text = mCannotCode
            cantFindCode.setOnClickListener { showToast(getString(R.string.we_send_new_code)) }
            password0.addTextChangedListener(MyTextWtcher(password1))
            password1.addTextChangedListener(MyTextWtcher(password2))
            password2.addTextChangedListener(MyTextWtcher(password3))
            password3.addTextChangedListener(MyTextWtcher(password4))
            password4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    hideKeyBoard(password4)
                }

                override fun afterTextChanged(s: Editable?) {}

            })

            continueBtn.setOnClickListener {
                if (checkedEdittextIsNotEmpty(password0) &&
                    checkedEdittextIsNotEmpty(password1) &&
                    checkedEdittextIsNotEmpty(password2) &&
                    checkedEdittextIsNotEmpty(password3) &&
                    checkedEdittextIsNotEmpty(password4)
                ) {
                    val code = password0.text.toString() +
                            password1.text.toString() +
                            password2.text.toString() +
                            password3.text.toString() +
                            password4.text.toString()
                    appDao.insertConfirmCode(Code(0, code))
                    findNavController().navigate(R.id.action_confirmCodeFragment_to_confirmAgeFragment)
                } else {
                    showToast(getString(R.string.please_enter_recieved_code))
                }
            }
        }


    }

    private fun MyTextWtcher(editText: EditText): TextWatcher {
        val mTextEditorWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                editText.requestFocus()
            }

            override fun afterTextChanged(s: Editable) {}
        }
        return mTextEditorWatcher
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}