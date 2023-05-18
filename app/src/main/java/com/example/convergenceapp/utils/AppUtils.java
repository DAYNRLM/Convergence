package com.example.convergenceapp.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.example.convergenceapp.BuildConfig;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  AppUtils {
    public static AppUtils utilsInstance;
    public static boolean wantToShow=true;

    //public static String buildURL = "https://nrlm.gov.in/nrlmwebservice/services/convergence/" ;   //live
    public static String buildURL = "http://10.197.183.105:8080/nrlmwebservice/services/convergence/" ;   //local


    public synchronized static AppUtils getInstance() {
        if (utilsInstance == null) {
            utilsInstance = new AppUtils();
        }
        return utilsInstance;
    }

    /*Checking device is rooted or not*/
    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String buildTags = Build.TAGS;
        return buildTags != null && buildTags.contains("test-keys");
    }



    private static boolean checkRootMethod2() {
        String[] paths = { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
                "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) return true;
        }
        return false;
    }



    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) return true;
            return false;
        } catch (Throwable t) {
            return false;
        } finally {
            if (process != null) process.destroy();
        }
    }






    public static boolean isValid(@NonNull String s)
    {
        Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    public String loadAssetData(Context context, String filName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    @NonNull
    private String bytesToHex(@NonNull byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getRandomOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000);
        return "" + otp;
    }

    public String removeComma(String string){
        return string.replaceAll("(,)*$", "");
    }

    public String getDeviceInfo() {
        String deviceInfo = "";
        try{
            deviceInfo = Build.MANUFACTURER + "-" + Build.DEVICE + "-" + Build.MODEL;
        }catch (Exception e){


        }

        if (deviceInfo.equalsIgnoreCase("")|| deviceInfo==null)
            return "123-dummy-123";

        //    appSharedPreferences.setDeviceInfo(deviceInfo);
        return deviceInfo;
    }

   /* public void setLocale(String localeName, Resources res) {
        Locale myLocale = new Locale(localeName);
        Locale.setDefault(myLocale);
        // Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }*/
    public String getAppVersion() {
        String appVersion = "";
        try {
            appVersion = BuildConfig.VERSION_NAME;
        }catch (Exception e){
        }
        return appVersion;
    }

    public String getAndroidApiVersion(){
        int version = 0;
        try {
            version =  Build.VERSION.SDK_INT;
        }catch (Exception e){
        }
        return String.valueOf(version);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCurrentDateAndTime() {
       /* DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        //AppUtils.getInstance().showLog("CurrentDtaeAndTime"+format,AppUtils.class);
        return format;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    public String getSha256(@NonNull String plain_text) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = null;
        hash = digest.digest(plain_text.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hash);
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getCurrentDate() {
       /* DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        //AppUtils.getInstance().showLog("CurrentDtaeAndTime"+format,AppUtils.class);
        return format;
    }
    public void setLocale(@NonNull String localeName, @NonNull Resources res, Context context) {

        Locale myLocale = new Locale(localeName);
        // Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

       /* if (!localeName.equals(localeName)) {
            Locale  myLocale = new Locale(localeName);
           // Resources res = context.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            *//*Intent refresh = new Intent(context, HomeActivity.class);
            refresh.putExtra(currentLang, localeName);
            context.startActivity(refresh);*//*
        } else {
            Toast.makeText(context, "Language already selected!", Toast.LENGTH_SHORT).show();
        }*/
    }




    public  void showLog(String logMsg, Class application) {
        if (wantToShow) {
            Log.d(application.getName(), logMsg);
        }
    }
}
