package com.android.trustingsocial.test.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModalFactory @Inject constructor(private val viewmodalsMap : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = viewmodalsMap[modelClass] ?: viewmodalsMap.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)

        }?.value ?: throw IllegalArgumentException("Unknow model class")

        return try {
            creator.get() as T
        } catch (e : Exception) {
            throw RuntimeException(e)
        }
    }
}