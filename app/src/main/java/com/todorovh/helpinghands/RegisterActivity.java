package com.todorovh.helpinghands;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private String nameStr;
    private String ageStr;
    private String cityStr;
    private String usernameStr;
    private String passwordStr;
    private String passwordConfirmStr;
    private String addressStr;
    private String phoneStr;
    private String emailStr;

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
                nameStr = name.getText().toString();
                ageStr = age.getText().toString();
                cityStr = city.getText().toString();
                usernameStr = rUsername.getText().toString();
                passwordStr = rPassword.getText().toString();
                passwordConfirmStr = rPasswordConfirm.getText().toString();

                addressStr = address.getText().toString();
                phoneStr = phone.getText().toString();
                emailStr = email.getText().toString();

                if (nameStr.isEmpty() | ageStr.isEmpty() |
                        cityStr.isEmpty() | usernameStr.isEmpty() |
                        passwordStr.isEmpty() | passwordConfirmStr.isEmpty()){
                    setToastRegister();
                }
                if (!passwordStr.isEmpty() & !passwordConfirmStr.isEmpty() & passwordStr != passwordConfirmStr){
                    setToastPassConfirm();
                }



                //bRegister.setBackgroundColor(Color.WHITE);
                //bRegister.setTextColor(Color.BLUE);
                //TODO...
                break;
        }
    }

    private void setToastRegister(){
        Toast toast = new Toast(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_register_toast,
                (ViewGroup) findViewById(R.id.toast_register_layout));
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void setToastPassConfirm(){
        Toast toast = new Toast(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_pass_confirm_toast,
                (ViewGroup) findViewById(R.id.toast_pass_confirm_layout));
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
