
package com.ai.covidainuas.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ai.covidainuas.model.IndonesiaSummaryModel;
import com.ai.covidainuas.service.ApiEndpoint;
import com.ai.covidainuas.service.RetrofitServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class IndonesiaSummaryViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<IndonesiaSummaryModel>> summaryModel = new MutableLiveData<>();

    public void setSummaryData(){
        Retrofit retrofit = RetrofitServiceApi.getRetrofitServiceIndo();
        ApiEndpoint endpoint = retrofit.create(ApiEndpoint.class);
        Call<List<IndonesiaSummaryModel>> call = endpoint.getSummaryIdn();
        call.enqueue(new Callback<List<IndonesiaSummaryModel>>() {
            @Override
            public void onResponse(Call<List<IndonesiaSummaryModel>> call, Response<List<IndonesiaSummaryModel>> response) {
                summaryModel.setValue((ArrayList<IndonesiaSummaryModel>) response.body());
            }

            @Override
            public void onFailure(Call<List<IndonesiaSummaryModel>> call, Throwable t) {

            }
        });

    }

    public LiveData<ArrayList<IndonesiaSummaryModel>> getSummaryData(){
        return summaryModel;
    }
}
