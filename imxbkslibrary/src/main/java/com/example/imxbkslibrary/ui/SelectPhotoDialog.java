package com.example.imxbkslibrary.ui;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.imxbkslibrary.R;

/**
 * Created by Administrator on 2017/12/7/007.
 */
public class SelectPhotoDialog
{

    private Dialog mDialog;             //对话框
    public Button btn_pz;               //子项点击框
    public Button btn_xc;
    public Button btn_qx;

    public SelectPhotoDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //选择头像的方式对话框布局文件
        View view = inflater.inflate(R.layout.im_activity_setphoto_select, null);
        mDialog = new Dialog(context);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        btn_pz = (Button) view.findViewById(R.id.btn_pz);
        btn_xc = (Button) view.findViewById(R.id.btn_xc);
        btn_qx = (Button) view.findViewById(R.id.btn_qx);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        // ���ñ�����͸����
        lp.dimAmount = 0.3f;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        lp.width = (int)((0.94)*display.getWidth()); //设置宽度
//		lp.y=15;
        mDialog.getWindow().setAttributes(lp);
//        mDialog.getWindow().setWindowAnimations(R.style.activity_anim_style_photo);
    }
    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }
}
