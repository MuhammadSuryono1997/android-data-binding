package com.yono.databindingtask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SplashScreen.my_shared_preferences, Context.MODE_PRIVATE);

    }


    public void NumberTwo(View view) {
    }

    public void NumberOne(View view) {
        startActivity(new Intent(MainActivity.this, OldUsers.class));
    }

    public void ResetSession(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SplashScreen.SESSION_SPLASH, false);
        editor.putBoolean(SplashScreen.SESSION_SYARAT, false);
        editor.commit();

        if (editor.commit()){
            startActivity(new Intent(MainActivity.this, SplashScreen.class));
            finish();
        }
    }
}