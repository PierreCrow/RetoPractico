package com.appledroideirl.appuntomarcafreelancer.presentation.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper {

    public static void saveUserAppPreference(Context context, Usuario userPreference) {
        SharedPreferences preferenciasssee = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editoriieei = preferenciasssee.edit();
        editoriieei.putInt(Constants.PREFERENCES_KEYS.CURRENT_USER_ID, userPreference.getId());
        editoriieei.putInt(Constants.PREFERENCES_KEYS.CURRENT_USER_ID_TYPE_DOCUMENT, userPreference.getId_type_document());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_ABOUT, userPreference.getAbout());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_AVG_RATE, userPreference.getAvg_rate().toString());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHONE, userPreference.getCellphone());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_DOCUMENT_NUMBER, userPreference.getDocument_number());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_FULL_NAME, userPreference.getFull_name());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_EMAIL, userPreference.getMail());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PASSWORD, userPreference.getPassword());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHOTO, userPreference.getPhoto());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_SOCIAL_NAME, userPreference.getSocial_name());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_TYPE_USER, userPreference.getType_user());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN, userPreference.getToken());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_HAS_LOCATION, userPreference.isHasLocation());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_LAT, userPreference.getLat());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_LNG, userPreference.getLng());
        editoriieei.putString(Constants.PREFERENCES_KEYS.CURRENT_USER_FCM, userPreference.getFcm());
        editoriieei.putBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, userPreference.isLogged());
        editoriieei.apply();
    }


    public static Usuario getUserAppPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.PREFERENCES.PREFERENCE_CURRENT_USER, Context.MODE_PRIVATE);
        Usuario userPreference =
                new Usuario(preferences.getInt(Constants.PREFERENCES_KEYS.CURRENT_USER_ID, 0),
                        preferences.getInt(Constants.PREFERENCES_KEYS.CURRENT_USER_ID_TYPE_DOCUMENT, 0),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_USER, "test"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_ABOUT, ""),
                        Double.parseDouble(preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_AVG_RATE, "0.0")),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHONE, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_DOCUMENT_NUMBER, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_FULL_NAME, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_EMAIL, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PASSWORD, "1234"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_PHOTO, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_SOCIAL_NAME, ""),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_TYPE_USER, ""),
                        null,
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_TOKEN, ""),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_HAS_LOCATION, false),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_LAT, "0.0"),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_LNG, "0.0"),
                        preferences.getBoolean(Constants.PREFERENCES_KEYS.CURRENT_USER_LOGGED, false),
                        preferences.getString(Constants.PREFERENCES_KEYS.CURRENT_USER_FCM, ""));

        return userPreference;
    }


    public static void urlToImageView(String urlFoto, ImageView imagev, Context contexto) {
        Glide.with(contexto)
                .load(urlFoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.override(100, 200)
                .fitCenter()
                .into(imagev);
    }

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean conectado = null;
        if (connectivity != null) {
            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                conectado = true;
            else {
                conectado = false;
            }
        } else {
            conectado = false;
        }
        return conectado;
    }

    public static String convertTwoDecimals(float number) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        String converted = Double.toString(bd.doubleValue());
        return converted;
    }

    public static String convertTwoDecimals(Double number) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String converted = Double.toString(bd.doubleValue());
        return converted;
    }


}
