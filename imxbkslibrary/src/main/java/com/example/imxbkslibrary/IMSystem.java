package com.example.imxbkslibrary;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Time:2020/5/23
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class IMSystem {
    public static IMSystem imSystem=null;
    private IMSystem(){};
    synchronized public static IMSystem getInstance(){
        if (imSystem==null){
            imSystem=new IMSystem();
        }
        return imSystem;
    }
    public String UserInfo;
    public String Token;



    public void intoIM(Context context, String userinfo, String token){

        if (TextUtils.isEmpty(userinfo)){
            throw new NullPointerException("检查参数是否正确");
        }
        try {
            UserInfo=userinfo;
            JSONObject jsonObject=new JSONObject(userinfo);
            JSONObject data=jsonObject.optJSONObject("data");
            String is_service=data.optString("is_service");
            if (!TextUtils.isEmpty(is_service)){

                Intent intent=new Intent();
                intent.putExtra("userinfo",userinfo);
                if (is_service.equals("0")){
                    intent.setClass(context,IMClientActivity.class);
                }else {
                    intent.setClass(context,IMConversationListActivity.class);
                }
                context.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("json解析错误");
        }

    }

    public String getToken() {
        return Token;
    }

    public String getUserInfo() {
        return UserInfo;
    }

}
