package com.ygc.estatedecoration.api;

import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.bean.MyActivitesBean;
import com.ygc.estatedecoration.bean.MyBrightBean;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.bean.RoleFindAllBean;
import com.ygc.estatedecoration.bean.UserInformationBean;
import com.ygc.estatedecoration.bean.UserBalanceOrderBean;
import com.ygc.estatedecoration.bean.UserShopCarBean;
import com.ygc.estatedecoration.entity.base.Base;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APPService {

    @FormUrlEncoded
    @POST("")
    Observable<Base> queryCasePanoramaData();


    @FormUrlEncoded
    @POST("")
    Observable<Base> queryUserHomeData();


    /*********************************************登录、注册、找回密码部分*******************************************/

    @FormUrlEncoded
    @POST("user/doSendCode.action")
    Observable<BaseBean> doSendCode(@Field("phone") String phone, @Field("type") String type);  //获取验证码

    @FormUrlEncoded
    @POST("Role/findAll.action")
    Observable<RoleFindAllBean> roleFindAll(@Field("") String s);  //注册~获取服务商与细致身份

    @FormUrlEncoded
    @POST("user/register.action")
    Observable<BaseBean> register(@Field("username") String username, @Field("type") int type,
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

    @FormUrlEncoded
    @POST("user/login_wechat.action")
    Observable<Base> login_wechat(@Field("code") String code);  //微信登录


    /*********************************************服务商端*******************************************/

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandList.action")
//所有需求
    Observable<NeedBean> queryAllNeed(@Field("page") int page, @Field("missionType") String missionType, @Field("constructionStatusQuo") String constructionStatusQuo, @Field("missionStartTime") String missionStartTime, @Field("missionEndTime") String missionEndTime, @Field("mixBuildingArea") String mixBuildingArea, @Field("maxBuildingArea") String maxBuildingArea, @Field("address") String address);

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandListByauId.action")
//推荐需求 交易管理
    Observable<NeedBean> queryRecommendNeed(@Field("auId") String auId, @Field("searchType") String searchType, @Field("orderState") String orderState, @Field("page") int page);

    @FormUrlEncoded
    @POST("wzd/Demand/addDemandOffer.action")
//报价
    Observable<Base> demandOffer(@Field("cId") String cId, @Field("dId") String dId, @Field("price") String price, @Field("needTime") String needTime, @Field("message") String message, @Field("toOpen") String toOpen);

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandOfferList.action")
//报价列表
    Observable<DemandOfferBean> getDemandOfferList(@Field("doId") String doId, @Field("dId") String dId, @Field("page") String page);


    /*********************************************个人中心*******************************************/

    @FormUrlEncoded
    @POST("user/getById.action")
    Observable<UserInformationBean> userInformation(@Field("au_id") String au_id);  //用户信息

    @FormUrlEncoded
    @POST("user/subCertification.action")
    Observable<BaseBean> authentication(@Field("au_id") String au_id, @Field("real_name") String real_name,
                                        @Field("numbe") String numbe,
                                        @Field("tel") String tel,
                                        @Field("file") File file);  //实名认证

    @FormUrlEncoded
    @POST("UserSparkle/findAllByAuId.action")
    Observable<MyBrightBean> myBright(@Field("au_id") String au_id);  //我的亮点


    @POST("UserSparkle/create.action")
    Observable<BaseBean> addMyBright(@Body RequestBody body);  //添加我的亮点

    //    Observable<BaseBean> addMyBright(@Field("au_id") String au_id, @Field("us_title") String us_title, @Body MultipartBody body);  //添加我的亮点
    /*@Multipart
    @POST("UserSparkle/create.action")
    Observable<BaseBean> addMyBright(@PartMap Map<String, RequestBody> params,
                                     @Part List<MultipartBody.Part> parts);*/
    @POST("UserFeedback/create.action")
    Observable<BaseBean> feedback(@Body RequestBody body);  //意见反馈

    @FormUrlEncoded
    @POST("user/getUserBalanceOrder.action")
    Observable<UserBalanceOrderBean> queryBalanceOrder(@Field("auId") String au_id, @Field("page") int page);  //用户明细

    @FormUrlEncoded
    @POST("wzd/commodityCar/getCommodityCarInfo.action")
    Observable<UserShopCarBean> queryCommodityCarInfo(@Field("auId") String au_id, @Field("page") int page);  //查询购物车信息

    @FormUrlEncoded
    @POST("Activity/findAllByAuId.action")
    Observable<MyActivitesBean> myActivites(@Field("au_id") String au_id);  //我的活动

    @POST("Activity/add.action")
    Observable<BaseBean> addMyActivites(@Body RequestBody body);  //添加我的活动
}
