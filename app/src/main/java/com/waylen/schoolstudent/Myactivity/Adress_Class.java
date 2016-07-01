package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.waylen.schoolstudent.Adapter.Class_Adapter;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.MyActivityManager;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.myActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/19.
 */
public class Adress_Class extends myActivity {
    private SwipeRefreshLayout myswipeLayout;
    private RecyclerView my_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityManager mam = MyActivityManager.getInstance();
        mam.pushOneActivity(this);//当前activity压入栈中
        setContentView(R.layout.grade_class);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        base_title_Txt.setText("班级");



        myswipeLayout = (SwipeRefreshLayout) findViewById(R.id.myswipeLayout);
        my_recyclerview = (RecyclerView) findViewById(R.id.news_Xlist);


        myswipeLayout.setOnRefreshListener(new myswipeLayout());
//        onLoadMore();
        initlist();


        base_Back_Btn.setOnClickListener(new base_Back_Btn());
    }
    public class base_Back_Btn implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

    public class myswipeLayout implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
//            last_id = "0";
//            NewsList(UrlVO.BaseUrl + UrlVO.NewsListUrl);
            myswipeLayout.setRefreshing(false);
            Toast.makeText(Adress_Class.this, "Refresh Finsh", Toast.LENGTH_SHORT).show();
        }
    }

//    public void onLoadMore() {
//        my_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                //SCROLL_STATE_DRAGGING  和   SCROLL_STATE_IDLE 两种效果
//                if (myswipeLayout.isRefreshing()) {
//
//                } else {
//                    int lastVisibleItem = mMangaer.findLastVisibleItemPosition();
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
////                        if (newsArr.size() != 0) {
////                            last_id = newsArr.get(newsArr.size() - 1).get("_id").toString();
////                        }
////                        NewsList(UrlVO.BaseUrl + UrlVO.NewsListUrl);
//                        myswipeLayout.setRefreshing(false);
////                        Toast.makeText(getActivity(), "LoadMore Finsh", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }

    private Class_Adapter mAdapter;
    LinearLayoutManager mMangaer;

    public void initlist() {
        List<Map<String, Object>> notifyArr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> oneArr = new HashMap<>();

            notifyArr.add(oneArr);
        }


        //创建Manager
        mMangaer = new LinearLayoutManager(this);
        mMangaer.setOrientation(LinearLayoutManager.VERTICAL);
        my_recyclerview.setLayoutManager(mMangaer);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        my_recyclerview.setHasFixedSize(true);
        mAdapter = new Class_Adapter(this, notifyArr);
        my_recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new Class_Adapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(Adress_Class.this,Adress_StudentOrTeacher.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
    }



}
