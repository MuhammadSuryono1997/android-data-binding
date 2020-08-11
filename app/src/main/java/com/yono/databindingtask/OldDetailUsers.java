package com.yono.databindingtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class OldDetailUsers extends AppCompatActivity {

    ImageView btnImage, profileImages;
    TextView nameDetailUsers,name,email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_detail_users);

        btnImage = findViewById(R.id.btn_back);
        nameDetailUsers = findViewById(R.id.name_bar_detail_users);
        profileImages = findViewById(R.id.profile_image);
        name = findViewById(R.id.txt_name);
        email = findViewById(R.id.txt_email);

        Bundle extras = getIntent().getExtras();
        nameDetailUsers.setText(extras.getString("name_users"));
        name.setText(extras.getString("name_users"));
        email.setText(extras.getString("email_users"));
        Glide.with(getApplicationContext()).load(extras.getString("image_users"))
                .apply(RequestOptions.centerCropTransform()).into(profileImages);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OldDetailUsers.this, OldUsers.class));
                finish();
            }
        });
    }
}
