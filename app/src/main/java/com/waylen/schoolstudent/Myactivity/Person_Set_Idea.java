package com.waylen.schoolstudent.Myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.myActivity;

/**
 * Created by Admin on 2016/4/19.
 */
public class Person_Set_Idea extends myActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.person_set_idea);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        Button base_Right_Btn = (Button) title_Include.findViewById(R.id.base_Right_Btn);
        base_Right_Btn.setText("提交");
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        StaticData.ViewScale(base_Right_Btn, 100, 80);
        base_title_Txt.setText("用户反馈");


        EditText idea_Edit = (EditText)findViewById(R.id.idea_Edit);
        StaticData.ViewScale(idea_Edit,600,300);

        base_Back_Btn.setOnClickListener(new base_Back_Btn());
    }

    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }
}
