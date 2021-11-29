package op.mobile.app.dev.chalbr1.travelling

import android.app.PendingIntent.getActivity
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.RootMatchers

import org.hamcrest.Matchers.*


@RunWith(AndroidJUnit4::class)
class RegisterFragmentInstrumentedTest {
    // lateinit allows you to initialise a variable later
    private lateinit var emailAddressExistsToBeTyped: String
    private lateinit var passwordToBeTyped : String
    private lateinit var emailDoesntExist : String
    private lateinit var password2ToBeTyped : String
    private lateinit var emailBadFormat : String
    private var decorView: View? = null
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Initialising variables marked with the lateinit keyword
        emailAddressExistsToBeTyped = "bcharm1100@gmail.com"
        passwordToBeTyped = "P@ssw0rd"
        password2ToBeTyped = "@ssw0rd"
        emailDoesntExist = "chalbr1@student.op.ac.nz"
        emailBadFormat = "testing"
    }

    fun loadDecorView() {
        activityRule.scenario.onActivity { activity: MainActivity ->
            decorView = activity.window.decorView
        }
    }
    @Test
    fun registerUserAlreadyExists() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.btn_sign_up)).perform(click())
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailAddressExistsToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.et_confirm_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_register)).perform(click())
        Thread.sleep(1000)
        onView(withText(R.string.user_already_exists)).inRoot(
            RootMatchers.withDecorView(
                not(
                    decorView
                )
            )).check(matches(isDisplayed()));
    }
    @Test
    fun registerUserSuccessfully() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.btn_sign_up)).perform(click())
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailDoesntExist), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.et_confirm_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_register)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()))
            // matches(isDisplayed()) is a ViewAssertion
    }
    @Test
    fun passwordsDontMatch() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.btn_sign_up)).perform(click())
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailDoesntExist), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.et_confirm_password))
            .perform(typeText(password2ToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_register)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.et_confirm_password)).check(matches(hasErrorText("Password doesnt match")))
    }
    @Test
    fun emailFormattedIncorrectly() {
        Thread.sleep(4000)
        // withId(R.id.et_email_address) is a ViewMatcher
        onView(withId(R.id.btn_sign_up)).perform(click())
        onView(withId(R.id.et_email_address))
            // typeText() is a ViewAction
            .perform(typeText(emailBadFormat), closeSoftKeyboard())
        onView(withId(R.id.et_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        onView(withId(R.id.et_confirm_password))
            .perform(typeText(passwordToBeTyped), closeSoftKeyboard())
        // click() is a ViewAction
        onView(withId(R.id.btn_register)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.et_email_address)).check(matches(hasErrorText("Email formatted incorrectly")))
    }
}