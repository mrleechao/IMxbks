package com.example.imxbkslibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Time:2020/5/23
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class ToastUtils {
    public static void ToastShowShort(Context context,String msg){
        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
