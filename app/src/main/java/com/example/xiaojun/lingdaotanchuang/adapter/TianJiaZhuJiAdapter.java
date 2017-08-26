package com.example.xiaojun.lingdaotanchuang.adapter;//package com.example.xiaojun.lingdaotanchuang.adapter;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.example.xiaojun.lingdaotanchuang.R;
//import com.example.xiaojun.lingdaotanchuang.beans.ZhuJiBean;
//
//import java.util.List;
//
//
//public class TianJiaZhuJiAdapter extends BaseAdapter {
//
//	private Context mCtx;
//	private LayoutInflater mInflater;
//	private List<ZhuJiBean> mList;
//
//
//	public TianJiaZhuJiAdapter(Context context, List<ZhuJiBean> list) {
//		mCtx = context;
//		mInflater = LayoutInflater.from(context);
//		mList = list;
//
//	}
//
//	@Override
//	public int getCount() {
//
//		return mList.size();
//
//	}
//
//	@Override
//	public Object getItem(int position) {
//
//
//		return null;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return mList.size();
//	}
//
//	@Override
//	public View getView(final int position, View convertView, ViewGroup parent) {
//
//		Holder holder = null;
//		if (null == convertView) {
//
//			convertView = mInflater.inflate(R.layout.tianjiazhuji_item, null);
//			holder = new Holder();
//			holder.haoyou_tv= (TextView) convertView.findViewById(R.id.aa);
//			holder.imageView= (ImageView) convertView.findViewById(R.id.im);
//			holder.relativeLayout= (RelativeLayout) convertView.findViewById(R.id.dd);
//			final Holder finalHolder = holder;
//			holder.relativeLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//				@Override
//				public void onFocusChange(View v, boolean hasFocus) {
//					Log.d("TianJiaZhuJiAdapter", "ddd");
//					if (hasFocus){
//						finalHolder.relativeLayout.setBackgroundResource(R.drawable.zidonghuoqu3);
//					}else {
//						finalHolder.relativeLayout.setBackgroundResource(R.drawable.zidonghuoqu2);
//					}
//				}
//			});
//
//			convertView.setTag(holder);
//
//		} else {
//
//			holder = (Holder) convertView.getTag();
//
//		}
//
//		if (holder!=null){
//
//				holder.haoyou_tv.setText(mList.get(position).getDizhi());
//			if (mList.get(position).getIsTrue()==1){
//				holder.imageView.setVisibility(View.VISIBLE);
//			}else {
//				holder.imageView.setVisibility(View.GONE);
//			}
//
//
//		}
//
//
//
//		return convertView;
//	}
//
//	private class Holder {
//
//		ImageView imageView;
//		TextView haoyou_tv;
//		RelativeLayout relativeLayout;
//
//	}
//
//}
