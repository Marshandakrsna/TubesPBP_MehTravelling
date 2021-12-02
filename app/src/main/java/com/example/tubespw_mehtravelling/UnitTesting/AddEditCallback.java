package com.example.tubespw_mehtravelling.UnitTesting;


import com.example.tubespw_mehtravelling.survey.models.Survey;

public interface AddEditCallback {

    void onSuccess(boolean value, Survey survey);
    void onError();
}
