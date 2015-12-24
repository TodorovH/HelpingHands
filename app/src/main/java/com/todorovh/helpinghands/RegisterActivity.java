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
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name,
            age, city, address, phone, email, rUsername, rPassword, rPasswordConfirm;
    private Button bRegister;
    private boolean fieldsAreSet = false,
            passwordIsConfirm = false,
            isReadyToRegister = false;
    private int ageNumber;

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
                String nameStr = name.getText().toString();
                String ageStr = age.getText().toString();
                String cityStr = city.getText().toString();
                String usernameStr = rUsername.getText().toString();
                String passwordStr = rPassword.getText().toString();
                String passwordConfirmStr = rPasswordConfirm.getText().toString();

                String addressStr = address.getText().toString();
                String phoneStr = phone.getText().toString();
                String emailStr = email.getText().toString();

                if (nameStr.isEmpty() | ageStr.isEmpty() |
                        cityStr.isEmpty() | usernameStr.isEmpty() |
                        passwordStr.isEmpty() | passwordConfirmStr.isEmpty()){
                    setToastRegister();
                } else {
                    fieldsAreSet = true;
                }
                if ((!passwordStr.isEmpty() & !passwordConfirmStr.isEmpty()) & !passwordStr.equals(passwordConfirmStr)){
                    setToastPassConfirm();
                } else {
                    passwordIsConfirm = true;
                }

                if (fieldsAreSet & passwordIsConfirm){
                    try {
                        ageNumber = Integer.parseInt(ageStr);
                        if (ageNumber < 1 | ageNumber > 150){
                            setToastInvalidAge();
                        } else {
                            isReadyToRegister = true;
                        }
                    } catch (Exception e){
                        setToastInvalidAge();
                    }
                }

                if (isReadyToRegister){
                    User user = new User(
                            nameStr,
                            ageNumber,
                            cityStr,
                            usernameStr,
                            passwordStr,
                            addressStr,
                            phoneStr,
                            emailStr);
                }

                //TODO...
                break;
        }
    }

    private void setToastRegister(){
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_register_toast,
                (ViewGroup) findViewById(R.id.toast_register_layout));
        toast.setView(v);
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

    private void setToastInvalidAge(){
        Toast toast = new Toast(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_invalid_age_toast,
                (ViewGroup) findViewById(R.id.toast_invalid_age_layout));
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
