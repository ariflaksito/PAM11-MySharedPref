package net.ariflaksito.mysharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private String KEY_NAME = "fullname";
    private String KEY_EMAIL = "email";
    private String KEY_DEPT = "dept";
    private String KEY_COMPANY = "company";
    private SharedPreferences preferences;

    UserPreference(Context context) {
        String PREFS_NAME = "UserPref";
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    public void setName(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
    }
    public String getName() {
        return preferences.getString(KEY_NAME, null);
    }
    public void setEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }
    public String getEmail() {
        return preferences.getString(KEY_EMAIL, null);
    }
    public void setDept(String dept) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_DEPT, dept);
        editor.apply();
    }
    public String getDept() {
        return preferences.getString(KEY_DEPT, null);
    }
    public void setCompany(String company) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_COMPANY, company);
        editor.apply();
    }
    public String getCompany() {
        return preferences.getString(KEY_COMPANY, null);
    }

    public void removeData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_NAME).commit();
        editor.remove(KEY_EMAIL).commit();
        editor.remove(KEY_DEPT).commit();
        editor.remove(KEY_COMPANY).commit();
    }

    public String checkData() {
        String stringMember = preferences.getString(KEY_NAME, "");
        return stringMember;
    }

}



