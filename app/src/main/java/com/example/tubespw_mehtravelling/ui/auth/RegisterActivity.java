package com.example.tubespw_mehtravelling.ui.auth;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tubespw_mehtravelling.API.ApiClient;
import com.example.tubespw_mehtravelling.API.ApiInterface;
import com.example.tubespw_mehtravelling.API.User.UserResponse;
import com.example.tubespw_mehtravelling.Constant;
import com.example.tubespw_mehtravelling.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout emailLayout, nameLayout, passLayout, phoneLayout, cityLayout, countryLayout;
    TextInputEditText email, name, pass, phone, city, country;
    CircleImageView profilePict;
    MaterialButton signUpBtn, cancelBtn;
    private ProgressDialog progressDialog;

    //Camera
    private static final int PERMISSION_CODE = 1000;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri imgUri, selectedImage = null;
    private Bitmap bitmap;
    private String stringImage = "";

    Constant constant;
    SharedPreferences.Editor editor;
    SharedPreferences app_preferences;
    int appTheme;
    int themeColor;
    int appColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this);
        appColor = app_preferences.getInt("color", 0);
        appTheme = app_preferences.getInt("theme", 0);
        themeColor = appColor;
        constant.color = appColor;

        progressDialog = new ProgressDialog(this);

        if (themeColor == 0) {
            setTheme(Constant.theme);
        } else if (appTheme == 0) {
            setTheme(Constant.theme);
        } else {
            setTheme(appTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        profilePict = findViewById(R.id.profile_image_signUp);
        email = findViewById(R.id.ti_signUp_email);
        name = findViewById(R.id.ti_signUp_name);
        pass = findViewById(R.id.ti_signUp_pass);
        phone = findViewById(R.id.ti_signUp_phone_number);
        city = findViewById(R.id.ti_signUp_city);
        country = findViewById(R.id.ti_signUp_country);
        signUpBtn = findViewById(R.id.btn_signUp_submit);
        cancelBtn = findViewById(R.id.btn_signUp_cancel);

        emailLayout = findViewById(R.id.til_signUp_email);
        nameLayout = findViewById(R.id.til_signUp_name);
        passLayout = findViewById(R.id.til_signUp_pass);
        phoneLayout = findViewById(R.id.til_signUp_phone_number);
        cityLayout = findViewById(R.id.til_signUp_city);
        countryLayout = findViewById(R.id.til_signUp_country);

        //Button Pressed
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Resgister Berhasil", Toast.LENGTH_SHORT).show();
                addUser();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profilePict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if system os is >= marshmallow, request runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //request enabling permission
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show popup to request permission
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        //permission granted
                        capturePhoto();
                    }
                } else {
                    //system os < marshmallow
                    capturePhoto();
                }
            }
        });
    }

    //handling permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    capturePhoto(); // permission from popup was granted
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            System.out.println("masuk");
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            System.out.println("bitmap " + bitmap);

            Glide.with(this)
                    .load(bitmap)
                    .into(profilePict);
            bitmap = getResizedBitmap(bitmap, 512);
            stringImage = getBase64String(bitmap);
        }
    }
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String base64String = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        return base64String;
    }

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,2);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private void addUser() {
        final String emailSign = email.getText().toString();
        final String nameSign = name.getText().toString();
        final String passSign = pass.getText().toString();
        final String phoneSign = phone.getText().toString();
        final String citySign = city.getText().toString();
        final String countrySign = country.getText().toString();

        if (stringImage.equals("")) {
            Toast.makeText(this, "Upload your image", Toast.LENGTH_SHORT).show();
        }
        if (emailSign.isEmpty()) emailLayout.setError("Please enter your email");
        else if (!isEmailValid(emailSign)) emailLayout.setError("Please enter a valid email");
        else emailLayout.setError(null);

        if (nameSign.isEmpty()) nameLayout.setError("Please enter your name");
        else nameLayout.setError(null);

        if (passSign.isEmpty()) passLayout.setError("Please enter your password");
        else passLayout.setError(null);

        if (phoneSign.isEmpty()) phoneLayout.setError("Please enter your phone number");
        else phoneLayout.setError(null);

        if (citySign.isEmpty()) cityLayout.setError("Please enter your city");
        else cityLayout.setError(null);

        if (countrySign.isEmpty()) countryLayout.setError("Please enter your country");
        else countryLayout.setError(null);

        if (!stringImage.equals("") && isEmailValid(emailSign) && !emailSign.isEmpty() && !nameSign.isEmpty() && !passSign.isEmpty()
                && !phoneSign.isEmpty() && !citySign.isEmpty() && !countrySign.isEmpty()) {

            progressDialog.setMessage("Processing....");
            progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<UserResponse> add = apiService.register(nameSign, emailSign, passSign,countrySign,citySign, phoneSign, "asdasd");
//            Call<UserResponse> add = apiService.register(nameSign, emailSign, passSign, phoneSign, citySign, countrySign, imgUri.toString());
            Log.d("asdasd","Masuk ke on response");

            add.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    //If response's code is 200
                    Log.d("asdasd",response.toString()+"asdasd");
                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                        onBackPressed();
                    } else {  //If response's code is 4xx (error)
                        try {
                            JSONObject error = new JSONObject(response.errorBody().string());
                            Toast.makeText(RegisterActivity.this, error.optString("message"), Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, "error", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                    Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }
}