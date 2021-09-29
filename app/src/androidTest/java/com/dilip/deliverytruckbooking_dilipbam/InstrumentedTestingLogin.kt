package com.dilip.deliverytruckbooking_dilipbam

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.dilip.deliverytruckbooking_dilipbam.ui.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class InstrumentedTestingLogin {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)
    //Login testing
    @Test
    fun testLoginUI(){
        onView(withId(R.id.etuName))
                .perform(typeText("dilip"))
        onView(withId(R.id.etPassword))
                .perform(typeText("1234"))

        closeSoftKeyboard()
        onView(withId(R.id.btnLogin))
                .perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()))
    }
}