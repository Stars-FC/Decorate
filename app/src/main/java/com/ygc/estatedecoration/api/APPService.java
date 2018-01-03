package com.ygc.estatedecoration.api;

import com.ygc.estatedecoration.bean.BankCardBean;
import com.ygc.estatedecoration.bean.BaseBean;
import com.ygc.estatedecoration.bean.CaseStyleBean;
import com.ygc.estatedecoration.bean.ContractContentBean;
import com.ygc.estatedecoration.bean.ContractInfoBean;
import com.ygc.estatedecoration.bean.DemandOfferBean;
import com.ygc.estatedecoration.bean.FindActivitesBean;
import com.ygc.estatedecoration.bean.FindAllTypeBean;
import com.ygc.estatedecoration.bean.FindMaterialBean;
import com.ygc.estatedecoration.bean.EffectBean;
import com.ygc.estatedecoration.bean.LoginBean;
import com.ygc.estatedecoration.bean.MyActivitesBean;
import com.ygc.estatedecoration.bean.MyBrightBean;
import com.ygc.estatedecoration.bean.MyStoreBean;
import com.ygc.estatedecoration.bean.NeedBean;
import com.ygc.estatedecoration.bean.PanoramaBean;
import com.ygc.estatedecoration.bean.RoleFindAllBean;
import com.ygc.estatedecoration.bean.ScheduleBean;
import com.ygc.estatedecoration.bean.ShopRecommendMaterialsBean;
import com.ygc.estatedecoration.bean.UserAddressDataListBean;
import com.ygc.estatedecoration.bean.UserBalanceOrderBean;
import com.ygc.estatedecoration.bean.UserCaseEffectDetailBean;
import com.ygc.estatedecoration.bean.UserCasePanoramaDetailBean;
import com.ygc.estatedecoration.bean.UserCollectionMaterialBean;
import com.ygc.estatedecoration.bean.UserCollectionPanoramaBean;
import com.ygc.estatedecoration.bean.UserCollectionResultChartBean;
import com.ygc.estatedecoration.bean.UserCommentBean;
import com.ygc.estatedecoration.bean.UserHomeSkillBean;
import com.ygc.estatedecoration.bean.UserInformationBean;
import com.ygc.estatedecoration.bean.UserProjectProgressBean;
import com.ygc.estatedecoration.bean.UserShopCarBean;
import com.ygc.estatedecoration.bean.VisiterBean;
import com.ygc.estatedecoration.entity.base.Base;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface APPService {

    /*********************************************登录、注册、找回密码部分*******************************************/

    @FormUrlEncoded
    @POST("user/doSendCode.action")
    Observable<BaseBean> doSendCode(@Field("phone") String phone, @Field("type") String type);  //获取验证码

    @POST("Role/findAll.action")
    Observable<RoleFindAllBean> roleFindAll();  //注册~获取服务商与细致身份

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
    Observable<NeedBean> queryAllNeed(@Field("page") int page, @Field("missionType") String missionType, @Field("constructionStatusQuo") String constructionStatusQuo, @Field("missionStartTime") String missionStartTime, @Field("missionEndTime") String missionEndTime, @Field("mixBuildingArea") String mixBuildingArea, @Field("maxBuildingArea") String maxBuildingArea, @Field("address") String address);


    @FormUrlEncoded
    @POST("wzd/Demand/getDemandList.action")
    Observable<NeedBean> queryAllNeed(@Field("cId") String cId, @Field("dState") int dState, @Field("page") int page, @Field("missionType") String missionType, @Field("constructionStatusQuo") String constructionStatusQuo, @Field("missionStartTime") String missionStartTime, @Field("missionEndTime") String missionEndTime, @Field("mixBuildingArea") String mixBuildingArea, @Field("maxBuildingArea") String maxBuildingArea, @Field("address") String address);

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandList.action")
    Observable<NeedBean> queryAllNeed(@Field("cId") String cId, @Field("page") int page, @Field("missionType") String missionType, @Field("constructionStatusQuo") String constructionStatusQuo, @Field("missionStartTime") String missionStartTime, @Field("missionEndTime") String missionEndTime, @Field("mixBuildingArea") String mixBuildingArea, @Field("maxBuildingArea") String maxBuildingArea, @Field("address") String address);

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandListByauId.action")
    Observable<NeedBean> queryRecommendNeed(@Field("auId") String auId, @Field("searchType") String searchType, @Field("orderState") String orderState, @Field("page") int page);

    @FormUrlEncoded
    @POST("wzd/Demand/addDemandOffer.action")
    Observable<Base> demandOffer(@Field("cId") String cId, @Field("dId") String dId, @Field("price") String price, @Field("needTime") String needTime, @Field("message") String message, @Field("toOpen") String toOpen);

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandOfferList.action")
    Observable<DemandOfferBean> getDemandOfferList(@Field("doId") String doId, @Field("dId") String dId, @Field("page") String page);

//报价列表

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandOfferList.action")//报价列表
    Observable<DemandOfferBean> getDemandOfferList(@Field("dId") String dId, @Field("page") String page);

    @FormUrlEncoded
    @POST("wzd/Demand/getDemandPlan.action")
    Observable<ScheduleBean> getDemandPlan(@Field("dId") String dId, @Field("category") String category);   //项目进度

    @FormUrlEncoded
    @POST("wzd/Demand/getUserDemandPlan.action")
//普通用户查看项目进度
    Observable<UserProjectProgressBean> getDemandPlan(@Field("cId") String cId, @Field("dId") String dId, @Field("category") String category);

    @Multipart
    @POST("wzd/Demand/addDemand.action")
    Observable<Base> publishDemand(@PartMap Map<String, RequestBody> params);//发布需求

    @FormUrlEncoded
    @POST("wzd/Demand/updateDemandOfferSelectState.action")//需求选标
    Observable<Base> demandSelected(@Field("dId") String dId , @Field("doId") String doId, @Field("category") int category);

    @FormUrlEncoded
    @POST("wzd/Demand/getContract.action")//获取合同信息
    Observable<ContractInfoBean> getContractInfo(@Field("dId") String dId , @Field("category") int category);

    @FormUrlEncoded
    @POST("wzd/Demand/getContract.action")//获取合同信息
    Observable<ContractInfoBean> getContractInfo(@Field("conId") String conId);

//获取合同主体内容
    @FormUrlEncoded
    @POST("wzd/Demand/editContractContent.action")//修改主体合同信息
    Observable<Base> modifyMainContractInfo(@Field("conId") String conId, @Field("qualityGuaranteeDeposit") String qualityGuaranteeDeposit);

    @FormUrlEncoded
    @POST("wzd/Demand/updateContractStage.action")//修改主体合同阶段
    Observable<Base> modifyMainContractStage(@Field("conId") String conId, @Field("title") String title, @Field("detail") String detail, @Field("price") String price, @Field("needDays") String needDays);

    @FormUrlEncoded
    @POST("wzd/Demand/updateContractState.action")//修改主体合同阶段状态
    Observable<Base> modifyMainContractState(@Field("consId") String consId, @Field("state") String state);

    @FormUrlEncoded
    @POST("wzd/Demand/updateContractState.action")//修改补充合同阶段状态
    Observable<Base> modifyBuChongContractState(@Field("rcId") String rcId, @Field("state") String state);

    @POST("wzd/Demand/getSystemData.action")//获取合同主体内容
    Observable<ContractContentBean> getContractContentData();

    @FormUrlEncoded
    @POST("wzd/Demand/confimContract.action")//雇主确认合同
    Observable<Base> confimContract(@Field("auId") String auId, @Field("conId") String conId);

    @FormUrlEncoded
    @POST("wzd/Demand/addContract.action")
    Observable<Base> faQiContract(@Field("contractDetail") String contractDetail, @Field("ServiceProvidersId") String ServiceProvidersId, @Field("confirmId") String confirmId,@Field("dId") String dId,@Field("totalPrice") String totalPrice,
                                  @Field("stageStartTime") String startTime,@Field("stageEndTime") String endTime,@Field("needTime") String needTime,@Field("qualityGuaranteeDeposit") String qualityGuaranteeDeposit,
                                  @Field("title") String title,@Field("detail") String detail,@Field("price") String price,@Field("needDays") String needDays,@Field("category") String category);//发布需求

    @Multipart
    @POST("wzd/Demand/addReplenishContract.action")//发起补充合同
    Observable<Base> addReplenishContract(@PartMap Map<String, RequestBody> params);

    @Multipart
    @POST("wzd/Demand/editReplenishContract.action")//修改补充合同
    Observable<Base> editReplenishContract(@PartMap Map<String, RequestBody> params);

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

    @FormUrlEncoded
    @POST("wzd/Demand/getUserComment.action")
    Observable<UserCommentBean> queryUserEvaluate(@Field("serverId") int serverId, @Field("page") int page);  //获取服务商评价

    @FormUrlEncoded
    @POST("wzd/Order/getAddressList.action")
    Observable<UserAddressDataListBean> myAddressDataList(@Field("auId") String auId, @Field("page") String page);  //用户地址

    @FormUrlEncoded
    @POST("wzd/Order/updateDefaultAddress.action")
    Observable<Base> modifyUserDefaultAddress(@Field("aId") String aId, @Field("auId") String auId);  //修改用户默认地址

    @FormUrlEncoded
    @POST("wzd/Order/deleteAddress.action")
    Observable<Base> deleteUserAddress(@Field("aId") String aId);  //删除地址

    @FormUrlEncoded
    @POST("wzd/Order/addAddress.action")
    Observable<Base> addUserAddress(@Field("auId") String auId, @Field("userName") String userName, @Field("userMobile") String userMobile, @Field("province") String province, @Field("detail") String detail);  //添加用户地址

    @FormUrlEncoded
    @POST("wzd/Order/editAddress.action")
    Observable<Base> editUserAddress(@Field("aId") String aId, @Field("auId") String auId, @Field("userName") String userName, @Field("userMobile") String userMobile, @Field("province") String province, @Field("detail") String detail);  //编辑用户地址


    @FormUrlEncoded
    @POST("wzd/bankCard/getBankCard.action")
    Observable<BankCardBean> getBankCard(@Field("auId") String auId);  //获取银行卡信息

    @FormUrlEncoded
    @POST("wzd/bankCard/addBankCard.action")
    Observable<BaseBean> addBankCard(@Field("bankNumber") String bankNumber, @Field("bankName") String bankName,
                                     @Field("userName") String userName, @Field("userMobile") String userMobile,
                                     @Field("auId") String auId);  //绑定银行卡

    @POST("user/update.action")
    Observable<BaseBean> updateInfo(@Body RequestBody body);   //编辑用户信息

    @FormUrlEncoded
    @POST("UserCollect/findAllByAuId.action")
    Observable<UserCollectionResultChartBean> myCollectionResultChart(@Field("auId") String auId,
                                                                      @Field("articleType") String articleType);  //我的收藏—效果图

    @FormUrlEncoded
    @POST("UserCollect/findAllByAuId.action")
    Observable<UserCollectionPanoramaBean> myCollectionPanorama(@Field("auId") String auId,
                                                                @Field("articleType") String articleType);  //我的收藏—全景图

    @FormUrlEncoded
    @POST("UserCollect/findAllByAuId.action")
    Observable<UserCollectionMaterialBean> myCollectionMaterial(@Field("auId") String auId,
                                                                @Field("articleType") String articleType);  //我的收藏—商品

    /*********************************************店铺*******************************************/
    @FormUrlEncoded
    @POST("Store/getByAuId.action")
    Observable<MyStoreBean> myStore(@Field("au_id") String au_id);  //我的店铺

    @POST("Store/update.action")
    Observable<BaseBean> editMyStore(@Body RequestBody body);//编辑我的店铺

    /*********************************************访客*******************************************/
    @FormUrlEncoded
    @POST("UserVisited/findAllByAuId.action")
    Observable<VisiterBean> findAllByAuId(@Field("au_id") String au_id);  //我的访客

    /**
     * @param visitor_id 访问者id
     * @param au_id      被访问者id
     * @return
     */
    @FormUrlEncoded
    @POST("UserVisited/doVisited.action")
    Observable<BaseBean> doVisited(@Field("visitor_id") String visitor_id, @Field("au_id") String au_id);  //访客

    /*********************************************用户案例*******************************************/
    @FormUrlEncoded
    @POST("Role/findAllByType.action")
    Observable<CaseStyleBean> getCaseStyleData(@Field("type") String type);


    /*********************************************用户端首页*******************************************/

    @POST("Commodity/findAllPage.action")
    Observable<FindMaterialBean> findMaterial(@Body RequestBody body);// 找材料

    @POST("Activity/findAllPage.action")
    Observable<FindActivitesBean> findActivity(@Body RequestBody body);// 推荐活动

    @POST("CasePanorama/findAllPageByType.action")
    Observable<FindAllTypeBean> findAllType(@Body RequestBody body);// 找设计/找施工/找监理

    @FormUrlEncoded
    @POST("Skill/findAllPage.action")
    Observable<UserHomeSkillBean> getSkillData(@Field("pn") String pn, @Field("pageSize") String pageSize);//小技巧

    @FormUrlEncoded
    @POST("CasePanorama/findAllPage.action")
    Observable<PanoramaBean> queryCasePanoramaData(@Field("pn") int pn, @Field("r_id") String r_id, @Field("status") String status, @Field("auId") String auId, @Field("upload_type") String upload_type);

    @FormUrlEncoded
    @POST("CaseEffect/findAllPage.action")
    Observable<EffectBean> queryCaseEffectData(@Field("pn") int pn, @Field("r_id") String r_id, @Field("status") String status, @Field("auId") String auId, @Field("upload_type") String upload_type);

    @FormUrlEncoded
    @POST("CaseEffect/getById.action")
    Observable<UserCaseEffectDetailBean> getCaseEffectDetailData(@Field("d_id") String d_id, @Field("auId") String auId);

    @FormUrlEncoded
    @POST("CasePanorama/getById.action")
    Observable<UserCasePanoramaDetailBean> getCasePanoramaDetailData(@Field("cp_id") String cp_id, @Field("auId") String auId);

    @FormUrlEncoded
    @POST("UserCollect/doCollect.action")
    Observable<Base> doCollect(@Field("auId") String auId, @Field("articleId") String articleId, @Field("articleType") String articleType);

    @FormUrlEncoded
    @POST("UserCollect/cancelCollect.action")
    Observable<Base> cancelCollect(@Field("auId") String auId, @Field("articleId") String articleId, @Field("articleType") String articleType);

    /*********************************************商城*******************************************/
    @FormUrlEncoded
    @POST("Commodity/findAll.action")
    Observable<ShopRecommendMaterialsBean> getRecommendMaterialsData(@Field("top_flag") String top_flag);

}
