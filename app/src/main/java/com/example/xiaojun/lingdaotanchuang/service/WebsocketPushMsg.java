package com.example.xiaojun.lingdaotanchuang.service;//package com.example.chenjun.ruitongone.service;
//
///**
// * Created by chenjun on 2017/4/2.
// */
//
//
//import android.os.Message;
//import android.util.Log;
//import com.example.chenjun.ruitongone.beans.WBBean;
//import com.example.chenjun.ruitongone.beans.WBShiBieDataBean;
//import com.example.chenjun.ruitongone.beans.WBShiBiePersonBean;
//import com.example.chenjun.ruitongone.beans.WBWeiShiBieDATABean;
//import com.example.chenjun.ruitongone.ui.VlcVideoActivity;
//import com.example.chenjun.ruitongone.utils.GsonUtil;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.util.Vector;
//
////来自于本地仓库 Java-Websocket-koala-1.3.0.jar
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.handshake.ServerHandshake;
//
//
///**
// * 识别消息推送
// * 主机盒子端API ws://[主机ip]:9000/video
// * 通过 websocket 获取 识别结果
// * @author Wangshutao
// */
//public class WebsocketPushMsg {
//
//    private WBShiBiePersonBean personBean;
//
//    /** * 识别消息推送
//     * @param wsUrl websocket接口 例如 ws://192.168.1.50:9000/video
//     * @param rtspUrl 视频流地址 门禁管理-门禁设备-视频流地址
//     *                例如 rtsp://192.168.0.100/live1.sdp
//     *                或者 rtsp://admin:admin12345@192.168.1.64/live1.sdp
//     *                或者 rtsp://192.168.1.103/user=admin&password=&channel=1&stream=0.sdp
//     *                或者 rtsp://192.168.1.100/live1.sdp
//     *                       ?__exper_tuner=lingyun&__exper_tuner_username=admin
//     *                       &__exper_tuner_password=admin&__exper_mentor=motion
//     *                       &__exper_levels=312,1,625,1,1250,1,2500,1,5000,1,5000,2,10000,2,10000,4,10000,8,10000,10
//     *                       &__exper_initlevel=6
//     * @throws URISyntaxException
//     * @throws
//     * @throws
//     *
//     *   rtsp://192.168.2.52/user=admin&password=&channel=1&stream=0.sdp
//     */
//    public static void startConnection(String wsUrl,String rtspUrl) throws URISyntaxException {
//        //当视频流地址中出现&符号时，需要进行进行url编码
//
//        try {
//            rtspUrl = URLEncoder.encode(rtspUrl,"UTF-8");
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            Log.d("WebsocketPushMsg", e.getMessage())
//        }
//        URI uri = URI.create(wsUrl + "?url=" + rtspUrl);
//        WebSocketClient webSocketClient = new WebSocketClient(uri) {
//            private Vector vector=new Vector();
//            private Vector vector2=new Vector();
//
//            @Override
//            public void onOpen(ServerHandshake serverHandshake) {
//                Log.d("WebsocketPushMsg", serverHandshake.getHttpStatusMessage());
//
//        }
//
//            @Override
//            public void onMessage(String ss) {
//              //  Log.d("WebsocketPushMsg", "onMessage:"+s);
//                String s=null;
//               s= ss.replace("\\","").replace("\"tag\": \"{\"","\"tag\": {\"").replace("jpg\"}\"","jpg\"}");
//               JsonObject jsonObject= GsonUtil.parse(s).getAsJsonObject();
//                Gson gson=new Gson();
//               WBBean wbBean= gson.fromJson(jsonObject, WBBean.class);
//                Log.d("WebsocketPushMsg", wbBean.getType());
//                if (wbBean.getType().equals("recognized")){ //识别
//                    JsonObject jsonObject1=jsonObject.get("data").getAsJsonObject();
//                    JsonObject jsonObject2=jsonObject.get("person").getAsJsonObject();
//                 //   JsonObject jsonObject3=jsonObject.get("screen").getAsJsonObject();
//                    WBShiBieDataBean dataBean=gson.fromJson(jsonObject1,WBShiBieDataBean.class);
//                    WBShiBiePersonBean personBean= gson.fromJson(jsonObject2,WBShiBiePersonBean.class);
//
//                    vector.addElement(dataBean);
//
//                    if (vector.size()==1){
//                        Message message=new Message();
//                        message.arg1=1;
//                        message.obj=personBean;
//                        VlcVideoActivity.handler.sendMessage(message);
//                        Log.d("WebsocketPushMsg", "第一次");
//                    }
//                    else if (vector.size()==2){
//                        WBShiBieDataBean b1=(WBShiBieDataBean)vector.get(0);
//                        WBShiBieDataBean b2=(WBShiBieDataBean)vector.get(1);
//                        Log.d("WebsocketPushMsg", "b1.getTrack():" + b1.getTrack());
//                        Log.d("WebsocketPushMsg", "b2.getTrack():" + b2.getTrack());
//
//                        if (b1.getTrack()!=b2.getTrack()){
//                            Message message=new Message();
//                            message.arg1=1;
//                            message.obj=personBean;
//                            VlcVideoActivity.handler.sendMessage(message);
//                            vector.remove(0);
//                            Log.d("WebsocketPushMsg", "第二次");
//                        }else {
//
//                            vector.remove(0);
//                            Log.d("WebsocketPushMsg", "删除");
//                        }
//
//                    }
//
//
//
//                 //   WBShiBieScreenBean screenBean=gson.fromJson(jsonObject3,WBShiBieScreenBean.class);
////                    try {
////
////                        generateImage(dataBean.getFace().getImage(), Environment.getExternalStorageDirectory().getPath()+
////                                File.separator+"aaaa.jpg");
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
//
//
//                }else if (wbBean.getType().equals("unrecognized")){
//
////                    if(s.length() > 4000) {
////                        for(int i=0;i<s.length();i+=4000){
////                            if(i+4000<s.length())
////                                Log.i("fffffffff"+i,s.substring(i, i+4000));
////
////                            else
////                                Log.i("fffffffff"+i,s.substring(i, s.length()));
////                        }
////                    } else
////                        Log.i("ffffffffff", s);
//
//                    JsonObject jsonObject1=jsonObject.get("data").getAsJsonObject();
//                  //  JsonObject jsonObject3=jsonObject.get("screen").getAsJsonObject();
//
//                    WBWeiShiBieDATABean dataBean=gson.fromJson(jsonObject1,WBWeiShiBieDATABean.class);
//                    vector2.addElement(dataBean);
//                    Log.d("WebsocketPushMsg", "未识别");
//
//                    Log.d("WebsocketPushMsg", "vector2.size():" + vector2.size());
//                    if (vector2.size()==1){
//
//                        Message message=new Message();
//                        message.arg1=2;
//                        message.obj=dataBean;
//                        VlcVideoActivity.handler.sendMessage(message);
//                        Log.d("WebsocketPushMsg", "未识别的第一次");
//
//
//                    }
//                     if (vector2.size()==2){
//                        Log.d("WebsocketPushMsg", "vector2.size()222:" + vector2.size());
//                        WBWeiShiBieDATABean b1=(WBWeiShiBieDATABean)vector2.get(0);
//                        WBWeiShiBieDATABean b2=(WBWeiShiBieDATABean)vector2.get(1);
//                        Log.d("WebsocketPushMsg", "b1.getTrack():" + b1.getTrack());
//                        Log.d("WebsocketPushMsg", "b2.getTrack():" + b2.getTrack());
//
//                        if (b1.getTrack()!=b2.getTrack()){
//
//                            Message message=new Message();
//                            message.arg1=2;
//                            message.obj=dataBean;
//                            VlcVideoActivity.handler.sendMessage(message);
//                            vector2.remove(0);
//                            Log.d("WebsocketPushMsg", "未识别的第二次");
//
//                        }else {
//
//                            vector2.remove(0);
//                            Log.d("WebsocketPushMsg", "未识别的删除");
//                        }
//
//                    }
//
//                 //   WBWeiShiBieScreenBean screenBean=gson.fromJson(jsonObject3,WBWeiShiBieScreenBean.class);
//
//                }
//
//
//            }
//
//            @Override
//            public void onClose(int i, String s, boolean b) {
//                Log.d("WebsocketPushMsg", "webSocketClient onClose " + s + " " + i + " " + b);
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Log.d("WebsocketPushMsg", "onError"+e.getMessage());
//
//            }
//        };
//
//        webSocketClient.connect();
//    }
//
//
//
//
//
//
//
//}