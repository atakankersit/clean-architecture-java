package baseprojectjava.atakan.android.baseprojectjava.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SharedPreferenceManager {

    public static final String TURKCELL_SAHA = "turkcell.saha";
    public static final String KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN";
    public static final String KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN";
    public static final String KEY_REMEMBER_USERNAME = "KEY_REMEMBER_USERNAME";
    public static final String KEY_REMEMBER_ME = "KEY_REMEMBER_ME";

    private SharedPreferences preferences;
    private Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SharedPreferenceManager(Context context) {
        preferences = context.getSharedPreferences(TURKCELL_SAHA, 0);
        editor = preferences.edit();
    }


    public void setAccessToken(String accessToken) {
        try {
            editor.putString(KEY_ACCESS_TOKEN, accessToken);
            editor.commit();
        } catch (Exception ignored) {
        }
    }

    public void setRefreshToken(String refreshToken) {
        try {
            editor.putString(KEY_REFRESH_TOKEN,refreshToken);
            editor.commit();
        } catch (Exception ignored) {
        }
    }


    public String getRefreshToken() {

        return preferences.getString(KEY_REFRESH_TOKEN, "");

    }

    public String getAccesToken() {
        return preferences.getString(KEY_ACCESS_TOKEN, "");

    }

    public String getRememberUserName(){
        return preferences.getString(KEY_REMEMBER_USERNAME,"");
    }

    public void setRememberUserName(String userName){
        editor.putString(KEY_REMEMBER_USERNAME,userName);
        editor.commit();
    }


    public boolean getRememberMe(){
        return preferences.getBoolean(KEY_REMEMBER_ME,false);
    }

    public void setRememberMe(boolean value){
        editor.putBoolean(KEY_REMEMBER_ME,value);
        editor.commit();
    }

}
