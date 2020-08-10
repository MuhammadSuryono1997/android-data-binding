package com.yono.databindingtask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
    CheckBox chk_setuju;
    Button btn_selanjuynya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SplashScreen.my_shared_preferences, Context.MODE_PRIVATE);

        if (!SplashScreen.syarat){
            ShowSyarat();
        }


    }

    private void ShowSyarat() {
        View dialogView;
        LayoutInflater inflater;
        inflater     = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_custome,null);
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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

                    alertDialog.dismiss();

                }else{
                    Toast.makeText(MainActivity.this, R.string.tidak_centang, Toast.LENGTH_LONG).show();
                }
            }
        });



        alertDialog.show();

    }

    public void NumberTwo(View view) {
    }

    public void NumberOne(View view) {
    }
}