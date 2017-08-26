package com.example.xiaojun.lingdaotanchuang.service;//package com.example.chenjun.ruitongone.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.Binder;
//import android.os.Handler;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.util.Log;
//
//import org.java_websocket.client.WebSocketClient;
//
///**
// * Created by chenjun on 2017/4/20.
// */
//
//public class MyService extends Service {
//    private MyBinder mBinder = new MyBinder();
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//
//        return mBinder;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//   public class MyBinder extends Binder {
//
//
//        public void startConntion(final WebSocketClient webSocketClient ) {
//
//
//
//
//        }
//
//    }
//}
