package com.example.marcus.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class RegisterActivity extends AppCompatActivity {

    private EditText userid, userpassword, etPhone ,useremail, SeriesNo;
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

           userid =(MaterialEditText) findViewById(R.id.etUserID);
           userpassword=(MaterialEditText) findViewById(R.id.etPassword);
           etPhone= (MaterialEditText)findViewById(R.id.etPhone);
           useremail=(MaterialEditText) findViewById(R.id.etEmail);
           SeriesNo=(MaterialEditText) findViewById(R.id.seriesno);
           regButton= (Button) findViewById(R.id.btnRegister);
           userlogin=(TextView) findViewById(R.id.tvUserLogin);

           final FirebaseDatabase database=FirebaseDatabase.getInstance();
           final DatabaseReference table_user = database.getReference("User");


           regButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   table_user.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           if (dataSnapshot.child(userid.getText().toString()).exists()) {
                               Intent goToWelcome = new Intent(RegisterActivity.this, welcome_page.class);
                               startActivity(goToWelcome);
                               Toast.makeText(RegisterActivity.this, "User already registered !", Toast.LENGTH_SHORT).show();
                           }
                           //get user
                       else {
                               User user = new User(userpassword.getText().toString(), useremail.getText().toString(),etPhone.getText().toString(),SeriesNo.getText().toString());
                               table_user.child(userid.getText().toString()).setValue(user);
                               Intent goToWelcome = new Intent(RegisterActivity.this, welcome_page.class);
                               startActivity(goToWelcome);
                               Toast.makeText(RegisterActivity.this, "Register Successfully !", Toast.LENGTH_SHORT).show();
                               finish();
                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {

                       }
                   });

               }
           });

       /*    userpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
               @Override
               public void onFocusChange(View v, boolean hasFocus) {
                   if(userpassword.getText().length()<5){
                       userpassword.setError(" Password at least 5 numbers");
                   }
               }
           }); */
        }
      /*
       private Boolean validate(){
        Boolean result = false;
        String name = uname.getText().toString();
        String uid = userid.getText().toString();
        String passwordd = userpassword.getText().toString();

        String email=useremail.getText().toString();

        if(name.isEmpty()&& uid.isEmpty() && passwordd.isEmpty() && confirmpassword.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            }else{
                 result = true;
            }
            return result;


    } */
     /*
      public void OnRegister (View view){
          if(!validateUserID(userid.getText().toString())){
              userid.setError("UserID length must be minimum character 4");
              userid.requestFocus();
          }
          if (!validateEmail(useremail.getText().toString())){
              useremail.setError("Email must has a valid email format. Please re-enter");
              useremail.requestFocus();
          }
          if (!validatePassword(userpassword.getText().toString())||userpassword.getText().toString().length()<5){
              userpassword.setError("Password must be at least 5 characters");
              userpassword.requestFocus();
          }
      }

      String RUserid= userid.getText().toString();
      String RPassword= userpassword.getText().toString();
      String REmail= useremail.getText().toString();
      String RPhonenumber= etPhone.getText().toString();
      String RSeriesNo= SeriesNo.getText().toString();
        protected boolean validatePassword(String userpassword) {

          Pattern pattern;
          Matcher matcher;
          final String PASSWORD_Pattern= "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
         pattern = Pattern.compile(PASSWORD_Pattern);
        matcher = pattern.matcher(userpassword);

        return matcher.matches();
    }
      protected boolean validateEmail(String useremail) {
          String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                  + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

          Pattern pattern = Pattern.compile(emailPattern);
          Matcher matcher = pattern.matcher(useremail);

          return matcher.matches();
      }

        protected boolean validateUserID (String userid){
         if(userid!=null && userid.length()>4) {
        return true;
            }else{
        return false;
         }
}
*/

    public void jumpToLogin(View view){
        Intent goToLogin = new Intent(this, LoginActivity.class);
        startActivity(goToLogin);
    }
}
