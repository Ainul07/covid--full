package com.ai.covidainuas.service;

import com.ai.covidainuas.model.IndonesiaProvinsiModel;
import com.ai.covidainuas.model.IndonesiaSummaryModel;
import com.ai.covidainuas.utilities.AppUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {


    //Endpoint Ringkasan Indonesia
    @GET(AppUtils.ENDPOINT_SUMMARY_INDONESIA)
    Call<List<IndonesiaSummaryModel>> getSummaryIdn();

    //Endpoint Provinsi Indonesia
    @GET(AppUtils.ENDPOINT_INDONESIA_PROVINSI)
    Call<List<IndonesiaProvinsiModel>> getProvince();


}
