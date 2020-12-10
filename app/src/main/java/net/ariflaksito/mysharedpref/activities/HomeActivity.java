package net.ariflaksito.mysharedpref.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.ariflaksito.mysharedpref.R;
import net.ariflaksito.mysharedpref.tools.UserPreference;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        UserPreference pref = new UserPreference(this);

        TextView tvName = (TextView)findViewById(R.id.tv_name);
        TextView tvEmail = (TextView)findViewById(R.id.tv_email);
        TextView tvDept = (TextView)findViewById(R.id.tv_dept);
        TextView tvCompany = (TextView)findViewById(R.id.tv_company);

        tvName.setText(pref.getName());
        tvEmail.setText(pref.getEmail());
        tvDept.setText(pref.getDept());
        tvCompany.setText(pref.getCompany());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.logout:

                UserPreference pref = new UserPreference(this);
                pref.removeData();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));

                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}
