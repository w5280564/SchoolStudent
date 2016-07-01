package com.waylen.schoolstudent.Myactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.Json;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.UrlVO;
import com.waylen.schoolstudent.StaticData.myActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.util.List;

/**
 * Created by Admin on 2016/4/14.
 */
public class ManLogin extends myActivity {
    private LinearLayout login_centet_Lin;
    private MyActivityManager mam;
    private EditText account_edit,psw_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.manlogin);

        DisplayMetrics dm = this.getApplicationContext().getResources().getDisplayMetrics();
        Float scale = (float) dm.widthPixels / 640;
        Float heghtSclae = (float) dm.heightPixels / 1136;
        UrlVO.saveShareData("scale", scale + "", this);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        base_title_Txt.setText("登录");
        StaticData.ViewScale(title_Include, 0, 88);

        SimpleDraweeView my_DraweeView_view = (SimpleDraweeView) findViewById(R.id.my_DraweeView_view);
        Uri uri = Uri.parse("res://com.waylen.schoolstudent/" + R.drawable.base_groud);
        my_DraweeView_view.setImageURI(uri);

        LinearLayout account_Lin = (LinearLayout) findViewById(R.id.account_Lin);
        StaticData.ViewScale(account_Lin, 540, 80);
        ImageView account_img = (ImageView) findViewById(R.id.account_img);
        StaticData.ViewScale(account_img, 34, 36);
        LinearLayout psw_Lin = (LinearLayout) findViewById(R.id.psw_Lin);
        ImageView psw_img = (ImageView) findViewById(R.id.psw_img);
        StaticData.ViewScale(psw_Lin, 540, 80);
        StaticData.ViewScale(psw_img, 31, 38);
        TextView forget_Txt = (TextView) findViewById(R.id.forget_Txt);
        StaticData.ViewScale(forget_Txt, 116, 30);

        int padtop = (int) (Float.parseFloat(UrlVO.getShareData("scale", this)) * 90);
        int padbelow = (int) (Float.parseFloat(UrlVO.getShareData("scale", this)) * 82);
        login_centet_Lin = (LinearLayout) findViewById(R.id.login_centet_Lin);
        login_centet_Lin.setPadding(0, padtop, 0, padbelow);
        ImageView login_center_img = (ImageView) findViewById(R.id.login_center_img);
        StaticData.ViewScale(login_center_img, 278, 311);

         account_edit = (EditText) findViewById(R.id.account_edit);
         psw_edit = (EditText) findViewById(R.id.psw_edit);
        Button login_Btn = (Button) findViewById(R.id.login_Btn);
        StaticData.ViewScale(login_Btn, 400, 80);

        forget_Txt.setOnClickListener(new forget_Txt());
        login_Btn.setOnClickListener(new login_Btn());

        if (!UrlVO.getShareData("islogin",this).equals("false")){
            Intent intent = new Intent(ManLogin.this, MainActivity.class);
            startActivity(intent);
            mam.finishAllActivity();
        }
    }

    public class forget_Txt implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new login_Popup(ManLogin.this, login_centet_Lin);
        }
    }

    public class login_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(ManLogin.this, MainActivity.class);
//            startActivity(intent);
            LoginMethod("http://swfx.sinthonic.com/swfx/main-login.action");

        }
    }

    public class login_Popup extends PopupWindow {
        public login_Popup(final Context mContext, View parent) {
            super(parent);
            View view = View.inflate(mContext, R.layout.popup_remind, null);
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setOutsideTouchable(true);
            setFocusable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.CENTER, 0, 0);
            update();
            LinearLayout popup_white_rel = (LinearLayout) view.findViewById(R.id.popup_white_rel);
            Button ensure_Btn = (Button) view.findViewById(R.id.ensure_Btn);
            StaticData.ViewScale(popup_white_rel, 560, 290);
            StaticData.ViewScale(ensure_Btn, 234, 66);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            ensure_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }

    Json JsonGet = new Json();
    //用户登录
    public void LoginMethod(String baseUrl) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("username",account_edit.getText());//"20150101"
            obj.put("password",psw_edit.getText());//"123456"
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestParams params = new RequestParams(baseUrl);
//        params.setHeader("X-Requested-With", "XMLHttpRequest");

        params.addBodyParameter("param",obj.toString());
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String resultString) {
                if (JsonGet.getReturnValue(resultString, "status").equals("1")) {//1是成功
//                    UrlVO.saveShareData("islogin", "true", ManLogin.this);
//                    Intent intent = new Intent(ManLogin.this, MainActivity.class);
//                    startActivity(intent);
//                    mam.finishAllActivity();
                    UrlVO.saveShareData("islogin", "true", ManLogin.this);

                    PersonData("http://swfx.sinthonic.com/swfx/m-userDetail.action");//  "http://127.0.0.1:8080/oa/doingHomework-work-list.action"


                } else {
                    Toast.makeText(ManLogin.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
                DbCookieStore instance = DbCookieStore.INSTANCE;
                List cookies = instance.getCookies();
                UrlVO.saveShareData("JSESSIONID", cookies.get(cookies.size() - 1).toString(), ManLogin.this);
            }
        });
    }



 //普通用户登录
    public void PersonData(String baseUrl) {
        RequestParams params = new RequestParams(baseUrl);
//        if (UrlVO.getShareData("JSESSIONID", this) != null) {
//            params.setHeader("Cookie", UrlVO.getShareData("JSESSIONID", this));
//        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String resultString) {
                Log.e("resultStringresult",resultString);
                if (JsonGet.getReturnValue(resultString, "status").equals("1")) {//1是成功

                String[] datakey = {"lastLogin","currentYear","userId","username","realName","schoolLogo",
                        "userType","lastLogin","currentYear","termCode","yearId",
                        "userType","termId"};
                    UrlVO.saveShareData("userId",JsonGet.getReturnValue(JsonGet.getReturnValue(resultString,"result"),"userId"),ManLogin.this);

                    Intent intent = new Intent(ManLogin.this, MainActivity.class);
                    startActivity(intent);
                    mam.finishAllActivity();
                }
            }


            @Override
            public void onError(Throwable throwable, boolean b) {
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });
    }






}
