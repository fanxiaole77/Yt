package com.example.myapplication.network

import com.example.myapplication.SmartCiryApplication
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface SmartCityApi {

    @POST("prod-api/api/login")
    fun postLogin(@Body user:Login):Call<Message>


    @POST("/prod-api/api/register")
    fun postRegister(@Body user:Register):Call<Message>

    @GET("/prod-api/api/rotation/list")
    fun getHomeBanner():Call<HomeBanner>

    @GET("/prod-api/api/service/list")
    fun getService(@Query("serviceType")serviceType:String?,@Query("serviceName")serviceName:String?):Call<HomeService>

    @GET("/prod-api/press/category/list")
    fun getNewsType():Call<NewsType>

    @GET("/prod-api/press/press/list")
    fun getNewList(@Query("title")title:String?,@Query("type")type:String?):Call<NewsList>

    @GET("/prod-api/press/press/{id}")
    fun getNewsContent(@Path("id")id:Int):Call<NewContent>

    @GET("/prod-api/api/common/user/getInfo")
    fun getUserInfo(@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<UserInfo>

    @PUT("/prod-api/api/common/user")
    fun putUser(@Body user:User,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<Message>

    @PUT("/prod-api/api/common/user/resetPwd")
    fun putPass(@Body pass:Pass,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<Message>

    @POST("/prod-api/api/common/feedback")
    fun postFeed(@Body pass:Feed,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/bus/order/list")
    fun getAll(@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<All>

    @GET("/prod-api/api/gov-service-hotline/ad-banner/list")
    fun getGovBanner(@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<GovBanner>

    @GET("/prod-api/api/gov-service-hotline/appeal-category/list")
    fun getGovType(@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<GovType>

    @GET("prod-api/api/gov-service-hotline/appeal/my-list")
    fun getMyGovList(@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<GovTpyeList>

    @GET("/prod-api/api/gov-service-hotline/appeal/list")
    fun getGovList(@Query("appealCategoryId")appealCategoryId:Int,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<GovTpyeList>

    @GET("/prod-api/api/gov-service-hotline/appeal/{id}")
    fun getGovContent(@Path("id")id:Int,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<GovContent>

    @POST("/prod-api/api/gov-service-hotline/appeal")
    fun postApp(@Body app:App,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/bus/line/list")
    fun getBusList(@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<BusList>

    @GET("/prod-api/api/bus/line/{id}")
    fun getBusContent(@Path("id")id:Int,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<BusContent>
    @POST("/prod-api/api/bus/order")
    fun postBus(@Body bus:Bus,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<Message>

    @GET("/prod-api/press/comments/list")
    fun getNewsComment(@Query("newsId")newsId:Int):Call<NewsComment>

    @POST("/prod-api/press/pressComment")
    fun postComment(@Body pass:Comment,@Header("Authorization")Authorization:String = SmartCiryApplication.TOKEN):Call<Message>


    @Multipart
    @POST("/prod-api/common/upload")
    fun upload(@Part("file") description: RequestBody?): Call<Up>


}