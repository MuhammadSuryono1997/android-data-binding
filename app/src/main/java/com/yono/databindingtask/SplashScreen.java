package com.yono.databindingtask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Handler handler;
    SharedPreferences sharedPreferences;
    ProgressBar pb;
    CheckBox chk_setuju;
    Button btn_selanjuynya;
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
        pb = findViewById(R.id.progress_splash);

        handler = new Handler();

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(status_session, true);

        syarat = sharedPreferences.getBoolean(SESSION_SYARAT, false);
        splash = sharedPreferences.getBoolean(SESSION_SPLASH, false);



        if (session){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!syarat){
                        ShowSyarat();
                    }else{
                        NextSession();
                    }
                }
            }, 3000);
        }

    }

    private void NextSession() {
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }


    private void ShowSyarat() {
        View dialogView;
        LayoutInflater inflater;

        inflater     = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_custome,null);

        final AlertDialog alertDialog = new AlertDialog.Builder(SplashScreen.this).create();
        alertDialog.setTitle(R.string.title_sk);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setCancelable(false);
        alertDialog.setView(dialogView);

        chk_setuju = dialogView.findViewById(R.id.checkbox_setuju);
        btn_selanjuynya = dialogView.findViewById(R.id.btn_selanjutnya);


        btn_selanjuynya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk_setuju.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(SplashScreen.SESSION_SYARAT, true);
                    editor.commit();

                    PesanSukses();
                    alertDialog.dismiss();
                    NextSession();
                }else{
                    Toast.makeText(SplashScreen.this, R.string.tidak_centang, Toast.LENGTH_LONG).show();
                }
            }
        });

        alertDialog.show();

    }

    public void PesanSukses() {
        Toast.makeText(SplashScreen.this, R.string.pesan_sukses,Toast.LENGTH_LONG).show();
    }
}
