package com.android.trustingsocial.test.viewmodal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.trustingsocial.test.ui.screenstate.ScreenState

open class BaseViewModal<T>(application: Application) : AndroidViewModel(application) {

    private val _modalState = MutableLiveData<ScreenState<T>>()
    val modalState : LiveData<ScreenState<T>>
        get() = _modalState


    protected fun postScreenState(state : ScreenState<T>) {
        _modalState.postValue(state)
    }
}