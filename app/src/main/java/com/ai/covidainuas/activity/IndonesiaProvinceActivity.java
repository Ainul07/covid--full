package com.ai.covidainuas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.ai.covidainuas.adapter.IndoProvinceListAdapter;
import com.ai.covidainuas.model.IndonesiaProvinsiModel;
import com.ai.covidainuas.viewmodel.IndonesiaProvinceViewModel;
import com.ai.covidainuluas.R;

import java.util.ArrayList;

public class IndonesiaProvinceActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private IndoProvinceListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshProvince;
    private TextView tvEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indonesia_province);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.province);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        tvEmpty = findViewById(R.id.tvEmptyListProvince);
        RecyclerView recyclerView = findViewById(R.id.provinceRecycler);
        adapter = new IndoProvinceListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshProvince = findViewById(R.id.swipeRefreshProvince);
        swipeRefreshProvince.setOnRefreshListener(this);

        loadProvinceData();

    }

    private void loadProvinceData() {
        IndonesiaProvinceViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(IndonesiaProvinceViewModel.class);
        viewModel.setProvinceData();
        refreshing(true);
        viewModel.getProvinceData().observe(this, new Observer<ArrayList<IndonesiaProvinsiModel>>() {
            @Override
            public void onChanged(ArrayList<IndonesiaProvinsiModel> indonesiaProvinsiModels) {
                if (indonesiaProvinsiModels == null) {
                    tvEmpty.setVisibility(View.VISIBLE);
                    refreshing(false);
                } else {
                    adapter.setIndoList(indonesiaProvinsiModels);
                    refreshing(false);
                }

            }
        });
    }

    private void refreshing(boolean isRefreshing) {
        if (isRefreshing) {
            swipeRefreshProvince.setRefreshing(true);
        } else {
            swipeRefreshProvince.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadProvinceData();
    }

}
