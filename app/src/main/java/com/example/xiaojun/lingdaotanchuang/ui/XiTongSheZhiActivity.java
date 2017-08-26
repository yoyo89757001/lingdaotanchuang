package com.example.xiaojun.lingdaotanchuang.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.example.xiaojun.lingdaotanchuang.R;
import com.example.xiaojun.lingdaotanchuang.beans.ZhuJiBean;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class XiTongSheZhiActivity extends Activity implements View.OnClickListener {
    private RelativeLayout rl1,rl2,rl3,rl4;
    private TextView shiping,zhuji,tuisong;
    private String zhuji_string="",shiping_string="",tuisong_dizhi="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xi_tong_she_zhi);

        rl1= (RelativeLayout) findViewById(R.id.rl1);

        rl1.setOnClickListener(this);

        rl2= (RelativeLayout) findViewById(R.id.rl2);

        rl2.setOnClickListener(this);

        rl3= (RelativeLayout) findViewById(R.id.rl3);
        rl3.setOnClickListener(this);
//
//        rl4= (RelativeLayout) findViewById(R.id.rl4);
//        rl4.setOnFocusChangeListener(this);
//        rl4.setOnClickListener(this);
        shiping= (TextView) findViewById(R.id.shiping);
        zhuji= (TextView) findViewById(R.id.zhuji);
        tuisong = (TextView) findViewById(R.id.tuisong);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Type resultType = new TypeToken<List<ZhuJiBean>>() {}.getType();
        Reservoir.getAsync("zhujidizhi", resultType, new ReservoirGetCallback<List<ZhuJiBean>>() {
            @Override
            public void onSuccess(List<ZhuJiBean> strings) {
             if (strings.size()>=1){
                    int a = strings.size();
                    for (int i = 0; i < a; i++) {
                        if (strings.get(i).getIsTrue()==1){
                            zhuji_string=strings.get(i).getDizhi();
                            zhuji.setText(zhuji_string);
                        }

                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                //error
            }
        });

        Type resultType2 = new TypeToken<List<ZhuJiBean>>() {}.getType();
        Reservoir.getAsync("fuwuqi", resultType2, new ReservoirGetCallback<List<ZhuJiBean>>() {
            @Override
            public void onSuccess(List<ZhuJiBean> strings) {
               if (strings.size()>=1){
                    int a = strings.size();
                    for (int i = 0; i < a; i++) {
                        if (strings.get(i).getIsTrue()==1){
                            shiping_string=strings.get(i).getDizhi();
                            shiping.setText(shiping_string);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {
                //error
            }
        });

        Type resultType3 = new TypeToken<List<ZhuJiBean>>() {}.getType();
        Reservoir.getAsync("tuisongdizhi", resultType3, new ReservoirGetCallback<List<ZhuJiBean>>() {
            @Override
            public void onSuccess(List<ZhuJiBean> strings) {
                if (strings.size()>=1){
                    int a = strings.size();
                    for (int i = 0; i < a; i++) {
                        if (strings.get(i).getIsTrue()==1){
                            tuisong_dizhi=strings.get(i).getDizhi();
                            tuisong.setText(tuisong_dizhi);
                        }
                    }

                }
            }

            @Override
            public void onFailure(Exception e) {
                //error
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (shiping_string!=null || zhuji_string!=null){
//            Intent intent=new Intent("gxshipingdizhi");
//            intent.putExtra("gxsp",shiping_string);
//            intent.putExtra("gxzj",zhuji_string);
//            sendBroadcast(intent);
//        }
        startActivity(new Intent(XiTongSheZhiActivity.this,VlcVideoActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl1:
                startActivity(new Intent(XiTongSheZhiActivity.this,TianJiaZhuJiActivity.class));

                break;
            case R.id.rl2:
                startActivity(new Intent(XiTongSheZhiActivity.this,TianJiaFWQActivity.class));
                break;
            case R.id.rl3:
                startActivity(new Intent(XiTongSheZhiActivity.this,TianJiaTuiSong1Activity.class));
                break;
//            case R.id.rl4:
//
//                break;


        }

    }
}
