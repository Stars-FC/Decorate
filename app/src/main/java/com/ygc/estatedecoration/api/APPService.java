package com.ygc.estatedecoration.api;

import com.ygc.estatedecoration.entity.base.Base;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface APPService {
    @Multipart
    @POST("register/registered.action")
    Observable<Base> register(
            @Part("auMobile") RequestBody mobile,
            @Part("auPasswordOne") RequestBody passwordOne,
            @Part("auPasswordTwo") RequestBody passwordTwo,
            @Part("auBuyerNick") RequestBody nick,
            @PartMap Map<String, RequestBody> params);

    @FormUrlEncoded
    @POST("logon/logonJudge.action")
    Observable<Base> login(@Field("auMobile") String mobile, @Field("auPassword") String password);


    @FormUrlEncoded
    @POST("")
    Observable<Base> queryCasePanoramaData();

}
