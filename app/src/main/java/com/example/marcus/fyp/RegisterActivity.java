package com.example.marcus.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText uname, userid, userpassword, cpassword ,useremail;
            private Button regButton;
            private TextView userlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();





       /* final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUserID =(EditText) findViewById(R.id.etUserID);
        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final EditText etCPassword =(EditText) findViewById(R.id.etCPassword);
        final Button bRegister =(Button) findViewById(R.id.bRegister);

       */
    }
       private void setupUIViews(){
           uname= (EditText) findViewById(R.id.etName);
           userid =(EditText) findViewById(R.id.etUserID);
           userpassword=(EditText) findViewById(R.id.etPassword);
           cpassword=(EditText) findViewById(R.id.etCPassword);
           useremail=(EditText) findViewById(R.id.etEmail);
           regButton= (Button) findViewById(R.id.bRegister);
           userlogin=(TextView) findViewById(R.id.tvUserLogin);

        }

    public void jumpToSetting(View view){
        Intent goToSetting = new Intent(this, LoginActivity.class);
        startActivity(goToSetting);
    }

       private Boolean validate(){
        Boolean result = false;
        String name = uname.getText().toString();
        String uid = userid.getText().toString();
        String passwordd = userpassword.getText().toString();
        String confirmpassword=cpassword.getText().toString();
        String email=useremail.getText().toString();

        if(name.isEmpty()&& uid.isEmpty() && passwordd.isEmpty() && confirmpassword.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            }else{
                 result = true;
            }
            return result;


    }
    public void jumpToSelectDoor(View view){
        Intent goToSetting = new Intent(this, MainActivity.class);
        startActivity(goToSetting);
    }
}
