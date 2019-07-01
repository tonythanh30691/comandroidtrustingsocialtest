package com.android.trustingsocial.test.util.testinghelper

class EspressoIdlingResource {

    companion object {
        var INSTANCE = CountingIdlingResource()

        fun getIdlingResource() : CountingIdlingResource{
            return INSTANCE
        }

        fun increment() {
            getIdlingResource().increment()
        }

        fun decrement() {
            getIdlingResource().decreasement()
        }
    }

}