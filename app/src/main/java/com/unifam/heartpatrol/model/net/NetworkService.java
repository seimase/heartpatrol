package com.unifam.heartpatrol.model.net;
import com.unifam.heartpatrol.model.ApiGithub;
import com.unifam.heartpatrol.model.GitHubUser;
import com.unifam.heartpatrol.model.ListUser;
import com.unifam.heartpatrol.model.LocationList;
import com.unifam.heartpatrol.model.Package;
import com.unifam.heartpatrol.model.Profile;
import com.unifam.heartpatrol.model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
                               @Field("last_name") String last_name);
    @FormUrlEncoded
    @POST("login")
    Call<Register> getLogin(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("loginSocmed")
    Call<Register> getLoginSocMed(@Field("name") String name);

    @FormUrlEncoded
    @POST("profile")
    Call<Profile> getProfile(@Field("user_name") String user_name);

    @POST("package")
    Call<Package> getPackage();
}
