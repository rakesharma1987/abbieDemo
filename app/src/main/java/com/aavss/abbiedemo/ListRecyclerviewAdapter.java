package com.aavss.abbiedemo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aavss.abbiedemo.data.User;

import java.util.List;

public class ListRecyclerviewAdapter extends RecyclerView.Adapter<ListRecyclerviewAdapter.MyViewHolder> {
    private List<User> userList;
    private Context context;
    private static ItemClickListener itemClickListener;

    public ListRecyclerviewAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_data, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvFname.setText(user.getFirstName());
        holder.tvLname.setText(user.getLastName());
        holder.tvMobile.setText(user.getMobile());
        holder.tvAddress.setText(user.getAddress());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbAdapter dbAdapter = new DbAdapter(context);
                dbAdapter.openDatabase();
                dbAdapter.deleteSingleRecord(context, user.getRowId());

                itemClickListener.onItemClick(holder.getAdapterPosition(), view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvFname, tvLname, tvMobile, tvAddress;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFname = itemView.findViewById(R.id.tv_Fname);
            tvLname = itemView.findViewById(R.id.tv_Lname);
            tvMobile = itemView.findViewById(R.id.tv_Mobile);
            tvAddress = itemView.findViewById(R.id.tv_Address);
            imageView = itemView.findViewById(R.id.iv_delete);
        }
    }

    public void setOnItemClickListener(ItemClickListener myClickListener) {
        ListRecyclerviewAdapter.itemClickListener = myClickListener;
    }
}
