package com.android.trustingsocial.test.util.binding

import android.widget.TextView
import androidx.databinding.BindingConversion
import java.text.DecimalFormat

object BindingConverter {
    @BindingConversion
    @JvmStatic
    fun convertToCurrency(newValue : Int) : String {
        return DecimalFormat("#,###").format(newValue.toInt())
    }
}