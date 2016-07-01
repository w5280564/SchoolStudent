package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.StaticData;

/**
 * Created by Admin on 2016/4/14.
 */
public class Fragment3_Notify extends Fragment {

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
            parentView = inflater.inflate(R.layout.fragment3_notify, null);

            View title_Include = parentView.findViewById(R.id.title_Include);
            TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
            base_title_Txt.setText("通知");
            StaticData.ViewScale(title_Include, 0, 88);


            RelativeLayout notify_rel = (RelativeLayout) parentView.findViewById(R.id.notify_rel);
            ImageView notify_img = (ImageView) parentView.findViewById(R.id.notify_img);
            RelativeLayout affiche_rel = (RelativeLayout) parentView.findViewById(R.id.affiche_rel);
            ImageView affiche_img = (ImageView) parentView.findViewById(R.id.affiche_img);
            RelativeLayout quest_rel = (RelativeLayout) parentView.findViewById(R.id.quest_rel);
            ImageView quest_img = (ImageView) parentView.findViewById(R.id.quest_img);

            StaticData.ViewScale(notify_rel, 0, 100);
            StaticData.ViewScale(affiche_rel, 0, 100);
            StaticData.ViewScale(quest_rel, 0, 100);
            StaticData.ViewScale(notify_img, 85, 85);
            StaticData.ViewScale(affiche_img, 85, 85);
            StaticData.ViewScale(quest_img, 85, 85);


            notify_rel.setOnClickListener(new notify_rel());
            affiche_rel.setOnClickListener(new affiche_rel());
            quest_rel.setOnClickListener(new quest_rel());


        }
        ViewGroup parent = (ViewGroup) parentView.getParent();
        if (parent != null) {
            parent.removeView(parentView);
        }
        return parentView;
    }

    public class notify_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Notify_Message.class);
            intent.putExtra("titlename", "通知");
            startActivity(intent);
        }
    }

    public class affiche_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Notify_Message.class);
            intent.putExtra("titlename", "公告");
            startActivity(intent);
        }
    }

    public class quest_rel implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), Notify_Message.class);
            intent.putExtra("titlename", "调查问卷");
            startActivity(intent);
        }
    }

}
