package com.android.trustingsocial.test.util

import androidx.lifecycle.ViewModel
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> FragmentActivity.getViewModal() : T {
    return ViewModelProviders.of(this)[T::class.java]
}