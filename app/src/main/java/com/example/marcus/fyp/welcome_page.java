package com.example.marcus.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome_page extends AppCompatActivity {

    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister =(Button)findViewById(R.id.btnRegister);


    }

    public void jumpToLogin(View view) {
        Intent goToLogin = new Intent(this, LoginActivity.class);
        startActivity(goToLogin);
    }
    public void jumpToRegister(View view){
        Intent goToRegister= new Intent(this,RegisterActivity.class);
        startActivity(goToRegister);
    }
}
