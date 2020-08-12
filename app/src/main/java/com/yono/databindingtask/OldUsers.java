package com.yono.databindingtask;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.yono.databindingtask.api.ApiHelper;
import com.yono.databindingtask.api.adapter.UserAdapter;
import com.yono.databindingtask.api.apiclient.Client;
import com.yono.databindingtask.api.models.Users;
import com.yono.databindingtask.api.response.UsersResponse;
import com.yono.databindingtask.api.service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OldUsers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Client client;
    private Service service;
    ProgressBar pbLoading;
    EditText etSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_users);

        recyclerView = findViewById(R.id.rvUsers);
        etSearch = findViewById(R.id.etSearch);
        pbLoading = findViewById(R.id.pbLoading);

        showLoading(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        client = new Client();
        service = client.Client(Service.class, ApiHelper.BASE_URL_OLD);
        GetAllUsers();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void GetAllUsers() {
        service.getUsers().enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                List<Users> users = response.body().getData();
                Log.d("Retrofit Get", "Jumlah data : " +
                        String.valueOf(users.size()));

                userAdapter = new UserAdapter();
                userAdapter.setUsersList(OldUsers.this, users);
                recyclerView.setAdapter(userAdapter);
                showLoading(false);

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.e("Error Get data USers", "Gagal get data", t);
            }
        });
    }


    public void showLoading(boolean isLoading) {
        if (isLoading) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
    }
}
