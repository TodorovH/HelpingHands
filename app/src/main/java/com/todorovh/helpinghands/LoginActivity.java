package com.todorovh.helpinghands;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button bLogin;
    private TextView registerLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        bLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);
    }

    private void init(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        bLogin = (Button) findViewById(R.id.button_login);
        registerLink = (TextView) findViewById(R.id.registerLink);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                //TODO...
                break;
            case R.id.registerLink:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
}