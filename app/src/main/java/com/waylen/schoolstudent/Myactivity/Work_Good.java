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

import com.waylen.schoolstudent.Adapter.workGood_Adapter;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.Json;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.myActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/19.
 */
public class Work_Good extends myActivity {
    private SwipeRefreshLayout myswipeLayout;
    private RecyclerView my_recyclerview;
    private String courseType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_good);

        View title_Include = findViewById(R.id.title_Include);
        TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
        Button base_Back_Btn = (Button) title_Include.findViewById(R.id.base_Back_Btn);
        base_Back_Btn.setBackgroundResource(R.drawable.back_icon);
        StaticData.ViewScale(title_Include, 0, 88);
        StaticData.ViewScale(base_Back_Btn, 23, 44);
        base_title_Txt.setText("优秀作业");


        myswipeLayout = (SwipeRefreshLayout) findViewById(R.id.myswipeLayout);
        my_recyclerview = (RecyclerView) findViewById(R.id.news_Xlist);


        Intent i = getIntent();
         courseType = i.getStringExtra("courseType");

        myswipeLayout.setOnRefreshListener(new myswipeLayout());
//        onLoadMore();
        initlist();
        base_Back_Btn.setOnClickListener(new base_Back_Btn());

        DoingWorkData("http://swfx.sinthonic.com/swfx/doingHomework-workType-list.action", courseType, "0");

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
            DoingWorkData("http://swfx.sinthonic.com/swfx/doingHomework-workType-list.action", courseType, "0");
            myswipeLayout.setRefreshing(false);
            Toast.makeText(Work_Good.this, "Refresh Finsh", Toast.LENGTH_SHORT).show();
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

    private workGood_Adapter mAdapter;
    LinearLayoutManager mMangaer;

    public void initlist() {
//        List<Map<String, Object>> notifyArr = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            Map<String, Object> oneArr = new HashMap<>();
//
//            notifyArr.add(oneArr);
//        }


        //创建Manager
        mMangaer = new LinearLayoutManager(this);
        mMangaer.setOrientation(LinearLayoutManager.VERTICAL);
        my_recyclerview.setLayoutManager(mMangaer);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        my_recyclerview.setHasFixedSize(true);
        mAdapter = new workGood_Adapter(this, baseArr);
        my_recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new workGood_Adapter.OnItemClickLitener() {
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

    List<Map<String, Object>> baseArr;
    Json JsonGet = new Json();

    //作业列表
    public void DoingWorkData(String baseUrl, String courseId, String workId) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("courseType", courseId);
            obj.put("workType", workId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(baseUrl);
        params.addBodyParameter("param", obj.toString());
        params.setMultipart(true);
//        if (UrlVO.getShareData("JSESSIONID", this) != null) {
//            params.setHeader("Cookie", UrlVO.getShareData("JSESSIONID", this));
//        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String resultString) {
                if (JsonGet.getReturnValue(resultString, "status").equals("1")) {//1是成功
                    String[] datakey = {"workStuId", "questionList", "publishDate"};
                    List<Map<String, Object>> workArr = new ArrayList<>();
                    if (workArr != null) {
                        workArr.clear();
                    }
                    workArr = JsonGet.getnotitleJSONArray(workArr, JsonGet.getReturnValue(resultString, "result"), datakey);

                    if (baseArr != null) {
                        baseArr.clear();
                    }
                    baseArr = new ArrayList<>();
                    for (int k = 0; k < workArr.size(); k++) {
                        String[] basekey = {"id", "workContext", "workQuestion", "workQuestionType"};
                        JSONArray jsonObject;
                        try {
                            jsonObject = new JSONArray(workArr.get(k).get("questionList").toString());
                            for (int i = 0; i < jsonObject.length(); i++) {
                                JSONObject jsonObject2 = (JSONObject) jsonObject.opt(i);
                                HashMap<String, Object> map = new HashMap<String, Object>();
                                for (int j = 0; j < basekey.length; j++) {
                                    try {
                                        map.put(basekey[j], jsonObject2.getString(basekey[j]));
                                    } catch (Exception e) {
                                        map.put(basekey[j], "");
                                    }
                                }
                                map.put("publishDate", workArr.get(i).get("publishDate").toString());
                                baseArr.add(map);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    initlist();
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
