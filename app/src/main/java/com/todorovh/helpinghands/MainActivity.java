package com.todorovh.helpinghands;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar toolbar;
    private ImageButton falseDrawerButton;
    private ListView mListView;
    private Animation animRotateClockWise;
    private Animation animRotateClockWiseRev;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        init();
        setSupportActionBar(toolbar);
        setmActionBarDrawerToggle();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        setFalseDrawerButtonOnClick();
        mMyAdapter = new MyAdapter(this);
        mListView.setAdapter(mMyAdapter);

        setLoginButtonOnClick(mListView);
    }

    public void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mListView = (ListView) findViewById(R.id.right_drawer_list_view);
        falseDrawerButton = (ImageButton) findViewById(R.id.false_drawer_button);
        animRotateClockWise = AnimationUtils
                .loadAnimation(this, R.anim.rotate_clockwise);
        animRotateClockWiseRev = AnimationUtils
                .loadAnimation(this, R.anim.rotate_clockwise_reverse);
        mDrawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout);
    }

    private void setmActionBarDrawerToggle(){
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.string.drawer_opened,
                R.string.drawer_closed
        ){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle( R.string.drawer_opened );
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar() != null)
                    getSupportActionBar().setTitle( R.string.drawer_closed );
            }
        };
    }

    private void setFalseDrawerButtonOnClick(){
        falseDrawerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                    v.startAnimation(animRotateClockWise);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                    v.startAnimation(animRotateClockWiseRev);
                }
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setLoginButtonOnClick(ListView listView){

        final ListView lv = listView;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object obj = lv.getAdapter().getItemId(position);

                if ((long)obj == (long)R.string.login){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else if ((long)obj == (long)R.string.register){
                    startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                }
            }
        });
    }
}