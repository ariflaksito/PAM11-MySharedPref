package net.ariflaksito.mysharedpref;

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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                postHttpResponse(tvUsr.getText().toString(), tvPwd.getText().toString());
            }
        });

    }

    public void postHttpResponse(String user, String pwd) {

        OkHttpClient httpClient = new OkHttpClient();
        String url = "http://ariflaksito.net/login.php";

        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("pwd", pwd)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.code() == 200) {

                    Gson gson = new Gson();
                    GsonParse gsonObj = gson.fromJson(response.body().string(), GsonParse.class);

                    UserPreference pref = new UserPreference(LoginActivity.this);
                    pref.setName(gsonObj.getFullname());
                    pref.setEmail(gsonObj.getEmail());
                    pref.setDept(gsonObj.getDept());
                    pref.setCompany(gsonObj.getCompany());

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"Error, User/Password salah!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }
        });
    }

    public class GsonParse {
        @SerializedName("fullname")
        private String fullname;

        @SerializedName("email")
        private String email;

        @SerializedName("dept")
        private String dept;

        @SerializedName("company")
        private String company;

        public String getFullname() {
            return fullname;
        }

        public String getEmail() {
            return email;
        }

        public String getDept() {
            return dept;
        }

        public String getCompany() {
            return company;
        }
    }
}

