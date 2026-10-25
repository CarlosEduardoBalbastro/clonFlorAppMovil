package com.ispc.florappmovil;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {

    @POST("api/token/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/flora/especies/")
    Call<List<Especie>> getEspecies(@Header("Authorization") String token);

}
