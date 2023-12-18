package com.example.csci3130project;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import android.util.Patterns;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {
        mainActivity = new MainActivity();
    }

    @Test
    public void checkIfUserNameIsEmpty() {
        assertTrue(mainActivity.isEmptyInput(""));
        assertFalse(mainActivity.isEmptyInput("xyz$56"));
    }

    @AfterClass
    public static void tearDown(){
        System.gc();
    }
}
