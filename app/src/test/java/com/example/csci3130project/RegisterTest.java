package com.example.csci3130project;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegisterTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    static RegisterPageActivity registerPageActivity;

    @BeforeClass
    public static void setup() {
        registerPageActivity = new RegisterPageActivity();
    }

    @Test
    public void checkIfUserNameIsEmpty() {
        assertTrue(registerPageActivity.isEmptyInput(""));
        assertFalse(registerPageActivity.isEmptyInput("xyz$56"));
    }

    @Test
    public void checkIfUserNameIsAlphaNumeric(){
        assertTrue(registerPageActivity.isAlphanumericUserName("xyz123"));
    }

    @Test
    public void checkIfUserNameIsNotAlphaNumeric(){
        assertFalse(registerPageActivity.isAlphanumericUserName(""));
        assertFalse(registerPageActivity.isAlphanumericUserName("&45abc^!"));
    }

    @Test
    public void checkIfEmailIsValid(){
        assertTrue(registerPageActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    @Test
    public void checkIfEmailIsInValid(){
        assertFalse(registerPageActivity.isValidEmailAddress("abc.123dal.ca"));
    }

    @Test
    public void checkIfEmailIsInValid1(){
        assertFalse(registerPageActivity.isValidEmailAddress("abc.123dal@.ca"));
    }

    @Test
    public void checkIfPasswordIsValid(){
        assertTrue(registerPageActivity.isValidPassword("Abcd1234!"));
    }

    @Test
    public void checkIfPasswordIsInValid(){
        assertFalse(registerPageActivity.isValidPassword("a_$%^$"));
    }

    @Test
    public void checkIfPasswordIsInValid2(){
        assertFalse(registerPageActivity.isValidPassword("Abcd123456"));
    }





    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
