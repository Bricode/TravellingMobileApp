package op.mobile.app.dev.chalbr1.travelling

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed


@RunWith(AndroidJUnit4::class)
class LoginFragmentInstrumentedTest {

    // lateinit allows you to initialise a variable later
    private lateinit var emailAddressToBeTyped: String
    private lateinit var passwordToBeTyped: String
    private lateinit var emailAddressDoesntExist: String
    private var decorView: View? = null
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Initialising variables marked with the lateinit keyword
        emailAddressToBeTyped = "bcharm1100@gmail.com"
        emailAddressDoesntExist = "test@test.com"
        passwordToBeTyped = "P@ssw0rd"
    }

    fun loadDecorView() {
        activityRule.scenario.onActivity { activity: MainActivity ->
            decorView = activity.window.decorView
        }
    }
    @Test
    fun login() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailAddressToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_login)).perform(click())
        Thread.sleep(4000)
        onView(allOf(withId(R.id.tv_title), withText("Australia"))).check(matches(withText("Australia")))
            // matches(isDisplayed()) is a ViewAssertion
            .check(matches(isDisplayed()))
    }
    @Test
    fun loginFailed() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailAddressDoesntExist), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_login)).perform(click())
        Thread.sleep(1000)
        onView(withText(R.string.sign_in_failed)).inRoot(
            RootMatchers.withDecorView(
                Matchers.not(
                    decorView
                )
            )).check(matches(isDisplayed()));
        onView(withId(R.id.et_email_address)).check(matches(isDisplayed()))
    }

    @Test
    fun loginMissingPassword() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailAddressDoesntExist), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_login)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.et_password)).check(matches(hasErrorText("Password is required")))
    }
    @Test
    fun loginMissingEmail() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_login)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.et_email_address)).check(matches(hasErrorText("Email is required")))
    }
}