package com.example.xiaojun.lingdaotanchuang.dialog;

/**
 * Created by chenjun on 2017/4/5.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaojun.lingdaotanchuang.R;
import com.example.xiaojun.lingdaotanchuang.beans.MoShengRenBeanDao;
import com.example.xiaojun.lingdaotanchuang.beans.ShiPingBeanDao;
import com.example.xiaojun.lingdaotanchuang.beans.YongHuBean;
import com.example.xiaojun.lingdaotanchuang.ui.TianJiaGuangGaoActivity;
import com.example.xiaojun.lingdaotanchuang.ui.VlcVideoActivity;
import com.example.xiaojun.lingdaotanchuang.ui.XiTongSheZhiActivity;
import com.example.xiaojun.lingdaotanchuang.utils.Utils;

import java.util.List;


public class CaiDanDialog extends Dialog implements View.OnFocusChangeListener, View.OnClickListener {
    private Button button1,button2,button3,button4;
    private Context context;
    private String title;     //这里定义个title，一会可以看到是指向上面xml文件的控件title的，也就是我们可以通过这个进行动态修改title
    private ShiPingBeanDao moShengRenBeanDao;
    private VlcVideoActivity.MyAdapter adapter=null;
    private List<YongHuBean> moShengRenBean2s;
    //可以看到两个构造器，想自定义样式的就用第二个啦


    public CaiDanDialog(Context context) {
        super(context);
        this.context = context;

    }

    public CaiDanDialog(Context context, int theme, ShiPingBeanDao dao, VlcVideoActivity.MyAdapter adapter,List<YongHuBean> moShengRenBean2List) {
        super(context, theme);
        this.context = context;
        moShengRenBeanDao=dao;
        this.adapter=adapter;
        moShengRenBean2s=moShengRenBean2List;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    //控件的声明
    private ImageView head,gou;
    private TextView textView;

    private void init(String title) {
        //以view的方式引入，然后回调activity方法，setContentView，实现自定义布局
        View view = LayoutInflater.from(context).inflate(R.layout.activity_shi_zhi, null);
        setContentView(view);
        //radiobutton的初始化
        button1= (Button)view.findViewById(R.id.bt1);
        button1.setOnFocusChangeListener(this);
        button1.setOnClickListener(this);

        button2= (Button)view.findViewById(R.id.bt2);
        button2.setOnClickListener(this);
        button2.setOnFocusChangeListener(this);

        button3= (Button)view.findViewById(R.id.bt3);
        button3.setOnFocusChangeListener(this);
        button3.setOnClickListener(this);

        button4= (Button)view.findViewById(R.id.bt4);
        button4.setOnClickListener(this);
        button4.setOnFocusChangeListener(this);
        //  groupBroadcast.setOnCheckedChangeListener(listener);
        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.BOTTOM);//设置对话框位置
       Point p= Utils.getDisplaySize(context); // 获取屏幕宽、高度

        params.width = p.x; // 宽度设置为屏幕的0.65，根据实际情况调整
        params.height= (int) (p.y* 0.3);
        dialogWindow.setAttributes(params);




    }


    private String fileName;// 文件命名
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                if (moShengRenBeanDao!=null){
                    moShengRenBeanDao.deleteAll();
                    if (moShengRenBean2s!=null){
                        moShengRenBean2s.clear();
                    }
                    if (adapter!=null)
                        adapter.notifyDataSetChanged();
                }
                break;
            case R.id.bt2:

                Intent intent=new Intent("shoudongshuaxin");
                context.sendBroadcast(intent);

                break;
            case R.id.bt3:
                context.startActivity(new Intent(context,TianJiaGuangGaoActivity.class));
                break;
            case R.id.bt4:
                context.startActivity(new Intent(context,XiTongSheZhiActivity.class));

                break;


        }


}




    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

}