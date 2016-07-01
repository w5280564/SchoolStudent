package com.waylen.schoolstudent.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.StaticData;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/15.
 */
public class Class_Adapter extends RecyclerView.Adapter<Class_Adapter.MyViewHolder> {
    List<Map<String, Object>> baselist;
    Context context;
    private int myposition;

    public Class_Adapter(Context context, List<Map<String, Object>> baselist) {
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

        myview = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.grade_adapter, null));

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
            Uri uri = Uri.parse("res://com.waylen.zenzox/" + R.drawable.class_one);
            holder.grade_img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return (baselist == null) ? 0 : baselist.size();//数据加一项
    }

    @Override
    public int getItemViewType(int position) {
        return myposition = position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView grade_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            RelativeLayout bsae_rel = (RelativeLayout) itemView.findViewById(R.id.bsae_rel);
             grade_img = (SimpleDraweeView) itemView.findViewById(R.id.grade_img);
            StaticData.ViewScale(bsae_rel,0,100);
            StaticData.ViewScale(grade_img,85,85);
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


}
