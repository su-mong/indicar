package com.iindicar.indicar.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import com.iindicar.indicar.Constant;

import java.util.Locale;

/**
 * Created by candykick on 2018. 6. 8..
 */

public class LocaleHelper {

    public static Context onAttach(Context context) {
        //String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        return setLocale(context);
    }

    public static Context onAttach(Context context, String defaultLanguage) {
        //String lang = getPersistedData(context, defaultLanguage);
        return setLocale(context);
    }

    public static String getLanguage(Context context) {
        return getPersistedData(context);
    }

    public static void setLanguage(Context context, String lang) {
        SharedPreferences preferences = context.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("locale", lang);
        editor.apply();
    }

    public static Context setLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context);
        }
        return updateResourcesLegacy(context);
    }

    private static String getPersistedData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        Locale systemLocale = context.getResources().getConfiguration().locale;
        return preferences.getString("locale",systemLocale.getLanguage());//kr 혹은 다른 곳
    }


    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context) {
        String lang = getPersistedData(context);
        Constant.locale=lang;
        if(!lang.equals("ko")) {
            lang = new String("en");
        }

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context) {
        String lang = getPersistedData(context);
        if(!lang.equals("ko")) {
            lang = new String("en");
        }

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }
}
