package com.unifam.heartpatrol.model.net;
import com.unifam.heartpatrol.model.ApiGithub;
import com.unifam.heartpatrol.model.Ecg_Result_Model;
import com.unifam.heartpatrol.model.GitHubUser;
import com.unifam.heartpatrol.model.ListUser;
import com.unifam.heartpatrol.model.LocationList;
import com.unifam.heartpatrol.model.Package;
import com.unifam.heartpatrol.model.Profile;
import com.unifam.heartpatrol.model.Promo;
import com.unifam.heartpatrol.model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by User on 20/07/2016.
 */
public interface NetworkService {
    @GET("group/{id}/users")
    Call<List<ApiGithub>> getGroupList(@Path("id") int groupId) ;

    @GET("users/{user}")
    Call<ListUser> getUserList(@Path("user") String user) ;

    @GET("users/{user}")
    Call<GitHubUser> getUser(@Path("user") String user);

    @GET("test/location")
    Call<List<LocationList>> getLocationList() ;

    @FormUrlEncoded
    @POST("register")
    Call<Register> getRegister(@Field("name") String name, @Field("password") String password,
                               @Field("source") String source, @Field("first_name") String first_name,
                               @Field("last_name") String last_name,
                               @Field("device_id") String device_id,
                               @Field("token") String token);

    @FormUrlEncoded
    @POST("login")
    Call<Register> getLogin(@Field("name") String name, @Field("password") String password,
                            @Field("device_id") String device_id,
                            @Field("token") String token);

    @FormUrlEncoded
    @POST("loginSocmed")
    Call<Register> getLoginSocMed(@Field("name") String name);

    @FormUrlEncoded
    @POST("profile")
    Call<Profile> getProfile(@Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("list_user")
    Call<Register> getListUser(@Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("UpdateProfile")
    Call<Profile> postUpdateProfile(@Field("user_name") String user_name,
                                    @Field("birth_date") String birth_date,
                                    @Field("weight") int weight,
                                    @Field("height") int height,
                                    @Field("gender") String gender);

    @POST("package")
    Call<Package> getPackage();

    @FormUrlEncoded
    @POST("ecg_result")
    Call<Ecg_Result_Model> getEcgResult(@Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("ecg_over_read")
    Call<Ecg_Result_Model> getEcgOverRead(@Field("user_name") String user_name,
                                          @Field("ecg_list[]") List<String> ecgList);

    @FormUrlEncoded
    @POST("ecg_over_read_confirm")
    Call<Ecg_Result_Model> getEcgOverReadConfirm(@Field("user_name") String user_name,
                                          @Field("ecg_list[]") List<String> ecgList);


    @FormUrlEncoded
    @POST("ecg_result_delete")
    Call<Ecg_Result_Model> postEcgResultDelete(@Field("user_name") String user_name,
                                               @Field("ecg_list[]") List<String> ecgList);

    @FormUrlEncoded
    @POST("ecg_review_delete")
    Call<Ecg_Result_Model> postEcgReviewDelete(@Field("user_name") String user_name,
                                               @Field("ecg_list[]") List<String> ecgList);

    @FormUrlEncoded
    @POST("ecg_review")
    Call<Ecg_Result_Model> getEcgReview(@Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("promo")
    Call<Promo> getPromo(@Field("promo_code") String user_name);
}
