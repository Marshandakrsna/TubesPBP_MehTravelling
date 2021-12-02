package com.example.tubespw_mehtravelling.UnitTesting;

import com.example.tubespw_mehtravelling.survey.api.ApiSurvey;
import com.example.tubespw_mehtravelling.survey.api.SurveyInterface;
import com.example.tubespw_mehtravelling.survey.models.Survey;
import com.example.tubespw_mehtravelling.survey.models.SurveyResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditService {

    //    TODO 6: silahkan salin code ProfilService

    public void survey(final com.example.tubespw_mehtravelling.UnitTesting.AddEditView view, Survey survey, final
    AddEditCallback callback) {
        SurveyInterface apiService =
                ApiSurvey.getSurvey().create(SurveyInterface.class);
        Call<SurveyResponse> surveyDAOCall =
                apiService.createSurvey(survey);
        surveyDAOCall.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call,
                                   Response<SurveyResponse> response) {

                if (response.body().getMessage().equalsIgnoreCase("berhasil daftar"
                )) {
                    callback.onSuccess(true,
                            response.body().getSurveyList().get(0));
                } else {
                    callback.onError();
                    view.showSurveyError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {
                view.showErrorSurveyResponse(t.getMessage());
                callback.onError();
            }
        });
    }

    public Boolean getValid(final com.example.tubespw_mehtravelling.UnitTesting.AddEditView view, Survey survey) {
        final Boolean[] bool = new Boolean[1];
        survey(view, survey, new AddEditCallback() {
            @Override
            public void onSuccess(boolean value, Survey survey) {
                bool[0] = true;
            }

            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}