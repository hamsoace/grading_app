package com.hamsoace.AppForAll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarToggle;

    //oncreate: should show the app name and show menu items
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



//    When Menu On Option Item selected goes to various type of activity.
//     i.e (Grade Entry, View Grade and Search)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem it) {

            int id = it.getItemId();
            if (id == R.id.nav_grade_entry) {
                gradeEntry();
                return true;
            } else if (id == R.id.nav_view_grade) {
                viewGrade();
                return true;
            } else if (id == R.id.nav_search){
                searchGrade();
                return true;
            } else if (actionBarToggle.onOptionsItemSelected(it)){
                return super.onOptionsItemSelected(it);
            }
            return true;
    }

    private void searchGrade() {
        Intent i = new Intent(MainActivity.this, Search.class);
        startActivity(i);
    }

    private void viewGrade() {
        Intent intent2 = new Intent(MainActivity.this, ViewMarks.class);
        startActivity(intent2);
    }

    private void gradeEntry() {
        Intent intent = new Intent(MainActivity.this, EnterMarks.class);
        startActivity(intent);
    }

}