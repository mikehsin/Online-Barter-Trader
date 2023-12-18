package com.example.csci3130project;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 * retrieved from csci3130 a2 solution https://git.cs.dal.ca/masud/csci-3130-a2-sol-winter2021.git
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {



    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);
    public IntentsTestRule<MainActivity> myIntentRule=new IntentsTestRule<>(MainActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }






    /***At-1**/
    @Test
    public void checkIfMoved2WelcomePage(){
        onView(withId(R.id.userName_LoginPage)).perform(typeText("mike"));
        onView(withId(R.id.password_LoginPage)).perform(typeText("abcd1234!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.mBtnToLogin)).perform(click());
        intended(hasComponent(HomePageActivity.class.getName()));
    }

    /*** AT-3**/
    @Test
    public void checkIfUserNameIsEmpty() {
        onView(withId(R.id.userName_LoginPage)).perform(typeText(""));
        onView(withId(R.id.password_LoginPage)).perform(typeText("abcd1234!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.mBtnToLogin)).perform(click());
        onView(withId(R.id.message_loginPage)).check(matches(withText(R.string.ERROR_IN_LOGIN)));
    }

    /*** AT-3-1**/
    @Test
    public void checkIfPasswordIsEmpty() {
        onView(withId(R.id.userName_LoginPage)).perform(typeText("mike"));
        onView(withId(R.id.password_LoginPage)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.mBtnToLogin)).perform(click());
        onView(withId(R.id.message_loginPage)).check(matches(withText(R.string.ERROR_IN_LOGIN)));
    }

    /*** AT-3-2**/
    @Test
    public void checkIfInvaildUser() {
        onView(withId(R.id.userName_LoginPage)).perform(typeText("mike1"));
        onView(withId(R.id.password_LoginPage)).perform(typeText("asdavac@"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.mBtnToLogin)).perform(click());
        onView(withId(R.id.message_loginPage)).check(matches(withText(R.string.ERROR_IN_LOGIN)));
    }

}
