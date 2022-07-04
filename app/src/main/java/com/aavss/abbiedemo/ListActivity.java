package com.aavss.abbiedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.aavss.abbiedemo.data.User;
import com.aavss.abbiedemo.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ActivityListBinding binding;
    private DbAdapter dbAdapter;
    private Cursor cursor;
    private ListRecyclerviewAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbAdapter = new DbAdapter(this);
        dbAdapter.openDatabase();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

    }

    private List<User> getAllPersonData(){
        cursor = dbAdapter.getAllData();
        List<User> data = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                User user = new User();
                user.setRowId(cursor.getString(0));
                user.setFirstName(cursor.getString(1)); // firstName
                user.setLastName(cursor.getString(2)); // lastName
                user.setMobile(cursor.getString(3)); // Mobile
                user.setAddress(cursor.getString(4)); // Address
                data.add(user);
            }while (cursor.moveToNext());
        }
        return data;
    }

    private void loadData(){
        if (getAllPersonData().size() == 0){
            Utility.toast(ListActivity.this, "No data found.");
            return;
        }

        listAdapter = new ListRecyclerviewAdapter(getAllPersonData(), ListActivity.this);
        binding.recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listAdapter == null) return;
        listAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                listAdapter = new ListRecyclerviewAdapter(getAllPersonData(), ListActivity.this);
                binding.recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();
            }
        });
    }
}