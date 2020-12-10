package net.ariflaksito.mysharedpref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        if (!pref.checkData().equals("")) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLogin(tvUsr.getText().toString(), tvPwd.getText().toString());
            }
        });

    }

    public void postLogin(String user, String pwd) {

        if (user.equals("mhs") && pwd.equals("p455word")) {
            UserPreference pref = new UserPreference(LoginActivity.this);
            pref.setName("Arfan Fatih");
            pref.setEmail("arfan.fs@gmail.com");
            pref.setDept("Informatics");
            pref.setCompany("BMD2 Jogja");

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else {

            Toast.makeText(LoginActivity.this, "Error, User/Password salah!",
                    Toast.LENGTH_LONG).show();

        }

    }

}

