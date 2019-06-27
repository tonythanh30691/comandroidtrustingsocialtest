package com.android.trustingsocial.test.util.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:text")
    fun TextView.bindTextFromIn(value : Int) {
        setText(value.toString())
    }
}
