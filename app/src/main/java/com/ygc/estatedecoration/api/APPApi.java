package com.ygc.estatedecoration.api;

import com.ygc.estatedecoration.entity.base.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APPApi {
    public APPService service;

    private APPApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APPService.class);
    }


    public static APPApi getInstance() {
        return UserHolder.INSTANCE;
    }

    private static class UserHolder {
        private static final APPApi INSTANCE = new APPApi();
    }
}
