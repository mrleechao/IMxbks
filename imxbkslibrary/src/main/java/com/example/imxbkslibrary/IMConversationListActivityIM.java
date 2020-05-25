package com.example.imxbkslibrary;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.example.imxbkslibrary.base.IMBaseActivity;
import com.example.imxbkslibrary.bean.FristSendSocketBean;
import com.example.imxbkslibrary.bean.ReceiveDataBean;
import com.example.imxbkslibrary.bean.SocketDataBean;
import com.example.imxbkslibrary.bean.IMUserInfoBean;
import com.example.imxbkslibrary.servicebean.ConversationListData;
import com.example.imxbkslibrary.ui.IMConversationListAdpater;
import com.example.imxbkslibrary.util.IMToastUtils;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 会话列表
 */
public class IMConversationListActivityIM extends IMBaseActivity implements View.OnClickListener {
    private final String TAG="IMConversationActivity";
    private RelativeLayout btn_back;
    private TextView top_title;
    private RecyclerView recycler_view;

    private SocketDataBean.Params params;
    private FristSendSocketBean fristSendSocketBean;
    private String filepath;
    private String imgUrl="";
    private boolean robot;
    private SocketDataBean.Params robotBean;
    private IMUserInfoBean IMUserInfoBean;
    private int page=1;

    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClientService jWebSClientService;
    private JWebSocketClient client;
    private MyServiceConnection conn;
    private ChatMessageReceiver chatMessageReceiver;

    private List<ConversationListData.MessageBean.DataBean > dataBeanList;
    private IMConversationListAdpater listAdpater;

    @Override
    protected int getLayoutID() {
        return R.layout.im_activity_conversation_list;
    }

    @Override
    protected void initView() {
        IMSystem.getInstance().addActivity(this);
        top_title=findViewById(R.id.top_title);
        btn_back=findViewById(R.id.btn_back);
        recycler_view=findViewById(R.id.recycler_view);

    }

    @Override
    protected void initData() {
        dataBeanList=new ArrayList<>();
        top_title.setText("用户消息");
        getUserinfo();
        registerReceiver();
        bindClientService();
        listAdpater = new IMConversationListAdpater(this,dataBeanList);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recycler_view.setAdapter(listAdpater);
    }

    @Override
    protected void listener() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void getUserinfo(){
        String userinfo=getIntent().getStringExtra("userinfo");
        Log.e(TAG,"获取用户信息"+userinfo);
        if (!TextUtils.isEmpty(userinfo)){
            IMUserInfoBean = com.alibaba.fastjson.JSONObject.parseObject(userinfo, IMUserInfoBean.class);
            params = new SocketDataBean.Params();
            params.setUid(IMUserInfoBean.getData().getUser_id());
            params.setAvatar(IMUserInfoBean.getData().getAvatar());
            params.setMobile(IMUserInfoBean.getData().getMobile());
            params.setRole(IMUserInfoBean.getData().getRole());
            params.setUname(IMUserInfoBean.getData().getNickname());


            IMUserInfoBean data= com.alibaba.fastjson.JSONObject.parseObject(userinfo, IMUserInfoBean.class);
            fristSendSocketBean = new FristSendSocketBean();
            FristSendSocketBean.Params params= fristSendSocketBean.new Params();
            params.setAvatar(data.getData().getAvatar());
            params.setCity_text(data.getData().getCity_text());
            params.setIs_shops("0");
            params.setMobile(data.getData().getMobile());
            params.setRole("1");
            params.setType("system");
            params.setUid(data.getData().getUser_id());
            params.setUname(data.getData().getNickname());
            fristSendSocketBean.setParams(params);


        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }


    private void registerReceiver(){
        chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.xbhc.servicecallback.content");
        registerReceiver(chatMessageReceiver,filter);
    }
    private void bindClientService(){
        Intent intent=new Intent();
        intent.setClass(this,JWebSocketClientService.class);
        conn = new MyServiceConnection();
        bindService(intent, conn,BIND_AUTO_CREATE);
    }
    public class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message=intent.getStringExtra("message");
            String close=intent.getStringExtra("close");
            if (!TextUtils.isEmpty(close)){
                IMToastUtils.ToastShowShort(IMConversationListActivityIM.this,""+close);
            }
            if (TextUtils.isEmpty(message)){
               return;
            }
            try {
                JSONObject jsonObject=new JSONObject(message);
                String code=jsonObject.optString("code");
                if (TextUtils.isEmpty(code)){

                    try {
                        SocketDataBean socketDataBean = com.alibaba.fastjson.JSONObject.parseObject(message, SocketDataBean.class);
                        SocketDataBean.Params params= socketDataBean.getParams();
//                        params.setStatus(0);
//                        listBeans.add(params);
//                        imMessageAdapter.notifyItemInserted(listBeans.size()-1);
                        Log.e("JWebSClientService", message);
                    }catch (JSONException e){
                        Log.e(TAG, "-----接收服务端数据解析出错"+e.getMessage());
                    }

                }else {
                    SocketDataBean.Params params=new SocketDataBean. Params();
                    switch (code){
                        case "0":
                            JSONObject jsonmessage=jsonObject.optJSONObject("message");
                            if (jsonmessage==null)return;
                            String type=jsonmessage.optString("type");
                            if (TextUtils.isEmpty(type))return;
                            int system=jsonmessage.optInt("system");
                            switch (type){
                                case "system":
                                    if (system==200){ //服务器自动推送10条历史消息
                                        try {
                                        }catch (JSONException e){
                                            Log.e(TAG, "-----历史消息JSONException"+e.getMessage());
                                        }
                                    }
                                    if (system==100){//服务器自动推送列表消息

                                        disposeMessage(message);

                                    }
                                    break;
                                case "image":

                                    break;

                                case "history"://用户主动拉取历史消息

                                    break;
                            }
                            break;
                        case "2"://连接成功
                            Log.e(TAG, "-----连接成功发送用户信息"+com.alibaba.fastjson.JSONObject.toJSONString(fristSendSocketBean));
                            client.send(com.alibaba.fastjson.JSONObject.toJSONString(fristSendSocketBean));
                            break;
                        case "131073":
                            JSONObject jsonmessage2=jsonObject.optJSONObject("message");
                            if (jsonmessage2==null)return;
                            String type2=jsonmessage2.optString("type");
                            if (TextUtils.isEmpty(type2))return;
                            int system2=jsonmessage2.optInt("system");
                            switch (type2){
                                case "robot":
                                    if (system2==0) {//帮帮机器人在线


                                    }
                                    break;
                                case "system":
                                    if (system2==201){//{"code":"131073","message":{"type":"system","system":201,"data":{"fid":"1757744"}}}

                                    }else if (system2==300){//其他设备登录了

                                    }

                                    break;
                                case "text":
                                    ReceiveDataBean receiveDataBean= com.alibaba.fastjson.JSONObject.parseObject(message,ReceiveDataBean.class);
                                    ConversationListData.MessageBean.DataBean dataBean=new ConversationListData.MessageBean.DataBean();
//                                    SocketDataBean. Params params1=new SocketDataBean.Params();
//                                        params1.setStatus(0);
                                    dataBean.setLastmsg(receiveDataBean.getMessage().getData().getContent() + "");
                                    dataBean.setUname(receiveDataBean.getMessage().getData().getUname() + "");
                                    dataBean.setAvatar(receiveDataBean.getMessage().getData().getAvatar() + "");
                                    dataBean.setLasttime(System.currentTimeMillis()+"");
                                    dataBean.setUid(receiveDataBean.getMessage().getData().getUid()+"");
                                    dataBean.setAvatar(receiveDataBean.getMessage().getData().getAvatar()+"");
                                    dataBean.setMobile(receiveDataBean.getMessage().getData().getMobile()+"");
                                    dataBean.setType(receiveDataBean.getMessage().getData().getType()+"");
                                    String uid=receiveDataBean.getMessage().getData().getUid()+"";
                                    boolean flag=false;
                                    for (int i = 0; i < dataBeanList.size(); i++) {
                                        if (uid.equals(dataBeanList.get(i).getUid())){
                                            dataBeanList.set(i,dataBean);
                                            dataBeanList.add(0,  dataBeanList.remove(i));
                                            flag=true;
                                            break;
                                        }
                                    }
                                    if (!flag){
                                        dataBeanList.add(0,dataBean);
                                    }
                                    listAdpater.notifyDataSetChanged();
                                    break;
                                case "image":
                                    ReceiveDataBean receiveDataBean1= com.alibaba.fastjson.JSONObject.parseObject(message,ReceiveDataBean.class);
                                    ConversationListData.MessageBean.DataBean dataBean1=new ConversationListData.MessageBean.DataBean();
//                                    SocketDataBean. Params params1=new SocketDataBean.Params();
//                                        params1.setStatus(0);
                                    dataBean1.setLastmsg(receiveDataBean1.getMessage().getData().getContent() + "");
                                    dataBean1.setUname(receiveDataBean1.getMessage().getData().getUname() + "");
                                    dataBean1.setAvatar(receiveDataBean1.getMessage().getData().getAvatar() + "");
                                    dataBean1.setLasttime(System.currentTimeMillis()+"");
                                    dataBean1.setUid(receiveDataBean1.getMessage().getData().getUid()+"");
                                    dataBean1.setAvatar(receiveDataBean1.getMessage().getData().getAvatar()+"");
                                    dataBean1.setMobile(receiveDataBean1.getMessage().getData().getMobile()+"");
                                    dataBean1.setType(receiveDataBean1.getMessage().getData().getType()+"");
                                    String uid1=receiveDataBean1.getMessage().getData().getUid()+"";
                                    boolean flag1=false;
                                    for (int i = 0; i < dataBeanList.size(); i++) {
                                        if (uid1.equals(dataBeanList.get(i).getUid())){
                                            dataBeanList.set(i,dataBean1);
                                            dataBeanList.add(0,  dataBeanList.remove(i));
                                            flag1=true;
                                            break;
                                        }
                                    }
                                    if (!flag1){
                                        dataBeanList.add(0,dataBean1);
                                    }
                                    listAdpater.notifyDataSetChanged();
                                    break;
                            }




                            break;



                    }
//                        Log.e(TAG, "-----接收服务端数据"+message);
                }
            } catch (org.json.JSONException e) {
                e.printStackTrace();
            }


        }
    }
    public class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder= (JWebSocketClientService.JWebSocketClientBinder) service;
            jWebSClientService=binder.getService();
            client=jWebSClientService.client;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /**
     * 处理列表消息
     * @param msg
     */
    private void disposeMessage(String msg){
        if (dataBeanList!=null){
            dataBeanList.clear();
            ConversationListData listData= com.alibaba.fastjson.JSONObject.parseObject(msg,ConversationListData.class);
            dataBeanList.addAll(listData.getMessage().getData());
            listAdpater.notifyDataSetChanged();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
        unregisterReceiver(chatMessageReceiver);
        IMSystem.getInstance().removeActivity(this);
    }
}
