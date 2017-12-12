package com.ygc.estatedecoration.api;

import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.bean.NeedBean;
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
    @POST("Role/findAll.action")
    Observable<RoleFindAllBean> roleFindAll(@Field("") String s);  //注册~获取服务商与细致身份

    @FormUrlEncoded
    @POST("user/register.action")
    Observable<BaseBean> register(@Field("username") String username,
                                  @Field("type") int type,
                                  @Field("r_id") int r_id,
                                  @Field("nickname") String nickname,
                                  @Field("password") String password,
                                  @Field("code") String code);  //注册

    @FormUrlEncoded
    @POST("user/login.action")
    Observable<LoginBean> login(@Field("username") String username, @Field("password") String password,
                                @Field("type") int type);  //登录

    @FormUrlEncoded
    @POST("user/updatePwd.action")
    Observable<LoginBean> updatePwd(@Field("username") String username, @Field("password")
            String password, @Field("code") String code);  //重置密码


    /*********************************************服务商段*******************************************/

    @FormUrlEncoded
    @POST("landMark/wzd/Demand/getDemandList.action")
    Observable<NeedBean> queryAllNeed(@Field("page") int page);

}
