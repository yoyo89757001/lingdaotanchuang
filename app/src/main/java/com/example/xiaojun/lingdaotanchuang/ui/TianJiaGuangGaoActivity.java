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
import com.google.gson.reflect.TypeToken;
import com.sdsmdg.tastytoast.TastyToast;

import java.lang.reflect.Type;

public class TianJiaGuangGaoActivity extends Activity {

    private EditText editText;
    private Button b1,b2;
    private String zhujidizhi=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_jia_gg);


        editText= (EditText) findViewById(R.id.et);
        b1= (Button) findViewById(R.id.bt1);
        b2= (Button) findViewById(R.id.bt2);

        Type resultType = new TypeToken<String>() {}.getType();
        Reservoir.getAsync("fuwuqi", resultType, new ReservoirGetCallback<String>() {
            @Override
            public void onSuccess(String strings) {
                editText.setText(strings);
            }

            @Override
            public void onFailure(Exception e) {



            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String aa=editText.getText().toString().trim();
                if (!aa.equals("")){

                    Reservoir.putAsync("guanggao", aa, new ReservoirPutCallback() {
                        @Override
                        public void onSuccess() {

                            Intent intent=new Intent("guanggao");
                            intent.putExtra("ggg",aa);
                            sendBroadcast(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Exception e) {

                            finish();
                        }
                    });


                }else {
                    TastyToast.makeText(TianJiaGuangGaoActivity.this, "你没有填写广告", Toast.LENGTH_SHORT,TastyToast.ERROR).show();
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
