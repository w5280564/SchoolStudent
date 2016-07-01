package com.waylen.schoolstudent.Myactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.UrlVO;

/**
 * Created by Admin on 2016/4/14.
 */
public class Fragment4_person extends Fragment {

    private View parentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (parentView == null) {
            parentView = inflater.inflate(R.layout.fragment4_person, null);
            View title_Include = parentView.findViewById(R.id.title_Include);
            TextView base_title_Txt = (TextView) title_Include.findViewById(R.id.base_title_Txt);
            base_title_Txt.setText("个人主页");
            StaticData.ViewScale(title_Include, 0, 88);
            Button base_Right_Btn = (Button) parentView.findViewById(R.id.base_Right_Btn);
            StaticData.ViewScale(base_Right_Btn, 46, 46);
            base_Right_Btn.setBackgroundResource(R.drawable.set_icon);


            SimpleDraweeView my_DraweeView_view = (SimpleDraweeView) parentView.findViewById(R.id.my_DraweeView_view);
            StaticData.ViewScale(my_DraweeView_view, 640, 320);
            Uri uri = Uri.parse("res://com.waylen.schoolstudent/" + R.drawable.person_bg);
            my_DraweeView_view.setImageURI(uri);

//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            int pad = (int) (Float.parseFloat(UrlVO.getShareData("scale", getActivity())) * 225);
//            params.setMargins(0,pad,0,0);
//            StaticData.layoutParamsScale(params, 135, 135);

            int pad = (int) (Float.parseFloat(UrlVO.getShareData("scale", getActivity())) * 313);
            int padbelow = (int) (Float.parseFloat(UrlVO.getShareData("scale", getActivity())) * 18);
            LinearLayout head_Lin = (LinearLayout) parentView.findViewById(R.id.head_Lin);
            head_Lin.setPadding(0, pad, 0, padbelow);
            SimpleDraweeView my_head = (SimpleDraweeView) parentView.findViewById(R.id.my_head);
            Uri headuri = Uri.parse("res://com.waylen.schoolstudent/" + R.drawable.head_icon);
            my_head.setImageURI(headuri);
            StaticData.ViewScale(my_head, 135, 135);

            RelativeLayout change_rel = (RelativeLayout) parentView.findViewById(R.id.change_rel);
            ImageView change_img = (ImageView) parentView.findViewById(R.id.change_img);
            StaticData.ViewScale(change_rel, 0, 80);
            StaticData.ViewScale(change_img, 19, 32);
            RelativeLayout work_rel = (RelativeLayout) parentView.findViewById(R.id.work_rel);
            ImageView work_img = (ImageView) parentView.findViewById(R.id.work_img);
            StaticData.ViewScale(work_rel, 0, 80);
            StaticData.ViewScale(work_img, 19, 32);


            base_Right_Btn.setOnClickListener(new base_Right_Btn());
            change_rel.setOnClickListener(new change_rel());
            work_rel.setOnClickListener(new work_rel());
        }
        ViewGroup parent = (ViewGroup) parentView.getParent();
        if (parent != null) {
            parent.removeView(parentView);
        }
        return parentView;
    }


    public class base_Right_Btn implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(),Person_Set.class);
            startActivity(i);
        }
    }
    public class change_rel implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(),Person_ChangeData.class);
            startActivity(i);
        }
    }

 public class work_rel implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(),Person_MyWork.class);
            startActivity(i);
        }
    }


}
