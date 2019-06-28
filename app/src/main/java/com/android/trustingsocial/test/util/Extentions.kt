package com.android.trustingsocial.test.util

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.getViewModal() : T {
    return ViewModelProviders.of(this)[T::class.java]
}

fun FragmentActivity.showToast(str : String, isLong : Boolean = false) {
    Toast.makeText(this, str, if(isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}