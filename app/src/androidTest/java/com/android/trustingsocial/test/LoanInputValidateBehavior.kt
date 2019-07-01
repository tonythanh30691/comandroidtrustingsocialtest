package com.android.trustingsocial.androidTest

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.trustingsocial.test.LoanApplication
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.ui.MainActivity
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import com.android.trustingsocial.test.util.testinghelper.EspressoIdlingResource
import org.junit.After


@RunWith(AndroidJUnit4::class)
class LoanInputValidateBehavior {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    private val resources = ApplicationProvider.getApplicationContext<LoanApplication>().resources

    @Before
    fun setup() {
        activityRule.launchActivity(null)
        registerIdlingResource()
    }

    private fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test fun notInputPhoneTest() {
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.eTxtPhone)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_phone_error))))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }



}