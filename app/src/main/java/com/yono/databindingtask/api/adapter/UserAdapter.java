package com.yono.databindingtask.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yono.databindingtask.OldDetailUsers;
import com.yono.databindingtask.R;
import com.yono.databindingtask.api.models.Users;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> implements Filterable {
    private List<Users> usersList;
    private List<Users> usersListFiltered;
    private Context context;

    public void setUsersList(Context context, final List<Users> usersList){
        this.context = context;

        if (this.usersList == null){
            this.usersList = usersList;
            this.usersListFiltered = usersList;
            notifyItemChanged(0, usersListFiltered);
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
            this.usersListFiltered = usersList;
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
        holder.name.setText(usersListFiltered.get(position).getFirstNameUsers()+ " "+usersListFiltered.get(position).getLastNameUsers());
        holder.email.setText(usersListFiltered.get(position).getEmailUsers());
        Glide.with(context).load(usersListFiltered.get(position).getImageUsers())
                .apply(RequestOptions.centerCropTransform()).into(holder.imageUsers);

    }

    @Override
    public int getItemCount() {

        if (usersList != null){
            return usersListFiltered.size();
        }else{
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    usersListFiltered = usersList;
                } else {
                    List<Users> filteredList = new ArrayList<>();
                    for (Users users : usersList) {
                        if (users.getFirstNameUsers().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(users);
                        }
                    }
                    usersListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = usersListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                usersListFiltered = (ArrayList<Users>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        ImageView imageUsers;
        CardView itemListUsers;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            email = itemView.findViewById(R.id.txt_email);
            imageUsers = itemView.findViewById(R.id.profile_image);
            itemListUsers = itemView.findViewById(R.id.item_users);


            itemListUsers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent();
                    intent = new Intent(context, OldDetailUsers.class);
                    intent.putExtra("name_users", usersListFiltered.get(position).getFirstNameUsers()+
                            " "+
                            usersListFiltered.get(position).getLastNameUsers());
                    intent.putExtra("email_users", usersListFiltered.get(position).getEmailUsers());
                    intent.putExtra("image_users", usersListFiltered.get(position).getImageUsers());
                    context.startActivity(intent);
                }
            });
        }
    }
}
