package ru.shadowsparky.scheduler.schedulers_menu

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.shadowsparky.scheduler.R
import ru.shadowsparky.scheduler.adapters.SchedulersList


@RunWith(AndroidJUnit4::class)
class SchedulersViewTest {
    @get:Rule public val activityActivityTestRule = ActivityTestRule<SchedulersView>(SchedulersView::class.java)
    val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun displayTest() {
        onView(withId(R.id.schedule_add)).check(matches(isDisplayed()))
        onView(withId(R.id.list)).check(matches(isDisplayed()))
    }

    @Test
    fun addNewScheduleClicked() {
        onView(withId(R.id.schedule_add)).perform(click())
    }

    @Test
    fun showFirstScheduleClicked() {
        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SchedulersList.MainViewHolder>(0, click())
        )
    }
}