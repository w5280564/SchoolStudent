package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.UrlVO;
import com.waylen.schoolstudent.StaticData.myActivity;

/**
 * Created by Admin on 2016/4/19.
 */
public class Person_Set extends myActivity {
    private MyActivityManager mam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.person_set);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        base_title_Txt.setText("设置");

        RelativeLayout set_rel = (RelativeLayout) findViewById(R.id.set_rel);
        ImageView set_img = (ImageView) findViewById(R.id.set_img);
        StaticData.ViewScale(set_rel, 0, 80);
        StaticData.ViewScale(set_img, 19, 32);

        RelativeLayout mess_rel = (RelativeLayout) findViewById(R.id.mess_rel);
        ImageView mess_img = (ImageView) findViewById(R.id.mess_img);
        StaticData.ViewScale(mess_rel, 0, 80);
        StaticData.ViewScale(mess_img, 19, 32);
        RelativeLayout change_rel = (RelativeLayout) findViewById(R.id.change_rel);
        ImageView change_img = (ImageView) findViewById(R.id.change_img);
        StaticData.ViewScale(change_rel, 0, 80);
        StaticData.ViewScale(change_img, 19, 32);
        RelativeLayout quit_rel = (RelativeLayout) findViewById(R.id.quit_rel);
        ImageView quit_img = (ImageView) findViewById(R.id.quit_img);
        StaticData.ViewScale(quit_rel, 0, 80);
        StaticData.ViewScale(quit_img, 19, 32);


        base_Back_Btn.setOnClickListener(new base_Back_Btn());
        set_rel.setOnClickListener(new set_rel());
        mess_rel.setOnClickListener(new mess_rel());
        change_rel.setOnClickListener(new change_rel());
        quit_rel.setOnClickListener(new quit_rel());
    }

    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    public class set_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Person_Set.this, Person_Set_System.class);
            startActivity(i);
        }
    }

    public class mess_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Person_Set.this, Person_Set_Idea.class);
            startActivity(i);
        }
    }

    public class change_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Person_Set.this, Person_Set_Change.class);
            startActivity(i);
        }
    }

    public class quit_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            UrlVO.clearShareData("islogin", Person_Set.this);
            Intent i = new Intent(Person_Set.this, ManLogin.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            mam.finishAllActivity();
        }
    }

}
