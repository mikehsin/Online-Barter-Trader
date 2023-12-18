package com.example.csci3130project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RegisterPageActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase database;
    DatabaseReference emailRef = null;
    DatabaseReference userNameRef = null;
    DatabaseReference passwordRef = null;
    DatabaseReference PreferenceRef = null;
    DatabaseReference FriendRef = null;

    private EditText input_emailAddress, input_userName, input_password, input_confirmPassword;
    public static List<String> db_emailAddress = new ArrayList<>();
    public static List<String> db_username = new ArrayList<>();
    public long id = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //attaching the event handler
        Button registerButton = findViewById(R.id.btnRegister_RegisterPage);
        registerButton.setOnClickListener(this);

        //initiating the Firebase
        initializeDatabase();
        db_emailAddress = retrieveFirebaseData(emailRef, db_emailAddress);
        db_username = retrieveFirebaseData(userNameRef, db_username);
    }

    protected void initializeDatabase() {
        //initialize database and reference field objects
        database = FirebaseDatabase.getInstance("https://csci3130-b92f1-default-rtdb.firebaseio.com/");
        emailRef = database.getReference("Email");
        userNameRef = database.getReference("Username");
        passwordRef = database.getReference("Password");
        PreferenceRef = database.getReference("Preference");
        FriendRef = database.getReference("Friend");
    }

    protected String getEmailAddress() {
        input_emailAddress = findViewById(R.id.eamilAddress_RegisterPage);
        return input_emailAddress.getText().toString().trim();
    }

    protected String getUserName() {
        input_userName = findViewById(R.id.userName_RegisterPage);
        return input_userName.getText().toString().trim();
    }

    protected String getPassword() {
        input_password = findViewById(R.id.password_RegisterPage);
        return input_password.getText().toString().trim();
    }

    protected String getConfirmPassword() {
        input_confirmPassword = findViewById(R.id.confirmPassword_RegisterPage);
        return input_confirmPassword.getText().toString().trim();
    }

    protected boolean isEmptyInput(String userName) {
        return userName.isEmpty();
    }

    protected boolean isValidEmailAddress(String emailAddress) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
        //retrieved from csci3130 a2 solution https://git.cs.dal.ca/masud/csci-3130-a2-sol-winter2021.git
    }

    protected boolean isAlphanumericUserName(String userName) {
        //your business logic goes here!
        return userName.matches("[A-Za-z0-9]+");
        //regex pattern from https://regex101.com/
    }

    protected boolean isValidPassword(String password) {
        //your business logic goes here!
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()])(?=\\S+$).{8,16}$");
        //regex pattern from https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
        //return password.matches("[a-zA-Z0-9\\\\!\\\\@\\\\#\\\\$]{8,24}")&&password.length()>=8&&password.length()<=16;
        //8-16 characters at least 1 Alphabet, 1 Number and 1 Special Character
        //regex pattern from https://stackoverflow.com/questions/36574183/how-to-validate-password-field-in-android
    }

    protected void switch2WelcomeWindow() {
        //clean the user input
        input_emailAddress.setText("");
        input_userName.setText("");
        input_password.setText("");
        input_confirmPassword.setText("");
        Intent intent = new Intent(RegisterPageActivity.this, MainActivity.class);
        //putExtrea() from android developer website
        startActivity(intent);
    }

    protected void saveEmailToFirebase(final String emailAddress) {
        //save email to Firebase
        emailRef.child(String.valueOf(id)).setValue(emailAddress);
    }

    protected void saveUserNameToFirebase(final String userName) {
        //save user name to Firebase
        userNameRef.child(String.valueOf(id)).setValue(userName);
    }

    protected void savePasswordToFirebase(final String password) {
        //save email to Firebase
        passwordRef.child(String.valueOf(id)).setValue(password);
    }

    protected void setStatusMessage(String message) {
        //show the message of register status
        //Toast.makeText(RegisterPageActivity.this, message, Toast.LENGTH_LONG).show();
        TextView message_RegisterPage = findViewById(R.id.message_RegisterPage);
        message_RegisterPage.setText(message);
    }

    protected List<String> retrieveFirebaseData(DatabaseReference dataRef, List<String> db_dataList){
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
                while (itr.hasNext()) {
                    String db_data = (String) itr.next().getValue();
                    db_dataList.add(db_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RegisterPageActivity.this, "Fail to read data", Toast.LENGTH_LONG).show();
            }
        });
        return db_dataList;
    }

    @Override
    public void onClick(View view) {
        String emailAddress = getEmailAddress();
        String userName = getUserName();
        String password = getPassword();
        String confirmPassword = getConfirmPassword();
        String errorMessage = "";
        String invalidEmail = getResources().getString(R.string.INVALID_EMAIL_ADDRESS);
        String invaliduserName = getResources().getString(R.string.NON_ALPHA_NUMERIC_USER_NAME);
        String invalidPassword = getResources().getString(R.string.INVALID_PASSWORD);

        if (isEmptyInput(userName)||isEmptyInput(emailAddress)||isEmptyInput(password)||isEmptyInput(confirmPassword)) {
            errorMessage = getResources().getString(R.string.EMPTY_STRING);
        }
        else if (!isAlphanumericUserName(userName)){
            errorMessage=invaliduserName;
        }
        else if (!isValidEmailAddress(emailAddress))
        {
            errorMessage=invalidEmail;
            //errorMessage = InvalidInput;
        }
        else if(!isValidPassword(password)){
            errorMessage=invalidPassword;
        }
        else if(!confirmPassword.equals(password)){
            errorMessage="Confirm Password is not match!";
        }
        else if(db_emailAddress.contains(emailAddress)){
            errorMessage = "Sorry, the email address has been registered.";
        }
        else if(db_username.contains(userName)) {
            errorMessage = "Sorry, the username has been used.";
        }
        else {
            errorMessage = "";
        }

        //check for valid user name and valid email email address
        //no errors were found!
        if (errorMessage.isEmpty()) {
            switch2WelcomeWindow();
            saveEmailToFirebase(emailAddress);
            saveUserNameToFirebase(userName);
            savePasswordToFirebase(password);
            PreferenceRef.child(String.valueOf(id)).setValue("");
            FriendRef.child(String.valueOf(id)).setValue("Cat");
            Toast.makeText(RegisterPageActivity.this, "Good Job", Toast.LENGTH_LONG).show();
        } else {
            setStatusMessage(errorMessage);
        }

    }
}