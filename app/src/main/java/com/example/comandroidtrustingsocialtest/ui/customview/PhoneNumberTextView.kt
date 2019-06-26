package com.example.comandroidtrustingsocialtest.ui.customview

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.example.comandroidtrustingsocialtest.R

class PhoneNumberTextView @JvmOverloads constructor(context: Context,
                                                    attributeSet: AttributeSet? = null,
                                                    defStyleAttr : Int = android.R.attr.editTextStyle) : EditText(context, attributeSet, defStyleAttr) {

    companion object {
        val PHONE_PREFIXS = arrayListOf("0120",
            "0121", "0122", "0123", "0124", "0125", "0126", "0127", "0128", "0129",
            "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "0186",
            "0188", "0199", "086", "088", "089", "090", "091", "092", "093", "094",
            "095", "096", "097", "098", "099").joinToString("|", prefix = "(", postfix = ")")
        val PHONE_POSFIX_LENGHT = 7
        val PHONE_MAX_LENGHT = 11
        val PHONE_REGEX_PATTERN = "^($PHONE_PREFIXS)\\d{$PHONE_POSFIX_LENGHT}$"

    }

    init {
        inputType = InputType.TYPE_CLASS_NUMBER
        filters = arrayOf<InputFilter>(InputFilter.LengthFilter(PHONE_MAX_LENGHT))
    }

    fun isPhoneValid() : Boolean {
        val textContent = text.toString().trim()
        var resulr =  PHONE_REGEX_PATTERN.toRegex().matches(textContent)
        if (!resulr) {
            error = resources.getString(R.string.error_phone_format)
        }
        return resulr
    }



}