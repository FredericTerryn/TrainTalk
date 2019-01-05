package com.personal.frederic.TrainTalk

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.matchers.JUnitMatchers.containsString
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
class MainTest {
    @get:Rule
    val actorActivityRule = ActivityTestRule(mainActivity::class.java)

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
        // onView(withId(R.id.stationInfoItem_textView)).check(matches(withText(containsString("Windspeed:"))))
    }
}