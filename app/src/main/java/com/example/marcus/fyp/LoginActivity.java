package com.example.marcus.fyp;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView ;



public class LoginActivity extends AppCompatActivity {

    private EditText userid;
    private EditText userpassword;
    private Button Login;
    private TextView registerLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* final EditText etUserID =(EditText) findViewById(R.id.etUserID);
        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final Button bLogin =(Button) findViewById(R.id.bLogin);
        final TextView registerLink =(TextView) findViewById(R.id.tvRegister);
*/
        userid = (EditText) findViewById(R.id.etUserID);
        userpassword = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.bLogin);
        registerLink = (TextView) findViewById(R.id.tvRegister);

    }

    public void jumpToSetting(View view){
        Intent goToSetting = new Intent(this, RegisterActivity.class);
        startActivity(goToSetting);

    }
    public void jumpToHome(View view){
        Intent goToSetting = new Intent(this, HomePageActivity.class);
        startActivity(goToSetting);
    }





}

