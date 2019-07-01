package com.android.trustingsocial.test

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.trustingsocial.test.ui.MainActivity
import org.apache.tools.ant.Main
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoanInputValidateBehavior {

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    val resources = ApplicationProvider.getApplicationContext<LoanApplication>().resources

    @Before
    fun setup() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test fun notInputPhoneTest() {
        Thread.sleep(5000)
        // WHEN
        onView(withId(R.id.btnSubmit)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSubmit)).perform(scrollTo(), click())
        onView(withId(R.id.eTxtPhone)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_phone_error))))
        // THEN
    }

}