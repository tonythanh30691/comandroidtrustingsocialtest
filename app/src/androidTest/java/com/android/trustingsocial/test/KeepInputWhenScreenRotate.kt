package com.android.trustingsocial.test

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.trustingsocial.test.ui.MainActivity
import com.android.trustingsocial.test.util.testinghelper.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.*
import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4::class)
class KeepInputWhenScreenRotate {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        activityRule.launchActivity(null)
        registerIdlingResource()
    }

    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test fun keepAllInputInformationWhenRotate() {
        // Rotate screen to PORTRAIT first
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Keep Phone information when rotate
        onView(withId(R.id.eTxtPhone))
            .perform(typeText(InputTestData.PHONE_NUMBER_VALID)).perform(closeSoftKeyboard())

        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onView(withId(R.id.eTxtPhone)).check(matches(withText(InputTestData.PHONE_NUMBER_VALID)))
        onView(withId(R.id.eTxtPhone)).check(matches(hasFocus()))

        // Keep Name information when rotate
        onView(withId(R.id.eTxtName))
            .perform(typeText(InputTestData.VALID_NAME)).perform(closeSoftKeyboard())

        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        onView(withId(R.id.eTxtName)).check(matches(withText(InputTestData.VALID_NAME)))
        onView(withId(R.id.eTxtName)).check(matches(hasFocus()))

        // Keep National ID information when rotate
        onView(withId(R.id.eTxtNationalId))
            .perform(typeText(InputTestData.VALID_ID)).perform(closeSoftKeyboard())

        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onView(withId(R.id.eTxtNationalId)).check(matches(withText(InputTestData.VALID_ID)))
        onView(withId(R.id.eTxtNationalId)).check(matches(hasFocus()))

    }

    @Test fun keepSpinnerPositionWhenRotate() {
        // Rotate screen to PORTRAIT first
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Select [InputTestData.SELECTED_PROVINCE] item on Province Spinner
        onView(withId(R.id.spnAddress)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(InputTestData.SELECTED_PROVINCE))).perform(click())
        onView(withId(R.id.spnAddress)).check(matches(withSpinnerText(containsString(InputTestData.SELECTED_PROVINCE))))

        // Check result when rotate
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        onView(withId(R.id.spnAddress)).check(matches(withSpinnerText(containsString(InputTestData.SELECTED_PROVINCE))))

        // Select [InputTestData.SELECTED_INCOME] item on Income Spinner
        onView(withId(R.id.spnIncome)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(InputTestData.SELECTED_INCOME))).perform(click())
        onView(withId(R.id.spnIncome)).check(matches(withSpinnerText(containsString(InputTestData.SELECTED_INCOME))))

        // Check result when rotate
        activityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onView(withId(R.id.spnIncome)).check(matches(withSpinnerText(containsString(InputTestData.SELECTED_INCOME))))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }

}