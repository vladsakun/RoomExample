package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.roomexample.data.User;
import com.example.roomexample.data.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private UsersAdapter mAdapter;
    private List<User> mUserList = new ArrayList<>();
    private Button mAddBtn, mDeleteBtn;
    private int counter = 0;
    private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.users_recyclerview);
        mAddBtn = findViewById(R.id.add);
        mDeleteBtn = findViewById(R.id.delete);

        repository = new UserRepository(getApplication());

        repository.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mUserList.clear();
                mUserList.addAll(users);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new UsersAdapter(mUserList, getApplication());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(mAdapter);

        final User user = new User("Test" + counter, "Test@test", counter);

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.insert(user);
                mUserList.add(user);
                mAdapter.notifyItemInserted(mAdapter.getItemCount());
                counter++;
            }
        });
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repository.deleteAll();
                mUserList.clear();
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}
