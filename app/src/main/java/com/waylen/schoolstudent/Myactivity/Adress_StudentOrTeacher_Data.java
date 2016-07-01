package com.waylen.schoolstudent.Myactivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.UrlVO;
import com.waylen.schoolstudent.StaticData.myActivity;

/**
 * Created by Admin on 2016/4/19.
 */
public class Adress_StudentOrTeacher_Data extends myActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.studentorteacher_data);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        base_title_Txt.setText("详细资料");


        SimpleDraweeView my_DraweeView_view = (SimpleDraweeView) findViewById(R.id.my_DraweeView_view);
        StaticData.ViewScale(my_DraweeView_view, 640, 320);
        Uri uri = Uri.parse("res://com.waylen.schoolstudent/" + R.drawable.person_bg);
        my_DraweeView_view.setImageURI(uri);

        int pad = (int) (Float.parseFloat(UrlVO.getShareData("scale", this)) * 313);
        int padbelow = (int) (Float.parseFloat(UrlVO.getShareData("scale", this)) * 18);
        LinearLayout head_Lin = (LinearLayout) findViewById(R.id.head_Lin);
        head_Lin.setPadding(0, pad, 0, padbelow);
        SimpleDraweeView my_head = (SimpleDraweeView) findViewById(R.id.my_head);
        Uri headuri = Uri.parse("res://com.waylen.schoolstudent/" + R.drawable.head_icon);
        my_head.setImageURI(headuri);
        StaticData.ViewScale(my_head, 135, 135);

        RelativeLayout name_rel = (RelativeLayout) findViewById(R.id.name_rel);
        StaticData.ViewScale(name_rel, 0, 80);
        RelativeLayout grade_rel = (RelativeLayout) findViewById(R.id.grade_rel);
        StaticData.ViewScale(grade_rel, 0, 80);
        RelativeLayout class_rel = (RelativeLayout) findViewById(R.id.class_rel);
        StaticData.ViewScale(class_rel, 0, 80);
        RelativeLayout job_rel = (RelativeLayout) findViewById(R.id.job_rel);
        StaticData.ViewScale(job_rel, 0, 80);

        Button mess_Btn = (Button) findViewById(R.id.mess_Btn);
        StaticData.ViewScale(mess_Btn, 400, 80);
        Button firend_Btn = (Button) findViewById(R.id.firend_Btn);
        StaticData.ViewScale(firend_Btn, 400, 80);

        if (UrlVO.getShareData("card", this).equals("老师")){
            job_rel.setVisibility(View.VISIBLE);
        }else {
            job_rel.setVisibility(View.INVISIBLE);
        }


        base_Back_Btn.setOnClickListener(new base_Back_Btn());
    }

    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }



}
