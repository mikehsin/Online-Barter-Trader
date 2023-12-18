package com.example.csci3130project;

import com.example.csci3130project.model.Validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorTest {
    @Test
    public void testEmail(){
        String email = "botuma";
        assertFalse(Validator.validateEmail(email));
    }

    @Test
    public void testEmail1(){
        String email = "botuma@";
        assertFalse(Validator.validateEmail(email));
    }

    @Test
    public void testEmail2(){
        String email = "botuma@google";
        assertFalse(Validator.validateEmail(email));
    }

    @Test
    public void testEmail3(){
        String email = "botuma@google.com";
        assertTrue(Validator.validateEmail(email));
    }

    @Test
    public void testUsername(){
        String username = "";
        assertFalse(Validator.validateUsername(username));
    }

    @Test
    public void testUsername1(){
        String username = " ";
        assertFalse(Validator.validateUsername(username));
    }

    @Test
    public void testUsername2(){
        String username = "123";
        assertTrue(Validator.validateUsername(username));
    }

    @Test
    public void testPassword(){
        String password = "123";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void testPassword1(){
        String password = "123";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void testPassword2(){
        String password = "123";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void testPassword3(){
        String password = "123A1234";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void testPassword4(){
        String password = "123a1234";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void testPassword5(){
        String password = "123a1234A";
        assertTrue(Validator.validatePassword(password));
    }

}
