package com.android.trustingsocial.androidTest

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
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


@RunWith(AndroidJUnit4::class)
class LoanInputValidateBehavior {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)
    val resources = ApplicationProvider.getApplicationContext<LoanApplication>().resources

    @Before
    fun setup() {
        activityRule.launchActivity(null)
    }

    @Test fun notInputPhoneTest() {
        Thread.sleep(2000)
        // WHEN
        onView(withId(R.id.btnSubmit)).perform(click())
        onView(withId(R.id.eTxtPhone)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_phone_error))))
        // THEN
    }

}