package net.ariflaksito.mysharedpref.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import net.ariflaksito.mysharedpref.R;
import net.ariflaksito.mysharedpref.models.Profile;
import net.ariflaksito.mysharedpref.network.Api;
import net.ariflaksito.mysharedpref.network.RetrofitClient;
import net.ariflaksito.mysharedpref.tools.UserPreference;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        progress = (ProgressBar) findViewById(R.id.progressBar);

        final EditText tvUsr = (EditText) findViewById(R.id.tv_usr);
        final EditText tvPwd = (EditText) findViewById(R.id.tv_pwd);

        UserPreference pref = new UserPreference(this);
        if(!pref.checkData().equals("")){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Profile> call = RetrofitClient.getInstance().getMyApi()
                        .checkLogin(tvUsr.getText().toString(), tvPwd.getText().toString());

                call.enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if(response.code()==200) {
                            Profile profile = response.body();
                            UserPreference pref = new UserPreference(LoginActivity.this);

                            pref.setName(profile.getFullname());
                            pref.setEmail(profile.getEmail());
                            pref.setDept(profile.getDept());
                            pref.setCompany(profile.getCompany());

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(LoginActivity.this,"Error, User/Password salah!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {

                    }
                });
            }
        });

    }

}

