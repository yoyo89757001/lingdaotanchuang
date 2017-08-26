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
//
//import com.bumptech.glide.Glide;
//import com.example.xiaojun.lingdaotanchuang.MyApplication;
//import com.example.xiaojun.lingdaotanchuang.R;
//import com.example.xiaojun.lingdaotanchuang.utils.GlideCircleTransform;
//import com.example.xiaojun.lingdaotanchuang.utils.Utils;
//
//
//
//public class VipDialog extends Dialog {
//    private boolean flag = true;
//    private Context context;
//    private String title,name;     //这里定义个title，一会可以看到是指向上面xml文件的控件title的，也就是我们可以通过这个进行动态修改title
//    private AdapterView.OnItemClickListener   onItemClickListener;      //这里定义了一个监听是为了实现内部的监听接口处理，从而实现代码分层管理
//    //可以看到两个构造器，想自定义样式的就用第二个啦
//
//    private int w,h;
//    private String dizhi;
//
//    public VipDialog(Activity context, String dizhi, int theme,String name) {
//        super(context,theme);
//        this.context = context;
//        this.name=name;
//        DisplayMetrics dm =context.getResources().getDisplayMetrics();
//        this.dizhi=dizhi;
//        w = dm.widthPixels;
//        h = dm.heightPixels;
//        init(title);
//    }
//
//    public VipDialog(Context context ) {
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
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_man2, null);
//        setContentView(view);
//        ImageView imageView= (ImageView) view.findViewById(R.id.image);
//        TextView textView= (TextView) view.findViewById(R.id.name);
//        textView.setText(name);
//            Glide.with(MyApplication.getAppContext())
//                    .load("http://192.168.2.58" + dizhi)
//                    .bitmapTransform(new GlideCircleTransform(MyApplication.getAppContext()))
//                    .into(imageView);
//
//        //  groupBroadcast.setOnCheckedChangeListener(listener);
//        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
//        Window dialogWindow = this.getWindow();
//        WindowManager manager = ((Activity) context).getWindowManager();
//        assert dialogWindow != null;
//        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
//        if (w<h){
//            dialogWindow.setGravity(Gravity.TOP);//设置对话框位置
//            Point p= Utils.getDisplaySize(context); // 获取屏幕宽、高度
//
//            params.width = p.x; // 宽度设置为屏幕的0.65，根据实际情况调整
//            params.height=  ((p.y/3)*2)-100;
//            params.y=180;
//
//        }else {
//            dialogWindow.setGravity(Gravity.TOP|Gravity.START);//设置对话框位置
//            Point p= Utils.getDisplaySize(context); // 获取屏幕宽、高度
//
//            params.width = (p.x/2)+dip2px(context,50); // 宽度设置为屏幕的0.65，根据实际情况调整
//            params.height=  ((p.y)/4)*3+dip2px(context,20);
//            params.x=dip2px(context,10);
//            params.y=dip2px(context,10);
//
//        }
//
//
//        dialogWindow.setAttributes(params);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(flag){
//                    try {
////                        Message msg2 = mHandler.obtainMessage();
////                        msg2.what = 99;
////                        mHandler.sendMessage(msg2);
//
//                        Thread.sleep(8000);
//                        Message msg = mHandler.obtainMessage();
//                        msg.what = 88;
//                        mHandler.sendMessage(msg);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();
//
//
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
//
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
//
//                dismiss();
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
//}
//
