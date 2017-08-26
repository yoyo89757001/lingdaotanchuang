package com.example.xiaojun.lingdaotanchuang.view;//package com.example.xiaojun.lingdaotanchuang.view;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.util.AttributeSet;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.View;
//
//import com.example.xiaojun.lingdaotanchuang.R;
//import com.example.xiaojun.lingdaotanchuang.beans.LineArraysBean;
//
//import java.util.Random;
//
///**
// * Created by chenjun on 2017/4/22.
// */
//
//public class MyView extends View{
//
//    private  Paint paint,paint2,paint3,paint4,paint5,paint6,paint_bg,paint7,paint_b_xiao,paint_blake;
//    private  Context context;
//    private Bitmap bitmap=null;
//    private DisplayUtil displayUtil=new DisplayUtil();
//    private Bitmap newbm=null,bg_bitmap=null,qipao_bitmap=null;
//    private LineArraysBean bytes;
//    private Random random1,random2,random3;
//    private String age=null;
//    private int type;
//
//    public MyView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.context = context;
//        setWillNotDraw(false);// 防止onDraw方法不执行
//
//        this.paint = new Paint();
//        this.paint.setColor(getResources().getColor(R.color.huise22));
//        this.paint.setStrokeWidth(1);
//        this.paint.setAntiAlias(true); //消除锯齿
//       // this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
//        // this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));  //因为我们先画了图所以DST_IN
//        //  bitmap =BitmapFactory.decodeResource(context.getResources(),R.drawable.bg22);
//        paint_bg = new Paint();
//        paint_bg.setColor(Color.WHITE);
//        paint_bg.setStrokeWidth(1);
//        paint_bg.setAntiAlias(true); //消除锯齿
//
//        paint2=new Paint();
//        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        // paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        paint2.setStrokeWidth(displayUtil.dip2px(context,60));
//        paint2.setStyle(Paint.Style.STROKE);
//        paint2.setColor(getResources().getColor(R.color.huise22));
//        paint2.setAntiAlias(true); //消除锯齿
//
//        paint4=new Paint();
//        paint4.setTextSize(displayUtil.dip2px(context,24));//设置字体大小
//        paint4.setColor(Color.WHITE);
//        paint4.setStrokeWidth(1);
//        paint4.setAntiAlias(true); //消除锯齿
//
//        paint_b_xiao=new Paint();
//        paint_b_xiao.setTextSize(displayUtil.dip2px(context,12));//设置字体大小
//        paint_b_xiao.setColor(Color.WHITE);
//        paint_b_xiao.setStrokeWidth(1);
//        paint_b_xiao.setAntiAlias(true); //消除锯齿
//
//        paint_blake=new Paint();
//        paint_blake.setTextSize(displayUtil.dip2px(context,12));//设置字体大小
//        paint_blake.setColor(Color.BLACK);
//        paint_blake.setStrokeWidth(1);
//        paint_blake.setAntiAlias(true); //消除锯齿
//
//        paint5=new Paint();
//        paint5.setTextSize(displayUtil.dip2px(context,12));//设置字体大小
//        paint5.setColor(getResources().getColor(R.color.huise22));
//        paint5.setStrokeWidth(1);
//        paint5.setAntiAlias(true); //消除锯齿
//
//        paint3=new Paint();
//        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        // paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        paint3.setStrokeWidth(displayUtil.dip2px(context,30));
//        paint3.setStyle(Paint.Style.STROKE);
//        paint3.setColor(getResources().getColor(R.color.huise22));
//        paint3.setAntiAlias(true); //消除锯齿
//        paint3.setAlpha(24);
//
//        paint7=new Paint();
//        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        // paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        paint7.setStrokeWidth(displayUtil.dip2px(context,30));
//        paint7.setStyle(Paint.Style.STROKE);
//        paint7.setColor(getResources().getColor(R.color.huise22));
//        paint7.setAntiAlias(true); //消除锯齿
//        paint7.setAlpha(10);
//
//        paint6=new Paint();
//        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        // paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
//        paint6.setStrokeWidth(displayUtil.dip2px(context,1));
//        paint6.setStyle(Paint.Style.STROKE);
//        paint6.setColor(Color.WHITE);
//        paint6.setAntiAlias(true); //消除锯齿
//
//    }
//
//    public void setBitmap(byte[] bytes){
//        //边圆形
//
//        bitmap= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        // 设置想要的大小
//        int newWidth = displayUtil.dip2px(context,200);
//        int newHeight = displayUtil.dip2px(context,200);
//        // 计算缩放比例
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // 取得想要缩放的matrix参数
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // 得到新的图片
//        newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
//
//
//         random1=new Random();
//         random2=new Random();
//         random3=new Random();
//
//    }
//
//    public void setPanmse(String age,int type){
//        this.type=type;
//        String s=age;
//       // String aa=(s.substring(0,s.indexOf(".")));
//        this.age= age;
//
//    }
//
//    private void setBgBitmap() {
//        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.bgbg);
//
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        // 设置想要的大小
//
//        int newWidth = getWidth();
//        int newHeight = getHeight();
//        // 计算缩放比例
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // 取得想要缩放的matrix参数
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // 得到新的图片
//        bg_bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
//
//    }
//
//    private void setQiPaoBitmap() {
//        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.qipao);
//
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        // 设置想要的大小
//
//        int newWidth = displayUtil.dip2px(context,140);
//        int newHeight = displayUtil.dip2px(context,120);
//        // 计算缩放比例
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // 取得想要缩放的matrix参数
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//        // 得到新的图片
//        qipao_bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix,true);
//
//    }
//
//    public void setLines(LineArraysBean bean){
//        this.bytes=bean;
//
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//
//
////        canvas.drawLine(getWidth()/2+displayUtil.dip2px(context,40),getHeight()/2-displayUtil.dip2px(context,30),
////                getWidth()/2+displayUtil.dip2px(context,80),getHeight()/2-displayUtil.dip2px(context,80),paint);
//        //画头像
//        if (bitmap!=null){
//            setBgBitmap();
//            setQiPaoBitmap();
//
//            //背景
//            canvas.drawBitmap(bg_bitmap,0,0,paint_bg);
//
//
//            RectF rect = new RectF(getWidth()/2-displayUtil.dip2px(context,131),
//                    getHeight()/2-displayUtil.dip2px(context,131),
//                    getWidth()/2+displayUtil.dip2px(context,131),
//                    getHeight()/2+displayUtil.dip2px(context,131));
//
//
//            RectF rect2 = new RectF(getWidth()/2-displayUtil.dip2px(context,120),
//                    getHeight()/2-displayUtil.dip2px(context,120),
//                    getWidth()/2+displayUtil.dip2px(context,120),
//                    getHeight()/2+displayUtil.dip2px(context,120));
//
//            RectF rect4 = new RectF(getWidth()/2-displayUtil.dip2px(context,60),
//                    getHeight()/2-displayUtil.dip2px(context,60),
//                    getWidth()/2+displayUtil.dip2px(context,60),
//                    getHeight()/2+displayUtil.dip2px(context,60));
//
//            RectF rect3 = new RectF(getWidth()/2-displayUtil.dip2px(context,101),
//                    getHeight()/2-displayUtil.dip2px(context,101),
//                    getWidth()/2+displayUtil.dip2px(context,101),
//                    getHeight()/2+displayUtil.dip2px(context,101));
//            //canvas.drawCircle(getWidth()/2, getHeight()/2, displayUtil.dip2px(context,50), paint);
//            //透明圆
//            canvas.drawArc(rect2, 0, 360, false, paint3);
//
//            //透明圆
//            canvas.drawArc(rect4, 0, 360, false, paint7);
//
//            //圆边框
//            canvas.drawArc(rect3, 0, 360, false, paint6);
//            //头像
//            canvas.drawBitmap( makeRoundCorner(newbm),getWidth()/2-displayUtil.dip2px(context,100)
//                    ,getHeight()/2-displayUtil.dip2px(context,100),paint);
//
//
//            //中上
//            canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,15),getHeight()/2-displayUtil.dip2px(context,100),
//                    getWidth()/2+displayUtil.dip2px(context,25),getHeight()/2-displayUtil.dip2px(context,180),paint);
//            canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,25),getHeight()/2-displayUtil.dip2px(context,180),
//                    getWidth()/2+displayUtil.dip2px(context,130),getHeight()/2-displayUtil.dip2px(context,180),paint);
//
//            canvas.drawText(90+random2.nextInt(8)+"",getWidth()/2+displayUtil.dip2px(context,28),getHeight()/2-displayUtil.dip2px(context,185),paint4);
//            canvas.drawText("3S材商",getWidth()/2+displayUtil.dip2px(context,58),getHeight()/2-displayUtil.dip2px(context,198),paint_b_xiao);
//            canvas.drawText("被追指数",getWidth()/2+displayUtil.dip2px(context,58),getHeight()/2-displayUtil.dip2px(context,185),paint_b_xiao);
//            canvas.drawText("Being chased",getWidth()/2+displayUtil.dip2px(context,28),getHeight()/2-displayUtil.dip2px(context,165),paint5);
//
//        //右上
//        canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,90),getHeight()/2-displayUtil.dip2px(context,48),
//                getWidth()/2+displayUtil.dip2px(context,130),getHeight()/2-displayUtil.dip2px(context,130),paint);
//        canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,130),getHeight()/2-displayUtil.dip2px(context,130),
//                getWidth()/2+displayUtil.dip2px(context,230),getHeight()/2-displayUtil.dip2px(context,130),paint);
//
//            //右边横线
//            canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,100),getHeight()/2,
//                    getWidth()/2+displayUtil.dip2px(context,250),getHeight()/2,paint);
//            canvas.drawText(90+random2.nextInt(8)+"",getWidth()/2+displayUtil.dip2px(context,166),getHeight()/2-displayUtil.dip2px(context,8),paint4);
//            canvas.drawText("3S材商",getWidth()/2+displayUtil.dip2px(context,196),getHeight()/2-displayUtil.dip2px(context,20),paint_b_xiao);
//            canvas.drawText("邂逅指数",getWidth()/2+displayUtil.dip2px(context,196),getHeight()/2-displayUtil.dip2px(context,8),paint_b_xiao);
//            canvas.drawText("Encounter",getWidth()/2+displayUtil.dip2px(context,166),getHeight()/2+displayUtil.dip2px(context,14),paint5);
//
//        //右下
//        canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,90),getHeight()/2+displayUtil.dip2px(context,48),
//                getWidth()/2+displayUtil.dip2px(context,130),getHeight()/2+displayUtil.dip2px(context,130),paint);
//        canvas.drawLine( getWidth()/2+displayUtil.dip2px(context,130),getHeight()/2+displayUtil.dip2px(context,130),
//                getWidth()/2+displayUtil.dip2px(context,230),getHeight()/2+displayUtil.dip2px(context,130),paint);
//
//        //左下
//        canvas.drawLine( getWidth()/2-displayUtil.dip2px(context,90),getHeight()/2+displayUtil.dip2px(context,38),
//                getWidth()/2-displayUtil.dip2px(context,130),getHeight()/2+displayUtil.dip2px(context,55),paint);
//        canvas.drawLine( getWidth()/2-displayUtil.dip2px(context,130),getHeight()/2+displayUtil.dip2px(context,55),
//                getWidth()/2-displayUtil.dip2px(context,230),getHeight()/2+displayUtil.dip2px(context,55),paint);
//        canvas.drawText(800+random2.nextInt(100)+"",getWidth()/2-displayUtil.dip2px(context,255),getHeight()/2+displayUtil.dip2px(context,46),paint4);
//        canvas.drawText("3S材商",getWidth()/2-displayUtil.dip2px(context,212),getHeight()/2+displayUtil.dip2px(context,34),paint_b_xiao);
//        canvas.drawText("幸福指数",getWidth()/2-displayUtil.dip2px(context,212),getHeight()/2+displayUtil.dip2px(context,46),paint_b_xiao);
//        canvas.drawText("Happiness",getWidth()/2-displayUtil.dip2px(context,212),getHeight()/2+displayUtil.dip2px(context,68),paint5);
//
//
//            //左最下
//            canvas.drawLine( getWidth()/2-displayUtil.dip2px(context,26),getHeight()/2+displayUtil.dip2px(context,99),
//                    getWidth()/2-displayUtil.dip2px(context,90),getHeight()/2+displayUtil.dip2px(context,140),paint);
//            canvas.drawLine( getWidth()/2-displayUtil.dip2px(context,90),getHeight()/2+displayUtil.dip2px(context,140),
//                    getWidth()/2-displayUtil.dip2px(context,200),getHeight()/2+displayUtil.dip2px(context,140),paint);
//
//        //左上
//        canvas.drawLine( getWidth()/2-displayUtil.dip2px(context,90),getHeight()/2-displayUtil.dip2px(context,48),
//                getWidth()/2-displayUtil.dip2px(context,130),getHeight()/2-displayUtil.dip2px(context,130),paint);
//        canvas.drawLine( getWidth()/2-displayUtil.dip2px(context,130),getHeight()/2-displayUtil.dip2px(context,130),
//                getWidth()/2-displayUtil.dip2px(context,230),getHeight()/2-displayUtil.dip2px(context,130),paint);
//
//        ///
//        canvas.drawText(82+random3.nextInt(12)+"",getWidth()/2+displayUtil.dip2px(context,140),getHeight()/2-displayUtil.dip2px(context,135),paint4);
//        canvas.drawText("3S材商",getWidth()/2+displayUtil.dip2px(context,170),getHeight()/2-displayUtil.dip2px(context,148),paint_b_xiao);
//        canvas.drawText("开心指数",getWidth()/2+displayUtil.dip2px(context,170),getHeight()/2-displayUtil.dip2px(context,135),paint_b_xiao);
//        canvas.drawText("Happy",getWidth()/2+displayUtil.dip2px(context,140),getHeight()/2-displayUtil.dip2px(context,118),paint5);
//
//
//        ///
//    //年龄
//   canvas.drawText(25+random2.nextInt(10)+age,getWidth()/2-displayUtil.dip2px(context,230),getHeight()/2-displayUtil.dip2px(context,140),paint4);
//   canvas.drawText("3S材商",getWidth()/2-displayUtil.dip2px(context,200),getHeight()/2-displayUtil.dip2px(context,152),paint_b_xiao);
//   canvas.drawText("年龄",getWidth()/2-displayUtil.dip2px(context,200),getHeight()/2-displayUtil.dip2px(context,140),paint_b_xiao);
//   canvas.drawText("Age",getWidth()/2-displayUtil.dip2px(context,200),getHeight()/2-displayUtil.dip2px(context,116),paint5);
//
//
//
//        //
//        canvas.drawText(600+random2.nextInt(100)+"",getWidth()/2+displayUtil.dip2px(context,142),getHeight()/2+displayUtil.dip2px(context,122),paint4);
//        canvas.drawText("3S材商综合",getWidth()/2+displayUtil.dip2px(context,188),getHeight()/2+displayUtil.dip2px(context,110),paint_b_xiao);
//        canvas.drawText("魅力指数排名",getWidth()/2+displayUtil.dip2px(context,188),getHeight()/2+displayUtil.dip2px(context,122),paint_b_xiao);
//        canvas.drawText("Charm index ranking",getWidth()/2+displayUtil.dip2px(context,142),getHeight()/2+displayUtil.dip2px(context,146),paint5);
//
//        //
//        canvas.drawText(86+random3.nextInt(10)+"",getWidth()/2-displayUtil.dip2px(context,220),getHeight()/2+displayUtil.dip2px(context,132),paint4);
//        canvas.drawText("3S材商",getWidth()/2-displayUtil.dip2px(context,190),getHeight()/2+displayUtil.dip2px(context,120),paint_b_xiao);
//        canvas.drawText("马路回头指数",getWidth()/2-displayUtil.dip2px(context,190),getHeight()/2+displayUtil.dip2px(context,132),paint_b_xiao);
//        canvas.drawText("Look back",getWidth()/2-displayUtil.dip2px(context,190),getHeight()/2+displayUtil.dip2px(context,154),paint5);
//
//        canvas.drawText("哎玛,你的排名击败了",getWidth()/2-displayUtil.dip2px(context,100),getHeight()-displayUtil.dip2px(context,20),paint5);
//        canvas.drawText(89+random1.nextInt(10)+"%",getWidth()/2+displayUtil.dip2px(context,12),getHeight()-displayUtil.dip2px(context,20),paint4);
//        canvas.drawText("的用户",getWidth()/2+displayUtil.dip2px(context,58),getHeight()-displayUtil.dip2px(context,20),paint5);
//
//        //  int saveLayerCount1 = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
//        // 画缺口边
//        canvas.drawArc(rect, -15, 10, false, paint2);
////        canvas.restoreToCount(saveLayerCount1);
//        //  int saveLayerCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawArc(rect, 50, 30, false, paint2);
//        canvas.drawArc(rect, 140, 11, false, paint2);
//        //  canvas.restoreToCount(saveLayerCount);
//        canvas.drawArc(rect, -105, 22, false, paint2);
//        canvas.drawArc(rect, -80, 6, false, paint2);
//
//        canvas.drawArc(rect, -190, 22, false, paint2);
//       // canvas.drawArc(rect, 83, 5, false, paint2);
//
//            //画气泡
//            canvas.drawBitmap(qipao_bitmap,getWidth()/2-displayUtil.dip2px(context,10),getHeight()/2+displayUtil.dip2px(context,70),paint_bg);
//            if (type==1){ //员工
//                canvas.drawText("我又来刷脸了",getWidth()/2+displayUtil.dip2px(context,24),getHeight()/2+displayUtil.dip2px(context,151),paint_blake);
//            }else { //陌生人
//                canvas.drawText("我来刷脸了",getWidth()/2+displayUtil.dip2px(context,30),getHeight()/2+displayUtil.dip2px(context,151),paint_blake);
//            }
//
//        }
//    }
//
//    public  Bitmap makeRoundCorner(Bitmap bitmap)
//    {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        float roundPx;
//        float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
//        if (width <= height) {
//            roundPx = width / 2;
//            top = 0;
//            bottom = width;
//            left = 0;
//            right = width;
//            height = width;
//            dst_left = 0;
//            dst_top = 0;
//            dst_right = width;
//            dst_bottom = width;
//        } else {
//            roundPx = height / 2;
//            float clip = (width - height) / 2;
//            left = clip;
//            right = width - clip;
//            top = 0;
//            bottom = height;
//            width = height;
//            dst_left = 0;
//            dst_top = 0;
//            dst_right = height;
//            dst_bottom = height;
//        }
//        Bitmap output = Bitmap.createBitmap(width,
//                height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(output);
//        final int color = 0xff424242;
//        final Paint paint = new Paint();
//        final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
//        final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
//        final RectF rectF = new RectF(dst);
//        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        paint.setColor(color);
//        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, src, dst, paint);
//        return output;
//    }
//
//
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
//        public void getPM(){
//            DisplayMetrics dm =getResources().getDisplayMetrics();
//            int w_screen = dm.widthPixels;
//            int h_screen = dm.heightPixels;
//            Log.d("ddd","屏幕尺寸2：宽度 = " + w_screen + "高度 = " + h_screen + "密度 = "+ dm.densityDpi);
//        }
//
//    }
//
//}
//
