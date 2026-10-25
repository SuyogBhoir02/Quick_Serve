package com.example.quickserve.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.quickserve.Adapter.BestFoodsAdapter;
import com.example.quickserve.Adapter.CategoryAdapter;
import com.example.quickserve.Domain.Category;
import com.example.quickserve.Domain.Foods;
import com.example.quickserve.Domain.Location;
import com.example.quickserve.Domain.Price;
import com.example.quickserve.Domain.Time;
import com.example.quickserve.R;
import com.example.quickserve.databinding.ActivityMainBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Display the logged-in user's name
        setUserName();

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
        setVariable();
    }

    private void setUserName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if (user != null) {
            String name = user.getDisplayName();

            if (name != null && !name.trim().isEmpty()) {
                binding.textView10.setText(name);
            } else {
                String email = user.getEmail();

                if (email != null && email.contains("@")) {
                    String emailName = email.substring(0, email.indexOf("@"));
                    binding.textView10.setText(emailName);
                } else {
                    binding.textView10.setText("User");
                }
            }
        } else {
            binding.textView10.setText("Guest");
        }
    }

    private void setVariable() {

        binding.logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        });

        binding.searchBtn.setOnClickListener(v -> {
            String text = binding.searchEdt.getText().toString().trim();

            if (!text.isEmpty()) {
                Intent intent = new Intent(
                        MainActivity.this,
                        ListFoodsActivity.class
                );

                intent.putExtra("text", text);
                intent.putExtra("isSearch", true);
                startActivity(intent);
            }
        });

        binding.cartBtn.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CartActivity.class))
        );
    }

    private void initBestFood() {
        DatabaseReference myRef = database.getReference("Foods");

        binding.progressBarBestFood.setVisibility(View.VISIBLE);

        ArrayList<Foods> list = new ArrayList<>();

        Query query = myRef.orderByChild("BestFood").equalTo(true);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Foods food = issue.getValue(Foods.class);

                        if (food != null) {
                            list.add(food);
                        }
                    }

                    if (!list.isEmpty()) {
                        binding.bestFoodView.setLayoutManager(
                                new LinearLayoutManager(
                                        MainActivity.this,
                                        LinearLayoutManager.HORIZONTAL,
                                        false
                                )
                        );

                        RecyclerView.Adapter<?> adapter =
                                new BestFoodsAdapter(list);

                        binding.bestFoodView.setAdapter(adapter);
                    }
                }

                binding.progressBarBestFood.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBarBestFood.setVisibility(View.GONE);
            }
        });
    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");

        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<Category> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Category category = issue.getValue(Category.class);

                        if (category != null) {
                            list.add(category);
                        }
                    }

                    if (!list.isEmpty()) {
                        binding.categoryView.setLayoutManager(
                                new GridLayoutManager(MainActivity.this, 4)
                        );

                        RecyclerView.Adapter<?> adapter =
                                new CategoryAdapter(list);

                        binding.categoryView.setAdapter(adapter);
                    }
                }

                binding.progressBarCategory.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.progressBarCategory.setVisibility(View.GONE);
            }
        });
    }

    private void initLocation() {
        DatabaseReference myRef = database.getReference("Location");

        ArrayList<Location> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Location location = issue.getValue(Location.class);

                        if (location != null) {
                            list.add(location);
                        }
                    }

                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            R.layout.sp_item,
                            list
                    );

                    adapter.setDropDownViewResource(
                            android.R.layout.simple_spinner_dropdown_item
                    );

                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Location loading failed.
            }
        });
    }

    private void initTime() {
        DatabaseReference myRef = database.getReference("Time");

        ArrayList<Time> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Time time = issue.getValue(Time.class);

                        if (time != null) {
                            list.add(time);
                        }
                    }

                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            R.layout.sp_item,
                            list
                    );

                    adapter.setDropDownViewResource(
                            android.R.layout.simple_spinner_dropdown_item
                    );

                    binding.timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Time loading failed.
            }
        });
    }

    private void initPrice() {
        DatabaseReference myRef = database.getReference("Price");

        ArrayList<Price> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Price price = issue.getValue(Price.class);

                        if (price != null) {
                            list.add(price);
                        }
                    }

                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(
                            MainActivity.this,
                            R.layout.sp_item,
                            list
                    );

                    adapter.setDropDownViewResource(
                            android.R.layout.simple_spinner_dropdown_item
                    );

                    binding.priceSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Price loading failed.
            }
        });
    }
}
