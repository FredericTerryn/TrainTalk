package com.personal.frederic.TrainTalk

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.matchers.JUnitMatchers.containsString
import org.junit.runner.RunWith
import android.support.test.espresso.assertion.ViewAssertions.matches

@RunWith(AndroidJUnit4::class)
class MainTest {
    @get:Rule
    val actorActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(actorActivityRule.activity.getIdlingResourceInTest())
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(actorActivityRule.activity.getIdlingResourceInTest())
    }

    @Test
    fun testStationInfo(){
          onView(withId(R.id.textView_Welcome)).check(matches(withText(containsString("Welcome"))))
    }

    @Test
    fun testPerforms(){
        onView((withId(R.id.navigation_item_favourites))).perform(click())
        onView((withId(R.id.textView))).perform(click())
        onView((withId(R.id.infoParent))).check(matches(isDisplayed()))
    }
}