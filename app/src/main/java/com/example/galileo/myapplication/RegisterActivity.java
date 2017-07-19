package com.example.galileo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    EditText fnameET, lnameET, addressET, emailET, usernameET, passwordET, confirmET, contactET, phone1ET, phone2ET, phone3ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = (EditText)findViewById(R.id.UserName);
        fnameET = (EditText)findViewById(R.id.FirstName);
        lnameET = (EditText)findViewById(R.id.LastName);
        addressET = (EditText)findViewById(R.id.Address);
        emailET = (EditText)findViewById(R.id.email);
        passwordET = (EditText)findViewById(R.id.password);
        confirmET = (EditText)findViewById(R.id.ConfirmPassword);
        contactET = (EditText)findViewById(R.id.ContactNumber);
        phone1ET = (EditText)findViewById(R.id.ContactNumber1);
        phone2ET = (EditText)findViewById(R.id.ContactNumber2);
        phone3ET = (EditText)findViewById(R.id.ContactNumber3);


    }

    public void doRegister(View view){

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String fname = fnameET.getText().toString();
        String lname = lnameET.getText().toString();
        String address = addressET.getText().toString();
        String email =emailET.getText().toString();
        String contact = contactET.getText().toString();
        String phone1 = phone1ET.getText().toString();
        String phone2 =phone2ET.getText().toString();
        String phone3 = phone3ET.getText().toString();

        String type = "register";

/*        usernameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if ((usernameET.getText().length() == 0) || (usernameET.length() < 3)) {
                    usernameET.setError("at least 5 characters");
                }
            }
        });*/

        if(validate()){
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            String status = null;
            try {
                status = backgroundWorker.execute(type, username, fname, lname, address, email, password, contact, phone1, phone2, phone3).get();
                if(status.equals("insert successful")){
                    Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(myIntent);
                    finish();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean validate(){
        boolean valid = true;

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String confirm = confirmET.getText().toString();
        String fname = fnameET.getText().toString();
        String lname = lnameET.getText().toString();
        String address = addressET.getText().toString();
        String email =emailET.getText().toString();
        String contact = contactET.getText().toString();
        String phone1 = phone1ET.getText().toString();
        String phone2 =phone2ET.getText().toString();
        String phone3 = phone3ET.getText().toString();

        if (username.isEmpty() || username.length() < 3) {
            usernameET.setError("at least 5 characters");
            valid = false;
        } else {
            usernameET.setError(null);
        }

        if (fname.isEmpty()) {
            fnameET.setError("enter first name");
            valid = false;
        } else {
            fnameET.setError(null);
        }

        if (lname.isEmpty()) {
            lnameET.setError("enter last name");
            valid = false;
        } else {
            lnameET.setError(null);
        }

        if (address.isEmpty()) {
            addressET.setError("enter address");
            valid = false;
        } else {
            addressET.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("enter a valid email address");
            valid = false;
        } else {
            emailET.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordET.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordET.setError(null);
        }

        if (!confirm.equals(password)) {
            confirmET.setError("passwords don't match");
            valid = false;
        } else {
            confirmET.setError(null);
        }
        String regexStr = "^[0-9]{10}$";
        if(contact.length()>10 || !contact.matches(regexStr)){
            contactET.setError("enter valid phone number");
            valid = false;
        } else{
            contactET.setError(null);
        }

        if (phone1.length() > 10 || !phone1.matches(regexStr)) {
                phone1ET.setError("enter valid phone number");
                valid = false;
            } else {
                phone1ET.setError(null);
            }

        if(phone2.length()>10 || !phone2.matches(regexStr)){
            phone2ET.setError("enter valid phone number");
            valid = false;
            } else{
                phone2ET.setError(null);
            }

        if (phone3.length() > 10 || !phone3.matches(regexStr)) {
                phone3ET.setError("enter valid phone number");
                valid = false;
            } else {
                phone3ET.setError(null);
            }

        return valid;
    }
}
