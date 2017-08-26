package com.example.xiaojun.lingdaotanchuang.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anupcowkur.reservoir.Reservoir;
import com.anupcowkur.reservoir.ReservoirGetCallback;
import com.anupcowkur.reservoir.ReservoirPutCallback;
import com.example.xiaojun.lingdaotanchuang.R;
import com.example.xiaojun.lingdaotanchuang.beans.ZhuJiBean;
import com.google.gson.reflect.TypeToken;
import com.sdsmdg.tastytoast.TastyToast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TianJiaIPActivity extends Activity {

    private EditText editText;
    private Button b1,b2;
    private String zhujidizhi=null;
    private int p=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_jia_ip);

        zhujidizhi=getIntent().getStringExtra("zhujidizhi");

        p=getIntent().getIntExtra("number",-1);

        editText= (EditText) findViewById(R.id.et);
        b1= (Button) findViewById(R.id.bt1);
        b2= (Button) findViewById(R.id.bt2);

        if (zhujidizhi!=null){
            editText.setText(zhujidizhi);
            b1.setText("修改");
        }

        final List<ZhuJiBean>  quchu=new ArrayList<>();

        Type resultType = new TypeToken<List<ZhuJiBean>>() {}.getType();
        Reservoir.getAsync("zhujidizhi", resultType, new ReservoirGetCallback<List<ZhuJiBean>>() {
            @Override
            public void onSuccess(List<ZhuJiBean> strings) {
                quchu.addAll(strings);

            }

            @Override
            public void onFailure(Exception e) {
                TastyToast.makeText(TianJiaIPActivity.this, e.getMessage(), Toast.LENGTH_SHORT,TastyToast.ERROR).show();
             //   finish();

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().trim().equals("")){
                    int a = quchu.size();
                    if (a>0){
                        for (int i = 0; i < a; i++) {
                            quchu.get(i).setIsTrue(0);
                        }
                    if (p!=-1 && zhujidizhi!=null){
                            quchu.remove(p);
                        }
                    }

                ZhuJiBean bean=new ZhuJiBean();
                bean.setIsTrue(1);
                bean.setDizhi(editText.getText().toString().trim());
                quchu.add(bean);

                    Reservoir.putAsync("zhujidizhi", quchu, new ReservoirPutCallback() {
                        @Override
                        public void onSuccess() {

                            Intent intent=new Intent("updatezhuji");

                            sendBroadcast(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Exception e) {

                            finish();
                        }
                    });


                }else {
                    TastyToast.makeText(TianJiaIPActivity.this, "你没有填写地址", Toast.LENGTH_SHORT,TastyToast.ERROR).show();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });

    }
}
