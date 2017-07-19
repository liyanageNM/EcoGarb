package com.example.galileo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends Activity {

    EditText UsernameET , passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Button login = (Button) findViewById(R.id.gotomainpage);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this, NavigationMain.class);
                startActivity(myIntent);
            }
        });*/
        UsernameET = (EditText)findViewById(R.id.etusername);
        passwordET = (EditText)findViewById(R.id.etpassword);


    }

    public void onLogin(View view){
        String username = UsernameET.getText().toString();
        String password = passwordET.getText().toString();
        String type = "login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        /*if(backgroundWorker.execute(type, username, password).equals("login successful")){
            Intent myIntent = new Intent(LoginActivity.this, NavigationMain.class);
            startActivity(myIntent);
        }*/
        try {
            String status = backgroundWorker.execute(type, username, password).get();
            if(status.equals("login successful")){
                Intent myIntent = new Intent(LoginActivity.this, NavigationMain.class);
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
