package com.ygc.estatedecoration.api;

import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.RoleFindAllBean;
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


    @FormUrlEncoded
    @POST("")
    Observable<Base> queryUserHomeData();


    /**
     * 登录、注册、找回密码部分
     */

    //用户端-------------------------
    @FormUrlEncoded
    @POST("user/doSendCode.action")
    Observable<BaseBean> doSendCode(@Field("phone") String phone, @Field("type") String type);  //获取验证码

    @FormUrlEncoded
    @POST("user/doSendCode.action")
    Observable<RoleFindAllBean> roleFindAll();  //注册~获取服务商与细致身份

    @FormUrlEncoded
    @POST("user/register.action")
    Observable<BaseBean> register(@Field("username") String username, @Field("type") int  type,
                                         @Field("r_id") int  r_id, @Field("nickname") String nickname,
                                         @Field("password") String password, @Field("code") String code);  //注册


    //服务商端-------------------------
}
