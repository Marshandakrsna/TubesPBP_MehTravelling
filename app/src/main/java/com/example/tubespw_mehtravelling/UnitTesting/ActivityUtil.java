package com.example.tubespw_mehtravelling.UnitTesting;

import android.content.Context;
import android.content.Intent;

import com.example.tubespw_mehtravelling.survey.AddEditActivity;

public class ActivityUtil {
//    TODO 3: silahkan salin code ActivityUtil

    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startMainAddEdit() {
        context.startActivity(new Intent(context, AddEditActivity.class));
    }
}
