package com.example.appcc.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.DialogFragment
import com.example.appcc.R
import com.example.appcc.databinding.DialogSetupLanguageBinding
import com.example.appcc.utils.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupLanguageDialog(
    private val lang: String, private val setupLanguage: (String) -> Unit = {}
) : DialogFragment(),View.OnClickListener {
    private lateinit var binding: DialogSetupLanguageBinding
    private var chooseLanguage: String = Const.ENGLISH

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DialogSetupLanguageBinding.inflate(layoutInflater, container, false)
        initUi()
        initListener()
        isCancelable = true
        return binding.root
    }

    private fun initUi() {
        when {
            lang == "en" || lang.lowercase() == "english" -> binding.tvEnglish.setTextColor(getResources().getColor(R.color.blue1))
            lang == "vi" || lang.lowercase() == "vietnamese" -> binding.tvVietNameese.setTextColor(getResources().getColor(R.color.blue1))

//            lang == "fr" || lang.lowercase() == "french" -> binding.rbFrench.isChecked = true
//            lang == "de" || lang.lowercase() == "german" -> binding.rbGerman.isChecked = true
//            lang == "in" || lang.lowercase() == "indonesia" -> binding.rbIndonesia.isChecked = true
//            lang == "ru" || lang.lowercase() == "russian" -> binding.rbRussian.isChecked = true
//            lang == "es" || lang.lowercase() == "spanish" -> binding.rbSpanish.isChecked = true
            else -> binding.tvEnglish.setTextColor(getResources().getColor(R.color.blue1))
        }
    }


    private fun initListener() {
        binding.tvEnglish.setOnClickListener {
            chooseLanguage = Const.ENGLISH
            Log.d(
                "TAG", "initListener: "+lang)
//            binding.rbEnglish.isChecked = true
            setupLanguage.invoke(chooseLanguage)
            this.dismiss()

        }

        binding.tvVietNameese.setOnClickListener {
            chooseLanguage = Const.VIETNAMESE
            Log.d("TAG", "initListener: "+lang)
            setupLanguage.invoke(chooseLanguage)
            this.dismiss()
//            binding.rbVietnamese.isChecked = true

        }


    }

    override fun getTheme(): Int {
        return R.style.AlertDialog
    }

    override fun onClick(v: View?) {
//        when (view) {
//            binding.tvEnglish -> {
//                setupLanguage(Const.ENGLISH)
//                dismiss()
//            }
//            binding.tvVietNameese -> {
//                setupLanguage(Const.VIETNAMESE)
//                dismiss()
//            }
//            binding.rbSpanish -> {
//                binding.rbSpanish.isChecked = true
//                setupLanguage(Constants.SPANISH)
//                dismiss()
//            }
//            binding.rbFrench -> {
//                binding.rbFrench.isChecked = true
//                setupLanguage(Constants.FRENCH)
//                dismiss()
//            }
//            binding.rbGerman -> {
//                binding.rbGerman.isChecked = true
//                setupLanguage(Constants.GERMAN)
//                dismiss()
//            }
//            binding.rbIndonesia -> {
//                binding.rbIndonesia.isChecked = true
//                setupLanguage(Constants.INDONESIAN)
//                dismiss()
//            }
//            binding.rbRussian -> {
//                binding.rbRussian.isChecked = true
//                setupLanguage(Constants.RUSSIAN)
//                dismiss()
//            }
//            else -> {}
//        }

    }
}