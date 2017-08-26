package com.example.xiaojun.lingdaotanchuang.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import android.view.KeyEvent;
import android.view.LayoutInflater;

import android.view.View;

import android.view.Window;
import android.view.WindowManager;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiaojun.lingdaotanchuang.MyApplication;
import com.example.xiaojun.lingdaotanchuang.R;
import com.example.xiaojun.lingdaotanchuang.beans.ChuanJianUserBean;
import com.example.xiaojun.lingdaotanchuang.beans.GongGaoBean;
import com.example.xiaojun.lingdaotanchuang.beans.IpAddress;
import com.example.xiaojun.lingdaotanchuang.beans.MoShengRenBean;
import com.example.xiaojun.lingdaotanchuang.beans.MoShengRenBean2;
import com.example.xiaojun.lingdaotanchuang.beans.MoShengRenBeanDao;
import com.example.xiaojun.lingdaotanchuang.beans.ShiBieBean;
import com.example.xiaojun.lingdaotanchuang.beans.ShiBieJiLuBean;
import com.example.xiaojun.lingdaotanchuang.beans.ShiPingBean;
import com.example.xiaojun.lingdaotanchuang.beans.ShiPingBeanDao;
import com.example.xiaojun.lingdaotanchuang.beans.TanChuangBean;
import com.example.xiaojun.lingdaotanchuang.beans.TianQiBean;
import com.example.xiaojun.lingdaotanchuang.beans.TuPianBean;
import com.example.xiaojun.lingdaotanchuang.beans.User;
import com.example.xiaojun.lingdaotanchuang.beans.WBBean;
import com.example.xiaojun.lingdaotanchuang.beans.WeiShiBieBean;
import com.example.xiaojun.lingdaotanchuang.beans.ZhuJiBean;
import com.example.xiaojun.lingdaotanchuang.dialog.CaiDanDialog;
import com.example.xiaojun.lingdaotanchuang.donghua.ExplosionField;
import com.example.xiaojun.lingdaotanchuang.media.Settings;
import com.example.xiaojun.lingdaotanchuang.utils.DateUtils;
import com.example.xiaojun.lingdaotanchuang.utils.GlideCircleTransform;
import com.example.xiaojun.lingdaotanchuang.utils.GsonUtil;

import com.example.xiaojun.lingdaotanchuang.utils.LibVLCUtil;
import com.example.xiaojun.lingdaotanchuang.utils.Utils;
import com.example.xiaojun.lingdaotanchuang.view.DividerItemDecoration;
import com.example.xiaojun.lingdaotanchuang.view.FllowerAnimation;
import com.example.xiaojun.lingdaotanchuang.view.WrapContentLinearLayoutManager;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.sdsmdg.tastytoast.TastyToast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.videolan.libvlc.IVLCVout;
import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import cn.jpush.android.api.JPushInterface;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class VlcVideoActivity extends Activity implements SpeechSynthesizerListener {
	private IVLCVout vlcVout=null;
	private MediaPlayer mediaPlayer=null;
	private final static String TAG = "VlcVideoActivity";
	private boolean is=false;
	//private SurfaceView mSurfaceView;
	//private SurfaceHolder mSurfaceHolder;
	//private List<ShiPingBean> shiPingBeanList;
//    private View mLoadingView;
//	private int mVideoHeight;
//	private int mVideoWidth;
//	private int mVideoVisibleHeight;
//	private int mVideoVisibleWidth;
//	private int mSarNum;
//	private int mSarDen;
	private SpeechSynthesizer mSpeechSynthesizer;
	private String mSampleDirPath;
	private static final String SAMPLE_DIR_NAME = "baiduTTS";
	private static final String SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female.dat";
	private static final String SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male.dat";
	private static final String TEXT_MODEL_NAME = "bd_etts_text.dat";
	//private static final String LICENSE_FILE_NAME = "temp_license";
	private static final String ENGLISH_SPEECH_FEMALE_MODEL_NAME = "bd_etts_speech_female_en.dat";
	private static final String ENGLISH_SPEECH_MALE_MODEL_NAME = "bd_etts_speech_male_en.dat";
	private static final String ENGLISH_TEXT_MODEL_NAME = "bd_etts_text_en.dat";

	private static final int PRINT = 0;
	private static final int UI_CHANGE_INPUT_TEXT_SELECTION = 1;
	private static final int UI_CHANGE_SYNTHES_TEXT_SELECTION = 2;
	private WindowManager wm;
	private ScrollView scollview;
	private LayoutInflater mInflater = null;
	private View view = null;
	private String fileType=null;
	private String fileNames=null;
	private static int quyu=0;
	//private static List<String> gonggaoList=null;
	private LinearLayout linearLayout2;
	private Media media;
	//private RelativeLayout tanchuang_RL;
	private MyReceiver myReceiver=null;
	//private MyReceiverFile myReceiverFile=null;
	private RecyclerView recyclerView;
	private RecyclerView recyclerView2;
	private MyAdapter adapter=null;
	private MyAdapter2 adapter2=null;
	//private Bitmap logo=null;
	private MoShengRenBeanDao daoSession=null;
	private ShiPingBeanDao shiPingBeanDao=null;
//	private ShiBieJiLuBeanDao shiBieJiLuBeanDao=null;
	private static List<String> voidePathList=new ArrayList<>();
	private static List<String> photoPathList=new ArrayList<>();
//	private static Vector<YongHuBean> moShengRenBean2List=new Vector<>();
	private WrapContentLinearLayoutManager manager;
	private WrapContentLinearLayoutManager manager2;
	//private GridLayoutManager gridLayoutManager;
	private WebSocketClient webSocketClient=null;
	private Runnable runnable=null;
	private Handler conntionHandler=null;
	public static String zhuji_string=null,shiping_string=null;
	private String ss;
	private String tupianPath=null;
	//private TextView textView;
	private String zhuji=null;
	private IVLCVout.Callback callback;
	private LibVLC libvlc;
	private TextView shi,riqi,xingqi,tianqi,wendu,tishi_tv33,jian,dian;
	private boolean isTO=true;
	//private MarqueeView marqueeView,marqueeView2;
	//private  static Vector<String> stringVector=new Vector<>();
	//private IjkMediaPlayer ijkMediaPlayer=null;
//	private IjkVideoView ijkVideoView=null;
	private static int Video_index = 0;
	//private RollPagerView rollPagerView=null;
	private  static Vector<TanChuangBean> tanchuangList=null;
	private  static Vector<TanChuangBean> yuangongList=null;
	// 撒花特效
	private RelativeLayout rlt_animation_layout;
	private FllowerAnimation fllowerAnimation;
	private ExplosionField mExplosionField;
	private ImageView bao;

	private int dw,dh;
	private static int shiPingCount=0;

	private ImageView touxiang33,tishi_im33;
	private LinearLayout zixunLL;

	private long timing=0;
	private boolean isChange=true;
	private static TextView gonggao;

//	private TextView gonggaobiaoti;
//	private TextView gonggaoriqi;
//	private String tuisong_dizhi=null;



	private static ScaleAnimation sato0 = new ScaleAnimation(1,0,1,1,
			Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
	private static ScaleAnimation sato1 = new ScaleAnimation(0,1,1,1,
			Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
	public  Handler handler=new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(final Message msg) {

			//识别
			if (msg.arg1==1){

				ShiBieBean.PersonBeanSB dataBean= (ShiBieBean.PersonBeanSB) msg.obj;

					try {

						final TanChuangBean bean=new TanChuangBean();
						bean.setBytes(null);
						bean.setType(dataBean.getSubject_type());
						bean.setName(dataBean.getName());
						bean.setTouxiang(dataBean.getAvatar());
						switch (dataBean.getSubject_type()){
							case 0: //员工
								Log.d(TAG, "员工");
								yuangongList.add(bean);
								adapter2.notifyItemInserted(yuangongList.size());
								manager2.scrollToPosition(yuangongList.size()-1);
								new Thread(new Runnable() {
									@Override
									public void run() {

										try {
											Thread.sleep(15000);

											Message message=Message.obtain();
											message.what=110;
											handler.sendMessage(message);


										} catch (InterruptedException e) {
											e.printStackTrace();
										}


									}
								}).start();

								break;
							case 1: //普通访客
								tanchuangList.add(bean);
								adapter.notifyItemInserted(tanchuangList.size());
								manager.scrollToPosition(tanchuangList.size()-1);
								new Thread(new Runnable() {
									@Override
									public void run() {

										try {
											Thread.sleep(15000);

											Message message=Message.obtain();
											message.what=999;
											handler.sendMessage(message);


										} catch (InterruptedException e) {
											e.printStackTrace();
										}


									}
								}).start();

								break;
							case 2:  //VIP访客

//								if (!fllowerAnimation.isRunings()){
//									fllowerAnimation.startAnimation();
//								}


								mExplosionField.explode(bao);

								tanchuangList.add(bean);
								adapter.notifyItemInserted(tanchuangList.size());
								manager.scrollToPosition(tanchuangList.size()-1);



								new Thread(new Runnable() {
									@Override
									public void run() {

										try {
											Thread.sleep(15000);

											Message message=Message.obtain();
											message.what=999;
											handler.sendMessage(message);


										} catch (InterruptedException e) {
											e.printStackTrace();
										}


									}
								}).start();

								break;

						}



					} catch (Exception e) {
						//Log.d("WebsocketPushMsg", e.getMessage());
						e.printStackTrace();
					}

			}

			switch (msg.what){
				case 111:
					//更新地址
					try {

						WebsocketPushMsg websocketPushMsg=new WebsocketPushMsg();
						if (zhuji_string!=null && shiping_string!=null){

							websocketPushMsg.startConnection(zhuji_string,shiping_string);
						}else {

							TastyToast.makeText(VlcVideoActivity.this, "请先设置主机和视频流", Toast.LENGTH_LONG,TastyToast.ERROR).show();

						}

					} catch (URISyntaxException e ) {

						Log.d(TAG, e.getMessage());
					}

					break;
				case 110:
					//员工弹窗消失

					if (yuangongList.size()>3) {
						yuangongList.remove(3);

						adapter2.notifyItemRemoved(3);
						//adapter.notifyItemChanged(1);
						//adapter.notifyItemRangeChanged(1,tanchuangList.size());
						//adapter.notifyDataSetChanged();
						manager2.scrollToPosition(tanchuangList.size() - 1);
						//Log.d(TAG, "tanchuangList.size():" + tanchuangList.size());

					}


					break;
				case 999:
				//访客弹窗消失



					if (tanchuangList.size()>2) {
						tanchuangList.remove(2);

						adapter.notifyItemRemoved(2);
						//adapter.notifyItemChanged(1);
						//adapter.notifyItemRangeChanged(1,tanchuangList.size());
						//adapter.notifyDataSetChanged();
						manager.scrollToPosition(tanchuangList.size() - 1);
						//Log.d(TAG, "tanchuangList.size():" + tanchuangList.size());

					}


					break;
				case 19: //更新识别记录
					int size=yuangongList.size();

					//adapter2.notifyItemInserted(size-1);
					//manager2.smoothScrollToPosition(recyclerView2,null,size-1);

					break;

			}


			return false;
		}
	});



//	private class TestLoopAdapter extends LoopPagerAdapter {
//
//		public TestLoopAdapter(RollPagerView viewPager) {
//			super(viewPager);
//		}
//
//		@Override
//		public View getView(ViewGroup container, int position) {
//			ImageView view = new ImageView(container.getContext());
//			try {
//
//				view.setImageDrawable(getImageDrawable(photoPathList.get(position)));
//			} catch (IOException e) {
//				Log.d("test", e.getMessage());
//			}
//
//			view.setScaleType(ImageView.ScaleType.FIT_XY);
//			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//			return view;
//		}
//
//		@Override
//		public int getRealCount() {
//			return photoPathList.size();
//		}
//	}
//	/**
//	 * 将文件生成位图
//	 * @param path
//	 * @return
//	 * @throws IOException
//	 */
//	public BitmapDrawable getImageDrawable(String path)
//			throws IOException
//	{
//		//打开文件
//		File file = new File(path);
//		if(!file.exists())
//		{
//			return null;
//		}
//
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		byte[] bt = new byte[1024];
//
//		//得到文件的输入流
//		InputStream in = new FileInputStream(file);
//
//		//将文件读出到输出流中
//		int readLength = in.read(bt);
//		while (readLength != -1) {
//			outStream.write(bt, 0, readLength);
//			readLength = in.read(bt);
//		}
//
//		//转换成byte 后 再格式化成位图
//		byte[] data = outStream.toByteArray();
//		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// 生成位图
//		BitmapDrawable bd = new BitmapDrawable(getResources(),bitmap);
//
//		return bd;
//	}

//	private void play(String path){
//		//Log.d(TAG, "播放完成了222");
//		ijkVideoView.setVideoPath(path);
//
//		ijkVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
//			@Override
//			public void onCompletion(IMediaPlayer iMediaPlayer) {
//			//	Log.d(TAG, "播放完成了");
//				++Video_index;
//				if (Video_index == voidePathList.size())
//					Video_index = 0;
//				play(voidePathList.get(Video_index));
//
//			}
//		});
//		ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//			@Override
//			public void onPrepared(IMediaPlayer mp) {
//
//				ijkVideoView.start();
//				Log.d(TAG, "准备播放");
//				Intent intent=new Intent("delectShiPing");
//				intent.putExtra("idid","fffff");
//				sendBroadcast(intent);
//
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						try {
//							Log.d(TAG, "进来线程");
//							Thread.sleep(3000);
//							Intent intent=new Intent("delectShiPing");
//							intent.putExtra("idid","fffff");
//							sendBroadcast(intent);
//							Log.d(TAG, "进来线程22");
//
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//
//		});
//
//	}

//	 static Handler handlerGongGao = new Handler();
//	static Runnable runnableGongGao = new Runnable() {
//
//		@Override
//		public void run() {
//			int cou=gonggaoList.size();
//			if (quyu<cou){
//				gonggao.startAnimation(sato0);
//				gonggao.setText(gonggaoList.get(quyu));
//
//				quyu++;
//				handlerGongGao.postDelayed(runnableGongGao,12000);//4秒后再次执行
//
//			}else {
//				quyu=0;
//				handlerGongGao.postDelayed(runnableGongGao,0);//4秒后再次执行
//			}
//
//		}
//	};

//	private static Handler handlerGongGao = new Handler(){
//		public  void handleMessage(android.os.Message msg) {
//
//
//
////				//逻辑处理
////				switch (quyu)
////
////				if (isTO){
////					isTO=false;
////					gonggao.setText(ss.substring(0,72));
////				}else {
////					gonggao.setText(ss.substring(72,ss.length()));
////				}
//
//
//		}
//	};



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//无title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏
		getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
				WindowManager.LayoutParams. FLAG_FULLSCREEN);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		dw=dm.widthPixels;
		dh=dm.heightPixels;

		//Log.d(TAG, "dw:" + dw);
		//Log.d(TAG, "dh:" + dh);




	//	DisplayMetrics dm = getResources().getDisplayMetrics();
//		w = dm.widthPixels;
//		h = dm.heightPixels;
		//String rid = JPushInterface.getRegistrationID(getApplicationContext());
		//Log.d(TAG, "极光id"+rid);

		new Thread(new Runnable() {
			@Override
			public void run() {
				JPushInterface.setAlias(VlcVideoActivity.this,1,"linhe");
			}
		}).start();

		//百度语音
		initialEnv();
		initialTts();

		IjkMediaPlayer.loadLibrariesOnce(null);
		IjkMediaPlayer.native_profileBegin("libijkplayer.so");

		tanchuangList=new Vector<>();
		yuangongList=new Vector<>();
	//	gonggaoList=new ArrayList<>();


		setContentView(R.layout.activity_video_vlc);
		bao= (ImageView) findViewById(R.id.bao);

		// 撒花初始化
		rlt_animation_layout = (RelativeLayout) findViewById(R.id.video_root);
		fllowerAnimation = new FllowerAnimation(this);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		fllowerAnimation.setLayoutParams(params);
		rlt_animation_layout.addView(fllowerAnimation);

//		wm=(WindowManager)getApplicationContext().getSystemService("window");
//		WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
//		mInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		view = mInflater.inflate(R.layout.activity_video_vlc, null);
//		wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
//		wmParams.format = PixelFormat.OPAQUE;
//		wmParams.flags=WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
//				| WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;;
//		wmParams.width=dw;
//		wmParams.height=dh;

//		tishi_im33= (ImageView) findViewById(R.id.tishi_im33);
//		tishi_tv33= (TextView) findViewById(R.id.tishi_tv33);
//		touxiang33= (ImageView) findViewById(R.id.touxiang33);
//
//		scollview= (ScrollView) findViewById(R.id.scollview);
//		gonggaobiaoti= (TextView) findViewById(R.id.gonggaobiaoti);
//		gonggaoriqi=(TextView) findViewById(R.id.gonggaoriqi);
//		gonggao=(TextView)findViewById(R.id.gonggao);
//		sato0.setDuration(800);
//		sato1.setDuration(800);
//
//		sato0.setAnimationListener(new Animation.AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				gonggao.startAnimation(sato1);
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//
//			}
//		});
//
//		Type resultType22222 = new TypeToken<ShiPingBean>() {}.getType();
//		Reservoir.getAsync("baocuntupian", resultType22222, new ReservoirGetCallback<ShiPingBean>() {
//			@Override
//			public void onSuccess(ShiPingBean strings) {
//				tupianPath=strings.getVideo();
//			}
//
//			@Override
//			public void onFailure(Exception e) {
//
//			}
//
//		});
//
//		Type resultType = new TypeToken<GongGaoBean>() {}.getType();
//		Reservoir.getAsync("baocungonggao", resultType, new ReservoirGetCallback<GongGaoBean>() {
//			@Override
//			public void onSuccess(GongGaoBean strings) {
//				ss=strings.getContent();
//				Log.d(TAG, "获取本地公告:"+ss);
//				if (gonggaoList.size()>0){
//					gonggaoList.clear();
//				}
//				if (ss.length()>80){
//
//					StringBuilder sb=new StringBuilder();
//					//遍历原始字符串的每一位字符,把它依次加入到sb中
//					for(int i=0;i<ss.length();i++){
//						sb.append((ss.charAt(i)));//依次加入sb中
//						if(i%80==0){
//							if (i>0){
//								sb.append("&");
//							}
//						}
//					}
//					String[] sourceStrArray = sb.toString().split("&");
//					for (String aSourceStrArray : sourceStrArray) {
//						gonggaoList.add("     "+aSourceStrArray);
//						//Log.d(TAG, aSourceStrArray);
//					}
//
//					handlerGongGao.postDelayed(runnableGongGao, 1);//启动handler，实现4秒定时循环执行
//
//				}else {
//					gonggao.startAnimation(sato0);
//					gonggao.setText(ss);
//
//				}
//
//				gonggaobiaoti.setText(strings.getTitle());
//				gonggaoriqi.setText(strings.getTimeDays());
//
//			}
//
//			@Override
//			public void onFailure(Exception e) {
//
//			}
//
//		});
		//marqueeView= (MarqueeView) findViewById(R.id.mv_bar1);
//		marqueeView2= (MarqueeView) findViewById(R.id.mv_bar12);
//		marqueeView.startWithList(vector1);
//		marqueeView2.startWithList(vector2);

		//autoScrollTextView.setText(".");
		//autoScrollTextView.init(getWindowManager());
		//autoScrollTextView.startScroll();
		Type resultType2 = new TypeToken<String>() {}.getType();
		Reservoir.getAsync("guanggao", resultType2, new ReservoirGetCallback<String>() {
			@Override
			public void onSuccess(String strings) {
				//autoScrollTextView.setText(strings);
				//autoScrollTextView.init(getWindowManager());
				//autoScrollTextView.startScroll();
			}

			@Override
			public void onFailure(Exception e) {
				//TastyToast.makeText(TianJiaIPActivity.this, e.getMessage(), Toast.LENGTH_SHORT,TastyToast.ERROR).show();
				//   finish();

			}
		});

		//textView = (TextView) findViewById(R.id.number);

		//实例化过滤器并设置要过滤的广播
		myReceiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("duanxianchonglian");
		intentFilter.addAction("gxshipingdizhi");
		intentFilter.addAction("shoudongshuaxin");
		intentFilter.addAction("updateGonggao");
		intentFilter.addAction("updateTuPian");
		intentFilter.addAction("updateShiPing");
		intentFilter.addAction("delectShiPing");
		intentFilter.addAction(Intent.ACTION_TIME_TICK);

		// 注册广播
		registerReceiver(myReceiver, intentFilter);

		//myReceiverFile = new MyReceiverFile();
//		IntentFilter intentFilter2 = new IntentFilter();
//		intentFilter2.addAction(Intent.ACTION_MEDIA_EJECT);
//		intentFilter2.addAction(Intent.ACTION_MEDIA_MOUNTED);
//		intentFilter2.addDataScheme("file");  //与其它 action 有冲突
//		registerReceiver(myReceiverFile, intentFilter2);

		//logo = BitmapFactory.decodeResource(super.getResources(), R.drawable.yixuanze_22);
		daoSession = MyApplication.getAppContext().getDaoSession().getMoShengRenBeanDao();
		shiPingBeanDao = MyApplication.getAppContext().getDaoSession().getShiPingBeanDao();


		//shiBieJiLuBeanDao = MyApplication.getAppContext().getDaoSession().getShiBieJiLuBeanDao();

//		recyclerView = (RecyclerView) findViewById(R.id.rccy);
//		((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
//		recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				//Log.i("abc","hasfocus:"+hasFocus);
//				if (hasFocus) {
//					if (recyclerView.getChildCount() > 0) {
//						recyclerView.getChildAt(0).requestFocus();
//					}
//				}
//			}
//		});



		//rollPagerView= (RollPagerView) findViewById(R.id.lunbo);


//		linearLayout = (RelativeLayout) findViewById(R.id.are);
//		linearLayout.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				startActivity(new Intent(VlcVideoActivity.this, XiTongSheZhiActivity.class));
//			}
//		});
//		if (w > h) {
//
//			gridLayoutManager = new GridLayoutManager(this, 2);
//			gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//			recyclerView.setLayoutManager(gridLayoutManager);
//			adapter = new MyAdapter(R.layout.erweima_item, moShengRenBean2List);
//			//adapter.addHeaderView(getView(),1);
//			recyclerView.setAdapter(adapter);
//			adapter.openLoadAnimation();
//			//adapter.addFooterView(getView());
//			adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
//			//recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
//		} else {
//
		TanChuangBean bean=new TanChuangBean();
		bean.setBytes(null);
		bean.setName(null);
		bean.setType(-2);
		bean.setTouxiang(null);
		tanchuangList.add(bean);

		TanChuangBean bean2=new TanChuangBean();
		bean2.setBytes(null);
		bean2.setName(null);
		bean2.setType(-2);
		bean2.setTouxiang(null);
		tanchuangList.add(bean2);

		TanChuangBean bean3=new TanChuangBean();
		bean3.setBytes(null);
		bean3.setName(null);
		bean3.setType(-2);
		bean3.setTouxiang(null);
		yuangongList.add(bean3);

		TanChuangBean bean4=new TanChuangBean();
		bean4.setBytes(null);
		bean4.setName(null);
		bean4.setType(-2);
		bean4.setTouxiang(null);
		yuangongList.add(bean4);

		TanChuangBean bean5=new TanChuangBean();
		bean5.setBytes(null);
		bean5.setName(null);
		bean5.setType(-2);
		bean5.setTouxiang(null);
		yuangongList.add(bean5);

	//	mSurfaceView = (SurfaceView) findViewById(R.id.video);


		recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView2= (RecyclerView) findViewById(R.id.recyclerView2);
//		recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
//		//	用来标记是否正在向最后一个滑动
//			boolean isSlidingToLast = false;
//
//			@Override
//			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//				super.onScrollStateChanged(recyclerView, newState);
//				LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//				// 当不滚动时
//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//					//获取最后一个完全显示的ItemPosition
//					int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//					int totalItemCount = manager.getItemCount();
////					Log.d(TAG, "lastVisibleItem:" + lastVisibleItem);
////					Log.d(TAG, "totalItemCount:" + totalItemCount);
//					// 判断是否滚动到底部，并且是向右滚动
//					if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
//						//加载更多功能的代码
//						manager2.smoothScrollToPosition(recyclerView2,null,0);
//					}
//
//					if (lastVisibleItem==4 && !isSlidingToLast){
//						manager2.smoothScrollToPosition(recyclerView2,null,yuangongList.size()-1);
//					}
//
//				}
//			}
//
//			@Override
//			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
//
//				//dx用来判断横向滑动方向，dy用来判断纵向滑动方向
//				//大于0表示正在向下滚动
//				//小于等于0表示停止或向上滚动
//				isSlidingToLast = dy > 0;
//			}
//		});

//		tianqi= (TextView) findViewById(R.id.tianqi);
//		wendu= (TextView) findViewById(R.id.wendu);
//		tanchuang_RL= (RelativeLayout) findViewById(R.id.ytutyii);
		Typeface typeFace =Typeface.createFromAsset(getAssets(),"fonts/led.ttf");
		shi= (TextView) findViewById(R.id.shi);
		jian= (TextView) findViewById(R.id.jian);
		dian= (TextView) findViewById(R.id.dian);
		riqi= (TextView) findViewById(R.id.riqi);
		xingqi= (TextView) findViewById(R.id.xingqi);

		//使用字体
		riqi.setTypeface(typeFace);
		shi.setTypeface(typeFace);
		dian.setTypeface(typeFace);
		jian.setTypeface(typeFace);

		final String time=(System.currentTimeMillis()/1000)+"";
		shi.setText(DateUtils.timeShi(time));
		jian.setText(DateUtils.timeJian(time));
		riqi.setText(DateUtils.timesTwo(time));
		Animation animation = AnimationUtils.loadAnimation(VlcVideoActivity.this, R.anim.alpha_anim);
		animation.setRepeatCount(-1);
		dian.setAnimation(animation);

		//xingqi.setText(DateUtils.getWeek(System.currentTimeMillis()));

//		final AnimatorSet animatorSet = new AnimatorSet();
//		animatorSet.playTogether(
//				//ObjectAnimator.ofFloat(dian,"translationY",-1000,0)
//				ObjectAnimator.ofFloat(dian,"alpha",1,1)
//				//ObjectAnimator.ofFloat(text,"translationX",-200,0)
//		);
//		animatorSet.setInterpolator(new DecelerateInterpolator());
//		animatorSet.setDuration(900);
//		animatorSet.addListener(new AnimatorListenerAdapter(){
//			@Override public void onAnimationEnd(Animator animation) {
//				if (is){
//					dian.setVisibility(View.INVISIBLE);
//					is=false;
//				}else {
//					dian.setVisibility(View.VISIBLE);
//					is=true;
//				}
//
//
//				animatorSet.playTogether(
//						//ObjectAnimator.ofFloat(dian,"translationY",-1000,0)
//						ObjectAnimator.ofFloat(dian,"alpha",1,1)
//						//ObjectAnimator.ofFloat(text,"translationX",-200,0)
//
//				);
//				animatorSet.setInterpolator(new DecelerateInterpolator());
//				animatorSet.setDuration(900);
//				animatorSet.start();
//
//			}
//		});
//
//		animatorSet.start();


		//TextView gonggao= (TextView) findViewById(R.id.gonggao);
	//	gonggao.setText("智慧互联 * 开创共赢\n");
		//	mSurfaceView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//		LinearLayout relativeLayout= (LinearLayout) findViewById(R.id.ffgghh);
//		relativeLayout.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//
//			}
//		});

//		Log.d(TAG, "findViewById(R.id.toptv).getWidth():" + findViewById(R.id.toptv).getWidth());

		//mLoadingView = findViewById(R.id.video_loading);

		//mSurfaceHolder = mSurfaceView.getHolder();

		//mSurfaceHolder.setFormat(PixelFormat.RGBX_8888);
		//mSurfaceView.setKeepScreenOn(true);


//		libvlc = LibVLCUtil.getLibVLC(null);
//
//		mediaPlayer = new MediaPlayer(libvlc);
//
//		vlcVout = mediaPlayer.getVLCVout();
//
//		callback = new IVLCVout.Callback() {
//			@Override
//			public void onNewLayout(IVLCVout ivlcVout, int i, int i1, int i2, int i3, int i4, int i5) {
//				Log.d(TAG, "i:" + i);
//				Log.d(TAG, "i1:" + i1);
//
//			}
//
//			@Override
//			public void onSurfacesCreated(IVLCVout ivlcVout) {
//
//				try {
//
//					changeSurfaceSize();
//
//				} catch (Exception e) {
//					Log.d("vlc-newlayout", e.toString());
//				}
//			}
//
//			@Override
//			public void onSurfacesDestroyed(IVLCVout ivlcVout) {
//
//					ivlcVout.removeCallback(callback);
//			}
//		};
//
//
//		vlcVout.addCallback(callback);
//		vlcVout.setVideoView(mSurfaceView);
//		vlcVout.attachViews();
		//this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		//		mMediaPlayer.setMediaList();
		//		mMediaPlayer.getMediaList().add(new Media(mMediaPlayer, "http://live.3gv.ifeng.com/zixun.m3u8"), false);
		//		mMediaPlayer.playIndex(0);
		//	mMediaPlayer.playMRL("rtsp://192.168.2.52/user=admin&password=&channel=1&stream=0.sdp");

		Type resultType111 = new TypeToken<List<ZhuJiBean>>() {
		}.getType();
		Reservoir.getAsync("fuwuqi", resultType111, new ReservoirGetCallback<List<ZhuJiBean>>() {
			@Override
			public void onSuccess(List<ZhuJiBean> strings) {

				if (strings.size() >= 1) {
					int a = strings.size();
					for (int i = 0; i < a; i++) {
						if (strings.get(i).getIsTrue() == 1) {
							shiping_string = strings.get(i).getDizhi();

							//mMediaPlayer.playMRL(shiping_string);
//							media = new Media(libvlc, Uri.parse(shiping_string));
//							mediaPlayer.setMedia(media);
//
//							if (mediaPlayer != null) {
//								mediaPlayer.play();
//								mSurfaceView.setKeepScreenOn(true);
//							}
							Type resultType = new TypeToken<List<ZhuJiBean>>() {
							}.getType();
							Reservoir.getAsync("zhujidizhi", resultType, new ReservoirGetCallback<List<ZhuJiBean>>() {
								@Override
								public void onSuccess(List<ZhuJiBean> strings) {
									if (strings.size() >= 1) {
										int a = strings.size();
										try {
											for (int i = 0; i < a; i++) {
												if (strings.get(i).getIsTrue() == 1) {
													zhuji_string = strings.get(i).getDizhi();

													if (zhuji_string!=null){
														String[] a1=zhuji_string.split("//");
														String[] b1=a1[1].split(":");
														zhuji="http://"+b1[0];
													}

													Message message = new Message();
													message.what = 111;
													handler.sendMessage(message);

													break;
												}
											}
										}catch (Exception e ){
											Log.d("VlcVideoActivity", e.getMessage());
										}

									}
								}

								@Override
								public void onFailure(Exception e) {

								}
							});

							break;

						}
					}
				}
			}

			@Override
			public void onFailure(Exception e) {

				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						TastyToast.makeText(VlcVideoActivity.this, "请先设置主机和视频流", Toast.LENGTH_LONG, TastyToast.ERROR).show();
					}
				});
			}
		});

	//	Settings mSettings = new Settings(this);
		//ijkMediaPlayer=new IjkMediaPlayer();
		//ijkMediaPlayer.setLogEnabled(false);

//		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
//		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
//		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
//		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 60);

//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 0);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "fps", 30);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_YV12);
//
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "max-buffer-size", 1024);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 10);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probsize", "4096");
//        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", "2000000");


//		ijkVideoView = (IjkVideoView) findViewById(R.id.vitamio);
//		ijkVideoView.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//				Message message=Message.obtain();
//				message.what=110;
//				handler.sendMessage(message);
//			}
//		});
//		ijkVideoView.setHudView(new TableLayout(this));


		//String rtspUrl="http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv";
		//String rtspUrl="mnt/usb_storage/USB_DISK1"+File.separator+"a1.mp4";
		//ijkVideoView.setVideoURI(Uri.parse(rtspUrl));
		//ijkVideoView.setVideoPath(rtspUrl);


//		File storage = new File("/storage");
//		File[] files = storage.listFiles();
//		for (final File file : files) {
//			if (file.canRead()) {
//				if (!file.getName().equals(Environment.getExternalStorageDirectory().getName())) {
//					//满足该条件的文件夹就是u盘在手机上的目录
//					Log.d(TAG, file.getName());
//					Log.d(TAG, Environment.getExternalStorageDirectory().getName());
//
//				}
//			}
//		}
//		File file=new File("mnt/usb_storage/USB_DISK1"+File.separator+"a1.mp4");
//		Log.d(TAG, "file.length():" + file.length());

		manager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		recyclerView.setLayoutManager(manager);

		manager2 = new WrapContentLinearLayoutManager(VlcVideoActivity.this);
		manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
		recyclerView2.setLayoutManager(manager2);

		adapter = new MyAdapter(R.layout.tanchuang_item2,tanchuangList);

		adapter2 = new MyAdapter2(R.layout.shibiejilu_item,yuangongList);
	
		recyclerView.setAdapter(adapter);
	    recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

		recyclerView2.setAdapter(adapter2);
		recyclerView2.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));

		daoSession.deleteAll();

	}

	private void print(Message msg) {
		String message = (String) msg.obj;
		if (message != null) {
			Log.w(TAG, message);
			//Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

		}
	}

	/*
     * @param arg0
     */
	@Override
	public void onSynthesizeStart(String utteranceId) {
		toPrint("onSynthesizeStart utteranceId=" + utteranceId);
	}

	/**
	 * 合成数据和进度的回调接口，分多次回调
	 *
	 * @param utteranceId
	 * @param data 合成的音频数据。该音频数据是采样率为16K，2字节精度，单声道的pcm数据。
	 * @param progress 文本按字符划分的进度，比如:你好啊 进度是0-3
	 */
	@Override
	public void onSynthesizeDataArrived(String utteranceId, byte[] data, int progress) {
		// toPrint("onSynthesizeDataArrived");
		//mHandler.sendMessage(mHandler.obtainMessage(UI_CHANGE_SYNTHES_TEXT_SELECTION, progress, 0));
	}

	/**
	 * 合成正常结束，每句合成正常结束都会回调，如果过程中出错，则回调onError，不再回调此接口
	 *
	 * @param utteranceId
	 */
	@Override
	public void onSynthesizeFinish(String utteranceId) {
		//toPrint("onSynthesizeFinish utteranceId=" + utteranceId);
	}

	/**
	 * 播放开始，每句播放开始都会回调
	 *
	 * @param utteranceId
	 */
	@Override
	public void onSpeechStart(String utteranceId) {
		//toPrint("onSpeechStart utteranceId=" + utteranceId);
	}

	/**
	 * 播放进度回调接口，分多次回调
	 *
	 * @param utteranceId
	 * @param progress 文本按字符划分的进度，比如:你好啊 进度是0-3
	 */
	@Override
	public void onSpeechProgressChanged(String utteranceId, int progress) {
		// toPrint("onSpeechProgressChanged");
		//mHandler.sendMessage(mHandler.obtainMessage(UI_CHANGE_INPUT_TEXT_SELECTION, progress, 0));
	}

	/**
	 * 播放正常结束，每句播放正常结束都会回调，如果过程中出错，则回调onError,不再回调此接口
	 *
	 * @param utteranceId
	 */
	@Override
	public void onSpeechFinish(String utteranceId) {
		//toPrint("onSpeechFinish utteranceId=" + utteranceId);
	}

	/**
	 * 当合成或者播放过程中出错时回调此接口
	 *
	 * @param utteranceId
	 * @param error 包含错误码和错误信息
	 */
	@Override
	public void onError(String utteranceId, SpeechError error) {
		toPrint("onError error=" + "(" + error.code + ")" + error.description + "--utteranceId=" + utteranceId);
	}

	private Handler mHandler = new Handler() {

		/*
         * @param msg
         */
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			int what = msg.what;
			switch (what) {
				case PRINT:
					print(msg);
					break;
//				case UI_CHANGE_INPUT_TEXT_SELECTION:  //播放进度
//					if (msg.arg1 <= mInput.getText().length()) {
//						mInput.setSelection(0,msg.arg1);
//					}
//					break;
//				case UI_CHANGE_SYNTHES_TEXT_SELECTION:  //合成进度
//					SpannableString colorfulText = new SpannableString(mInput.getText().toString());
//					if (msg.arg1 <= colorfulText.toString().length()) {
//						colorfulText.setSpan(new ForegroundColorSpan(Color.GRAY), 0, msg.arg1, Spannable
//								.SPAN_EXCLUSIVE_EXCLUSIVE);
//						mInput.setText(colorfulText);
//					}
//					break;
				default:
					break;
			}
		}

	};

	private void initialEnv() {
		if (mSampleDirPath == null) {
			String sdcardPath = Environment.getExternalStorageDirectory().toString();
			mSampleDirPath = sdcardPath + "/" + SAMPLE_DIR_NAME;
		}
		Utils.makeDir(mSampleDirPath);
		Utils.copyFromAssetsToSdcard(false, SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_FEMALE_MODEL_NAME,this);
		Utils.copyFromAssetsToSdcard(false, SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_MALE_MODEL_NAME,this);
		Utils.copyFromAssetsToSdcard(false, TEXT_MODEL_NAME, mSampleDirPath + "/" + TEXT_MODEL_NAME,this);
		//Utils.copyFromAssetsToSdcard(false, LICENSE_FILE_NAME, mSampleDirPath + "/" + LICENSE_FILE_NAME,this);
		Utils.copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/"
				+ ENGLISH_SPEECH_FEMALE_MODEL_NAME,this);
		Utils.copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/"
				+ ENGLISH_SPEECH_MALE_MODEL_NAME,this);
		Utils.copyFromAssetsToSdcard(false, "english/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath + "/"
				+ ENGLISH_TEXT_MODEL_NAME,this);
	}

	private void initialTts() {
		this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
		this.mSpeechSynthesizer.setContext(this);
		this.mSpeechSynthesizer.setSpeechSynthesizerListener(this);
		// 文本模型文件路径 (离线引擎使用)
		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
				+ TEXT_MODEL_NAME);
		// 声学模型文件路径 (离线引擎使用)
		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
				+ SPEECH_FEMALE_MODEL_NAME);
		// 本地授权文件路径,如未设置将使用默认路径.设置临时授权文件路径，LICENCE_FILE_NAME请替换成临时授权文件的实际路径，仅在使用临时license文件时需要进行设置，如果在[应用管理]中开通了正式离线授权，不需要设置该参数，建议将该行代码删除（离线引擎）
		// 如果合成结果出现临时授权文件将要到期的提示，说明使用了临时授权文件，请删除临时授权即可。
		//this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, mSampleDirPath + "/"
		//	+ LICENSE_FILE_NAME);
		// 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
		this.mSpeechSynthesizer.setAppId("9990556"/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
		// 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
		this.mSpeechSynthesizer.setApiKey("0fxLiYpG9gUC9660agtU3rEU",
				"68288f5f80eaa85a6384ac30a14b622e"/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);
		// 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
		// 设置Mix模式的合成策略
		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
		//合成引擎速度优化等级，取值范围[0, 2]，值越大速度越快（离线引擎）
		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOCODER_OPTIM_LEVEL, "2");

		// 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
		// AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
		AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);

		if (authInfo.isSuccess()) {
			toPrint("auth success");
		} else {
			String errorMsg = authInfo.getTtsError().getDetailMessage();
			toPrint("auth failed errorMsg=" + errorMsg);
		}

		// 初始化tts
		mSpeechSynthesizer.initTts(TtsMode.MIX);
		// 加载离线英文资源（提供离线英文合成功能）
		int result =
				mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath
						+ "/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
		toPrint("loadEnglishModel result=" + result);

		//打印引擎信息和model基本信息
		//printEngineInfo();
	}
	private void toPrint(String str) {
		Message msg = Message.obtain();
		msg.obj = str;
		this.mHandler.sendMessage(msg);
	}

	// in code.
//	public  void screenShot(View view)
//	{
////xxxxx
//		EGL10 egl = (EGL10) EGLContext.getEGL();
//		GL10 gl = (GL10)egl.eglGetCurrentContext().getGL();
//		Bitmap bp = SavePixels(0, 0, view.getWidth(), view.getHeight(), gl);
//		//保存截屏图片
//		File file = new File(Environment.getExternalStorageDirectory()	  +"/"+ System.currentTimeMillis() + "aaa.jpg");
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream(file);
//			bp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			fos.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
////xxx
//	}
//
//	public  Bitmap SavePixels(int x, int y, int w, int h, GL10 gl)
//	{
//		int b[]=new int[w*(y+h)];
//		int bt[]=new int[w*h];
//		IntBuffer ib=IntBuffer.wrap(b);
//		ib.position(0);
//		gl.glReadPixels(x, 0, w, y+h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, ib);
//
//		for(int i=0, k=0; i<h; i++, k++)
//		{//remember, that OpenGL bitmap is incompatible with Android bitmap
//			//and so, some correction need.
//			for(int j=0; j<w; j++)
//			{
//				int pix=b[i*w+j];
//				int pb=(pix>>16)&0xff;
//				int pr=(pix<<16)&0x00ff0000;
//				int pix1=(pix&0xff00ff00) | pr | pb;
//				bt[(h-k-1)*w+j]=pix1;
//			}
//		}


//		Bitmap bp=Bitmap.createBitmap(bt, w, h, Bitmap.Config.ARGB_8888);
//
//		return bp;
//	}

//	// 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
//	private void getAllFiles(File root){
//		File files[] = root.listFiles();
//
//		if(files != null){
//			for (File f : files){
//				if(f.isDirectory()){
//					getAllFiles(f);
//				}else{
//				String name=f.getName();
//					if (name.substring(name.length()-3,name.length()).equals("mp4")){
//						Log.d(TAG, f.getAbsolutePath());
//						voidePathList.add(f.getAbsolutePath());
//
//					}else if (name.substring(name.length()-3,name.length()).equals("jpg")||name.substring(name.length()-3,name.length()).equals("png")){
//						photoPathList.add(f.getAbsolutePath());
//					}
//
//				}
//			}
//			Log.d(TAG, "voidePathList.size():" + voidePathList.size());
//			Message message=Message.obtain();
//			message.what=110;
//			handler.sendMessage(message);
//
//		}
//	}



	@Override
	protected void onStart() {
		super.onStart();

	}

	public  class MyAdapter extends BaseQuickAdapter<TanChuangBean,BaseViewHolder> {
		private View view;
	//	private List<TanChuangBean> d;
		private int p;
		private int vid;


		private MyAdapter(int layoutResId, List<TanChuangBean> data) {
			super(layoutResId, data);
			vid=layoutResId;
		//d=data;
		}



		@Override
		protected void convert(BaseViewHolder helper, TanChuangBean item) {
			//Log.d(TAG, "动画执行");
			ViewAnimator
					.animate(helper.itemView)
					.scale(0,1)
//					.alpha(0,1)
					.duration(1000)
					.start();

			RelativeLayout toprl= helper.getView(R.id.ffflll);

			ImageView imageView= helper.getView(R.id.touxiang);
			ImageView tishi_im= helper.getView(R.id.tishi_im);
			TextView tishi_tv= helper.getView(R.id.tishi_tv);
			TextView tishi_tv2= helper.getView(R.id.ddd);

			if (helper.getAdapterPosition()==0 ||helper.getAdapterPosition()==1 ){
				toprl.setBackgroundColor(Color.parseColor("#00000000"));
				tishi_tv.setText("");
				tishi_tv2.setText("");
			}

				switch (item.getType()){
					case -1:
						//陌生人
					//	toprl.setBackgroundResource(R.drawable.tanchuang);


						break;
					case 0:
						//员工
					//	toprl.setBackgroundResource(R.drawable.tanchuang);
					//	tishi_tv.setText("欢迎老板");
					//	tishi_im.setBackgroundResource(R.drawable.tike);

						break;

					case 1:
						//访客

						view=toprl;
						toprl.setBackgroundResource(R.drawable.toubgbgbg);
						tishi_tv.setText("热烈欢迎"+item.getName());
						tishi_tv2.setText("莅临参观指导");


						break;
					case 2:
						//VIP访客
						view=toprl;
						toprl.setBackgroundResource(R.drawable.toubgbgbg);
						tishi_tv.setText("热烈欢迎"+item.getName());
						tishi_tv2.setText("莅临参观指导");

						break;


				}


			if (item.getTouxiang()!=null){

				Glide.with(MyApplication.getAppContext())
						.load(zhuji+item.getTouxiang())
						.transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
						.into((ImageView) helper.getView(R.id.touxiang));
			}else {
				Glide.with(MyApplication.getAppContext())
						.load(item.getBytes())
						.transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
						.into((ImageView) helper.getView(R.id.touxiang));
		    	}
			}




	}



	private  class MyAdapter2 extends BaseQuickAdapter<TanChuangBean,BaseViewHolder> {

		private MyAdapter2(int layoutResId, List<TanChuangBean> data) {
			super(layoutResId, data);

		}

		@Override
		protected void convert(BaseViewHolder helper, TanChuangBean item) {
			ViewAnimator
					.animate(helper.itemView)
					.scale(0,1)
//					.alpha(0,1)
					.duration(1000)
					.start();

			RelativeLayout toprl= helper.getView(R.id.ffflll);

			ImageView imageView= helper.getView(R.id.touxiang);
			ImageView tishi_im= helper.getView(R.id.tishi_im);
			TextView tishi_tv= helper.getView(R.id.tishi_tv);

			if (helper.getAdapterPosition()==0 ||helper.getAdapterPosition()==1 ){
				toprl.setBackgroundColor(Color.parseColor("#00000000"));
				tishi_tv.setText("");

			}else {

				switch (item.getType()) {
					case -1:
						//陌生人
						//	toprl.setBackgroundResource(R.drawable.tanchuang);


						break;
					case 0:
						//员工

						toprl.setBackgroundResource(R.drawable.ybg);
						tishi_tv.setText(item.getName());


						break;

					case 1:
						//访客
						//	toprl.setBackgroundResource(R.drawable.tanchuang);

						//richeng.setText("");
						//name.setText(item.getName());
						//autoScrollTextView.setText("欢迎你来本公司参观指导。");


						break;
					case 2:
						//VIP访客
						//toprl.setBackgroundResource(R.drawable.tanchuang);
						//	richeng.setText("");
						//	name.setText(item.getName());
						//autoScrollTextView.setText("欢迎VIP访客 "+item.getName()+" 来本公司指导工作。");

						break;


				}
			}

				if (item.getTouxiang()!=null){

					Glide.with(MyApplication.getAppContext())
							.load(zhuji+item.getTouxiang())
							.transform(new GlideCircleTransform(MyApplication.getAppContext()))
							.into((ImageView) helper.getView(R.id.touxiang));
				}else {

					Glide.with(MyApplication.getAppContext())
							.load(item.getBytes())
							.transform(new GlideCircleTransform(MyApplication.getAppContext()))
							.into((ImageView) helper.getView(R.id.touxiang));
				}


	}
	}

//	/**
//	 * 生成二维码
//	 * @param string 二维码中包含的文本信息
//	 * @param mBitmap logo图片
//	 * @param format  编码格式
//	 * [url=home.php?mod=space&uid=309376]@return[/url] Bitmap 位图
//	 * @throws WriterException
//	 */
//	private static final int IMAGE_HALFWIDTH = 1;//宽度值，影响中间图片大小
//	public Bitmap createCode(String string,Bitmap mBitmap, BarcodeFormat format)
//			throws WriterException {
//		Matrix m = new Matrix();
//		float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
//		float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
//		m.setScale(sx, sy);//设置缩放信息
//		//将logo图片按martix设置的信息缩放
//		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
//				mBitmap.getWidth(), mBitmap.getHeight(), m, false);
//		MultiFormatWriter writer = new MultiFormatWriter();
//		Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
//		hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");//设置字符编码
//		BitMatrix matrix = writer.encode(string, format, 600, 600, hst);//生成二维码矩阵信息
//		int width = matrix.getWidth();//矩阵高度
//		int height = matrix.getHeight();//矩阵宽度
//		int halfW = width/2;
//		int halfH = height/2;
//		int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
//		for (int y = 0; y < height; y++) {//从行开始迭代矩阵
//			for (int x = 0; x < width; x++) {//迭代列
//				if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
//						&& y > halfH - IMAGE_HALFWIDTH
//						&& y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息
//			//记录图片每个像素信息
//					pixels[y * width + x] = mBitmap.getPixel(x - halfW
//							+ IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);              } else {
//					if (matrix.get(x, y)) {//如果有黑块点，记录信息
//						pixels[y * width + x] = 0xff000000;//记录黑块信息
//					}
//				}
//			}
//		}
//		Bitmap bitmap = Bitmap.createBitmap(width, height,
//				Bitmap.Config.ARGB_8888);
//		// 通过像素数组生成bitmap
//		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//		return bitmap;
//	}
//	private class MyReceiverFile  extends BroadcastReceiver {
//
//		@Override
//		public void onReceive(Context context, final Intent intent) {
//			String action = intent.getAction();
//			if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
//				//USB设备移除，更新UI
//				Log.d(TAG, "设备被移出");
////				if (rollPagerView!=null){
////					if (rollPagerView.isPlaying()){
////						rollPagerView.pause();
////					}
////
////
////				}
//				if (ijkMediaPlayer!=null){
//					Log.d(TAG, "播放暂停");
//					//ijkVideoView.pause();
//					//ijkVideoView.canPause();
//
//					//ijkVideoView.stopPlayback();
//					//ijkMediaPlayer.stop();
//				}
//			}
////			else if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
////				//USB设备挂载，更新UI
////				Log.d(TAG, "设备插入");
////				new Thread(new Runnable() {
////					@Override
////					public void run() {
////						String usbPath = intent.getDataString();//（usb在手机上的路径）
////
////						getAllFiles(new File(usbPath.split("file:///")[1]+File.separator+"file"));
////						Log.d(TAG, usbPath);
////					}
////				}).start();
////			}
//
//		}
//	}


	private class MyReceiver  extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, final Intent intent) {
			//Log.d(TAG, "intent:" + intent.getAction());

			if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
				//ijkVideoView.requestFocus();
				String time=(System.currentTimeMillis()/1000)+"";
				shi.setText(DateUtils.timeShi(time));
				jian.setText(DateUtils.timeJian(time));
				riqi.setText(DateUtils.timesTwo(time));
				xingqi.setText(DateUtils.getWeek(System.currentTimeMillis()));
				if (DateUtils.timeMinute(time).equals("06:00")){
					//link_chengshi();
					Reservoir.putAsync("shulianshu",  0, new ReservoirPutCallback() {
						@Override
						public void onSuccess() {
							//Log.d("WebsocketPushMsg", "保存刷脸人数成功"+i);

						}

						@Override
						public void onFailure(Exception e) {
							Log.d("WebsocketPushMsg", e.getMessage());
							//error
						}
					});
				}


//				if (System.currentTimeMillis()-timing>30000 &&  System.currentTimeMillis()-timing<93000){
//					if (ijkMediaPlayer!=null && ijkVideoView!=null){
//							if (!ijkVideoView.isPlaying()){
//								zixunLL.setVisibility(View.GONE);
//								ijkVideoView.setVisibility(View.VISIBLE);
//								ijkVideoView.start();
//								Log.d(TAG, "设置视频显示");
//								ijkVideoView.requestFocus();
//								scollview.setVisibility(View.GONE);
//							}else {
//								zixunLL.setVisibility(View.GONE);
//								scollview.setVisibility(View.GONE);
//								ijkVideoView.setVisibility(View.VISIBLE);
//								ijkVideoView.requestFocus();
//							}
//					}
//
//
//				}


			} else {
				if (intent.getAction().equals("duanxianchonglian")) {
					//断线重连
					//if (webSocketClient!=null){
					//	if (!webSocketClient.isOpen()){
					try {
						WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
						if (zhuji_string != null && shiping_string != null) {
							websocketPushMsg.startConnection(zhuji_string, shiping_string);
						}
						//恢复视频流
						startActivity(new Intent(VlcVideoActivity.this, ErWeiMaActivity.class));

					} catch (URISyntaxException e) {
						e.printStackTrace();

					}
				}
				if (intent.getAction().equals("gxshipingdizhi")) {
					//更新视频流地址
					//Log.d(TAG, "收到更新地址广播");
					String a = intent.getStringExtra("gxsp");
					String b = intent.getStringExtra("gxzj");
					if (!a.equals("")) {
						shiping_string = a;
						media = new Media(libvlc, Uri.parse(shiping_string));
						mediaPlayer.setMedia(media);
						mediaPlayer.play();
						//mMediaPlayer.playMRL(shiping_string);
						startActivity(new Intent(VlcVideoActivity.this, ErWeiMaActivity.class));
					}
					if (!b.equals("")) {

						zhuji_string = b;
						try {
							String[] a1=zhuji_string.split("//");
							String[] b1=a1[1].split(":");
							zhuji="http://"+b1[0];

							WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
							if (zhuji_string != null && shiping_string != null) {
								websocketPushMsg.startConnection(zhuji_string, shiping_string);
							}
						} catch (URISyntaxException e) {
							e.printStackTrace();

						}

					}

				}
				if (intent.getAction().equals("shoudongshuaxin")) {
					if (shiping_string != null && zhuji_string != null) {

						media = new Media(libvlc, Uri.parse(shiping_string));
						mediaPlayer.setMedia(media);
						mediaPlayer.play();
						//mLoadingView.setVisibility(View.GONE);

						try {
							WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
							if (zhuji_string != null && shiping_string != null) {
								websocketPushMsg.startConnection(zhuji_string, shiping_string);
							}
						} catch (URISyntaxException e) {
							e.printStackTrace();

						}

						startActivity(new Intent(VlcVideoActivity.this, ErWeiMaActivity.class));
					}

				}
//				if (intent.getAction().equals("guanggao")) {
//
//					//autoScrollTextView.setText(intent.getStringExtra("ggg"));
//					//autoScrollTextView.init(getWindowManager());
//					//autoScrollTextView.startScroll();
//
//				}

			}
//			if (intent.getAction().equals("updateGonggao")){
//
//				handlerGongGao.removeCallbacks(runnableGongGao);
//
//				Type resultType = new TypeToken<GongGaoBean>() {}.getType();
//				Reservoir.getAsync("baocungonggao", resultType, new ReservoirGetCallback<GongGaoBean>() {
//					@Override
//					public void onSuccess(GongGaoBean strings) {
//
//						ss=strings.getContent();
//
//						if (gonggaoList.size()>0){
//							gonggaoList.clear();
//						}
//						if (ss.length()>80){
//							isChange=true;
//							StringBuilder sb=new StringBuilder();
//							//遍历原始字符串的每一位字符,把它依次加入到sb中
//							for(int i=0;i<ss.length();i++){
//								sb.append((ss.charAt(i)));//依次加入sb中
//								if(i%80==0){
//									if (i>0){
//										sb.append("&");
//									}
//								}
//							}
//							String[] sourceStrArray = sb.toString().split("&");
//							for (String aSourceStrArray : sourceStrArray) {
//								gonggaoList.add("     "+aSourceStrArray);
//							}
//
//							handlerGongGao.postDelayed(runnableGongGao,0);//4秒后再次执行
//
//						}else {
//							gonggao.startAnimation(sato0);
//							gonggao.setText(ss);
//
//						}
//
//						//gonggaobiaoti.setText(strings.getTitle());
//						//gonggaoriqi.setText(strings.getTimeDays());
//					}
//
//					@Override
//					public void onFailure(Exception e) {
//
//
//					}
//
//						});
//
//			}
//			if (intent.getAction().equals("updateShiPing")){
//
//
//				ShiPingBean shiBieJiLuBean=shiPingBeanDao.load(intent.getStringExtra("idid"));
////				Log.d("VlcVideoActivity", "更新视频ID："+shiBieJiLuBean.getId());
//				if (!shiBieJiLuBean.getIsDownload()){
//
//				Intent intent33 = new Intent(VlcVideoActivity.this, DownloadService.class);
//				intent33.putExtra("url", "http://192.168.2.100:80/rtps/a"+shiBieJiLuBean.getVideo());
//				//intent33.putExtra("url", "http://183.60.197.32/4/w/k/d/l/wkdlmnucuyduwzdqfjtmdvsxfsotsv/he.yinyuetai.com/C033015644E6D35D99022E014A4761A1.flv?sc\\u003d35b0e75f99e3878e\\u0026br\\u003d3138\\u0026vid\\u003d2650626\\u0026aid\\u003d167\\u0026area\\u003dHT\\u0026vst\\u003d2");
//				fileNames=shiBieJiLuBean.getId();
//					try {
//						fileType=shiBieJiLuBean.getVideoType().split("\\/")[1];
//					}catch (Exception e){
//						Log.d(TAG, "截取最后视频格式错误"+e.getMessage());
//						return;
//					}
//
//				intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
//				intent33.putExtra("urlName",fileNames);
//				intent33.putExtra("nameType",fileType);
//				startService(intent33);
//				Log.d(TAG, "收到下载视频的广播　,下载地址"+fileType);
//				}else {
//
//					if (shiPingBeanList.size()>0){
//						shiPingBeanList.clear();
//					}
//					shiPingBeanList=shiPingBeanDao.loadAll();
//
//					ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"linhefile"+File.separator+shiBieJiLuBean.getId()+"."+shiBieJiLuBean.getVideoType().split("\\/")[1]);
//					ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//						@Override
//						public void onPrepared(IMediaPlayer iMediaPlayer) {
//							shiPingCount=-1;
//							ijkVideoView.start();
//						}
//					});
//				}
//
//			}
//			if (intent.getAction().equals("delectShiPing")){
//
//				final String idid=intent.getStringExtra("idid");
//
//				Log.d(TAG, "收到删除视频的广播");
//
//				try {
//
////				int ii= shiPingBeanList.size();
////				Log.d(TAG, "iiiiii:" + ii);
////				int i2=0;
////				if (ii>1){
////					for (int i=ii-1;i>=0;i--){
////						Log.d(TAG, "i:" + i);
////
////						if (shiPingBeanList.get(i).getIsDownload()){
////							i2++;
////							if (i2>=1){
////								//视频文件大于２　并且都是下载过的,可以暂停　删除本地文件
////								//删除数据库
//								final String ty=shiPingBeanDao.load(idid).getVideoType().split("\\/")[1];
//
//								new Thread(new Runnable() {
//									@Override
//									public void run() {
//										String path= Environment.getExternalStorageDirectory()+File.separator+"linhefile";
//
//										getAllFiles(new File(path),idid+"."+ty);
//									}
//								}).start();
//
//								shiPingBeanDao.deleteByKey(idid);
//
//								if (ijkVideoView.isPlaying()){
//									ijkVideoView.pause();
//								}
//								//更新视频列表
//								if (shiPingBeanList.size()>0){
//									shiPingBeanList.clear();
//								}
//								shiPingBeanList=shiPingBeanDao.loadAll();
//								int spl=shiPingBeanList.size();
//								Log.d(TAG, "现在shiPingCount:" + shiPingCount);
//								if (spl>0){
//									if (shiPingCount>0){
//										--shiPingCount;
//									}
//									ShiPingBean sssss=shiPingBeanList.get(shiPingCount);
//									ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"linhefile"+File.separator+ sssss.getId()+"."+sssss.getVideoType().split("\\/")[1]);
//									ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//										@Override
//										public void onPrepared(IMediaPlayer iMediaPlayer) {
//											ijkVideoView.start();
//										}
//									});
//								}
//
//
//
////							}else {
////								TastyToast.makeText(VlcVideoActivity.this,"不能删除，必须有一个可以播放的视频",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
////							}
////						}
////
////					}
////
////				}else {
////					TastyToast.makeText(VlcVideoActivity.this,"不能删除，必须有一个可以播放的视频",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
////				}
//
//				}catch (Exception e){
//					Log.d(TAG, "捕捉到异常delectShiPing"+e.getMessage());
//			}
//
////				}catch (Exception e){
////					Log.d(TAG, e.getMessage());
////					TastyToast.makeText(VlcVideoActivity.this,"删除视频出错",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
////				}
//
//
//			}
//			if (intent.getAction().equals("updateTuPian")){
//				Log.d(TAG, "收到图片推送");
//				Type resultType = new TypeToken<ShiPingBean>() {}.getType();
//				Reservoir.getAsync("baocuntupian", resultType, new ReservoirGetCallback<ShiPingBean>() {
//					@Override
//					public void onSuccess(ShiPingBean strings) {
//						Log.d(TAG, "收到图片推送2");
//						tupianPath=strings.getVideo();
//                        Intent intent33 = new Intent(VlcVideoActivity.this, DownloadTuPianService.class);
//                        intent33.putExtra("receiver", new DownloadReceiverTuPian(new Handler()));
//                        intent33.putExtra("url", "http://192.168.2.100:80/rtps/a"+tupianPath);
//                        startService(intent33);
//						Log.d(TAG, "收到图片推送3");
//					}
//
//					@Override
//					public void onFailure(Exception e) {
//
//
//					}
//
//				});
//			}
		}

	}

//	private void link_gengxing_erweima() {
//
//		OkHttpClient okHttpClient= MyApplication.getOkHttpClient();
//		RequestBody body = new FormBody.Builder()
//				.add("cmd","getUnSignList")
////                .add("subjectId",zhaoPianBean.getId()+"")
////                .add("subjectPhoto",zhaoPianBean.getAvatar())
//				.build();
//		Request.Builder requestBuilder = new Request.Builder()
//				.header("Content-Type", "application/json")
//				.post(body)
//				.url("http://192.168.2.17:8080/sign");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//
//				Log.d("AllConnects", "请求获取二维码失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				//Log.d("AllConnects", "请求获取二维码成功"+call.request().toString());
//				//获得返回体
//				//List<YongHuBean> yongHuBeanList=new ArrayList<>();
//				//List<MoShengRenBean2> intlist=new ArrayList<>();
//			//	intlist.addAll(moShengRenBean2List);
//				try {
//
//				if (moShengRenBean2List.size()!=0)
//				 moShengRenBean2List.clear();
//				ResponseBody body = response.body();
//				//  Log.d("AllConnects", "aa   "+response.body().string());
//
//					JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//					Gson gson=new Gson();
//						int code=jsonObject.get("resultCode").getAsInt();
//						if (code==0){
//					JsonArray array=jsonObject.get("data").getAsJsonArray();
//					int a=array.size();
//					for (int i=0;i<a;i++){
//						YongHuBean zhaoPianBean=gson.fromJson(array.get(i),YongHuBean.class);
//						moShengRenBean2List.add(zhaoPianBean);
//						//Log.d("VlcVideoActivity", zhaoPianBean.getSubjectId());
//					}
//						//	Log.d("VlcVideoActivity", "moShengRenBean2List.size():" + moShengRenBean2List.size());
////					int a1=intlist.size();
////					int b=yongHuBeanList.size();
////
////						for (int i=0;i<a1;i++){
////							for (int j=0;j<b;j++){
////								Log.d("VlcVideoActivity", intlist.get(i).getId()+"ttt");
////								Log.d("VlcVideoActivity", yongHuBeanList.get(j).getSubjectId()+"ttt");
////								if (intlist.get(i).getId().equals(yongHuBeanList.get(j).getSubjectId())){
////									moShengRenBean2List.add(intlist.get(i));
////								}
////							}
////						}
//
//					Message message=Message.obtain();
//					message.what=12;
//					//  message.obj=yongHuBeanList;
//					handler.sendMessage(message);
//					}
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//
//			}
//		});
//
//	}


	// 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
	public static void getAllFiles(File root,String nameaaa){
		File files[] = root.listFiles();

		if(files != null){
			for (File f : files){
				if(f.isDirectory()){
					getAllFiles(f,nameaaa);
				}else{
					String name=f.getName();
					if (name.equals(nameaaa)){
						Log.d(TAG, "视频文件删除:" + f.delete());
					}
				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mExplosionField = ExplosionField.attach2Window(VlcVideoActivity.this);
		mExplosionField.expandExplosionBound(900,1200);
//		 dw = Utils.getDisplaySize(getApplicationContext()).x;
//		 dh = Utils.getDisplaySize(getApplicationContext()).y;



//		if (ijkVideoView!=null){
//			if (!ijkVideoView.isPlaying()){
//				ijkVideoView.start();
//			}
//		}

//		if (mediaPlayer==null){
//			mediaPlayer = new MediaPlayer(libvlc);
//			vlcVout = mediaPlayer.getVLCVout();
//
//			callback = new IVLCVout.Callback() {
//				@Override
//				public void onNewLayout(IVLCVout ivlcVout, int i, int i1, int i2, int i3, int i4, int i5) {
//
//				}
//
//				@Override
//				public void onSurfacesCreated(IVLCVout ivlcVout) {
//					try {
//
//						changeSurfaceSize();
//
//					} catch (Exception e) {
//						Log.d("vlc-newlayout", e.toString());
//					}
//				}
//
//				@Override
//				public void onSurfacesDestroyed(IVLCVout ivlcVout) {
//
//				}
//			};
//
//			vlcVout.addCallback(callback);
//			vlcVout.setVideoView(mSurfaceView);
//			vlcVout.attachViews();
//		}
//
//		if (mediaPlayer != null) {
//
//			mSurfaceView.setKeepScreenOn(true);
//			if (shiping_string!=null){
//				media = new Media(libvlc, Uri.parse(shiping_string));
//				mediaPlayer.setMedia(media);
//			//	Log.d(TAG, "ggggggggggggggggg"+shiping_string);
//				mediaPlayer.play();
//
//			}
//
//		}

//		Type resultType = new TypeToken<Integer>() {
//		}.getType();
//		Reservoir.getAsync("shulianshu", resultType, new ReservoirGetCallback<Integer>() {
//			@Override
//			public void onSuccess(Integer i) {
//				//Log.d("WebsocketPushMsg", "i:" + i);
//				//textView.setText(i+"");
//
//			}
//
//			@Override
//			public void onFailure(Exception e) {
//				//Log.d("WebsocketPushMsg", e.getMessage());
//
//
//			}
//		});

	//	link_gengxing_erweima();

//		Type resultType = new TypeToken<List<ZhuJiBean>>() {
//		}.getType();
//		Reservoir.getAsync("tuisongdizhi", resultType, new ReservoirGetCallback<List<ZhuJiBean>>() {
//			@Override
//			public void onSuccess(List<ZhuJiBean> strings) {
//				if (strings.size() >= 1) {
//					int a = strings.size();
//					try{
//						for (int i = 0; i < a; i++) {
//							if (strings.get(i).getIsTrue() == 1) {
//								tuisong_dizhi=strings.get(i).getDizhi();
//								break;
//							}
//						}
//					}
//					catch (Exception e){
//						Log.d("VlcVideoActivity", e.getMessage());
//					}
//
//				}
//			}
//
//			@Override
//			public void onFailure(Exception e) {
//
//			}
//		});
	}

	@Override
	protected void onRestart() {
		super.onRestart();
//
//		if (ijkVideoView!=null){
//			if (!ijkVideoView.isPlaying()){
//				ijkVideoView.start();
//			}
//		}
//
//		if (mediaPlayer==null){
//			mediaPlayer = new MediaPlayer(libvlc);
//			vlcVout = mediaPlayer.getVLCVout();
//
//			callback = new IVLCVout.Callback() {
//				@Override
//				public void onNewLayout(IVLCVout ivlcVout, int i, int i1, int i2, int i3, int i4, int i5) {
//
//				}
//
//				@Override
//				public void onSurfacesCreated(IVLCVout ivlcVout) {
//					try {
//
//						changeSurfaceSize();
//
//					} catch (Exception e) {
//						Log.d("vlc-newlayout", e.toString());
//					}
//				}
//
//				@Override
//				public void onSurfacesDestroyed(IVLCVout ivlcVout) {
//
//				}
//			};
//
//			vlcVout.addCallback(callback);
//			vlcVout.setVideoView(mSurfaceView);
//			vlcVout.attachViews();
//		}
//
//		if (mediaPlayer != null) {
//
//			mSurfaceView.setKeepScreenOn(true);
//			if (shiping_string!=null){
//				media = new Media(libvlc, Uri.parse(shiping_string));
//				mediaPlayer.setMedia(media);
//				//	Log.d(TAG, "ggggggggggggggggg"+shiping_string);
//				mediaPlayer.play();
//
//			}
//
//		}

	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "暂停");
//		if (mediaPlayer != null) {
//			mediaPlayer.pause();
//		}
//		if (ijkVideoView!=null){
//			ijkVideoView.pause();
//		}

	}

	@Override
	protected void onDestroy() {
		shiPingCount=0;

		quyu=0;
//		gonggaoList=null;
//		handlerGongGao.removeCallbacks(runnableGongGao);
		super.onDestroy();
//		if (mediaPlayer != null) {
//			mediaPlayer.release();
//			mediaPlayer=null;
//			vlcVout=null;
//		}
		//ijkVideoView.stopPlayback();

		handler.removeCallbacksAndMessages(null);
		if (myReceiver!=null)
		unregisterReceiver(myReceiver);
		//unregisterReceiver(myReceiverFile);

	}

//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		setSurfaceSize(mVideoWidth, mVideoHeight, mVideoVisibleWidth, mVideoVisibleHeight, mSarNum, mSarDen);
//		super.onConfigurationChanged(newConfig);
//}

//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		if (mMediaPlayer != null) {
//			mSurfaceHolder = holder;
//			mMediaPlayer.attachSurface(holder.getSurface(), this);
//		}
//	}
//
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//		mSurfaceHolder = holder;
//		if (mMediaPlayer != null) {
//			mMediaPlayer.attachSurface(holder.getSurface(), this);//, width, height
//		}
//		if (width > 0) {
//			mVideoHeight = height;
//			mVideoWidth = width;
//		}
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		if (mMediaPlayer != null) {
//			mMediaPlayer.detachSurface();
//		}
//	}
//
//	@Override
//	public void setSurfaceSize(int width, int height, int visible_width, int visible_height, int sar_num, int sar_den) {
//
//		mVideoHeight = height;
//		mVideoWidth = width;
//		mVideoVisibleHeight = visible_height;
//		mVideoVisibleWidth = visible_width;
//		mSarNum = sar_num;
//		mSarDen = sar_den;
//		mHandler.removeMessages(HANDLER_SURFACE_SIZE);
//		mHandler.sendEmptyMessage(HANDLER_SURFACE_SIZE);
//	}

	//private static final int HANDLER_BUFFER_START = 1;
//	private static final int HANDLER_BUFFER_END = 2;
	//private static final int HANDLER_SURFACE_SIZE = 3;

//	private static final int SURFACE_BEST_FIT = 0;
//	private static final int SURFACE_FIT_HORIZONTAL = 1;
//	private static final int SURFACE_FIT_VERTICAL = 2;
//	private static final int SURFACE_FILL = 3;
//	private static final int SURFACE_16_9 = 4;
//	private static final int SURFACE_4_3 = 5;
	//private static final int SURFACE_ORIGINAL = 6;
//	private int mCurrentSize = SURFACE_BEST_FIT;

//	private Handler mVlcHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			if (msg == null || msg.getData() == null)
//				return;
//
//			switch (msg.getData().getInt("event")) {
//			case EventHandler.MediaPlayerTimeChanged:
//				break;
//			case EventHandler.MediaPlayerPositionChanged:
//				break;
//			case EventHandler.MediaPlayerPlaying:
//				mHandler.removeMessages(HANDLER_BUFFER_END);
//				mHandler.sendEmptyMessage(HANDLER_BUFFER_END);
//				break;
//			case EventHandler.MediaPlayerBuffering:
//				break;
//			case EventHandler.MediaPlayerLengthChanged:
//				break;
//			case EventHandler.MediaPlayerEndReached:
//				//播放完成
//				break;
//			}
//
//		}
//	};

//	private Handler mHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case HANDLER_BUFFER_START:
//                showLoading();
//				break;
//			case HANDLER_BUFFER_END:
//                hideLoading();
//				break;
//			case HANDLER_SURFACE_SIZE:
//				changeSurfaceSize();
//				break;
//			}
//		}
//	};


//	private void changeSurfaceSize() {
//		// get screen size
//		int dw = Utils.getDisplaySize(getApplicationContext()).x;
//		int dh = Utils.getDisplaySize(getApplicationContext()).y;
//		Log.d(TAG, "dw:" + dw);
//		//Log.d(TAG, "dh:" + dh);
////		// calculate aspect ratio
////		double ar = (double) mVideoWidth / (double) mVideoHeight;
////		// calculate display aspect ratio
////		double dar = (double) dw / (double) dh;
////
////		switch (mCurrentSize) {
////		case SURFACE_BEST_FIT:
////			if (dar < ar)
////				dh = (int) (dw / ar);
////			else
////				dw = (int) (dh * ar);
////			break;
////		case SURFACE_FIT_HORIZONTAL:
////			dh = (int) (dw / ar);
////			break;
////		case SURFACE_FIT_VERTICAL:
////			dw = (int) (dh * ar);
////			break;
////		case SURFACE_FILL:
////			break;
////		case SURFACE_16_9:
////			ar = 16.0 / 9.0;
////			if (dar < ar)
////				dh = (int) (dw / ar);
////			else
////				dw = (int) (dh * ar);
////			break;
////		case SURFACE_4_3:
////			ar = 4.0 / 3.0;
////			if (dar < ar)
////				dh = (int) (dw / ar);
////			else
////				dw = (int) (dh * ar);
////			break;
////		case SURFACE_ORIGINAL:
////			dh = mVideoHeight;
////			dw = mVideoWidth;
////			break;
////		}
////		Log.d(TAG, "mVideoHeight:" + mVideoHeight);
////		Log.d(TAG, "(dh*3)/2:" + ((dh /3) * 2));
////		Log.d(TAG, "mVideoWidth:" + mVideoWidth);
//	//	mSurfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);
//
//		//if (w<h){
////			RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
////
////			lp.width = dw;
////			lp.height = dh;
////			//Log.d(TAG, "dh:" + dh);
////			linearLayout.setLayoutParams(lp);
////			linearLayout.invalidate();
//
//		//Log.d(TAG, "mediaPlayer22:" + mediaPlayer);
////
////		}else {
////
////			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
////			lp.gravity= Gravity.TOP;
////
////			lp.width = (dw/2)+dip2px(context,30);
////			lp.height = dh-dip2px(this,90);
////			lp.topMargin=dip2px(this,30);
////			lp.leftMargin=dip2px(this,30);
////			linearLayout.setLayoutParams(lp);
////			linearLayout.invalidate();
////		}
//
//		//mSurfaceView.setLayoutParams(lp);
//		//mSurfaceView.invalidate();
//		if (mediaPlayer != null) {
//			if (shiping_string!=null){
//				media = new Media(libvlc, Uri.parse(shiping_string));
//				mediaPlayer.setMedia(media);
//				Log.d(TAG, "ggggggggggggggggg"+shiping_string);
//				mediaPlayer.play();
//				mSurfaceView.setKeepScreenOn(true);
//			}
//
//		}



	//}
//	/**
//	 * websocket接口返回face.image
//	 * image为base64编码的字符串
//	 * 将字符串转为可以识别的图片
//	 * @param imgStr
//	 * @return
//	 */
//	public Bitmap generateImage(String imgStr, int cont, WBWeiShiBieDATABean dataBean, Context context) throws Exception {
//		// 对字节数组字符串进行Base64解码并生成图片
//		if (imgStr == null) // 图像数据为空
//			return null;
//		BASE64Decoder decoder = new BASE64Decoder();
//		try {
//			// Base64解码
//			final byte[][] b = {decoder.decodeBuffer(imgStr)};
//			for (int i = 0; i < b[0].length; ++i) {
//				if (b[0][i] < 0) {// 调整异常数据
//					b[0][i] += 256;
//				}
//			}
//			MoShengRenBean2 moShengRenBean2=new MoShengRenBean2();
//			moShengRenBean2.setId(dataBean.getTrack());
//			moShengRenBean2.setBytes(b[0]);
//			moShengRenBean2.setUrl("dd");
//
//			moShengRenBean2List.add(moShengRenBean2);
//
//				adapter.notifyDataSetChanged();
//
//
//
//
//
//			//   Bitmap bitmap= BitmapFactory.decodeByteArray(b[0],0, b[0].length);
//
//			//  Log.d("WebsocketPushMsg", "bitmap.getHeight():" + bitmap.getHeight());
//
//			// 生成jpeg图片
//			//  OutputStream out = new FileOutputStream(imgFilePath);
//			//   out.write(b);
//			//  out.flush();
//			//  out.close();
//
//
////			dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
////				@Override
////				public void onDismiss(DialogInterface dialog) {
////					Log.d("VlcVideoActivity", "Dialog销毁2");
////					b[0] =null;
////				}
////			});
//			//dialog.show();
//
//
//			return null;
//		} catch (Exception e) {
//			throw e;
//
//		}
//	}

	public  int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}
	public  int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5f);
	}
	/**
	 * 识别消息推送
	 * 主机盒子端API ws://[主机ip]:9000/video
	 * 通过 websocket 获取 识别结果
	 * @author Wangshutao
	 */
	private class WebsocketPushMsg {
		/** * 识别消息推送
		 * @param wsUrl websocket接口 例如 ws://192.168.1.50:9000/video
		 * @param rtspUrl 视频流地址 门禁管理-门禁设备-视频流地址
		 *                例如 rtsp://192.168.0.100/live1.sdp
		 *                或者 rtsp://admin:admin12345@192.168.1.64/live1.sdp
		 *                或者 rtsp://192.168.1.103/user=admin&password=&channel=1&stream=0.sdp
		 *                或者 rtsp://192.168.1.100/live1.sdp
		 *                       ?__exper_tuner=lingyun&__exper_tuner_username=admin
		 *                       &__exper_tuner_password=admin&__exper_mentor=motion
		 *                       &__exper_levels=312,1,625,1,1250,1,2500,1,5000,1,5000,2,10000,2,10000,4,10000,8,10000,10
		 *                       &__exper_initlevel=6
		 * @throws URISyntaxException
		 * @throws
		 * @throws
		 *
		 *  ://192.168.2.52/user=admin&password=&channel=1&stream=0.sdp
		 *
		 *   rtsp://192.166.2.55:554/user=admin_password=tljwpbo6_channel=1_stream=0.sdp?real_stream
		 */
		public void startConnection(String wsUrl, String rtspUrl) throws URISyntaxException {
			//当视频流地址中出现&符号时，需要进行进行url编码
			if (rtspUrl.contains("&")){
				try {
					//Log.d("WebsocketPushMsg", "dddddttttttttttttttt"+rtspUrl);
					rtspUrl = URLEncoder.encode(rtspUrl,"UTF-8");

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					//Log.d("WebsocketPushMsg", e.getMessage());
				}
			}

			URI uri = URI.create(wsUrl + "?url=" + rtspUrl);
		//	Log.d("WebsocketPushMsg", "url="+uri);
			  webSocketClient = new WebSocketClient(uri) {
			//	private Vector vector=new Vector();

				@Override
				public void onOpen(ServerHandshake serverHandshake) {
					//Log.d("WebsocketPushMsg onOpen", serverHandshake.getHttpStatusMessage());
						if (this.isOpen()){
							if (conntionHandler!=null && runnable!=null){
								conntionHandler.removeCallbacks(runnable);
								conntionHandler=null;
								runnable=null;
								//Log.d("WebsocketPushMsg", "终止runnable");
							}

						}
				}

				@Override
				public void onMessage(String ss) {

//					  Log.d("WebsocketPushMsg", "onMessage:"+ss);
//
//					     if(ss.length() > 3000) {
//                        for(int i=0;i<ss.length();i+=3000){
//                            if(i+3000<ss.length())
//                                Log.i("fffffffff"+i,ss.substring(i, i+3000));
//
//                            else
//                                Log.i("fffffffff"+i,ss.substring(i, ss.length()));
//                        }
//                    } else
//                        Log.i("ffffffffff", ss);


				//
					JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
					Gson gson=new Gson();
					WBBean wbBean= gson.fromJson(jsonObject, WBBean.class);

					//Log.d("WebsocketPushMsg", wbBean.getType());
					if (wbBean.getType().equals("recognized")) {


						//识别
						//Log.d("WebsocketPushMsg", "识别出了");

						//String s = ss.replace("\\\\\\", "").replace("\"tag\": \"{\"", "\"tag\": {\"").replace("jpg\"}\"", "jpg\"}");
					//	JsonObject jsonObject5 = GsonUtil.parse(ss).getAsJsonObject();

					//	JsonObject jsonObject1 = jsonObject.get("data").getAsJsonObject();
						//JsonObject jsonObject2 = jsonObject5.get("person").getAsJsonObject();
						//   JsonObject jsonObject3=jsonObject.get("screen").getAsJsonObject();
						final ShiBieBean dataBean = gson.fromJson(jsonObject, ShiBieBean.class);

							try {
						//Log.d("WebsocketPushMsg", dataBean.getPerson().getSrc()+"kkkk");

					//	final WBShiBiePersonBean personBean = gson.fromJson(jsonObject2, WBShiBiePersonBean.class);
						//Log.d("WebsocketPushMsg", "personBean.getSubject_type():" + personBean.getSubject_type());

//						if (dataBean.getPerson().getSubject_type() == 2) {
//
//							//Log.d("WebsocketPushMsg", personBean.getAvatar());
//							runOnUiThread(new Runnable() {
//								@Override
//								public void run() {
//
//									stringVector.add("欢迎VIP访客 "+dataBean.getPerson().getName()+" 来访！ 来访时间: "+DateUtils.getCurrentTime_Today());
//									Collections.reverse(stringVector);
//
//									delet();
//
//									runOnUiThread(new Runnable() {
//										@Override
//										public void run() {
//											marqueeView.stopFlipping();
//											marqueeView.startWithList(stringVector);
//
//										}
//									});
////									VipDialog dialog=new VipDialog(VlcVideoActivity.this,personBean.getAvatar(),R.style.dialog_style,personBean.getName());
////									Log.d("WebsocketPushMsg", "vip");
////									dialog.show();
//								}
//							});
//
//
//						}else {
								if (dataBean.getPerson().getSubject_type()==2 || dataBean.getPerson().getSubject_type()==1){
									mSpeechSynthesizer.speak("热烈欢迎"+dataBean.getPerson().getName()+"莅临参观指导");

								}

							MoShengRenBean bean = new MoShengRenBean(dataBean.getPerson().getId(), "sss");
//							ShiBieJiLuBean shiBieJiLuBean=new ShiBieJiLuBean();
//								shiBieJiLuBean.setId(dataBean.getPerson().getId());
//								shiBieJiLuBean.setName(dataBean.getPerson().getName());
//								shiBieJiLuBean.setTimes(DateUtils.times(System.currentTimeMillis()));
//								shiBieJiLuBean.setUrlPath(dataBean.getPerson().getAvatar());
								daoSession.insert(bean);


								//更新右边上下滚动列表
								//shiBieJiLuBeanDao.insert(shiBieJiLuBean);
//								yuangongList.add(shiBieJiLuBean);
//								Message message = Message.obtain();
//								message.what = 19;
//								handler.sendMessage(message);

//								if (vector2.size()>30){
//									vector2.clear();
//									vector2.add("欢迎 "+dataBean.getPerson().getName()+" 签到:"+DateUtils.getCurrentTime_Today());
//								}
//
//							vector2.add("欢迎 "+dataBean.getPerson().getName()+" 签到:"+DateUtils.getCurrentTime_Today());
//								Collections.reverse(vector2);


//								runOnUiThread(new Runnable() {
//									@Override
//									public void run() {
//										marqueeView2.stopFlipping();
//										marqueeView2.startWithList(vector2);
//
//									}
//								});


							//异步保存今天刷脸的人数

									Message message = new Message();
									message.arg1 = 1;
									message.obj = dataBean.getPerson();
									handler.sendMessage(message);



							}catch (Exception e){
								Log.d("WebsocketPushMsg", e.getMessage());
							}finally {
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								try {
									daoSession.deleteByKey(dataBean.getPerson().getId());
								//	Log.d("WebsocketPushMsg", "删除");
								}catch (Exception e){
									Log.d("WebsocketPushMsg", e.getMessage());
								}
							}

//							//	Log.d("WebsocketPushMsg", "第一次");
//						} else if (vector.size() == 2) {
//							WBShiBieDataBean b1 = (WBShiBieDataBean) vector.get(0);
//							WBShiBieDataBean b2 = (WBShiBieDataBean) vector.get(1);
//							//Log.d("WebsocketPushMsg", "b1.getTrack():" + b1.getTrack());
//							//Log.d("WebsocketPushMsg", "b2.getTrack():" + b2.getTrack());
//
//							if (b1.getTrack() != b2.getTrack()) {
//								//异步保存今天刷脸的人数
//								Type resultType = new TypeToken<Integer>() {
//								}.getType();
//								Reservoir.getAsync("shulianshu", resultType, new ReservoirGetCallback<Integer>() {
//									@Override
//									public void onSuccess(Integer i) {
//										//Log.d("WebsocketPushMsg", "i:" + i);
//										Message message = new Message();
//										message.arg1 = 1;
//										message.arg2 = i + 1;
//										message.obj = personBean;
//										handler.sendMessage(message);
//
//										Reservoir.putAsync("shulianshu", i + 1, new ReservoirPutCallback() {
//											@Override
//											public void onSuccess() {
//												//	Log.d("WebsocketPushMsg", "保存刷脸人数成功");
//
//											}
//
//											@Override
//											public void onFailure(Exception e) {
//												//Log.d("WebsocketPushMsg66666", e.getMessage());
//												//error
//											}
//										});
//									}
//
//									@Override
//									public void onFailure(Exception e) {
//										//Log.d("WebsocketPushMsg", e.getMessage());
//										Message message = new Message();
//										message.arg1 = 1;
//										message.arg2 = 1;
//										message.obj = personBean;
//										handler.sendMessage(message);
//										try {
//											Reservoir.put("shulianshu", 1);
//											//	Log.d("WebsocketPushMsg", "ffffffff");
//										} catch (IOException e1) {
//											//	Log.d("WebsocketPushMsg", e1.getMessage());
//
//										}
//
//									}
//								});
//
//								vector.remove(0);
//								//	Log.d("WebsocketPushMsg", "第二次");
//							} else {
//
//								vector.remove(0);
//								//Log.d("WebsocketPushMsg", "删除");
//							}
//
//						}
				//	}

						//   WBShiBieScreenBean screenBean=gson.fromJson(jsonObject3,WBShiBieScreenBean.class);
//                    try {
//
//                        generateImage(dataBean.getFace().getImage(), Environment.getExternalStorageDirectory().getPath()+
//                                File.separator+"aaaa.jpg");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


					}else if (wbBean.getType().equals("unrecognized")) {

//						if (vector2.size()>30){
//							vector2.clear();
//							vector2.add("欢迎领导签到:"+DateUtils.getCurrentTime_Today());
//						}
//
//						vector2.add("欢迎领导签到:"+DateUtils.getCurrentTime_Today());
//						Collections.reverse(vector2);


//						runOnUiThread(new Runnable() {
//							@Override
//							public void run() {
//								marqueeView2.stopFlipping();
//								marqueeView2.startWithList(vector2);
//
//							}
//						});

					//	String s= ss.replace("\\","").replace("\"tag\": \"{\"","\"tag\": {\"").replace("jpg\"}\"","jpg\"}");
					//	JsonObject jsonObject5= GsonUtil.parse(s).getAsJsonObject();
					//	JsonObject jsonObject1 = jsonObject.get("data").getAsJsonObject();
						//  JsonObject jsonObject3=jsonObject.get("screen").getAsJsonObject();

					//	final WeiShiBieBean dataBean = gson.fromJson(jsonObject1, WeiShiBieBean.class);
						//Log.d("WebsocketPushMsg", "dataBean.getAttr().getAge():" + dataBean.getAttr().getAge());
						//Log.d("WebsocketPushMsg", dataBean.getFace().getImage());
						//Log.d("WebsocketPushMsg", "识别陌生人3333");
//						vector2.addElement(dataBean);
						//Log.d("WebsocketPushMsg", "dataBean:" + dataBean.toString());
						//Log.d("WebsocketPushMsg", "未识别"+dataBean.getAttr().getAge());
//
//						Log.d("WebsocketPushMsg", "vector2.size():" + vector2.size());
//						if (vector2.size()==1){
//
//							Message message=new Message();
//							message.arg1=2;
//							message.obj=dataBean;
//							handler.sendMessage(message);
//							Log.d("WebsocketPushMsg", "未识别的第一次");
//
//						}
//						if (vector2.size()==2){
//							Log.d("WebsocketPushMsg", "vector2.size()222:" + vector2.size());
//							WBWeiShiBieDATABean b1=(WBWeiShiBieDATABean)vector2.get(0);
//							WBWeiShiBieDATABean b2=(WBWeiShiBieDATABean)vector2.get(1);
//							Log.d("WebsocketPushMsg", "b1.getTrack():" + b1.getTrack()+"    "+b1.getPerson().getId()+"   "+b1.getPerson().getTag().getAvatar());
//							Log.d("WebsocketPushMsg", "b2.getTrack():" + b2.getTrack()+"     "+b2.getPerson().getId()+"   "+b2.getPerson().getTag().getAvatar());
//
//							if (b1.getTrack()!=b2.getTrack()){
//								if (b1.getPerson().getId().equals(b2.getPerson().getId())){


					//	Log.d("WebsocketPushMsg", "识别陌生人");
//						try {
//
//						MoShengRenBean bean = new MoShengRenBean(dataBean.getTrack(), "sss");
//
//						daoSession.insert(bean);

//							stringVector.add("欢迎你参观！  "+DateUtils.getCurrentTime_Today());
//							Collections.reverse(stringVector);
//
//							delet();

//							runOnUiThread(new Runnable() {
//								@Override
//								public void run() {
//									marqueeView.stopFlipping();
//									marqueeView.startWithList(stringVector);
//
//								}
//							});


							//异步保存今天刷脸的人数
//							Type resultType = new TypeToken<Integer>() {
//							}.getType();
//							Reservoir.getAsync("shulianshu", resultType, new ReservoirGetCallback<Integer>() {
//								@Override
//								public void onSuccess(Integer i) {
//									//Log.d("WebsocketPushMsg", "i:" + i);
//									Message message = new Message();
//									message.arg1 = 2;
//									message.arg2 = i + 1;
//									message.obj=dataBean;
//									handler.sendMessage(message);
//
//									Reservoir.putAsync("shulianshu", i + 1, new ReservoirPutCallback() {
//										@Override
//										public void onSuccess() {
//											//	Log.d("WebsocketPushMsg", "保存刷脸人数成功");
//
//										}
//
//										@Override
//										public void onFailure(Exception e) {
//											Log.d("WebsocketPushMsg66666", e.getMessage());
//											//error
//										}
//									});
//								}
//
//								@Override
//								public void onFailure(Exception e) {
//									//Log.d("WebsocketPushMsg", e.getMessage());
//									Message message = new Message();
//									message.arg1 = 2;
//									message.arg2 = 1;
//									message.obj=dataBean;
//									handler.sendMessage(message);
//									try {
//										Reservoir.put("shulianshu", 1);
//										//	Log.d("WebsocketPushMsg", "ffffffff");
//									} catch (IOException e1) {
//										//	Log.d("WebsocketPushMsg", e1.getMessage());
//
//									}
//
//								}
//							});
//
//						//	Log.d("WebsocketPushMsg", "ddddoooooo");
//
//							//Double d= dataBean.getAttr().getAge();
//							//if (d==0){
//								//创建用户\
//							//	creatUser(b[0],dataBean.getTrack(),"32");
//						//	}else {
//								//creatUser(b[0],dataBean.getTrack(),"");
//							//}
//
//
//						} catch (Exception e) {
//							Log.d("WebsocketPushMsg", e.getMessage());
//							//e.printStackTrace();
//						}finally {
//							try {
//								Thread.sleep(400);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//							try {
//								daoSession.deleteByKey(dataBean.getTrack());
//								//Log.d("WebsocketPushMsg", "删除");
//							}catch (Exception e){
//								Log.d("WebsocketPushMsg", e.getMessage());
//							}
//						}
					}
				}

				@Override
				public void onClose(int i, String s, boolean b) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							TastyToast.makeText(VlcVideoActivity.this,"连接出现异常,10秒后重新连接", Toast.LENGTH_LONG,TastyToast.ERROR).show();
						}
					});
					//Log.d("WebsocketPushMsg", "onClose " + s + " " + i + " " + b);

					if (conntionHandler==null && runnable==null){
						Looper.prepare();
						conntionHandler=new Handler();
						runnable=new Runnable() {
							@Override
							public void run() {
								Intent intent=new Intent("duanxianchonglian");
								sendBroadcast(intent);
							}
						};
						conntionHandler.postDelayed(runnable, 13000);
						Looper.loop();
					}

					//Intent bindIntent = new Intent(VlcVideoActivity.this, MyService.class);
					//bindService(bindIntent, connection, BIND_AUTO_CREATE);

				}

				@Override
				public void onError(Exception e) {
					Log.d("WebsocketPushMsg", "onError"+e.getMessage());

				}
			};

			webSocketClient.connect();
		}


	}

//	private void delet(){
//		if (stringVector.size()>=10){
//			stringVector.remove(stringVector.firstElement());
//		}else {
//			return;
//		}
//		delet();
//	}

//	private void creatUser(byte[] bytes, Long tt, String age) {
//		//Log.d("WebsocketPushMsg", "创建用户");
//		String fileName="tong"+System.currentTimeMillis()+".jpg";
//		//通过bytes数组创建图片文件
//		createFileWithByte(bytes,fileName,tt,age);
//		//上传
//	//	addPhoto(fileName);
//	}

	/**
	 * 根据byte数组生成文件
	 *
	 * @param
	 *
	 * @param
	 */
//	private void createFileWithByte(byte[] bytes, String filename, Long tt, String age) {
//
//		/**
//		 * 创建File对象，其中包含文件所在的目录以及文件的命名
//		 */
//		File file=null;
//		String	sdDir = this.getFilesDir().getAbsolutePath();//获取跟目录
//		makeRootDirectory(sdDir);
//
//		// 创建FileOutputStream对象
//		FileOutputStream outputStream = null;
//		// 创建BufferedOutputStream对象
//		BufferedOutputStream bufferedOutputStream = null;
//
//		try {
//			file = new File(sdDir +File.separator+ filename);
//			// 在文件系统中根据路径创建一个新的空文件
//		//	file2.createNewFile();
//		//	Log.d(TAG, file.createNewFile()+"");
//
//			// 获取FileOutputStream对象
//			outputStream = new FileOutputStream(file);
//			// 获取BufferedOutputStream对象
//			bufferedOutputStream = new BufferedOutputStream(outputStream);
//			// 往文件所在的缓冲输出流中写byte数据
//			bufferedOutputStream.write(bytes);
//			// 刷出缓冲输出流，该步很关键，要是不执行flush()方法，那么文件的内容是空的。
//			bufferedOutputStream.flush();
//			//上传文件
//			addPhoto(sdDir,filename,bytes,tt,age);
//
//		} catch (Exception e) {
//			// 打印异常信息
//			//Log.d(TAG, "ssssssssssssssssss"+e.getMessage());
//		} finally {
//			// 关闭创建的流对象
//			if (outputStream != null) {
//				try {
//					outputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (bufferedOutputStream != null) {
//				try {
//					bufferedOutputStream.close();
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			}
//		}
//	}

	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {

		}
	}

//	private void addPhoto(final String path, final String fname, final byte[] b, final Long truck, final String age){
//
//		if (zhuji_string!=null){
//			String[] a=zhuji_string.split("//");
//			String[] b1=a[1].split(":");
//			zhuji="http://"+b1[0];
//		}
//
//		OkHttpClient okHttpClient= MyApplication.getOkHttpClient();
//
//         /* 第一个要上传的file */
//		File file1 = new File(path,fname);
//		RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
//		final String file1Name = System.currentTimeMillis()+"testFile.jpg";
//
////    /* 第二个要上传的文件,这里偷懒了,和file1用的一个图片 */
////        File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/a.jpg");
////        RequestBody fileBody2 = RequestBody.create(MediaType.parse("application/octet-stream") , file2);
////        String file2Name = "testFile2.txt";
//
//
////    /* form的分割线,自己定义 */
////        String boundary = "xx--------------------------------------------------------------xx";
//
//		MultipartBody mBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//            /* 底下是上传了两个文件 */
//				.addFormDataPart("photo" , file1Name , fileBody1)
//                  /* 上传一个普通的String参数 */
//				//  .addFormDataPart("subject_id" , subject_id+"")
////                .addFormDataPart("file" , file2Name , fileBody2)
//				.build();
//		Request.Builder requestBuilder = new Request.Builder()
//				// .header("Content-Type", "application/json")
//				.post(mBody)
//				.url(zhuji2+"/subject/photo");
//		//    .url(HOST+"/mobile-admin/subjects");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//		//step 4: 开始异步请求
//		final String finalZhuji = zhuji;
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(final Call call, final IOException e) {
//			//	Log.d("AllConnects", "照片上传失败"+e.getMessage());
//
//			}
//
//			@Override
//			public void onResponse(final Call call, Response response) throws IOException {
//				Log.d("AllConnects", "照片上传成功"+call.request().toString());
//
//				try{
//
//				//获得返回体
//				ResponseBody body = response.body();
//				// Log.d("WebsocketPushMsg", "aa   "+response.body().string());
//				JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//				Gson gson=new Gson();
//				int code=jsonObject.get("code").getAsInt();
//				if (code==0){
//					JsonObject array=jsonObject.get("data").getAsJsonObject();
//					TuPianBean zhaoPianBean=gson.fromJson(array,TuPianBean.class);
//					//创建用户
//				//	Log.d("VlcVideoActivity", "zhaoPianBean.getId():" + zhaoPianBean.getId());
//					link(finalZhuji,"a",zhaoPianBean.getId()+"",b,age);
//
//				}else {
//
//					runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							TastyToast.makeText(getApplicationContext(),
//									"图片不够清晰，请再来一张", TastyToast.LENGTH_LONG, TastyToast.ERROR);
//						}
//					});
//
//				}
//				//删除照片
//				Log.d("VlcVideoActivity", "删除照片:" + VlcVideoActivity.this.deleteFile(fname));
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//			}
//
//
//		});
//
//
//		}


//	private void link(String zhuji, String name, String id, final byte[] b, final String age){
//
//		final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//
//		OkHttpClient okHttpClient= MyApplication.getOkHttpClient();
//
//		List<Long> longs=new ArrayList<>();
//		longs.add(Long.valueOf(id));
//		ChuanJianUserBean bean=new ChuanJianUserBean();
//		bean.setName(name);
//		bean.setPhoto_ids(longs);
//		bean.setSubject_type("0");
//
//		String json = new Gson ().toJson(bean);
//		RequestBody requestBody = RequestBody.create(JSON, json);
//
//
////		RequestBody body = new FormBody.Builder()
////				.add("name",name)
////				.add("subject_type",0+"")
////				.add("photo_ids","["+id+"]")
////				.build();
//		Request.Builder requestBuilder = new Request.Builder()
//				.header("Content-Type", "application/json")
//				.post(requestBody)
//				.url(zhuji2+"/subject");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("AllConnects", "请求失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				//Log.d("AllConnects", "请求成功"+call.request().toString());
//				//获得返回体
//				try{
//
//				ResponseBody body = response.body();
//				//  Log.d("AllConnects", "aa   "+response.body().string());
//
//				JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//				Gson gson=new Gson();
//				int code=jsonObject.get("code").getAsInt();
//				if (code==0){
//					JsonObject array=jsonObject.get("data").getAsJsonObject();
//					User zhaoPianBean=gson.fromJson(array,User.class);
//					link_houtai(zhaoPianBean);
//					final MoShengRenBean2 moShengRenBean2 = new MoShengRenBean2();
//					moShengRenBean2.setId(zhaoPianBean.getId());
//					moShengRenBean2.setAge(age);
//					moShengRenBean2.setBytes(b);
//				//	moShengRenBean2.setUrl("http://192.168.2.7:8080/sign?cmd=signScan&subjectId="+zhaoPianBean.getId());
//
//
//
//				}
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//
//			}
//		});
//
//
//	}

//	private void link_houtai(User zhaoPianBean) {
//		//final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//		//http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
//		OkHttpClient okHttpClient= MyApplication.getOkHttpClient();
//
////		List<Long> longs=new ArrayList<>();
////		longs.add(Long.valueOf(id));
////		ChuanJianUserBean bean=new ChuanJianUserBean();
////		bean.setName(name);
////		bean.setPhoto_ids(longs);
////		bean.setSubject_type("0");
////
////		String json = new Gson ().toJson(bean);
////		RequestBody requestBody = RequestBody.create(JSON, json);
//	//	http://192.168.2.4:8080/sign?cmd=addSign&subjectId=jfgsdf
//		//Log.d(TAG, zhaoPianBean.getPhotos().get(0).getUrl()+"ggggggggggggggggggggggggggggg");
//		RequestBody body = new FormBody.Builder()
//				.add("cmd","addSign")
//				.add("subjectId",zhaoPianBean.getId()+"")
//				.add("subjectPhoto",zhaoPianBean.getPhotos().get(0).getUrl())
//				.build();
//		Request.Builder requestBuilder = new Request.Builder()
//				.header("Content-Type", "application/json")
//				.post(body)
//				.url("http://192.168.2.17:8080/sign");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("AllConnects", "请求添加陌生人失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				Log.d("AllConnects", "请求添加陌生人成功"+call.request().toString());
//				//获得返回体
//				try {
//
//				ResponseBody body = response.body();
//			//	Log.d("AllConnects", "aa   "+response.body().string());
//
//				JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//				Gson gson=new Gson();
//				int code=jsonObject.get("resultCode").getAsInt();
//				if (code==0){
////					JsonObject array=jsonObject.get("data").getAsJsonObject();
////					User zhaoPianBean=gson.fromJson(array,User.class);
////					link_houtai(zhaoPianBean);
//					//link_gengxing_erweima();
//				}
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//			}
//		});
//
//
//		}



//	//得到View的bitmap
//	public  Bitmap loadBitmapFromView(View v) {
//		if (v == null) {
//			return null;
//		}
//
//		Bitmap screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
//
//		Canvas canvas = new Canvas(screenshot);
//
//		v.draw(canvas);// 将 view 画到画布上
//	//	Log.d(TAG, Environment.getDataDirectory().getPath() + "/" + System.currentTimeMillis() + "aaa.jpg");
//		//保存截屏图片
//		File file = new File(Environment.getExternalStorageDirectory()	  +"/"+ System.currentTimeMillis() + "aaa.jpg");
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream(file);
//			screenshot.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//			fos.close();
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return screenshot;
//
//	}


//	public static final int TIMEOUT = 1000 * 60;
//	private void link_chengshi() {
//		//final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//		//http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
//		OkHttpClient okHttpClient= new OkHttpClient.Builder()
//				.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.retryOnConnectionFailure(true)
//				.build();
//
////		RequestBody body = new FormBody.Builder()
////				.add("cityCode","101040100")
////				.add("weatherType","1")
////				.build();
//
//		Request.Builder requestBuilder = new Request.Builder()
//				//.header("Content-Type", "application/json")
//				.get()
//				.url("http://api.map.baidu.com/location/ip?ak=OkLLk9ojkdcEsUEWTpc2MVoY6DDSptik" +
//						"&mcode=7D:2D:D4:76:15:CE:C5:44:3D:25:01:FF:4C:0A:96:3C:92:4B:0E:FD;com.example.xiaojun.lingdaotanchuang");
//		//.url("http://wthrcdn.etouch.cn/weather_mini?city=广州市");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("AllConnects", "请求添加陌生人失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				Log.d("AllConnects", "请求添加陌生人成功"+call.request().toString());
//				//获得返回体
//				try {
//
//					ResponseBody body = response.body();
//				//	Log.d("AllConnects", "aa   "+response.body().string());
//					String ss=body.string();
//					Log.d("VlcVideoActivity", ss);
//					JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
//					Gson gson=new Gson();
//				//	JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
//
//					IpAddress zhaoPianBean=gson.fromJson(jsonObject,IpAddress.class);
//
//
//					/**从assets中读取txt
//					 * 按行读取txt
//					 * @param
//					 * @return
//					 * @throws Exception
//					 */
//
//						InputStream is = getAssets().open("tianqi.txt");
//						InputStreamReader reader = new InputStreamReader(is);
//						BufferedReader bufferedReader = new BufferedReader(reader);
//						//StringBuffer buffer = new StringBuffer("");
//						String str;
//						String aa=zhaoPianBean.getContent().getAddress_detail().getCity();
//						if (aa.length()>2){
//							aa=aa.substring(0,2);
//						//	Log.d("VlcVideoActivity", "fffff9"+aa);
//						}
//						while ((str = bufferedReader.readLine()) != null) {
//
//
//							if (str.contains(aa)){
//								//Log.d("VlcVideoActivity", "fffff3"+str);
//								link_tianqi(str);
//								break;
//							}
//						}
//
//
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//			}
//		});
//
//	}

//	private void link_tianqi(String bean) {
//		//final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//		//http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
//		OkHttpClient okHttpClient= new OkHttpClient.Builder()
//				.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.retryOnConnectionFailure(true)
//				.build();
////		RequestBody body = new FormBody.Builder()
////				.add("cityCode","101040100")
////				.add("weatherType","1")
////				.build();
//
//		Request.Builder requestBuilder = new Request.Builder()
//				//.header("Content-Type", "application/json")
//				.get()
//				//.url("https://api.map.baidu.com/location/ip?ak=uTTmEt0NeHSsgAKsXGLAMC8mvg6zPNLm" +
//					//	"&mcode=21:21:DA:F2:00:51:3B:AB:C4:E6:19:18:31:C6:98:CA:D6:7B:44:AE;com.example.xiaojun.lingdaotanchuang");
//
//				.url("http://wthrcdn.etouch.cn/weather_mini?citykey=" + bean.substring(bean.length() - 9, bean.length()));
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("AllConnects", "请求添加陌生人失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				Log.d("AllConnects", "请求天气成功"+call.request().toString());
//				//获得返回体
//				try {
//
//					ResponseBody body = response.body();
//					//Log.d("AllConnects", "aa   "+response.body().string());
//					String ss=body.string();
//					Log.d("VlcVideoActivity", ss);
//					JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
//					Gson gson=new Gson();
//					//JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
//
//					final TianQiBean zhaoPianBean=gson.fromJson(jsonObject,TianQiBean.class);
//					runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							tianqi.setText(zhaoPianBean.getData().getCity());
//							wendu.setText(zhaoPianBean.getData().getWendu()+" 度");
//							//tianqi2.setText(zhaoPianBean.getData().getGanmao());
//						}
//					});
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//			}
//		});
//
//	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if( KeyEvent.KEYCODE_MENU == keyCode ){  //如果按下的是菜单
			Log.d(TAG, "按下菜单键");
			CaiDanDialog danDialog=new CaiDanDialog(VlcVideoActivity.this,R.style.dialog_ALL,shiPingBeanDao,null,null);
			danDialog.show();
		}


		return super.onKeyDown(keyCode, event);


	}


//	private class DownloadReceiver extends ResultReceiver {
//		public DownloadReceiver(Handler handler) {
//			super(handler);
//		}
//		@Override
//		protected void onReceiveResult(int resultCode, Bundle resultData) {
//			super.onReceiveResult(resultCode, resultData);
//			if (resultCode == DownloadService.UPDATE_PROGRESS) {
//				String ididid=resultData.getString("ididid2");
//				int progress = resultData.getInt("progress");
//
//				if (progress == 100) {
//					try {
//
//					//下载完成
//					//更新状态
//					Log.d(TAG, "ididididididd值："+ididid);
//					if (ididid!=null) {
//						ShiPingBean b = shiPingBeanDao.load(ididid);
//						b.setIsDownload(true);
//						shiPingBeanDao.update(b);
//
//						if (shiPingBeanList.size() > 0) {
//							shiPingBeanList.clear();
//						}
//						shiPingBeanList = shiPingBeanDao.loadAll();
//
//						ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory() + File.separator + "linhefile" + File.separator + b.getId() + "." + b.getVideoType().split("\\/")[1]);
//						ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//							@Override
//							public void onPrepared(IMediaPlayer iMediaPlayer) {
//								ijkVideoView.start();
//							}
//						});
//					}else {
//						Log.d(TAG, "id的值是空");
//					}
//
//					}catch (Exception e){
//						Log.d(TAG, "捕捉到异常onReceiveResult"+e.getMessage());
//					}
//
//					//ijkVideoView.setVideoPath(mFile.getPath());
//					//ijkVideoView.start();
////					Intent install = new Intent();
////					install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////					install.setAction(android.content.Intent.ACTION_VIEW);
////					install.setDataAndType(Uri.fromFile(mFile),"application/vnd.android.package-archive");
////					startActivity(install);  //下载完成打开APK
//				}
//			}
//		}
//	}

//    private class DownloadReceiverTuPian extends ResultReceiver {
//        public DownloadReceiverTuPian(Handler handler) {
//            super(handler);
//        }
//        @Override
//        protected void onReceiveResult(int resultCode, Bundle resultData) {
//            super.onReceiveResult(resultCode, resultData);
//            if (resultCode == DownloadTuPianService.UPDATE_PROGRESS2) {
//                int progress = resultData.getInt("progress");
//
//                if (progress == 100) {
//                    try {
//                        //下载完成
//                       // Environment.getExternalStorageDirectory()+ File.separator+"linhefile"+File.separator+"tupian111.jpg"
//                        Log.d(TAG, "图片下载完成");
//
//                    }catch (Exception e){
//                        Log.d(TAG, "捕捉到异常onReceiveResult"+e.getMessage());
//                    }
//
//                }
//            }
//        }
//    }
}
