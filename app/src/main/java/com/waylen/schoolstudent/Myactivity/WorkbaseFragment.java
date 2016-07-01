package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.waylen.schoolstudent.Adapter.workbase_Adapter;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.Json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/18.
 */
public class WorkbaseFragment extends Fragment {
    private String defaultHello = "default value";
    private SwipeRefreshLayout myswipeLayout;
    private RecyclerView my_recyclerview;
    private String courseType;
    private String workType;


    static WorkbaseFragment newInstance(String courseType, String workType) {
        WorkbaseFragment newFragment = new WorkbaseFragment();
        Bundle bundle = new Bundle();
        bundle.putString("courseType", courseType);
        bundle.putString("workType", workType);
        newFragment.setArguments(bundle);
        //bundle可以在每个标签里传送数据
        return newFragment;
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        //判断Fragment中的ListView时候存在，判断该Fragment时候已经正在前台显示  通过这两个判断，就可以知道什么时候去加载数据了
//        if (isVisibleToUser && isVisible() && my_recyclerview.getVisibility() != View.VISIBLE) {
//            initlist(); //加载数据的方法
//        }
//        super.setUserVisibleHint(isVisibleToUser);
//    }
//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        if (getUserVisibleHint() && my_recyclerview.getVisibility() != View.VISIBLE) {
//            initlist(); //加载数据的方法
//        }
//        super.onActivityCreated(savedInstanceState);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if(parentView == null) {
        parentView = inflater.inflate(R.layout.workbasefragment, container, false);
//        TextView viewhello = (TextView) parentView.findViewById(R.id.tv);
//        viewhello.setText(titletring);
        Bundle args = getArguments();
        courseType = args != null ? args.getString("courseType") : defaultHello;
        workType = args != null ? args.getString("workType") : defaultHello;

        myswipeLayout = (SwipeRefreshLayout) parentView.findViewById(R.id.myswipeLayout);
        my_recyclerview = (RecyclerView) parentView.findViewById(R.id.news_Xlist);

        Log.e("当前标签", courseType + workType);
        myswipeLayout.setOnRefreshListener(new myswipeLayout());
//        onLoadMore();

//        }
//        ViewGroup parent = (ViewGroup)parentView.getParent();
//        if(parent != null) {
//            parent.removeView(parentView);
//        }
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

    @Override
    public void onResume() {
        super.onResume();
        DoingWorkData("http://swfx.sinthonic.com/swfx/doingHomework-workType-list.action", courseType, workType);
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

    private workbase_Adapter mAdapter;
    LinearLayoutManager mMangaer;

    public void initlist() {
        //创建Manager
        mMangaer = new LinearLayoutManager(getActivity());
        mMangaer.setOrientation(LinearLayoutManager.VERTICAL);
        my_recyclerview.setLayoutManager(mMangaer);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        my_recyclerview.setHasFixedSize(true);
        mAdapter = new workbase_Adapter(getActivity(), baseArr);
        my_recyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new workbase_Adapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                List<Map<String,Object>>  detailArr = new ArrayList<Map<String, Object>>();
                String[] detailkey = {"id","workQuestion","workQuestionType","workContext"};
                String[] detailString = JsonGet.getJSON(workArr.get(position).get("questionList").toString(),detailkey);
                detailArr = JsonGet.getnotitleJSONArray(detailArr,workArr.get(position).get("questionList").toString(),detailkey);

                if (workType.equals("1") || workType.equals("2")){
                    Intent i = new Intent(getActivity(), Work_Detail.class);
                    i.putExtra("detailArr",(Serializable)detailArr);
                    i.putExtra("workStuId",workArr.get(position).get("workStuId").toString());
                    i.putExtra("courseType",courseType);
                    startActivity(i);

                }

//                if (position % 2 == 0) {
//                    Intent i = new Intent(getActivity(), Work_Detail.class);
//                    i.putExtra("type", "0");
//                    startActivity(i);
//                } else {
//                    Intent i = new Intent(getActivity(), Work_Detail.class);
//                    i.putExtra("type", "1");
//                    startActivity(i);
//                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });
    }
    List<Map<String, Object>> workArr;
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
//                Log.e("resultStringresult", resultString);
                if (JsonGet.getReturnValue(resultString, "status").equals("1")) {//1是成功
                    String[] datakey = {"workStuId", "questionList", "publishDate"};
                     workArr = new ArrayList<>();
                    if (workArr != null) {
                        workArr.clear();
                    }
                    workArr = JsonGet.getnotitleJSONArray(workArr, JsonGet.getReturnValue(resultString, "result"), datakey);
//                    if (baseArr != null) {
//                        baseArr.clear();
//                    }
//                    baseArr = new ArrayList<>();
//                    for (int i = 0; i < workArr.size(); i++) {
//                        String[] basekey = {"id", "workContext", "workQuestion", "workQuestionType"};
//                        baseArr = JsonGet.getnotitleJSONArray(baseArr, workArr.get(i).get("questionList").toString(), basekey);
////                        Map<String, Object> onedata = new HashMap<String, Object>();
////                        onedata.put("publishDate", workArr.get(i).get("publishDate").toString());
////                        baseArr.add(onedata);
//
//                    }
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
