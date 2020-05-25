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
    private String UserInfo;
    private String Token;


    /**
     * 进入客服系统调用方法
     * @param context
     * @param userinfo  用户信息
     * @param token
     */
    public void intoIM(Context context, String userinfo, String token){
        if (TextUtils.isEmpty(userinfo)){
            throw new NullPointerException("检查参数是否正确");
        }
        try {
            UserInfo=userinfo;
            Token=token;
            JSONObject jsonObject=new JSONObject(userinfo);
            JSONObject data=jsonObject.optJSONObject("data");
            String is_service=data.optString("is_service");
            if (!TextUtils.isEmpty(is_service)){

                Intent intent=new Intent();
                intent.putExtra("userinfo",userinfo);
                if (is_service.equals("0")){
                    intent.setClass(context, IMClientActivityIM.class);
                }else {
                    intent.setClass(context, IMConversationListActivityIM.class);
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


    /**
     * 图片上传下发接口
     */
    public interface ExecuteImageUp{
        /**
         * 实现改方法接收需要上传的图片地址
         * @param path  本地图片地址
         * @param id
         * @param upImageCallback 上传成功后调用
         */
        void upImg(String path, String id, UpImageCallback upImageCallback);

    }

    private ExecuteImageUp executeImageUp;

    protected ExecuteImageUp getExecuteImageUp() {
        return executeImageUp;
    }

    public void setExecuteImageUp(ExecuteImageUp executeImageUp) {
        this.executeImageUp = executeImageUp;
    }
}
