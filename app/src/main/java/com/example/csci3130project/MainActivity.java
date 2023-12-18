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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference emailRef = null;
    DatabaseReference userNameRef = null;
    DatabaseReference passwordRef = null;
    
    private EditText userName, password;
    public static List<String> db_username = new ArrayList<>();
    public static List<String> db_password = new ArrayList<>();
    private TextView message_loginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the button login
        Button btnToLoginPage = findViewById(R.id.mBtnToLogin);
        btnToLoginPage.setOnClickListener(this);
        //find the button register
        Button btnToRegisterPage = findViewById(R.id.mBtnToRegisterPage);
        btnToRegisterPage.setOnClickListener(this);

        initializeDatabase();
        db_username = retrieveFirebaseData(userNameRef, db_username);
        db_password = retrieveFirebaseData(passwordRef, db_password);
    }

    protected void initializeDatabase() {
        //initialize database and reference field objects
        database = FirebaseDatabase.getInstance("https://csci3130-b92f1-default-rtdb.firebaseio.com/");
        emailRef = database.getReference("Email");
        userNameRef = database.getReference("Username");
        passwordRef = database.getReference("Password");

    }


    protected String getUserName() {
        userName = findViewById(R.id.userName_LoginPage);
        return userName.getText().toString().trim();
    }

    protected String getPassword() {
        password = findViewById(R.id.password_LoginPage);
        return password.getText().toString().trim();
    }

    protected boolean isEmptyInput(String userName) {
        return userName.isEmpty();
    }

    protected void setMessage(String message){
        message_loginPage = findViewById(R.id.message_loginPage);
        message_loginPage.setText(message);
    }

    protected List<String> retrieveFirebaseData(DatabaseReference dataRef, List<String> db_list){
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
                while (itr.hasNext()) {
                    String db_data = itr.next().getValue(String.class);
                    db_list.add(db_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Fail to read data", Toast.LENGTH_LONG).show();
            }
        });
        return db_list;
    }

    protected boolean loginChecking(List user, List passwd) {
        String str_username = getUserName();
        String str_password = getPassword();

        if(!user.contains(str_username)){
            return false;
        }
        else if(passwd.get(user.indexOf(str_username)).equals(str_password)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public void onClick(View view) {
        String str_username = getUserName();
        String str_password = getPassword();

        switch (view.getId()) {
            case R.id.mBtnToLogin:
                if(loginChecking(db_username, db_password)&&(!isEmptyInput(str_username)&& !isEmptyInput(str_password))){
                    userName.setText(""); password.setText(""); //set both input as null if the user log out
                    Intent intentToHome = new Intent(MainActivity.this, HomePageActivity.class);
                    intentToHome.putExtra("username", str_username);//send username to other activity
                    setMessage(getResources().getString(R.string.EMPTY_STRING));
                    startActivity(intentToHome);
                }
                else {
                    //Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_LONG).show();
                    setMessage(getResources().getString(R.string.ERROR_IN_LOGIN));
                }
                break;
            case R.id.mBtnToRegisterPage:
                Intent intent = new Intent(MainActivity.this, RegisterPageActivity.class);
                setMessage(getResources().getString(R.string.EMPTY_STRING));
                startActivity(intent);break;
            default: break;
        }

    }

}