package com.example.xiaojun.lingdaotanchuang.dialog;//package com.example.chenjun.ruitongone.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.meijie.mjwtsk.R;
//
///**
// * @Function: 自定义对话框
// * @Date: 2013-10-28
// * @Time: 下午12:37:43
// * @author Tom.Cai
// */
//public class QueRenDialog extends Dialog {
//
//    private Button positiveButton,quxiao;
//    private TextView shuoming;
//    private View quxiao_v;
//    public QueRenDialog(Context context) {
//        super(context, R.style.dialog_time);
//        setCustomDialog();
//    }
//
//    private void setCustomDialog() {
//        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll, null);
//        shuoming= (TextView) mView.findViewById(R.id.dddddd);
//        positiveButton = (Button) mView.findViewById(R.id.queding_xingming);
//        quxiao= (Button) mView.findViewById(R.id.quxiao);
//        quxiao_v=mView.findViewById(R.id.quxiao_v);
//        super.setContentView(mView);
//    }
//
//
//    public void setVisibility_BT(){
//        quxiao_v.setVisibility(View.VISIBLE);
//        quxiao.setVisibility(View.VISIBLE);
//
//    }
//
//    public  void setCountText(String ss){
//        shuoming.setText(ss);
//    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//    }
//
//    @Override
//    public void setContentView(View view, LayoutParams params) {
//    }
//
//    @Override
//    public void setContentView(View view) {
//    }
//
//    /**
//     * 确定键监听器
//     * @param listener
//     */
//    public void setOnPositiveListener(View.OnClickListener listener){
//        positiveButton.setOnClickListener(listener);
//    }
//    /**
//     * 取消键监听器
//     * @param listener
//     */
//    public void setOnQuXiaoListener(View.OnClickListener listener){
//        quxiao.setOnClickListener(listener);
//    }
//}
