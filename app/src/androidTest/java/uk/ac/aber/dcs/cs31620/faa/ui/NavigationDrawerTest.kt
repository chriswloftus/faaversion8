package uk.ac.aber.dcs.cs31620.faa.ui

import android.view.Gravity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.DrawerMatchers
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.aber.dcs.cs31620.faa.R
import androidx.test.core.app.ActivityScenario.launch

@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationDrawerTest {

    @Test
    fun clickOnAndroidHomeIcon_OpensNavigation(){
        val scenario = launch(MainActivity::class.java)

        // Check that left drawer is closed at startup
        Espresso.onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.

        // We're going to obtain the open drawer content description
        var openDrawerDesc = ""

        // Do some stuff with the activity. In this
        // case we want to obtain the content description value
        // from our string resource

        scenario.onActivity { activity ->

            openDrawerDesc = activity
                .getString(R.string.nav_open_drawer)
        }
        // Continue with the testing

        // Find the view with the content description and then open it
        Espresso.onView(ViewMatchers.withContentDescription(openDrawerDesc))
            .perform(ViewActions.click())

        // Check if drawer is open
        Espresso.onView(withId(R.id.drawer_layout))
            .check(ViewAssertions.matches(DrawerMatchers.isOpen(Gravity.LEFT))) // Left drawer is open open.

        // Close the scenario so doesn't affect other tests
        scenario.close()
    }
}