package com.example.xiaojun.lingdaotanchuang.utils;//package com.example.chenjun.ruitongone.utils;
//
//import android.os.Message;
//import android.util.Log;
//
//import com.example.chenjun.ruitongone.MyApplication;
//import com.example.chenjun.ruitongone.beans.User;
//import com.example.chenjun.ruitongone.ui.VlcVideoActivity;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//
///**
// * Created by chenjun on 2017/4/1.
// */
//
//public class AllConnects {
//    private static final String HOST="http://192.168.2.50";
//    private static final String GetYongHuLieBiao="/mobile-admin/subjects/list";
//    private static final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
////    category	string	空	√	'employee' - 员工; 'visitor' - 所有访客; 'vip' - VIP访客
////    name	string	空		要筛选的人的中文或拼音
////    order	string	'time'		排序的方式: 'time' - 按照创建时间倒序排序; 'name' - 按照英文和拼音的字典序排序
////    page	int	1		第几页
////    size	int	10		每页多少条数据
//    public static List<User> getUserLits(String category, String name, String order, int page, int size){
//        OkHttpClient okHttpClient= MyApplication.getOkHttpClient();
//        String canshu=null;
//        final List<User>list=new ArrayList<>();
//        if (name!=null){
//            canshu  ="?category="+category+"&name="+name+"&page="+page+"&size="+size;
//        }else {
//             canshu ="?category="+category+"&page="+page+"&size="+size;
//        }
//
////        JSONObject json=new JSONObject();
////        try {
////            json.put("category", category);
////            json.put("name", name);
////            json.put("order", order);
////            json.put("page", page+"");
////            json.put("size", size+"");
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//      //  RequestBody body = RequestBody.create(JSON, json.toString());
//
////        RequestBody body = new FormBody.Builder()
////                .add()
////                .add()
////                .add()
////                .add()
////                .add()
////                .build();
//        Request.Builder requestBuilder = new Request.Builder()
//                .header("Content-Type", "application/json")
//              //  .post(body)
//                .url(HOST+GetYongHuLieBiao+canshu);
//
//        // step 3：创建 Call 对象
//        Call call = okHttpClient.newCall(requestBuilder.build());
//
//        //step 4: 开始异步请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // TODO: 请求失败
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("AllConnects", call.request().toString());
//                // TODO: 请求成功
//                //获得返回体
//                ResponseBody body = response.body();
//              //  Log.d("AllConnects", response.body().string());
//            JsonObject jsonObject=GsonUtil.parse(body.string()).getAsJsonObject();
//                Gson gson=new Gson();
//                JsonArray array=jsonObject.get("data").getAsJsonArray();
//                int size=array.size();
//                for (int i=0;i<size;i++){
//                    JsonElement el= array.get(i);
//                    User user=gson.fromJson(el,User.class);
//                    list.add(user);
//                }
//                Message message=new Message();
//                message.arg1=3;
//                message.obj=list;
//
//                VlcVideoActivity.handler.sendMessage(message);
//
//            }
//        });
//
//
//        return list;
//    }
//
//}
