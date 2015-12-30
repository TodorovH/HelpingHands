package com.todorovh.helpinghands;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password;
    private Button bLogin;
    private TextView registerLink;
    UserLocalStore userLocalStore;
    static String usernameStr;
    static String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        bLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
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
                usernameStr = username.getText().toString();
                passwordStr = password.getText().toString();

                User user = new User(usernameStr, passwordStr);

                if (usernameStr.isEmpty() || passwordStr.isEmpty()){
                    setToast();
                } else {
                    authenticate(user);
                }

                break;
            case R.id.registerLink:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void authenticate(User user){
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null){
                    clearEditViews();
                    showErrorMessage();
                } else {
                    clearEditViews();
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginActivity.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser){
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        startActivity(new Intent(LoginActivity.this, UserActivity.class));
    }

    private void clearEditViews(){
        ((EditText)findViewById(R.id.username)).setText(R.string.Empty_string);
        ((EditText)findViewById(R.id.password)).setText(R.string.Empty_string);
    }

    private void setToast(){
        Toast toast = new Toast(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_login_toast,
                (ViewGroup) findViewById(R.id.toast_login_layout));
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}