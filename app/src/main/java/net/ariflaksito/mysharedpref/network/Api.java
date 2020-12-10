package net.ariflaksito.mysharedpref.network;

import net.ariflaksito.mysharedpref.models.Profile;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://10.0.2.2:8000/";

    @FormUrlEncoded
    @POST("login.php")
    Call<Profile> login(@Field("user") String usr, @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("login.php")
    Call<Profile> checkLogin(@Field("user") String usr, @Field("pwd") String pwd);

}
