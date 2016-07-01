package com.waylen.schoolstudent.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.Myactivity.Adress_Grade;
import com.waylen.schoolstudent.Myactivity.Adress_StudentOrTeacher;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.StaticData;
import com.waylen.schoolstudent.StaticData.UrlVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/3/29.
 */
public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.MyViewHolder> {
    List<Map<String, Object>> baselist;
    Context context;
    private int myposition;

    public Address_Adapter(Context context, List<Map<String, Object>> baselist) {
        this.baselist = baselist;
        this.context = context;
    }

    public void setMoreData(List<Map<String, Object>> datas) {
        this.baselist = datas;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myview;
        if (myposition == 0) {
            myview = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notify_choice, null));
        } else {
            myview = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notify_adapter, null));
        }
        return myview;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }

        if (myposition == 0) {
            navs(holder.adress_Lin);
        } else {
//            Uri uri = Uri.parse(UrlVO.BaseUrl+UrlVO.FileUrl + "?id="+baselist.get(position-1).get("photo"));
//            holder.new_img.setImageURI(uri);
//
//            holder.new_title_Txt.setText(baselist.get(position-1).get("title").toString());
//            holder.collectcount_Txt.setText(baselist.get(position-1).get("collectNum").toString()+"人收藏");
////            holder.time_Txt.setText(baselist.get(position-1).get("date").toString());
//            holder.time_Txt.setText(StaticData.Datatypetwo(baselist.get(position - 1).get("date").toString()));

        }


    }

    @Override
    public int getItemCount() {
        return (baselist == null) ? 1 : baselist.size() + 1;//数据加一项
    }

    @Override
    public int getItemViewType(int position) {
        return myposition = position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout adress_Lin;

        public MyViewHolder(View itemView) {
            super(itemView);
            if (myposition == 0) {
                HorizontalScrollView myscro = (HorizontalScrollView) itemView.findViewById(R.id.myscro);
                adress_Lin = (LinearLayout) itemView.findViewById(R.id.adress_Lin);
                TextView adressTxt = (TextView) itemView.findViewById(R.id.adressTxt);
                StaticData.ViewScale(myscro, 640, 150);
                StaticData.ViewScale(adress_Lin, 640, 150);
                StaticData.ViewScale(adressTxt, 0, 53);
            } else {
                RelativeLayout adapter_rel = (RelativeLayout) itemView.findViewById(R.id.adapter_rel);
                ImageView student_img = (ImageView) itemView.findViewById(R.id.student_img);
                StaticData.ViewScale(adapter_rel, 0, 100);
                StaticData.ViewScale(student_img, 85, 85);
            }
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener listener) {
        this.mOnItemClickLitener = listener;
    }


    private Integer[] imgtrings = {R.drawable.adress_icon, R.drawable.friend_icon, R.drawable.group_icon};
    private String[] strings = {"通讯录", "好友", "群组"};
    private List<LinearLayout> myLinArr;
    public void navs(final LinearLayout baseview) {
        myLinArr = new ArrayList<>();
        baseview.removeAllViews();
        for (int i = 0; i < strings.length; i++) {
            View myview = LayoutInflater.from(context).inflate(R.layout.adressview, null);
            LinearLayout base_Lin = (LinearLayout) myview.findViewById(R.id.base_Lin);
            ImageView base_img = (ImageView) myview.findViewById(R.id.base_img);
            TextView base_Txt = (TextView) myview.findViewById(R.id.base_Txt);
            StaticData.ViewScale(base_img, 85, 85);
            base_img.setImageResource(imgtrings[i]);
            base_Txt.setText(strings[i]);
            RadioGroup.LayoutParams itemParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            int pad = (int) (Float.parseFloat(UrlVO.getShareData("scale", context)) * 33);
            itemParams.setMargins(pad, 0, pad, 0);
            base_Lin.setLayoutParams(itemParams);
            base_Lin.setTag(i);
            myLinArr.add(base_Lin);
            baseview.addView(myview);
            base_Lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (Integer) v.getTag();
                    if (tag == 0) {
                       new Group_Popup(context,baseview);
                    }

                }
            });
        }
    }

    public class Group_Popup extends PopupWindow {
        public Group_Popup(final Context mContext, View parent) {
            super(parent);
            View view = View.inflate(mContext, R.layout.popup_group, null);
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setOutsideTouchable(true);
            setFocusable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.CENTER, 0, 0);
            update();
            LinearLayout group_Lin = (LinearLayout) view.findViewById(R.id.group_Lin);
            TextView topbtn = (TextView) view.findViewById(R.id.topbtn);
            TextView belowbtn = (TextView) view.findViewById(R.id.belowbtn);
            TextView cancelbtn = (TextView) view.findViewById(R.id.cancelbtn);
            StaticData.ViewScale(group_Lin, 750, 302);
            topbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UrlVO.saveShareData("card","老师",context);
                    dismiss();
                    Intent i = new Intent(context,Adress_StudentOrTeacher.class);
                    context.startActivity(i);
                }
            });
            belowbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UrlVO.saveShareData("card","学生",context);
                    dismiss();
                    Intent i = new Intent(context,Adress_Grade.class);
                    context.startActivity(i);
                }
            });
            cancelbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }


}
