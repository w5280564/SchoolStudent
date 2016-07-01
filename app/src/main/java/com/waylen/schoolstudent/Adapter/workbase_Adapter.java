package com.waylen.schoolstudent.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.waylen.schoolstudent.R;
import com.waylen.schoolstudent.StaticData.Json;
import com.waylen.schoolstudent.StaticData.UrlVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/4/15.
 */
public class workbase_Adapter extends RecyclerView.Adapter<workbase_Adapter.MyViewHolder> {
    List<Map<String, Object>> baselist;
    Context context;
    private int myposition;
    Json JsonGet = new Json();

    public workbase_Adapter(Context context, List<Map<String, Object>> baselist) {
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

        myview = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.workbase_adapter, null));

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
//            Uri uri = Uri.parse(UrlVO.BaseUrl+UrlVO.FileUrl + "?id="+baselist.get(position-1).get("photo"));
//            holder.new_img.setImageURI(uri);
//
//            holder.new_title_Txt.setText(baselist.get(position-1).get("title").toString());
//            holder.collectcount_Txt.setText(baselist.get(position-1).get("collectNum").toString()+"人收藏");
////            holder.time_Txt.setText(baselist.get(position-1).get("date").toString());
//            holder.time_Txt.setText(StaticData.Datatypetwo(baselist.get(position - 1).get("date").toString()));


//        String[] basekey = {"id","workContext","workQuestion","workQuestionType"};
//        List<Map<String,Object>>  baseArr = new ArrayList<>();
//        if (baseArr != null){
//            baseArr.clear();
//        }
//        baseArr = JsonGet.getnotitleJSONArray(baseArr,baselist.get(position).get("questionList").toString(),basekey);

        holder.name_Txt.setText(baselist.get(position).get("workQuestion").toString());
        holder.time_txt.setText(baselist.get(position).get("publishDate").toString());
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

        private  TextView name_Txt,time_txt;
        private LinearLayout adress_Lin;

        public MyViewHolder(View itemView) {
            super(itemView);
            int pad = (int) (Float.parseFloat(UrlVO.getShareData("scale", context)) * 20);
            RelativeLayout work_rel = (RelativeLayout) itemView.findViewById(R.id.work_rel);
             name_Txt = (TextView) itemView.findViewById(R.id.name_Txt);
             time_txt = (TextView) itemView.findViewById(R.id.time_txt);
            work_rel.setPadding(pad, pad, pad, pad);
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
