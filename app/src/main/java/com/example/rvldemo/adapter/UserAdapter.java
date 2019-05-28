package com.example.rvldemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rvldemo.R;
import com.example.rvldemo.database.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context mContext;
    private List<User> mUserList;

    public UserAdapter(Context mContext, List<User> mUserList) {
        this.mContext = mContext;
        this.mUserList = mUserList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_user_item, viewGroup, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        User mCurrentUser = mUserList.get(i);
        userViewHolder.tvId.setText(Integer.toString(mCurrentUser.getId()));
        userViewHolder.tvName.setText(mCurrentUser.getName());
        userViewHolder.tvUserName.setText(mCurrentUser.getUsername());
        userViewHolder.tvEmail.setText(mCurrentUser.getEmail());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void setUser(List<User> user) {
        mUserList = user;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvUserName, tvEmail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
