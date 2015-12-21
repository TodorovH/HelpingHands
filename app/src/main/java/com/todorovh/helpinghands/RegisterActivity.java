package com.todorovh.helpinghands;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name;
    private EditText age;
    private EditText city;
    private EditText address;
    private EditText phone;
    private EditText email;
    private EditText rUsername;
    private EditText rPassword;
    private EditText rPasswordConfirm;
    private Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        bRegister.setOnClickListener(this);
    }

    private void init(){
        name = (EditText) findViewById(R.id.register_name_text);
        age = (EditText) findViewById(R.id.register_age_text);
        city = (EditText) findViewById(R.id.register_city_text);
        address = (EditText) findViewById(R.id.register_address_text);
        phone = (EditText) findViewById(R.id.register_phone_text);
        email = (EditText) findViewById(R.id.register_email_text);
        rUsername = (EditText) findViewById(R.id.register_username_text);
        rPassword = (EditText) findViewById(R.id.register_password_text);
        rPasswordConfirm = (EditText) findViewById(R.id.register_password_confirm_text);
        bRegister = (Button) findViewById(R.id.button_register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_register:



                //bRegister.setBackgroundColor(Color.WHITE);
                //bRegister.setTextColor(Color.BLUE);
                //TODO...
                break;
        }
    }
}
