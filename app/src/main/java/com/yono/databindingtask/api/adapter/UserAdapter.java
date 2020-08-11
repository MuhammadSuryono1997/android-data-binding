package com.yono.databindingtask.api.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yono.databindingtask.R;
import com.yono.databindingtask.api.models.Users;
import com.yono.databindingtask.api.response.UsersResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private List<Users> usersList;
    private Context context;

    public void setUsersList(Context context, final List<Users> usersList){
        this.context = context;


        if (this.usersList == null){

            this.usersList = usersList;
            notifyDataSetChanged();
        }else{
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UserAdapter.this.usersList.size();
                }

                @Override
                public int getNewListSize() {
                    return usersList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UserAdapter.this.usersList
                            .get(oldItemPosition).getEmailUsers()
                            ==
                            usersList.get(newItemPosition).getEmailUsers();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Users newUsers = UserAdapter.this.usersList.get(oldItemPosition);
                    Users oldUsers = usersList.get(newItemPosition);
                    return newUsers.getEmailUsers() == oldUsers.getEmailUsers();
                }
            });

            this.usersList = usersList;
            result.dispatchUpdatesTo(this);
        }
    }


    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.old_item_users,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        holder.name.setText(usersList.get(position).getFirstNameUsers()+ " "+usersList.get(position).getLastNameUsers());
        holder.email.setText(usersList.get(position).getEmailUsers());
    }

    @Override
    public int getItemCount() {

        if (usersList != null){
            return usersList.size();
        }else{
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        ImageView imageUsers;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            email = itemView.findViewById(R.id.txt_email);
            imageUsers = itemView.findViewById(R.id.profile_image);
        }
    }
}
