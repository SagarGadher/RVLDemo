package com.example.rvldemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rvldemo.R;
import com.example.rvldemo.adapter.UserAdapter;
import com.example.rvldemo.database.User;
import com.example.rvldemo.model.UserViewModel;
import com.example.rvldemo.networkservice.DataServiceGenerator;
import com.example.rvldemo.networkservice.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvUser;
    UserAdapter userAdapter;
    List<User> mUserList;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUser = findViewById(R.id.rvUser);
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setHasFixedSize(true);

        mUserList = new ArrayList<>();
        fetchData();
        userAdapter = new UserAdapter(this, mUserList);
        rvUser.setAdapter(userAdapter);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userAdapter.setUser(users);
                /*mUserList = users;
                userAdapter.notifyDataSetChanged();*/
            }
        });
    }

    private void fetchData() {
        DataServiceGenerator dataServiceGenerator = new DataServiceGenerator();
        RetrofitService service = DataServiceGenerator.createService(RetrofitService.class);

        Call<List<User>> call = service.getAllUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response != null) {
                    List<User> mUserModelList = response.body();
                    for (int i = 0; i < mUserModelList.size(); i++) {
                        int id;
                        String name, userName, email;
                        id = mUserModelList.get(i).getId();
                        name = mUserModelList.get(i).getName();
                        userName = mUserModelList.get(i).getUsername();
                        email = mUserModelList.get(i).getEmail();

                        User user = new User(id, name, userName, email);
                        userViewModel.insert(user);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
