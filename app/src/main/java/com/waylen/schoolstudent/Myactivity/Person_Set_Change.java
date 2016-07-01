package com.waylen.schoolstudent.Myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.myActivity;

/**
 * Created by Admin on 2016/4/19.
 */
public class Person_Set_Change extends myActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.person_set_change);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        base_title_Txt.setText("修改密码");


        LinearLayout old_Lin = (LinearLayout) findViewById(R.id.old_Lin);
        ImageView old_img = (ImageView) findViewById(R.id.old_img);
        StaticData.ViewScale(old_Lin, 540, 80);
        StaticData.ViewScale(old_img, 31, 38);

        LinearLayout new_Lin = (LinearLayout) findViewById(R.id.new_Lin);
        ImageView new_img = (ImageView) findViewById(R.id.new_img);
        StaticData.ViewScale(new_Lin, 540, 80);
        StaticData.ViewScale(new_img, 31, 38);

        LinearLayout again_Lin = (LinearLayout) findViewById(R.id.again_Lin);
        ImageView again_img = (ImageView) findViewById(R.id.again_img);
        StaticData.ViewScale(again_Lin, 540, 80);
        StaticData.ViewScale(again_img, 31, 38);

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
