package com.waylen.schoolstudent.StaticData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"WorldReadableFiles", "WorldWriteableFiles"})
public class UrlVO {
//    public static String BaseUrl = "http://192.168.2.118:3000";
    public static String BaseUrl = "http://test.gazrey.com:3003";

    public static String ImgCodeUrl = "/users/requestAuPic";
    public static String CheckCodeUrl = "/users/checkAuCode";
    public static String PhoneCodeUrl = "/users/requestAuCode";
    public static String RegistUrl = "/users/regist";
    public static String UploadHeadUrl = "/users/uploadPhoto";
    public static String UptadeInfoUrl = "/users/updateUserInfo";
    public static String ForgetUrl = "/users/forget";
    public static String LogintUrl = "/users/login";
    public static String VisLogintUrl = "/users/visitor";
    public static String NewsListUrl = "/news/getList";
    public static String DetailUrl = "/news/getDetail";
    public static String ZanCaiUrl = "/news/think";
    public static String CreateCollectUrl = "/collect/create";
    public static String MoveCollectUrl = "/collect/remove";
    public static String BannertUrl = "/lunbotu";
    public static String FileUrl = "/file/";
    public static String GoodListUrl = "/goods/getList";
    public static String GoodlUrl = "/goods/getDetail";
    public static String PersonDataUrl = "/users/getuserInfo";
    public static String HistoryListUrl = "/history/getList";
    public static String CollectListUrl = "/collect/getList";
    public static String IdeaUrl = "/suggestion/send";
    public static String UpdateVersionUrl = "/version";


    private static SharedPreferences mShared = null;
    /**
     * 程序中可以同时存在多个SharedPreferences数据， 根据SharedPreferences的名称就可以拿到对象
     **/
    public final static String SHARED_MAIN = "main";
    /**
     * SharedPreferences中储存数据的Key名称
     **/
    public final static String KEY_NAME = "name";
    public final static String KEY_NUMBER = "number";

    /**
     * SharedPreferences中储存数据的路径
     **/
    public final static String DATA_URL = "/data/test/";
    public final static String SHARED_MAIN_XML = "test.xml";

    public static void saveShareData(String keyname, String key, Context context) {
        mShared = context.getSharedPreferences(SHARED_MAIN, Context.MODE_PRIVATE);
        Editor editor = mShared.edit();
        editor.putString(keyname, key);
        /**put完毕必需要commit()否则无法保存**/
        editor.commit();
    }

    public static String getShareData(String keyname, Context context) {
        mShared = context.getSharedPreferences(SHARED_MAIN, Context.MODE_PRIVATE);
        String name = mShared.getString(keyname, "false");
        Log.i("zoey", "name:" + name);
        return name;
    }

    public static void clearShareData(String keyname, Context context) {
        mShared = context.getSharedPreferences(SHARED_MAIN, Context.MODE_PRIVATE);
        Editor editor = mShared.edit();
        editor.remove(keyname);
        editor.commit();
    }

    public static void savelistData(List<Map<Integer, Boolean>> listA, Context context) {
        Editor editor = context.getSharedPreferences("listselect", Context.MODE_WORLD_WRITEABLE).edit();
        editor.clear();
        List<Map<Integer, Boolean>> list = listA;
        editor.putInt("size", list.size());
        for (int i = 0; i < list.size(); i++) {
            editor.remove("isexist" + i);
            editor.putString("isexist" + i, list.get(i).get(i).toString());
        }
        editor.commit();
    }

    public static ArrayList<Map<Integer, Boolean>> getlistData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("listselect", Context.MODE_WORLD_READABLE);
        int size = sp.getInt("size", 0);
        ArrayList<Map<Integer, Boolean>> list2 = new ArrayList<Map<Integer, Boolean>>();
        if (size == 0) {
            list2 = new ArrayList<Map<Integer, Boolean>>();
        } else {
            for (int j = 0; j < size; j++) {
                Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
                map.put(j, Boolean.valueOf(sp.getString("isexist" + j, "")));
                list2.add(map);
            }
        }
        return list2;
    }

    //保存List历史
    public static void locList(Context context, String KEY_NAME, String content,List<String> baseList) {
        baseList.add(content);
        SharedPreferences sp = context.getSharedPreferences("locArr", Context.MODE_WORLD_READABLE);
        Editor editor = sp.edit();
        editor.putString(KEY_NAME, baseList.toString());
        editor.commit();
    }

    public static List<String> getlocList(Context context, String KEY_NAME) {
        List<String> baseList = new ArrayList<>();
        SharedPreferences sp = context.getSharedPreferences("locArr", Context.MODE_PRIVATE);
        String result = sp.getString(KEY_NAME, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                baseList.add(0, array.getString(i));
            }
        } catch (JSONException e) {
        }
        return baseList;
    }


    public static void saveInfo(Context context, String key, List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();
            JSONObject object = new JSONObject();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {

                }
            }
            mJsonArray.put(object);
        }
        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    public static List<Map<String, String>> getInfo(Context context, String key) {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        SharedPreferences sp = context.getSharedPreferences("finals", Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<String, String>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {
        }
        return datas;
    }


}
