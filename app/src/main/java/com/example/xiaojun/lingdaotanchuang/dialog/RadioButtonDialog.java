package com.example.xiaojun.lingdaotanchuang.dialog;//package com.example.xiaojun.lingdaotanchuang.dialog;
//
///**
// * Created by chenjun on 2017/4/5.
// */
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Point;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.widget.RecyclerView;
//import android.util.DisplayMetrics;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import com.bumptech.glide.Glide;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.example.xiaojun.lingdaotanchuang.MyApplication;
//import com.example.xiaojun.lingdaotanchuang.R;
//import com.example.xiaojun.lingdaotanchuang.beans.YongHuBean;
//import com.example.xiaojun.lingdaotanchuang.utils.GlideCircleTransform;
//import com.example.xiaojun.lingdaotanchuang.utils.Utils;
//import com.github.florent37.viewanimator.AnimationListener;
//import com.github.florent37.viewanimator.ViewAnimator;
//
//import java.util.List;
//
//
//public class RadioButtonDialog extends Dialog {
//    private boolean flag = true;
//    private Context context;
//    private String title,name;     //这里定义个title，一会可以看到是指向上面xml文件的控件title的，也就是我们可以通过这个进行动态修改title
//    private AdapterView.OnItemClickListener   onItemClickListener;      //这里定义了一个监听是为了实现内部的监听接口处理，从而实现代码分层管理
//    //可以看到两个构造器，想自定义样式的就用第二个啦
//    private String path=null;
//   // private int renshu=1;
//    private RecyclerView recyclerView;
//  //  private TextView textView,t1;
//    private byte[] bitmap=null;
//  //  private MyView myView;
//    private int w,h;
//   // private List<LineArraysBean> beanList;
//   // private DisplayUtil displayUtil;
//    private String age;
//    private int type;
//    private View view;
//    private String zhuji;
//
//    public RadioButtonDialog(Activity context, String age, String title, String name, byte[] bitmap
//            ,int theme,int type,String zhuji,String path) {
//        super(context,theme);
//        this.context = context;
//        this.path=path;
//        this.name=name;
//        this.age=age;
//        this.type=type;
//        this.title=title;
//        this.bitmap=bitmap;
//        this.zhuji=zhuji;
//      //  renshu=cont;
//        DisplayMetrics dm =context.getResources().getDisplayMetrics();
//        w = dm.widthPixels;
//        h = dm.heightPixels;
//       // beanList =new ArrayList<>();
//       /// displayUtil =new DisplayUtil();
//        init(title);
//    }
//
//    public RadioButtonDialog(Context context ) {
//        super(context);
//        this.context = context;
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
////    public void setTitle(String title) {
////       name2.setText("你是今天第1位刷脸用户");
////    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//        if (onItemClickListener != null)
//            this.onItemClickListener = onItemClickListener;
//    }
//    //控件的声明
//
//   // private TextView textView,name2;
//
//    private void init(String title) {
//        //以view的方式引入，然后回调activity方法，setContentView，实现自定义布局
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_man, null);
//        setContentView(view);
////        //view加载完成时回调
////        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
////            @Override
////            public void onGlobalLayout() {
////
////
////
////
////            }
////        });
//        this.view=view;
//        //radiobutton的初始化
//        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerView);
//
//
//
//        // head= (RelativeLayout)view. findViewById(R.id.ddd);
//       // textView= (TextView)view.findViewById(R.id.ddff);
//       // name2= (TextView) view.findViewById(R.id.name);
//     //   textView.setText("欢迎进入3s展厅");
//       // name2.setText("你是今天第"+renshu+"位刷脸用户");
//       // myView= (MyView) view.findViewById(R.id.image_head);
////        ViewTreeObserver vto = myView.getViewTreeObserver();
////
////
////        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
////            @Override
////            public void onGlobalLayout() {
////                myView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
////                // Log.d("MainActivity", "myView.getHeight():" + myView.getHeight());
////                // Log.d("MainActivity", "myView.getWidth():" + myView.getWidth());
////                float w = myView.getWidth();
////                float h = myView.getHeight();
////                int i= (int) (w/2+displayUtil.dip2px(context,40));
////                int i2= (int) (w/2+displayUtil.dip2px(context,80));
////                int i3= (int) (h/2-displayUtil.dip2px(context,30));
////
////
//////
//////                for (; i < i2; i+=2) { //x坐标
//////                    // y坐标
//////                    float a = i3--;
//////                    LineArraysBean bean = new LineArraysBean(i, a);
////////                    Log.d("RadioButtonDialog", "i:" + i);
////////                    Log.d("RadioButtonDialog", "a:" + a);
//////
//////                    beanList.add(bean);
//////
//////                }
////
//////                new Thread(new Runnable() {
//////                    @Override
//////                    public void run() {
//////
//////                        for (int i=0;i<beanList.size();i++){
//////                            myView.setLines(beanList.get(i));
//////
//////                            myView.postInvalidate();
//////
//////                            // mHandler.sendMessage(message);
//////
//////                            try {
//////                                Thread.sleep(10);
//////                            } catch (InterruptedException e) {
//////                                e.printStackTrace();
//////                            }
//////                        }
//////
//////
//////                    }
//////
//////
//////                }).start();
////            }
////
////        });
//     //   myView.setPanmse(age,type);
//     //   myView.setBitmap(bitmap);
//      //  myView.invalidate();
//
//
//       // myView.requestLayout();
//
//     //    Log.d("RadioButtonDialog", "bitmap:" + bitmap);
////        if (bitmap!=null) {
////           // textView.setText("识别未通过");
////            Glide.with(MyApplication.getAppContext())
////                    .load(bitmap)
//////                    .bitmapTransform(new GlideCircleTransform(MyApplication.getAppContext()))
////                    .into(im1);
////
////                }
////        if (type!=2 && path!=null){
////            textView.setText("认证通过");
////            Glide.with(MyApplication.getAppContext())
////                    .load(zhuji + path)
////                   // .bitmapTransform(new GlideCircleTransform(MyApplication.getAppContext()))
////                    .into(im2);
////            left_im.setImageResource(R.drawable.yixuanze_22);
////            ViewAnimator
////                    .animate(left_im)
////                    .bounce()
////                    .start();
////        }else {
////            textView.setText("未通过认证");
////            im3.setVisibility(View.GONE);
////            im2.setVisibility(View.GONE);
////            t1.setVisibility(View.VISIBLE);
////            left_im.setImageResource(R.drawable.chacha);
////            ViewAnimator
////                    .animate(left_im)
////                    .tada()
////                    .start();
//     //   }
//
//
//            //  groupBroadcast.setOnCheckedChangeListener(listener);
//            //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
//            Window dialogWindow = this.getWindow();
//            WindowManager manager = ((Activity) context).getWindowManager();
//            assert dialogWindow != null;
//            WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//            dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
////            if (w < h) {
////                dialogWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);//设置对话框位置
////                Point p = Utils.getDisplaySize(context); // 获取屏幕宽、高度
////
////                params.width = (p.x * 4) / 5; // 宽度设置为屏幕的0.65，根据实际情况调整
////                params.height = (p.y) / 3;
////                // params.horizontalMargin=60;
////                params.y = 100;
////
////            } else {
//                dialogWindow.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);//设置对话框位置
//                Point p = Utils.getDisplaySize(context); // 获取屏幕宽、高度
//
//                params.width = (p.x ); // 宽度设置为屏幕的0.65，根据实际情况调整
//                params.height = ((p.y)) / 3 ;
//
//                params.y = dip2px(context,180);
//
//          //  }
//
//
//            dialogWindow.setAttributes(params);
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (flag) {
//                        try {
////                        Message msg2 = mHandler.obtainMessage();
////                        msg2.what = 99;
////                        mHandler.sendMessage(msg2);
//
//                            Thread.sleep(8000);
//                            Message msg = mHandler.obtainMessage();
//                            msg.what = 88;
//                            mHandler.sendMessage(msg);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }
//            }).start();
//
//
//   }
////    public MyView getMyView(){
////        return myView;
////    }
//
//    public View getView(){
//        return view;
//    }
//
//    //监听接口
//    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//
//        }
//    };
//    public  int dip2px(Context context, float dipValue){
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int)(dipValue * scale + 0.5f);
//    }
//    public  int px2dip(Context context, float pxValue){
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int)(pxValue / scale + 0.5f);
//    }
//
//    @Override
//    public void dismiss() {
//        flag = false;
//        mHandler.removeCallbacksAndMessages(null);
//        if (bitmap!=null)
//            bitmap=null;
////        if (myView!=null)
////            myView=null;
//        System.gc();
//        super.dismiss();
//
//    }
//
//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            if(msg.what == 88){
//                ViewAnimator
//                        .animate(getView())
//                        .scale(1,0)
//                        .onStop(new AnimationListener.Stop() {
//                            @Override
//                            public void onStop() {
//
//                                dismiss();
//                            }
//                        })
//                        .alpha(1,0)
//                        .duration(1000)
//                        .start();
//
//
//
////                if (title.equals("陌生人")){
////                    Intent intent=new Intent("dialogxiaoshi");
////                    context.sendBroadcast(intent);
////
////                }
//            }
//        }
//
//    };
//
//    public class DisplayUtil {
//        public  int dip2px(Context context, float dipValue){
//            final float scale = context.getResources().getDisplayMetrics().density;
//            return (int)(dipValue * scale + 0.5f);
//        }
//        public  int px2dip(Context context, float pxValue){
//            final float scale = context.getResources().getDisplayMetrics().density;
//            return (int)(pxValue / scale + 0.5f);
//        }
//
////        public void getPM(){
////            DisplayMetrics dm =getResources().getDisplayMetrics();
////            int w_screen = dm.widthPixels;
////            int h_screen = dm.heightPixels;
////            Log.d("ddd","屏幕尺寸2：宽度 = " + w_screen + "高度 = " + h_screen + "密度 = "+ dm.densityDpi);
////        }
//
//    }
//
//    private   class MyAdapter extends BaseQuickAdapter<YongHuBean,BaseViewHolder> {
//
//        public MyAdapter(int layoutResId, List<YongHuBean> data) {
//            super(layoutResId, data);
//
//        }
//
//
//        @Override
//        protected void convert(BaseViewHolder helper, YongHuBean item) {
//
//
//            //二维码中包含的文本信息
//            //	String contents= ;
////			try {
////				//调用方法createCode生成二维码
////				//Bitmap bm=createCode("http://zh.ct118114.cn/sign?cmd=subjectScan&subjectId="+item.getSubjectId(),logo, BarcodeFormat.QR_CODE);
////				//将二维码在界面中显示
////			//	ImageView imageView=helper.getView(R.id.erweimaim2);
////			//	imageView.setImageBitmap(bm);
////			} catch (WriterException e) {
////				e.printStackTrace();
////			}
////			if (zhuji==null){
////				zhuji="192.168.2.50";
////			}
//            Glide.with(MyApplication.getAppContext())
//                    .load(zhuji+item.getSubjectPhoto())
//                    .bitmapTransform(new GlideCircleTransform(MyApplication.getAppContext()))
//                    .into((ImageView) helper.getView(R.id.erweimaim));
//
//            helper.itemView.setFocusable(true);
//            if (helper.getLayoutPosition()==0){
//                helper.itemView.requestFocus();
//            }
//        }
//    }
//
//}
//
