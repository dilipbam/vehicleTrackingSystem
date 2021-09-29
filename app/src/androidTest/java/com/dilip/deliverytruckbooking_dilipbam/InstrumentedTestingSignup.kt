package com.dilip.deliverytruckbooking_dilipbam

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.base.RootViewPicker_Factory.newInstance
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dilip.deliverytruckbooking_dilipbam.ui.SignUpActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.Exception
import java.lang.reflect.Array.newInstance


@LargeTest
@RunWith(AndroidJUnit4::class)
class InstrumentedTestingSignup {


    @Test
    fun testSignUpFragment() {

      val scenario = launchFragmentInContainer<CustomerSignUp>()
            Espresso.onView(ViewMatchers.withId(R.id.etFname))
                .perform(ViewActions.typeText("santoo"))

            Thread.sleep(2000)
            Espresso.onView(ViewMatchers.withId(R.id.etMname))
                .perform(ViewActions.typeText("shrestha"))
            Espresso.onView(ViewMatchers.withId(R.id.etLname))
                .perform(ViewActions.typeText("don"))
            Espresso.onView(ViewMatchers.withId(R.id.etuName))
                .perform(ViewActions.typeText("santoodon"))
            Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.typeText("don12345"))
            Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.typeText("don123@gmail.com"))
            Espresso.onView(ViewMatchers.withId(R.id.etContact))
                .perform(ViewActions.typeText("donno1"))

            Espresso.closeSoftKeyboard()
            Espresso.onView(ViewMatchers.withId(R.id.btnSignup))
                .perform(ViewActions.click())

            Thread.sleep(2000)

            Espresso.onView(ViewMatchers.withId(R.id.tvSignup))
                .check(ViewAssertions.matches(ViewMatchers.withText("signup")))

    }
}

