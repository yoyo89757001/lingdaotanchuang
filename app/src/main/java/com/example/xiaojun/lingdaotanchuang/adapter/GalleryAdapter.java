package com.example.xiaojun.lingdaotanchuang.adapter;

/**
 * Created by chenjun on 2017/4/7.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiaojun.lingdaotanchuang.R;
import com.example.xiaojun.lingdaotanchuang.beans.ZhuJiBean;

import java.util.ArrayList;
import java.util.List;

//import com.example.xiaojun.lingdaotanchuang.R;
//import com.example.xiaojun.lingdaotanchuang.beans.ZhuJiBean;

/**
 * Created by txt on 2015/11/11.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder>{
    private LayoutInflater mInflater;
    private List<ZhuJiBean> mDatas;
    private List<Integer> heights;
    private int currentPosition;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public interface OnItemSelectListener{
        void onItemSelect(View view, int position);
    }

    private OnItemClickListener mListener;
    private OnItemSelectListener mSelectListener;

    public void setOnItemSelectListener(OnItemSelectListener listener){
        mSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public GalleryAdapter(Context context,List<ZhuJiBean> datas){
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
       // getRandomHeight(mDatas.size());
    }

    public void setDatas(List datas){
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tianjiazhuji_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.mImg = (ImageView) view.findViewById(R.id.im);
        holder.mTxt = (TextView)view.findViewById(R.id.aa);
        holder.relativeLayout= (RelativeLayout) view.findViewById(R.id.dd);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      //  ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
      //  params.height = heights.get(position%mDatas.size());
      //  holder.itemView.setLayoutParams(params);
       // holder.mImg.setImageResource(mDatas.get(position % mDatas.size()));

        if (mDatas.get(position).getIsTrue()==1){
           // Log.d("GalleryAdapter", "ttttt.L");
            holder.mImg.setVisibility(View.VISIBLE);
        }else {
           // Log.d("GalleryAdapter", "wwwwwww.L");
            holder.mImg.setVisibility(View.GONE);
        }
        holder.mTxt.setText(mDatas.get(position).getDizhi());


        holder.itemView.setTag(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
              //  Log.i("adapter", "hasfocus:" + position + "--" + hasFocus);
                if(hasFocus){
                    currentPosition = (int)holder.itemView.getTag();
                   // mSelectListener.onItemSelect(holder.itemView,currentPosition);
                    holder.relativeLayout.setBackgroundResource(R.drawable.zidonghuoqu3);
                }else {
                    holder.relativeLayout.setBackgroundResource(R.drawable.zidonghuoqu12);
                }

            }
        });
        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v,holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClick(v,holder.getLayoutPosition());
                    return true;
                }
            });
        }

        holder.itemView.setFocusable(true);
        if (position==0){
            holder.itemView.requestFocus();
            holder.relativeLayout.setBackgroundResource(R.drawable.zidonghuoqu3);
        }

    }

    @Override
    public int getItemCount() {
//        return Integer.MAX_VALUE;
        return mDatas.size();
    }


    private void getRandomHeight(int size){
        heights = new ArrayList<>();
        for(int i=0;i<size;i++){
            heights.add((int)(200+Math.random()*400));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mImg;
        TextView mTxt;
        RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}