package com.example.tubespw_mehtravelling.profile;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.tubespw_mehtravelling.API.ApiClient;
import com.example.tubespw_mehtravelling.API.ApiInterface;
import com.example.tubespw_mehtravelling.API.User.UserResponse;
import com.example.tubespw_mehtravelling.R;
import com.example.tubespw_mehtravelling.databinding.FragmentEditProfileBinding;
import com.example.tubespw_mehtravelling.profile.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditProfileFragment extends Fragment {
    private TextInputLayout nameLayout, phoneLayout, cityLayout, countryLayout;
    private TextInputEditText name, phone, city, country;
    private String nameEdit, phoneEdit, cityEdit, countryEdit;
    private CircleImageView image;
    FragmentEditProfileBinding editProfileBinding;
    private String sIdUser, sName, sEmail, sCountry, sCity, sPhone, sImage;
    private View view;

    SharedPreferences shared;
    int idUser;
    String token;
    List<User> userList;

    MaterialButton btnEdit, btnCancel;

    //Camera
    private static final int PERMISSION_CODE = 1000;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Uri imgUri;
    private Uri selectedImage = null;
    private Bitmap bitmap;

    private ProgressDialog progressDialog;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        editProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false);
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        //Get sharepreferences for ID user
        shared = getActivity().getSharedPreferences("getId", Context.MODE_PRIVATE);
        idUser = shared.getInt("idUser", -1);
        token = shared.getString("token", null);
        Log.d("ID USER Edit Profile", String.valueOf(idUser));

        progressDialog = new ProgressDialog(getContext());

        Intent i = getActivity().getIntent();
        sIdUser = i.getStringExtra("id");
        sName = i.getStringExtra("name");
        sEmail = i.getStringExtra("email");
        sCountry = i.getStringExtra("country");
        sCity = i.getStringExtra("city");
        sPhone = i.getStringExtra("phone");
        sImage = i.getStringExtra("image");

        image = view.findViewById(R.id.profile_image_edit);

        //Fill the field with previous values
        getUsers();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Get ID
        btnEdit = view.findViewById(R.id.btn_editSubmitProfile);

        name = view.findViewById(R.id.ti_name);
        phone = view.findViewById(R.id.ti_phone_number);
        city = view.findViewById(R.id.ti_city);
        country = view.findViewById(R.id.ti_country);

        nameLayout = view.findViewById(R.id.til_name);
        phoneLayout = view.findViewById(R.id.til_phone_number);
        cityLayout = view.findViewById(R.id.til_city);
        countryLayout = view.findViewById(R.id.til_country);

        btnCancel = view.findViewById(R.id.btn_editCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent mainActivity = new Intent(EditProfileFragment.this, MainActivity.class);
//                startActivity(mainActivity);
            }
        });

        //Profile image pressed
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if system os is >= marshmallow, request runtime permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                            ActivityCompat.checkSelfPermission(getContext(),
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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

        //Button edit profile pressed
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(view);
            }
        });
    }
    //handling permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    capturePhoto(); // permission from popup was granted
                } else {
                    Toast.makeText(this.getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1)
        {
            selectedImage = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            image.setImageBitmap(bitmap);
            bitmap = getResizedBitmap(bitmap, 512);
        }
        else if(resultCode == RESULT_OK && requestCode == 2)
        {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(bitmap);
            bitmap = getResizedBitmap(bitmap, 512);
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

    //Camera
    public void capturePhoto() {


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,2);
    }

    private void getUsers(){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> load = apiService.getUser("Bearer " + token);
        load.enqueue(new Callback<UserResponse>()
        {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                sName = response.body().getUsers().getName();
                sEmail = response.body().getUsers().getEmail();
                sPhone = response.body().getUsers().getPhone();
                sCity = response.body().getUsers().getCity();
                sCountry = response.body().getUsers().getCountry();
                sImage = response.body().getUsers().getPhoto();
                name.setText(sName);
                phone.setText(sPhone);
                city.setText(sCity);
                country.setText(sCountry);

                String url = "https://www.mehtravellingtubes.xyz/public/api/" + sImage;
                System.out.println("url gambar " + url);

                Glide.with(getContext())
                        .load(url)
                        .into(image);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update(View view) {
        //Get value from text fields
        nameEdit = name.getText().toString();
        phoneEdit = phone.getText().toString();
        cityEdit = city.getText().toString();
        countryEdit = country.getText().toString();

        //Input Edit Profile Exception
        if (nameEdit.isEmpty()) nameLayout.setError("Please enter your name");
        else nameLayout.setError(null);

        if (phoneEdit.isEmpty()) phoneLayout.setError("Please enter your phone number");
        else phoneLayout.setError(null);

        if (cityEdit.isEmpty()) cityLayout.setError("Please enter your city");
        else cityLayout.setError(null);

        if (countryEdit.isEmpty()) countryLayout.setError("Please enter your country");
        else countryLayout.setError(null);

        if (!nameEdit.isEmpty() && !phoneEdit.isEmpty()
                && !cityEdit.isEmpty() && !countryEdit.isEmpty()) {

            progressDialog.setMessage("Updating....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            String gambar = "";
            if (bitmap != null){
                System.out.println("BITMAP GA NULL");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                gambar = Base64.encodeToString(bytes, Base64.DEFAULT);
                System.out.println("BASE64" + gambar);
            }

            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<UserResponse> req = apiService.updateUser(String.valueOf(idUser), nameEdit,
                    sEmail, countryEdit, cityEdit, phoneEdit, gambar,
                    "Bearer " + token);

            req.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    //If response's code is 200
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
//                        Intent mainActivity = new Intent(EditProfileFragment.this, MainActivity.class);
//                        startActivity(mainActivity);
                    } else { //If response's code is 4xx (error)
                        try {
                            JSONObject error = new JSONObject(response.errorBody().string());
                            Toast.makeText(getContext(), error.optString("message"), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getContext(), "Update data failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Log.i("UPDATE", "Msg: " + t.getMessage());
                }
            });
        }
    }

}