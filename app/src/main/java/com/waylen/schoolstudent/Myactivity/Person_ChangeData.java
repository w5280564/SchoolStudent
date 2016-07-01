package com.waylen.schoolstudent.Myactivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.myActivity;

/**
 * Created by Admin on 2016/4/19.
 */
public class Person_ChangeData extends myActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.person_changedata);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        base_title_Txt.setText("人个资料");


        RelativeLayout head_rel = (RelativeLayout) findViewById(R.id.head_rel);
        SimpleDraweeView my_head = (SimpleDraweeView) findViewById(R.id.my_head);
        Uri headuri = Uri.parse("res://com.waylen.schoolstudent/" + R.drawable.head_icon);
        my_head.setImageURI(headuri);
        StaticData.ViewScale(head_rel, 0, 163);
        StaticData.ViewScale(my_head, 135, 135);


        RelativeLayout grade_rel = (RelativeLayout) findViewById(R.id.grade_rel);
        StaticData.ViewScale(grade_rel, 0, 80);
        RelativeLayout class_rel = (RelativeLayout) findViewById(R.id.class_rel);
        StaticData.ViewScale(class_rel, 0, 80);
        RelativeLayout name_rel = (RelativeLayout) findViewById(R.id.name_rel);
        StaticData.ViewScale(name_rel, 0, 80);
        RelativeLayout family_rel = (RelativeLayout) findViewById(R.id.family_rel);
        StaticData.ViewScale(family_rel, 0, 80);
        Button finish_Btn = (Button) findViewById(R.id.finish_Btn);
        StaticData.ViewScale(finish_Btn, 400, 80);


        base_Back_Btn.setOnClickListener(new base_Back_Btn());
    }

    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }



}
