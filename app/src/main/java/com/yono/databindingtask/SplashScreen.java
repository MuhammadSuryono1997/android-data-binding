package com.yono.databindingtask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Handler handler;
    SharedPreferences sharedPreferences;
    public static String SESSION_SPLASH = "splash";
    public static String SESSION_SYARAT = "syarat";
    public static Boolean splash, syarat;
    public static Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String status_session = "status_session";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler = new Handler();

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(status_session, true);

        syarat = sharedPreferences.getBoolean(SESSION_SYARAT, false);
        splash = sharedPreferences.getBoolean(SESSION_SPLASH, false);



        if (session){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(SESSION_SPLASH, true);
                    editor.commit();

                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }, 3000);
        }


    }
}
