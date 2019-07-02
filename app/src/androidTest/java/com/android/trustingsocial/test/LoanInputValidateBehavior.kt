package com.android.trustingsocial.androidTest

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.trustingsocial.test.LoanApplication
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.ui.MainActivity

import org.junit.runner.RunWith
import com.android.trustingsocial.test.util.testinghelper.EspressoIdlingResource
import org.hamcrest.CoreMatchers.not
import org.junit.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.android.trustingsocial.test.InputTestData
import org.hamcrest.CoreMatchers.`is`


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

    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource())
    }

    @Test fun notInputPhone_showErrorMessage() {
        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.eTxtPhone)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_phone_error))))
        onView(withId(R.id.eTxtPhone)).check(matches(hasFocus()))
    }

    @Test fun inputInvalidPhone_showErrorMessage() {
        onView(withId(R.id.eTxtPhone)).perform(typeText(InputTestData.PHONE_NUMBER_INVALID)).perform(closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.eTxtPhone)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_phone_error))))
        onView(withId(R.id.eTxtPhone)).check(matches(hasFocus()))
    }

    @Test fun inputValidPhone_showErrorMessageOnName() {
        onView(withId(R.id.eTxtPhone)).perform(typeText(InputTestData.PHONE_NUMBER_VALID)).perform(closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.eTxtPhone)).check(matches(not(hasErrorText(resources.getString(R.string.loan_edit_text_default_phone_error)))))
        onView(withId(R.id.eTxtName)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_name_error))))
        onView(withId(R.id.eTxtName)).check(matches(hasFocus()))
    }

    @Test fun inputValidPhoneName_notShowErrorOnID() {
        onView(withId(R.id.eTxtPhone)).perform(typeText(InputTestData.PHONE_NUMBER_VALID)).perform(closeSoftKeyboard())
        onView(withId(R.id.eTxtName)).perform(typeText(InputTestData.VALID_NAME)).perform(closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.eTxtNationalId)).check(matches(not(hasErrorText(resources.getString(R.string.loan_edit_text_default_id_error)))))
        onView(withId(R.id.eTxtName)).check(matches(hasFocus()))
    }

    @Test fun inputInvalidID_showErrorMessage() {
        onView(withId(R.id.eTxtPhone)).perform(typeText(InputTestData.PHONE_NUMBER_VALID)).perform(closeSoftKeyboard())
        onView(withId(R.id.eTxtName)).perform(typeText(InputTestData.VALID_NAME)).perform(closeSoftKeyboard())
        onView(withId(R.id.eTxtNationalId)).perform(typeText(InputTestData.INVALID_ID)).perform(closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withId(R.id.eTxtNationalId)).check(matches(hasErrorText(resources.getString(R.string.loan_edit_text_default_id_error))))
        onView(withId(R.id.eTxtNationalId)).check(matches(hasFocus()))
    }

    @Test fun notSelectIncomeGreaterThanRequire_showToastMessage() {
        onView(withId(R.id.eTxtPhone)).perform(typeText(InputTestData.PHONE_NUMBER_VALID)).perform(closeSoftKeyboard())
        onView(withId(R.id.eTxtName)).perform(typeText(InputTestData.VALID_NAME)).perform(closeSoftKeyboard())
        onView(withId(R.id.eTxtNationalId)).perform(typeText(InputTestData.VALID_ID)).perform(closeSoftKeyboard())

        onView(withId(R.id.btnSubmit)).perform(click())

        onView(withText(R.string.loan_edit_text_default_income_error)).
            inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource())
    }



}