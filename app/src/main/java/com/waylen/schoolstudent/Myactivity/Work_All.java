package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;

import java.util.ArrayList;

/**
 * Created by Admin on 2016/4/18.
 */
public class Work_All extends FragmentActivity {
    private TextView view1, view2, view3, view4;
    private int bmpW;//横线图片宽度
    private int offset;//图片移动的偏移量
    private int currIndex;//当前页卡编号
    private ViewPager mPager;
    private ImageView image;
    private ArrayList<Fragment> fragmentList;
    private String titlename;
    private String courseType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.work_all);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);

        Intent i = getIntent();
         titlename = i.getStringExtra("titlename");
         courseType = i.getStringExtra("courseType");
        base_title_Txt.setText(titlename);

        base_Back_Btn.setOnClickListener(new base_Back_Btn());
        InitTextView();
        InitImage();
        InitViewPager();

    }

    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    /*
     * 初始化标签名
     */
    public void InitTextView() {
        LinearLayout tab_menu = (LinearLayout) findViewById(R.id.tab_menu);
        StaticData.ViewScale(tab_menu, 0, 80);
        view1 = (TextView) findViewById(R.id.work_one);
        view2 = (TextView) findViewById(R.id.work_two);
        view3 = (TextView) findViewById(R.id.work_three);
        view4 = (TextView) findViewById(R.id.work_four);

        view1.setOnClickListener(new txListener(0));
        view2.setOnClickListener(new txListener(1));
        view3.setOnClickListener(new txListener(2));
        view4.setOnClickListener(new txListener(3));
    }

    public class txListener implements View.OnClickListener {
        private int index = 0;

        public txListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            mPager.setCurrentItem(index);
        }
    }

    /*
     * 初始化图片的位移像素
     */
    public void InitImage() {
        image = (ImageView) findViewById(R.id.cursor);
        StaticData.ViewScale(image, 160, 6);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.cursor_icon).getWidth();
//        bmpW =(int)Float.parseFloat(UrlVO.getShareData("scale", Work_All.this)) * bmpW;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW / 4 - bmpW) / 2;
        //imgageview设置平移，使下划线平移到初始位置（平移一个offset）
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        image.setImageMatrix(matrix);
    }


    /*
     * 初始化ViewPager
     */
    public void InitViewPager() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        //关闭预加载，默认一次只加载一个Fragment
        mPager.setOffscreenPageLimit(0);
        fragmentList = new ArrayList<Fragment>();
        //workType 1作业 2以往作业 3长期作业 4推荐阅读  courseType 语数外 优秀作业

         Fragment firstFragment = WorkbaseFragment.newInstance(courseType,"1");
        Fragment secondFragment = WorkbaseFragment.newInstance(courseType,"2");
        Fragment thirdFragment = WorkbaseFragment.newInstance(courseType,"3");
        Fragment fourthFragment = WorkbaseFragment.newInstance(courseType,"4");
        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(thirdFragment);
        fragmentList.add(fourthFragment);

        //给ViewPager设置适配器
        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mPager.setCurrentItem(0);//设置当前显示标签页为第一页
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
    }


    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int one = offset * 2 + bmpW;//两个相邻页面的偏移量
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            Animation animation = new TranslateAnimation(currIndex * one, arg0 * one, 0, 0);//平移动画
            currIndex = arg0;
            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            animation.setDuration(200);//动画持续时间0.2秒
            image.startAnimation(animation);//用ImageView来显示动画的
//            fragmentList.get(arg0).setUserVisibleHint(true);
//             int workType = currIndex + 1;
//            WorkbaseFragment.newInstance(titlename,workType+"");
//            Toast.makeText(MainActivity.this, "您选择了第" + workType + "个页卡", Toast.LENGTH_SHORT).show();
        }
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            fragmentList = list;
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }





}


