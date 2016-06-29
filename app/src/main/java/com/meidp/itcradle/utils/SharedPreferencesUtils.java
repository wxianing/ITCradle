package com.meidp.itcradle.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    private static String CONFIG = "config";
    private static SharedPreferences sharedPreferences;
    private static final String USER = "user";

    public static void saveStringData(Context context, String key, String data) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        // 存储数据
        sharedPreferences.edit().putString(key, data).commit();
    }

    public static String getStringData(Context context, String key,
                                       String defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defValue);
    }

    public static void saveIntData(Context context, String key, int data) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        // 存储数据
        sharedPreferences.edit().putInt(key, data).commit();
    }

    public static int getIntData(Context context, String key, int defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getInt(key, defValue);
    }

    public static void savebooleanData(Context context, String key, boolean data) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        // 存储数据
        sharedPreferences.edit().putBoolean(key, data).commit();
    }

    public static boolean getbooleanData(Context context, String key,
                                         boolean defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, defValue);
    }

    public static void clearData() {
        sharedPreferences.edit().clear().commit();
    }

    public static void save(Context context, String key, String str) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, str);
        edit.commit();
    }

//    public static boolean saveUser(Context context, User user) {
//        if (user == null) {
//            save(context, USER, "");
//            return true;
//        }
//        try {
//            String userStr = toBase64(user);
//            save(context, USER, userStr);
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public static User getUser(Context context) {
//        String str = getValue(context, USER);
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        try {
//            User user = (User) base64ToObject(str);
//            return user;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    private static Object base64ToObject(String str) throws Exception,
//            ClassNotFoundException {
//        byte[] buf = Base64.decode(str);
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
//                buf);
//        ObjectInputStream objectInputStream = new ObjectInputStream(
//                byteArrayInputStream);
//        Object obj = objectInputStream.readObject();
//        objectInputStream.close();
//        byteArrayInputStream.close();
//        return obj;
//    }
//
//    public static String getValue(Context context, String key) {
//        SharedPreferences sp = context.getSharedPreferences(CONFIG,
//                Context.MODE_PRIVATE);
//        return sp.getString(key, "");
//    }
//
//    private static String toBase64(Object obj) throws IOException {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
//                byteArrayOutputStream);
//        objectOutputStream.writeObject(obj);
//        String serStr = new String(Base64.encode(byteArrayOutputStream.toByteArray()));
//        objectOutputStream.close();
//        byteArrayOutputStream.close();
//        return serStr;
//    }

    public static boolean getLoginTag(Context context) {
        return getbooleanData(context, "login", false);
    }

    public static void setLoginTag(Context context, boolean boo) {
        savebooleanData(context, "login", boo);
    }


}
