package com.example.marcus.fyp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView ;
import android.widget.Toast;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class LoginActivity extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener{

    private EditText userid;
    private EditText userpassword;
    private EditText etPhone;
    private Button Login;
    private CheckBox rem_userpass;
    private TextView registerLink;
    private ProgressBar loginProgress;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME="prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERID = " UserID";
    private static final String KEY_PASS= "Password";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferences= getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
        rem_userpass= (CheckBox) findViewById(R.id.checkBox);
        userid = (EditText)findViewById(R.id.etUserID);
        userpassword = (EditText)findViewById(R.id.etPassword);

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        userid.setText(sharedPreferences.getString(KEY_USERID,""));
        userpassword.setText(sharedPreferences.getString(KEY_PASS,""));

        userid.addTextChangedListener(this);
        userpassword.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);




        userid = (MaterialEditText) findViewById(R.id.etUserID);
        userpassword = (MaterialEditText) findViewById(R.id.etPassword);
        etPhone= (MaterialEditText)findViewById(R.id.etPhone);
        Login = (Button) findViewById(R.id.btnLogin);
        loginProgress= (ProgressBar) findViewById(R.id.login_process);
        registerLink = (TextView) findViewById(R.id.tvRegister);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.VISIBLE);
                Login.setVisibility(View.INVISIBLE);
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(userid.getText().toString()).exists()) {

                            //Get user information
                            User user = dataSnapshot.child(userid.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(userpassword.getText().toString())) {
                                Intent goToHome = new Intent(LoginActivity.this, HomePageActivity.class);
                                startActivity(goToHome);
                                Toast.makeText(LoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                                loginProgress.setVisibility(View.INVISIBLE);
                                Login.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(LoginActivity.this, "You have entered wrong password!! ", Toast.LENGTH_SHORT).show();
                                loginProgress.setVisibility(View.INVISIBLE);
                                Login.setVisibility(View.VISIBLE);
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "User do not exist!", Toast.LENGTH_SHORT).show();
                            loginProgress.setVisibility(View.INVISIBLE);
                            Login.setVisibility(View.VISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERID, userid.getText().toString().trim());
            editor.putString(KEY_PASS, userpassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERID);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }



    public void jumpToHome(View view){
        Intent goToHome = new Intent(this, SelectDoorFragment.class);
        startActivity(goToHome);
    }

    public void jumpToRegister(View view){
        Intent goToRegister = new Intent(this,RegisterActivity.class);
        startActivity(goToRegister);
    }


}

