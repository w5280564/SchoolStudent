package com.waylen.schoolstudent.Myactivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;

/**
 * Created by Admin on 2016/4/14.
 */
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//就把当前activity压入了栈中
        setContentView(R.layout.mainactivity);

        initView();
    }

    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;
    private final Class[] fragments = {Fragment1_Work.class, Fragment2_Adress.class,
            Fragment3_Notify.class, Fragment4_person.class};

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = fragments.length;
        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments[i], null);
        }


        RadioGroup tab_menu = (RadioGroup) findViewById(R.id.tab_menu);
        RadioButton tab_work = (RadioButton) findViewById(R.id.tab_work);
        RadioButton tab_Address = (RadioButton) findViewById(R.id.tab_address);
        RadioButton tab_notify = (RadioButton) findViewById(R.id.tab_notify);
        RadioButton tab_person = (RadioButton) findViewById(R.id.tab_person);
//        StaticData.radioGroupScale(tab_menu,0,102);
        StaticData.radioButtonScale(tab_work, 160, 98);
        StaticData.radioButtonScale(tab_notify, 160, 98);
        StaticData.radioButtonScale(tab_Address, 160, 98);
        StaticData.radioButtonScale(tab_person, 160, 98);
        tab_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_work:
                        mTabHost.setCurrentTab(0);
                        break;
                    case R.id.tab_address:
                        mTabHost.setCurrentTab(1);
                        break;
                    case R.id.tab_notify:
                        mTabHost.setCurrentTab(2);
                        break;
                    case R.id.tab_person:
                        mTabHost.setCurrentTab(3);
                        break;
                    default:
                        break;
                }

            }
        });

//        Intent intent = getIntent();
//        String Current = intent.getStringExtra("count");
//        if (Current != null) {
//            if (Current.equals("0")) {
//                tab_person.setChecked(true);
//                mTabHost.setCurrentTab(2);
//            }
//        }
    }

}
