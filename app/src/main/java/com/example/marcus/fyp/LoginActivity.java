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
import android.widget.Toast;

import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class LoginActivity extends AppCompatActivity {

    private EditText userid;
    private EditText userpassword;
    private EditText etPhone;
    private Button Login;
    private TextView registerLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        userid = (MaterialEditText) findViewById(R.id.etUserID);
        userpassword = (MaterialEditText) findViewById(R.id.etPassword);
        etPhone= (MaterialEditText)findViewById(R.id.etPhone);
        Login = (Button) findViewById(R.id.btnLogin);
        registerLink = (TextView) findViewById(R.id.tvRegister);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get user information
                        User user= dataSnapshot.child(etPhone.getText().toString()).getValue(User.class);
                        if(user.getPassword().equals(userpassword.getText().toString())) {
                            Toast.makeText(LoginActivity.this, "Sign in successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"You have entered wrong password!! ",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }


    public void jumpToHome(View view){
        Intent goToHome = new Intent(this, HomePageActivity.class);
        startActivity(goToHome);
    }

    public void jumpToRegister(View view){
        Intent goToRegister = new Intent(this,RegisterActivity.class);
        startActivity(goToRegister);
    }


}

