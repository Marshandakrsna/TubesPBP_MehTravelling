package com.example.tubespw_mehtravelling.survey.api;
import com.example.tubespw_mehtravelling.survey.models.Survey;
import com.example.tubespw_mehtravelling.survey.models.SurveyResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface SurveyInterface {
    @Headers({"Accept: application/json"})
    @GET("travelling")
    Call<SurveyResponse> getAllSurvey(@Header("Authorization")String authHeader);

//    @Headers({"Accept:application/json"})
//    @POST("travelling")
//    Call<SurveyResponse>createSurvey(@Body Survey travelling, @Header("Authorization")String authHeader);

    @POST("travelling")
    @FormUrlEncoded
    Call<SurveyResponse>createSurvey(@Field("namaDestinasi") String namaDestinasi,
                                     @Field("namaPengguna") String namaPengguna,
                                     @Field("alasan") String alasan,
                                     @Field("penilaian") String penilaian,@Header("Authorization")String authHeader);

    @Headers({"Accept: application/json"})
    @GET("travelling/{id}")
    Call<SurveyResponse> getSurveyById(@Path("id") long id,@Header("Authorization")String authHeader);



    @PUT("travelling/{id}")
    @FormUrlEncoded
    Call<SurveyResponse> updateSurvey(@Path("id") long id,
                                      @Field("namaDestinasi") String namaDestinasi,
                                      @Field("namaPengguna") String namaPengguna,
                                      @Field("alasan") String alasan,
                                      @Field("penilaian") String penilaian,@Header("Authorization")String authHeader);

    @Headers({"Accept: application/json"})
    @DELETE("travelling/{id}")
    Call<SurveyResponse> deleteSurvey(@Path("id") long id,@Header("Authorization")String authHeader);

    Call<SurveyResponse> createSurvey(Survey survey);
}