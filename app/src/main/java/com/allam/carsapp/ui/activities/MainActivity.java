package com.allam.carsapp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.allam.carsapp.R;
import com.allam.carsapp.databinding.ActivityMainBinding;
import com.allam.carsapp.ui.adapters.CarsAdapter;
import com.allam.carsapp.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private static final String TAG = "MainActivity";
    private CarsAdapter carsAdapter;
    //i set last page = 3 , api response have no args about last page
    private int page = 1, last_page = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        carsAdapter = new CarsAdapter(this);
        binding.recyclerview.setAdapter(carsAdapter);

        mainViewModel.errorMessage.observe(this, s -> {
            if (s != null) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        mainViewModel.carsRequest(page);
        carsResponse();
        recyclerviewPaging();
    }

    private void carsResponse() {
        mainViewModel.carModelMutableLiveData.observe(this, response -> {
            if (response.getStatus() == 1) {
                if (response.getData() != null) {
                    carsAdapter.setList(response.getData());
                    carsAdapter.notifyDataSetChanged();
                    binding.recyclerview.setVisibility(View.VISIBLE);
                    binding.noData.setVisibility(View.GONE);
                } else {
                    binding.recyclerview.setVisibility(View.GONE);
                    binding.noData.setVisibility(View.VISIBLE);
                }


            } else {
                Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void recyclerviewPaging() {
        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                            if (page < last_page) {
                                page++;
                                mainViewModel.carsRequest(page);
                            }
                        }
                    }
                });
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}