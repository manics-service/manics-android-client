package com.app.manics.tool;


import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "manics";
    private static final int PREF_PRIVATE_MODE = 0;
    public static final String PREF_SESSION_KEY = "session";

    private SharedPreferences mSharedPreferences;

    public SessionManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, PREF_PRIVATE_MODE);
    }

    public void createSession(String session) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(PREF_SESSION_KEY, session);
        editor.apply();
    }

    public String getSession() {
        return mSharedPreferences.getString(PREF_SESSION_KEY, null);
    }

    public void clearSession(){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
