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
public class RegisterInstrumentedTest {



    @Rule
    public ActivityScenarioRule<RegisterPageActivity> myRule1 = new ActivityScenarioRule<>(RegisterPageActivity.class);
    public IntentsTestRule<RegisterPageActivity> myIntentRule=new IntentsTestRule<>(RegisterPageActivity.class);

    @BeforeClass
    public static void setup(){
        Intents.init();
    }






    /***At-1**/
    @Test
    public void checkIfMoved2HomePage(){
        onView(withId(R.id.userName_RegisterPage)).perform(typeText("yijiu"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.eamilAddress_RegisterPage)).perform(typeText("yijiu@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_RegisterPage)).perform(typeText("abcd1123!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPassword_RegisterPage)).perform(typeText("abcd1123!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnRegister_RegisterPage)).perform(click());
        intended(hasComponent(HomePageActivity.class.getName()));
    }

    /***At-2**/
    @Test
    public void checkIfNotInputInfor(){
        onView(withId(R.id.userName_RegisterPage)).perform(typeText("yijiu233"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.eamilAddress_RegisterPage)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_RegisterPage)).perform(typeText("abcd1123."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPassword_RegisterPage)).perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnRegister_RegisterPage)).perform(click());
        onView(withId(R.id.message_RegisterPage)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /***At-3**/
    @Test
    public void checkIfNotInputInforIncorrect(){
        onView(withId(R.id.userName_RegisterPage)).perform(typeText("yijiu233"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.eamilAddress_RegisterPage)).perform(typeText("yj1234dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_RegisterPage)).perform(typeText("abcd1123."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPassword_RegisterPage)).perform(typeText("abcd1123."));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnRegister_RegisterPage)).perform(click());
        onView(withId(R.id.message_RegisterPage)).check(matches(withText("Something is wrong!")));
    }

    /***At-4**/
    @Test
    public void checkIfUserNameExist(){
        onView(withId(R.id.userName_RegisterPage)).perform(typeText("mike"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.eamilAddress_RegisterPage)).perform(typeText("abcd1234@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_RegisterPage)).perform(typeText("abcd1123!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPassword_RegisterPage)).perform(typeText("abcd1123!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnRegister_RegisterPage)).perform(click());
        onView(withId(R.id.message_RegisterPage)).check(matches(withText("Sorry, the username has been used.")));
    }

    /***At-4-1**/
    @Test
    public void checkIfEmailExist(){
        onView(withId(R.id.userName_RegisterPage)).perform(typeText("miaomiaomi"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.eamilAddress_RegisterPage)).perform(typeText("mike@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_RegisterPage)).perform(typeText("abcd1123!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPassword_RegisterPage)).perform(typeText("abcd1123!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btnRegister_RegisterPage)).perform(click());
        onView(withId(R.id.message_RegisterPage)).check(matches(withText("Sorry, the email address has been registered.")));
    }

}
