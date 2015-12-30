package com.todorovh.helpinghands;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {

    }
}