package com.nasa.nasaimages.views

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.adevinta.android.barista.assertion.BaristaListAssertions
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListNotEmpty
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.adevinta.android.barista.interaction.BaristaSleepInteractions.sleep
import com.nasa.nasaimages.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun `test_load_images`() {

        sleep(1500)
        Espresso.onView(ViewMatchers.withText("NASA Images"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        sleep(100)

        assertListNotEmpty(R.id.recyclerview)

        sleep(500)

    }

    @Test
    fun `test_images_details`() {

        sleep(1500)
        onView(ViewMatchers.withText("NASA Images"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        sleep(100)

        assertListNotEmpty(R.id.recyclerview)

        sleep(500)

        clickListItem(R.id.recyclerview, 2)

        sleep(500)

        onView(ViewMatchers.withText("Show Details"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        sleep(500)

        onView(withId(R.id.nasa_image_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        sleep(500)

        onView(ViewMatchers.withText("Electric Night"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        sleep(500)

        onView(ViewMatchers.withText("Electric Night"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.swipeDown())

    }

}