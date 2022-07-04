package com.aavss.abbiedemo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.aavss.abbiedemo.db.DbAdapter;
import com.aavss.abbiedemo.R;
import com.aavss.abbiedemo.util.Utility;
import com.aavss.abbiedemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbAdapter = new DbAdapter(this);
        dbAdapter.openDatabase();

        binding.btnSave.setOnClickListener(this);
        binding.btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save){
            if (!TextUtils.isEmpty(binding.tvFname.getEditText().getText().toString()) && !TextUtils.isEmpty(binding.tvLname.getEditText().getText().toString())
                && !TextUtils.isEmpty(binding.tvMobileNo.getEditText().getText().toString()) && !TextUtils.isEmpty(binding.tvAddress.getEditText().getText().toString())){
                dbAdapter.insertData(MainActivity.this, binding.tvFname.getEditText().getText().toString(), binding.tvLname.getEditText().getText().toString(), binding.tvMobileNo.getEditText().getText().toString(), binding.tvAddress.getEditText().getText().toString());

                binding.tvFname.getEditText().setText("");
                binding.tvLname.getEditText().setText("");
                binding.tvMobileNo.getEditText().setText("");
                binding.tvAddress.getEditText().setText("");

            }else {
                Utility.toast(MainActivity.this, "Please fill all fields.");
            }
        }else {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
        }
    }
}