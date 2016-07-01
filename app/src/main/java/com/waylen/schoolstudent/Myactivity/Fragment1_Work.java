package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.Json;
import com.waylen.schoolstudent.StaticData.StaticData;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/14.
 */
public class Fragment1_Work extends Fragment {

    private View parentView;
    private TextView chinese_content, math_content, english_content, excellent_work,excellent_content, chinese_Time, math_Time, english_Time, excellent_Time;
    private RelativeLayout chinese_rel,math_rel,english_rel,excellent_rel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment1_work, null);

            View title_Include = parentView.findViewById(R.id.title_Include);
            TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
            base_title_Txt.setText("作业");
            StaticData.ViewScale(title_Include, 0, 88);


             chinese_rel = (RelativeLayout) parentView.findViewById(R.id.chinese_rel);
            ImageView chinese_img = (ImageView) parentView.findViewById(R.id.chinese_img);
            chinese_content = (TextView) parentView.findViewById(R.id.chinese_content);
            chinese_Time = (TextView) parentView.findViewById(R.id.chinese_Time);
            StaticData.ViewScale(chinese_rel, 0, 100);
            StaticData.ViewScale(chinese_img, 86, 86);

             math_rel = (RelativeLayout) parentView.findViewById(R.id.math_rel);
            ImageView math_img = (ImageView) parentView.findViewById(R.id.math_img);
            math_content = (TextView) parentView.findViewById(R.id.math_content);
            math_Time = (TextView) parentView.findViewById(R.id.math_Time);
            StaticData.ViewScale(math_img, 86, 86);
            StaticData.ViewScale(math_rel, 0, 100);

             english_rel = (RelativeLayout) parentView.findViewById(R.id.english_rel);
            ImageView english_img = (ImageView) parentView.findViewById(R.id.english_img);
            english_content = (TextView) parentView.findViewById(R.id.english_content);
            english_Time = (TextView) parentView.findViewById(R.id.english_Time);
            StaticData.ViewScale(english_img, 85, 85);
            StaticData.ViewScale(english_rel, 0, 100);

             excellent_rel = (RelativeLayout) parentView.findViewById(R.id.excellent_rel);
            ImageView excellent_img = (ImageView) parentView.findViewById(R.id.excellent_img);
            excellent_work = (TextView) parentView.findViewById(R.id.excellent_work);
            excellent_content = (TextView) parentView.findViewById(R.id.excellent_content);
            excellent_Time = (TextView) parentView.findViewById(R.id.excellent_Time);
            StaticData.ViewScale(excellent_img, 85, 85);
            StaticData.ViewScale(excellent_rel, 0, 100);


            chinese_rel.setOnClickListener(new chinese_rel());
            math_rel.setOnClickListener(new math_rel());
            english_rel.setOnClickListener(new english_rel());
            excellent_rel.setOnClickListener(new excellent_rel());



            DoingWorkData("http://swfx.sinthonic.com/swfx/doingHomework-newWork-list.action");
        }
        ViewGroup parent = (ViewGroup) parentView.getParent();
        if (parent != null) {
            parent.removeView(parentView);
        }
        return parentView;
    }

    public class chinese_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), Work_All.class);
            i.putExtra("titlename", "语文作业");
            i.putExtra("courseType", "2");
            startActivity(i);
        }
    }

    public class math_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), Work_All.class);
            i.putExtra("titlename", "数学作业");
            i.putExtra("courseType", "3");
            startActivity(i);
        }
    }

    public class english_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), Work_All.class);
            i.putExtra("titlename", "英语作业");
            i.putExtra("courseType", "4");
            startActivity(i);
        }
    }

    public class excellent_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), Work_Good.class);
            i.putExtra("courseType", "0");
            startActivity(i);
        }
    }



    Json JsonGet = new Json();
    //作业列表
    public void DoingWorkData(String baseUrl) {
        RequestParams params = new RequestParams(baseUrl);
//        if (UrlVO.getShareData("JSESSIONID", this) != null) {
//            params.setHeader("Cookie", UrlVO.getShareData("JSESSIONID", this));
//        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String resultString) {
                Log.e("resultStringresult", resultString);
                if (JsonGet.getReturnValue(resultString, "status").equals("1")) {//1是成功
                    String[] datakey = {"question", "courseType", "publishDate"};
                    List<Map<String, Object>> workArr = new ArrayList<>();
                    if (workArr != null) {
                        workArr.clear();
                    }
                    workArr = JsonGet.getnotitleJSONArray(workArr, JsonGet.getReturnValue(resultString, "result"), datakey);

                    chinese_rel.setVisibility(View.GONE);
                    math_rel.setVisibility(View.GONE);
                    english_rel.setVisibility(View.GONE);
                    excellent_rel.setVisibility(View.GONE);

                    for (int i = 0; i < workArr.size(); i++) {//0是优秀 234就是语数英
                        if (workArr.get(i).get("courseType").equals("2")) {
                            chinese_rel.setVisibility(View.VISIBLE);
                            chinese_content.setText(workArr.get(i).get("question").toString());
                            chinese_Time.setText(workArr.get(i).get("publishDate").toString());

                        } else if (workArr.get(i).get("courseType").equals("3")) {
                            math_rel.setVisibility(View.VISIBLE);
                            math_content.setText(workArr.get(i).get("question").toString());
                            math_Time.setText(workArr.get(i).get("publishDate").toString());
                        } else if (workArr.get(i).get("courseType").equals("4")) {
                            english_rel.setVisibility(View.VISIBLE);
                            english_content.setText(workArr.get(i).get("question").toString());
                            english_Time.setText(workArr.get(i).get("publishDate").toString());
                        } else if (workArr.get(i).get("courseType").equals("0")) {
                            excellent_rel.setVisibility(View.VISIBLE);
                            if (!workArr.get(i).get("question").equals("null")) {
                                excellent_content.setText(workArr.get(i).get("question").toString());
                            }
                            if (!workArr.get(i).get("question").equals("null")) {
                                excellent_Time.setText(workArr.get(i).get("publishDate").toString());
                            }
                        }

                    }

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
