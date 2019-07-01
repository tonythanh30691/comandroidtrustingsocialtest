package com.android.trustingsocial.test.util.testinghelper

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class CountingIdlingResource : IdlingResource
{
    private var resourceCallback : IdlingResource.ResourceCallback? = null
    private var counter = AtomicInteger(0)

    override fun getName(): String = CountingIdlingResource::class.java.simpleName

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decreasement() {
        var counterValue = counter.decrementAndGet()
        if (counterValue == 0) {
            resourceCallback?.let {
                it.onTransitionToIdle()
            }
        }

        if (counterValue < 0) {
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }

}