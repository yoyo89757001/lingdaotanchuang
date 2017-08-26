package com.example.xiaojun.lingdaotanchuang.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.xiaojun.lingdaotanchuang.R;


public class ErWeiMaActivity extends Activity {
//    private EditText etCompany;
//    private Bitmap logo;
//    private static final int IMAGE_HALFWIDTH = 1;//宽度值，影响中间图片大小

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_wei_ma);
//        //获得资源图片，可改成获取本地图片或拍照获取图片
//        logo= BitmapFactory.decodeResource(super.getResources(),R.drawable.yixuanze_22);
//        etCompany =(EditText) findViewById(R.id.etCompany);
//
//        findViewById(R.id.but).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                String company=etCompany.getText().toString().trim() ;
//
//                //二维码中包含的文本信息
//                String contents= "www.baidu.com";
//                try {
//                    //调用方法createCode生成二维码
//                    Bitmap bm=createCode(contents,logo, BarcodeFormat.QR_CODE);
//                    ImageView img=(ImageView)findViewById(R.id.imgCode) ;
//                    //将二维码在界面中显示
//                    img.setImageBitmap(bm);
//                } catch (WriterException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    /**
//     * 生成二维码
//     * @param string 二维码中包含的文本信息
//     * @param mBitmap logo图片
//     * @param format  编码格式
//     * [url=home.php?mod=space&uid=309376]@return[/url] Bitmap 位图
//     * @throws WriterException
//     */
//    public Bitmap createCode(String string,Bitmap mBitmap, BarcodeFormat format)
//            throws WriterException {
//        Matrix m = new Matrix();
//        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
//        float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
//        m.setScale(sx, sy);//设置缩放信息
//        //将logo图片按martix设置的信息缩放
//        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
//                mBitmap.getWidth(), mBitmap.getHeight(), m, false);
//        MultiFormatWriter writer = new MultiFormatWriter();
//        Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
//        hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");//设置字符编码
//        BitMatrix matrix = writer.encode(string, format, 600, 600, hst);//生成二维码矩阵信息
//        int width = matrix.getWidth();//矩阵高度
//        int height = matrix.getHeight();//矩阵宽度
//        int halfW = width/2;
//        int halfH = height/2;
//        int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
//        for (int y = 0; y < height; y++) {//从行开始迭代矩阵
//            for (int x = 0; x < width; x++) {//迭代列
//                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
//                        && y > halfH - IMAGE_HALFWIDTH
//                        && y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息
////记录图片每个像素信息
//                    pixels[y * width + x] = mBitmap.getPixel(x - halfW
//                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);              } else {
//                    if (matrix.get(x, y)) {//如果有黑块点，记录信息
//                        pixels[y * width + x] = 0xff000000;//记录黑块信息
//                    }
//                }
//            }
//        }
//        Bitmap bitmap = Bitmap.createBitmap(width, height,
//                Bitmap.Config.ARGB_8888);
//        // 通过像素数组生成bitmap
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//        return bitmap;
//    }

        finish();
    }
}
