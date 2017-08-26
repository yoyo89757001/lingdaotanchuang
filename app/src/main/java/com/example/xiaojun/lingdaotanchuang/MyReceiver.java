package com.example.xiaojun.lingdaotanchuang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.example.xiaojun.lingdaotanchuang.beans.GongGaoBean;
import com.example.xiaojun.lingdaotanchuang.beans.ShiPingBean;
import com.example.xiaojun.lingdaotanchuang.beans.ShiPingBeanDao;
import com.example.xiaojun.lingdaotanchuang.utils.GsonUtil;
import com.example.xiaojun.lingdaotanchuang.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "VlcVideoActivity";
	private ShiPingBeanDao shiPingBeanDao=null;

	@Override
	public void onReceive(Context context, Intent intent) {

		try {
			Bundle bundle = intent.getExtras();
			shiPingBeanDao = MyApplication.getAppContext().getDaoSession().getShiPingBeanDao();
			Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle,context,shiPingBeanDao));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
			//	processCustomMessage(context, bundle);


			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

//				//打开自定义的Activity
//				Intent i = new Intent(context, TestActivity.class);
//				i.putExtras(bundle);
//				//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//				context.startActivity(i);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Logger.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle, final Context context,ShiPingBeanDao shiPingBeanDao) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey111:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey222:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey333:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");

					}
				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}

			} else {
//				 cn.jpush.android.TITLE
//				 cn.jpush.android.MESSAGE
//				 cn.jpush.android.CONTENT_TYPE
//				 cn.jpush.android.APPKEY
//				 cn.jpush.android.MSG_ID

				sb.append("\nkey444:" + key + ", value:" + bundle.getString(key));
				try {
					if (key.equals("cn.jpush.android.TITLE")){
						if (bundle.getString(key).equals("公告")){
							JsonObject jsonObject= GsonUtil.parse(bundle.getString("cn.jpush.android.MESSAGE")).getAsJsonObject();
							Gson gson=new Gson();
							//	JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
							GongGaoBean zhaoPianBean=gson.fromJson(jsonObject,GongGaoBean.class);
							Reservoir.putAsync("baocungonggao", zhaoPianBean, new ReservoirPutCallback() {
								@Override
								public void onSuccess() {

									Intent intent=new Intent("updateGonggao");
									context.sendBroadcast(intent);

								}

								@Override
								public void onFailure(Exception e) {


								}
							});

						}
					else if (bundle.getString(key).equals("视频")){
						Log.d(TAG, "视频推送");

                        JsonObject jsonObject= GsonUtil.parse(bundle.getString("cn.jpush.android.MESSAGE")).getAsJsonObject();
                        Gson gson=new Gson();
                        //	JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
                        ShiPingBean zhaoPianBean=gson.fromJson(jsonObject,ShiPingBean.class);
							Log.d(TAG, "推送的id:" + zhaoPianBean.getId());
							final ShiPingBean pingBean=shiPingBeanDao.load(zhaoPianBean.getId());
                            if (null==pingBean){ //没找到，是新的数据
                                zhaoPianBean.setIsDownload(false);
                                Log.d(TAG, "插入新的视频到数据库:" + shiPingBeanDao.insert(zhaoPianBean));
                                Intent intent=new Intent("updateShiPing");
                                intent.putExtra("idid",zhaoPianBean.getId());
                                context.sendBroadcast(intent);

							}else { //是要更新时间段，或者要删除视频
                                if (zhaoPianBean.getIsRemove()){ //true 是要删除
                                    //做删除的动作
                                    if (pingBean.getIsDownload()){
                                        //已经下载过，发送广播删除内存文件
										Log.d(TAG, "删除视频广播");
										Intent intent=new Intent("delectShiPing");
										intent.putExtra("idid",zhaoPianBean.getId());
										context.sendBroadcast(intent);

										//shiPingBeanDao.delete(pingBean);
                                    }else {
                                        //没有下载，直接删除
										Log.d(TAG, "删除视频广播2");
                                        shiPingBeanDao.delete(pingBean);
                                    }
                                }else {
                                    //更新时间段
                                    if (pingBean.getIsDownload()){
                                        //已经下载过，更新就行
										Log.d(TAG, "更新视频广播");
                                        zhaoPianBean.setIsDownload(true);
                                        shiPingBeanDao.updateInTx(zhaoPianBean);
										Intent intent=new Intent("updateShiPing");
										intent.putExtra("idid",zhaoPianBean.getId());
										context.sendBroadcast(intent);


                                    }else {
                                        //没有下载，去下载
										Log.d(TAG, "更新视频广播2");
                                        zhaoPianBean.setIsDownload(false);
                                        shiPingBeanDao.updateInTx(zhaoPianBean);
                                        Intent intent=new Intent("updateShiPing");
                                        intent.putExtra("idid",zhaoPianBean.getId());
                                        context.sendBroadcast(intent);
                                    }

                                }

                            }
                    }else if (bundle.getString(key).equals("图片")){

							JsonObject jsonObject= GsonUtil.parse(bundle.getString("cn.jpush.android.MESSAGE")).getAsJsonObject();
							Gson gson=new Gson();
							//	JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
                            ShiPingBean zhaoPianBean=gson.fromJson(jsonObject,ShiPingBean.class);
                            Reservoir.putAsync("baocuntupian", zhaoPianBean, new ReservoirPutCallback() {
                                @Override
                                public void onSuccess() {

                                    Intent intent=new Intent("updateTuPian");
                                    context.sendBroadcast(intent);

                                }

                                @Override
                                public void onFailure(Exception e) {


                                }
                            });

						}
					}


				}catch (Exception e){
					Log.d(TAG, "捕获到推送异常"+e.getMessage());
				}



			}
		}
		return sb.toString();
	}


//	//send msg to MainActivity
//	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//		}
//	}
}
