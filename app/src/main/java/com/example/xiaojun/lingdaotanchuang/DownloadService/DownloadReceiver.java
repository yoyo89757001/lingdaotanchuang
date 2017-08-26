package com.example.xiaojun.lingdaotanchuang.DownloadService;//package com.meijie.mjwt.DownloadService;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.os.ResultReceiver;
//
///**
// * Created by Administrator on 2016/7/6 0006.
// */
//public class DownloadReceiver extends ResultReceiver  {
//    public DownloadReceiver(Handler handler) {
//        super(handler);
//    }
//    @Override
//    protected void onReceiveResult(int resultCode, Bundle resultData) {
//        super.onReceiveResult(resultCode, resultData);
//        if (resultCode == DownloadService.UPDATE_PROGRESS) {
//            int progress = resultData.getInt("progress");
//            mProgressDialog.setProgress(progress);
//            if (progress == 100) {
//                mProgressDialog.dismiss();
//            }
//        }
//    }
//}