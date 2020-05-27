package com.example.imxbkslibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

/**
 * Time:2020/5/23
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class IMSystem {
    public static IMSystem imSystem=null;
    private static Stack<Activity> activityStack;
    private IMSystem(){};
    synchronized public static IMSystem getInstance(){
        if (imSystem==null){
            imSystem=new IMSystem();
        }
        return imSystem;
    }
    private String UserInfo;
    private String Token;
    private String authority;
    private String serverUri;


    /**
     * 进入客服系统调用方法
     * @param context
     * @param serverUri WebSocket地址
     * @param userinfo  用户信息
     * @param authority The authority of a {@link FileProvider} defined in a
     *        {@code <provider>} element in your app's manifest.
     */
    public IMSystem intoIM(@NonNull Context context,@NonNull  String serverUri,@NonNull  String userinfo,@NonNull  String authority){
        if (TextUtils.isEmpty(userinfo)){
            throw new NullPointerException("检查参数userinfo是否正确");
        }
        if (TextUtils.isEmpty(serverUri)){
            throw new NullPointerException("检查参数serverUri是否正确");
        }
        if (TextUtils.isEmpty(authority)){
            throw new NullPointerException("检查参数authority是否正确");
        }
        try {
            this.UserInfo=userinfo;
            this.authority=authority;
            this.serverUri=serverUri;
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
        return this;
    }

    /**
     * 用户信息
     * @param userInfo
     * @return
     */
    public IMSystem setUserInfo(String userInfo){
        this.UserInfo=userInfo;
        return this;

    }
    /**
     *
     * @param  authority The authority of a {@link FileProvider} defined in a
     *        {@code <provider>} element in your app's manifest.
     */
    public IMSystem setAuthority(String authority){
        this.authority=authority;
        return this;
    }

    public String getToken() {
        return Token;
    }

    protected String getUserInfo() {
        return UserInfo;
    }

    protected String getAuthority() {
        return authority;
    }

    protected String getServerUri() {
        return serverUri;
    }

    /**
     * 图片上传下发接口
     */
    public interface ExecuteImageUp{
        /**
         * 实现改方法接收需要上传的图片地址
         * @param context  若需要实现加载框，请传入当前上下文
         * @param path  本地图片地址
         * @param id
         * @param upImageCallback 上传成功后调用
         */
        void upImg(Context context,String path, String id, UpImageCallback upImageCallback);

//        void setImageLoader(Context context,ImageView view,String path);

    }

    private ExecuteImageUp executeImageUp;

    public ExecuteImageUp getExecuteImageUp() {
        return executeImageUp;
    }

    public void setExecuteImageUp(ExecuteImageUp executeImageUp) {
        this.executeImageUp = executeImageUp;
    }



    protected void addActivity(Activity activity){
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    protected void removeActivity(Activity activity){
        if (activity != null) {
            if (activityStack!=null){
                activityStack.remove(activity);
                activity.finish();
                activity = null;
            }

        }
    }

    /**
     * 结束所有Activity
     */
    protected void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
