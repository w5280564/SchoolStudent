package com.waylen.schoolstudent.Myactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.waylen.schoolstudent.Adapter.Address_Adapter;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.StaticData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/14.
 */
public class Fragment2_Adress extends Fragment{

    private View parentView;
    private SwipeRefreshLayout myswipeLayout;
    private RecyclerView my_recyclerview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment2_address, null);
            View title_Include = parentView.findViewById(R.id.title_Include);
            TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
            base_title_Txt.setText("联系人");
            StaticData.ViewScale(title_Include, 0, 88);

            LinearLayout seek_Lin = (LinearLayout) parentView.findViewById(R.id.seek_Lin);
            ImageView seek_icon = (ImageView) parentView.findViewById(R.id.seek_icon);

            StaticData.ViewScale(seek_Lin, 600, 80);
            StaticData.ViewScale(seek_icon, 43, 43);


            myswipeLayout = (SwipeRefreshLayout) parentView.findViewById(R.id.myswipeLayout);
            my_recyclerview = (RecyclerView) parentView.findViewById(R.id.news_Xlist);


            myswipeLayout.setOnRefreshListener(new myswipeLayout());
//            onLoadMore();
            initlist();


        }
        ViewGroup parent = (ViewGroup) parentView.getParent();
        if (parent != null) {
            parent.removeView(parentView);
        }
        return parentView;
    }

    public class myswipeLayout implements SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {
//            last_id = "0";
//            NewsList(UrlVO.BaseUrl + UrlVO.NewsListUrl);
            myswipeLayout.setRefreshing(false);
            Toast.makeText(getActivity(), "Refresh Finsh", Toast.LENGTH_SHORT).show();
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

    private Address_Adapter mAdapter;
    LinearLayoutManager mMangaer;
    public void initlist() {
        List<Map<String, Object>> notifyArr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> oneArr = new HashMap<>();

            notifyArr.add(oneArr);
        }


        //创建Manager
        mMangaer = new LinearLayoutManager(getActivity());
        mMangaer.setOrientation(LinearLayoutManager.VERTICAL);
        my_recyclerview.setLayoutManager(mMangaer);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        my_recyclerview.setHasFixedSize(true);
        mAdapter = new Address_Adapter(getActivity(), notifyArr);
        my_recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new Address_Adapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position > 0) {
//                    Intent i = new Intent(getActivity(), News_Detail.class);
//                    i.putExtra("id",newsArr.get(position-1).get("_id").toString());
//                    i.putExtra("classString",classString);
//                    startActivity(i);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
    }
}
