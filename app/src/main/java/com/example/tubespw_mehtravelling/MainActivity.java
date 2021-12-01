package com.example.tubespw_mehtravelling;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.tubespw_mehtravelling.API.ApiClient;
import com.example.tubespw_mehtravelling.API.ApiInterface;
import com.example.tubespw_mehtravelling.API.User.UserResponse;
import com.example.tubespw_mehtravelling.Preferences.UserPreferences;
import com.example.tubespw_mehtravelling.databinding.ActivityMainBinding;
import com.example.tubespw_mehtravelling.hardware.QRScannerMain;
import com.example.tubespw_mehtravelling.listDestinasi.TampilDataDestinasi;
import com.example.tubespw_mehtravelling.pesanDestinasi.ActivityInputPesan;
import com.example.tubespw_mehtravelling.pesanDestinasi.PesanActivity;
import com.example.tubespw_mehtravelling.profile.ProfileActivity;
import com.example.tubespw_mehtravelling.survey.SurveyActivity;
import com.example.tubespw_mehtravelling.ui.auth.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences app_preferences, shared;
    UserPreferences userPreferences = null;
    private ProgressDialog progressDialog;
    String token;
    int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        shared = getSharedPreferences("getId", Context.MODE_PRIVATE);
        idUser = shared.getInt("idUser", -1);
        token = shared.getString("token", null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Disini kita menghubungkan menu yang telah kita buat dengan activity ini
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_scanner){
            Intent mainActivity = new Intent(MainActivity.this, QRScannerMain.class);
            startActivity(mainActivity);
            setTitle("QR Scanner");
        }

        else if(item.getItemId()==R.id.menu_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
//    final ImageButton dropdownMenu = findViewById(R.id.btn_dropdown);
//            dropdownMenu.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            PopupMenu popup = new PopupMenu(MainActivity.this, dropdownMenu);
//            popup.getMenuInflater()
//                    .inflate(R.menu.menu_dropdown, popup.getMenu());
//
//            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                public boolean onMenuItemClick(MenuItem item) {
//                    Intent intent;
//                    if(item.getItemId()==R.id.menu_profile){
////                                intent = new Intent(getApplicationContext(), LocationActivity.class);
////                                startActivity(intent);
//                    }else if (item.getItemId()==R.id.setting_theme){
//                        intent = new Intent(getApplicationContext(), ThemeActivity.class);
//                        startActivity(intent);
//                    }else if (item.getItemId()==R.id.qrscanner){
//                        Intent mainActivity = new Intent(MainActivity.this, QRScannerMain.class);
//                        startActivity(mainActivity);
//                    } else if(item.getItemId()==R.id.menu_logout) {
//                        logout();
//                    }
//                    return true;
//                }
//            });
//
//            popup.show();
//        }
//    };

    public View.OnClickListener btnListDestinasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, TampilDataDestinasi.class);
            startActivity(mainActivity);
        }
    };

    public View.OnClickListener btnPesanDestinasi = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, ActivityInputPesan.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnRiwayatDestinasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, PesanActivity.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnAboutUs = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, AboutUs.class);
           startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnLokasi= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, LokasiActivity.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnSurvey= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, SurveyActivity.class);
            startActivity(mainActivity);
        }
    };
    public View.OnClickListener btnProfileMenu= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mainActivity = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(mainActivity);
        }
    };



//    public View.OnClickListener btnLogout= new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
////            Intent mainActivity = new Intent(MainActivity.this, RegisterActivity.class);
////            startActivity(mainActivity);
////            finish();
//            userPreferences.logout();
//            Toast.makeText(MainActivity.this, "Baiii baiii", Toast.LENGTH_SHORT).show();
//            checkLogin();
//        }
//    };
//    private void checkLogin(){
//        /* this function will check if user login , akan memunculkan toast jika tidak redirect ke login activity */
//        if(!userPreferences.checkLogin()){
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            finish();
//        }else {
//            Toast.makeText(MainActivity.this, "Welcome back !", Toast.LENGTH_SHORT).show();
//        }
//    }
public void logout() {
    progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Logging Out....");
    progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
    progressDialog.show();

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    Call<UserResponse> logout = apiService.logout("Bearer " + token);

    logout.enqueue(new Callback<UserResponse>() {
        @Override
        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
            //If response's code is 200
            if (response.isSuccessful()) {
                //Edit sharedpreferences
                SharedPreferences.Editor editor = shared.edit();
                editor.putInt("idUser", -1);
                editor.putString("token", null);
                editor.apply();
                Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                Intent moveToLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(moveToLogin);
                finish();
            } else {  //If response's code is 4xx (error)
                try {
                    JSONObject error = new JSONObject(response.errorBody().string());
                    Toast.makeText(MainActivity.this, error.optString("message"), Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MainActivity.this, "Logout Failed", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    });
}
    //  Method untuk mengubah fragment
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.profile_menu,fragment)
                .commit();
    }
}