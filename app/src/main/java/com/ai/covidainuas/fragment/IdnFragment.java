package com.ai.covidainuas.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ai.covidainuas.activity.IndonesiaProvinceActivity;
import com.ai.covidainuas.activity.Map;
import com.ai.covidainuas.activity.VidActivity;
import com.ai.covidainuas.model.IndonesiaSummaryModel;
import com.ai.covidainuas.viewmodel.IndonesiaSummaryViewModel;
import com.ai.covidainuluas.R;

import java.util.ArrayList;
public class IdnFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private SwipeRefreshLayout swipe;
    private TextView tvPositive;
    private TextView tvRecovered;
    private TextView tvDeath;
    WebView webViewMap;
    ProgressBar bar;
    Button btnMap;
    public IdnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_idn, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnMap = view.findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Map.class);
                startActivity(i);
            }
        });

        //webview map
        webViewMap = view.findViewById(R.id.webViewMap);
        bar=view.findViewById(R.id.pbWeb);
        webViewMap.setWebViewClient(new myWebclient());
        webViewMap.getSettings().setJavaScriptEnabled(true);
        webViewMap.loadUrl("https://experience.arcgis.com/experience/bf4eb5d76e98423c865678e32c8937d4");


        //idn
        FrameLayout frameLayout = view.findViewById(R.id.cv_detail_idn);
        swipe = view.findViewById(R.id.swipeRefreshIdn);
        swipe.setOnRefreshListener(this);
        tvPositive = view.findViewById(R.id.tvValuePositifIdn);
        tvRecovered = view.findViewById(R.id.tvValueRecoveredIdn);
        tvDeath = view.findViewById(R.id.tvValueDeathsIdn);
        frameLayout.setOnClickListener(this);
        loadIdnData();
    }


    private void loadIdnData() {
        IndonesiaSummaryViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(IndonesiaSummaryViewModel.class);
        viewModel.setSummaryData();
        refreshData(true);
        viewModel.getSummaryData().observe(this, new Observer<ArrayList<IndonesiaSummaryModel>>() {
            @Override
            public void onChanged(ArrayList<IndonesiaSummaryModel> indonesiaSummaryModels) {
                if (indonesiaSummaryModels.size() > 0) {
                    refreshData(false);
                    tvPositive.setText(indonesiaSummaryModels.get(0).getPositifIdn());
                    tvRecovered.setText(indonesiaSummaryModels.get(0).getSembuhIdn());
                    tvDeath.setText(indonesiaSummaryModels.get(0).getMeninggalIdn());
                }
            }
        });

    }

    private void refreshData(boolean isRefresh) {
        if (isRefresh) {
            swipe.setRefreshing(true);
        } else {
            swipe.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        loadIdnData();
    }

    @Nullable


    @Override
    public void onClick(View view) {
        Intent provinceIntent = new Intent(getContext(), IndonesiaProvinceActivity.class);
        startActivity(provinceIntent);
    }

    public class myWebclient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }


        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

}
