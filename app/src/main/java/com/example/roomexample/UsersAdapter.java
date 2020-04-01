package com.example.roomexample;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomexample.data.User;
import com.example.roomexample.data.UserRepository;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    private List<User> mUserList;
    private UserRepository mRepository;

    public UsersAdapter(List<User> userList, Application application) {
        mUserList = userList;
        mRepository = new UserRepository(application);
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, final int position) {

        holder.userEmail.setText(mUserList.get(position).getEmail());
        holder.userName.setText(mUserList.get(position).getName());
        holder.userPhoneNumber.setText(String.valueOf(mUserList.get(position).getPhoneNumber()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.updateSpecific(mUserList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder {

        private TextView userName, userEmail, userPhoneNumber;

        public UsersHolder(@NonNull View itemView) {

            super(itemView);

            userName = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            userPhoneNumber = itemView.findViewById(R.id.user_phone_number);
        }
    }
}
