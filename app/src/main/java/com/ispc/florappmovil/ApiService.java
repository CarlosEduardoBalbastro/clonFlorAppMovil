package com.ispc.florappmovil;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ApiService {

    @POST("api/token/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}
