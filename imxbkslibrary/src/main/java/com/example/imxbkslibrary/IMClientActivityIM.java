package com.example.imxbkslibrary;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.alibaba.fastjson.JSONException;
import com.example.imxbkslibrary.base.IMBaseActivity;
import com.example.imxbkslibrary.bean.FristSendSocketBean;
import com.example.imxbkslibrary.bean.HistoryDataBean;
import com.example.imxbkslibrary.bean.PullHistoryData;
import com.example.imxbkslibrary.bean.ReceiveDataBean;
import com.example.imxbkslibrary.bean.ReceiveDataListBean;
import com.example.imxbkslibrary.bean.RobotDataBean;
import com.example.imxbkslibrary.bean.SocketDataBean;
import com.example.imxbkslibrary.bean.IMUserInfoBean;
import com.example.imxbkslibrary.ui.IMMessageAdapter;
import com.example.imxbkslibrary.ui.IMSelectPhotoDialog;
import com.example.imxbkslibrary.util.IMBitmapComPressUtils;
import com.example.imxbkslibrary.util.IMCacheUtils;
import com.example.imxbkslibrary.util.IMKeyboardChangeListener;
import com.example.imxbkslibrary.util.IMToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import static com.scwang.smartrefresh.layout.header.ClassicsHeader.REFRESH_HEADER_PULLDOWN;

/**
 * 客户端
 */
public class IMClientActivityIM extends IMBaseActivity implements View.OnClickListener {
    private String TAG="--IMClientActivityIM--";
    private JWebSocketClient client;
    private JWebSocketClientService.JWebSocketClientBinder binder;
    private JWebSocketClientService jWebSClientService;

    private RelativeLayout btn_back;
    private TextView top_title;
    private Button tv_send;
    private ImageView iv_open_img;
    private EditText et_content;
    private RecyclerView recycler_view;
    private SmartRefreshLayout mRefreshLayout;
    private IMMessageAdapter imMessageAdapter;
    private List<SocketDataBean.Params> listBeans;

    private IMSelectPhotoDialog photoview;
    private File imgFile = null;

    private Uri uri;
    private static final int FLAG_CHOOSE_IMG = 0x11;
    private static final int FLAG_CHOOSE_CAMERA = 0x17;
    private static final int FLAG_MODIFY_FINISH = 7;





    private ChatMessageReceiver chatMessageReceiver;
    private String fid="";//

    private Handler uihandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if (listBeans.size()>1)
                    recycler_view.smoothScrollToPosition(listBeans.size()-1);
                    break;
                case 2:
                    hideKeyboard();
                    break;
            }
            return false;
        }
    });
    private SocketDataBean.Params params;
    private FristSendSocketBean fristSendSocketBean;
    private String filepath;
    private String imgUrl="";
    private boolean robot;
    private SocketDataBean.Params robotBean;
    private IMUserInfoBean IMUserInfoBean;
    private int page=1;
    private String bundleFid;
    private String ucity;
    private String isshop;
    private String uname;
    private String uavatar;
    private String umobile;

    @Override
    protected int getLayoutID() {
        return R.layout.im_activity_chat;
    }

    @Override
    protected void initView() {
        top_title=findViewById(R.id.top_title);
        btn_back=findViewById(R.id.btn_back);
        tv_send = findViewById(R.id.tv_send);
        recycler_view=findViewById(R.id.recycler_view);
        et_content=findViewById(R.id.et_content);
        iv_open_img=findViewById(R.id.iv_open_img);
        mRefreshLayout=findViewById(R.id.mRefreshLayout);
        top_title.setText("在线客服");
        iv_open_img.setVisibility(View.VISIBLE);
        tv_send.setVisibility(View.GONE);
        //创建选择相册对话框
        photoview = new IMSelectPhotoDialog(this);
//        startJWebSClientService();
        bindService();
        doRegisterReceiver();
//         REFRESH_HEADER_PULLDOWN="下拉加载历史消息";
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initData() {
        bundleFid = getIntent().getStringExtra("bundleFid");
        if (bundleFid ==null){
            getUserinfo();
        }else {
            getUserinfoService();
            uname = getIntent().getStringExtra("uname");
            et_content.setHint("点击输入文字...");
            top_title.setText(""+ uname);
        }

        listBeans=new ArrayList<>();
        imMessageAdapter=new IMMessageAdapter(listBeans,this, IMUserInfoBean.getData().getUser_id());
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recycler_view.setAdapter(imMessageAdapter);

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int stringlength=et_content.getText().toString().length();
                if (stringlength>0){
                    tv_send.setVisibility(View.VISIBLE);
                    iv_open_img.setVisibility(View.GONE);
                }else {
                    tv_send.setVisibility(View.GONE);
                    iv_open_img.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    protected void listener() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(0);
            }
        });
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState== RecyclerView.SCROLL_STATE_DRAGGING ){
                    hideKeyboard();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        IMKeyboardChangeListener softKeyboardStateHelper = new IMKeyboardChangeListener(this);
        softKeyboardStateHelper.setKeyBoardListener(new IMKeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (isShow) {
                    //键盘的弹出
                    uihandler.sendEmptyMessageDelayed(1,160);
                } else {
                    //键盘的收起
//                    et_seartch.setCursorVisible(false);
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                sendMessage(2);

            }
        });

        iv_open_img.setOnClickListener(this);

        imMessageAdapter.setImOnClickListener(new IMMessageAdapter.IMOnClickListener() {
            @Override
            public void onClickheadleft(int i) { //查看信息
                if (bundleFid!=null){//客服可以查看
                    String avatar=uavatar;
                    String city_text=ucity;
                    String is_shops=isshop;
                    String mobile=umobile;
                    String name=uname;
                  Intent intent=new Intent();
                  intent.setClass(IMClientActivityIM.this, IMUserInfoActivityIM.class);
                  intent.putExtra("avatar",avatar);
                  intent.putExtra("city_text",city_text);
                  intent.putExtra("mobile",mobile);
                  intent.putExtra("name",name);
                  intent.putExtra("is_shops",is_shops);
                  startActivity(intent);
                }

            }
        });

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_qx){
            photoview.dismiss();
        }else if (v.getId()==R.id.btn_pz){
            photoview.dismiss();
            if (-1==ContextCompat.checkSelfPermission(IMClientActivityIM.this, Manifest.permission.CAMERA)){
                ActivityCompat.requestPermissions(IMClientActivityIM.this, new String[]{Manifest.permission.CAMERA},
                        6);
            }
            else{
                takePhoto();
            }
        }else if (v.getId()==R.id.btn_xc){
            photoview.dismiss();
            if (-1==ContextCompat.checkSelfPermission(IMClientActivityIM.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(IMClientActivityIM.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        7);
            }
            else{
                choosePhoto();
            }
        }else if (v.getId()==R.id.iv_open_img){
            //显示选择头像的方式对话框
            photoview.show();
            //对话框中的子选项
            photoview.btn_qx.setOnClickListener(this);
            photoview.btn_pz.setOnClickListener(this);
            photoview.btn_xc.setOnClickListener(this);
        }
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
          params.setRole("0");
          params.setUname(IMUserInfoBean.getData().getNickname());


          IMUserInfoBean data= com.alibaba.fastjson.JSONObject.parseObject(userinfo, IMUserInfoBean.class);
          fristSendSocketBean = new FristSendSocketBean();
          FristSendSocketBean.Params params= fristSendSocketBean.new Params();
          params.setAvatar(data.getData().getAvatar());
          params.setCity_text(data.getData().getCity_text());
          params.setIs_shops("0");
          params.setMobile(data.getData().getMobile());
          params.setRole("0");
          params.setType("system");
          params.setUid(data.getData().getUser_id());
          params.setUname(data.getData().getNickname());
          fristSendSocketBean.setParams(params);


      }
  }

    private void getUserinfoService(){
        String userinfo= getIntent().getStringExtra("userinfo");
        if (userinfo==null){
            userinfo=IMSystem.getInstance().getUserInfo();
        }
        Log.e(TAG,"获取用户信息"+userinfo);
        if (!TextUtils.isEmpty(userinfo)){
            IMUserInfoBean = com.alibaba.fastjson.JSONObject.parseObject(userinfo, IMUserInfoBean.class);
            IMUserInfoBean data= com.alibaba.fastjson.JSONObject.parseObject(userinfo, IMUserInfoBean.class);
            fristSendSocketBean = new FristSendSocketBean();
            FristSendSocketBean.Params params= fristSendSocketBean.new Params();
            params.setAvatar(data.getData().getAvatar());
            params.setCity_text(data.getData().getCity_text());
            params.setIs_shops("0");
            params.setMobile(data.getData().getMobile());
            params.setService(102);
            params.setRole("1");
            params.setType("service");
            params.setUid(data.getData().getUser_id());
            params.setFid(""+bundleFid);
            params.setUname(data.getData().getNickname());
            fristSendSocketBean.setParams(params);


        }
    }

    /**
     * 发送类型 文字0 图片1
     * @param type
     */
  private void sendMessage(int type){
      SocketDataBean socketDataBean =new SocketDataBean();
      if (client != null && client.isOpen()) {
          switch (type){
              case 0:
                  if (!TextUtils.isEmpty(et_content.getText().toString().trim())){
                      SocketDataBean.Params params1=new SocketDataBean.Params();
                      params1.setContent(et_content.getText().toString().trim());
                      params1.setType("text");
                      params1.setTime(System.currentTimeMillis()+"");
                      params1.setUid(IMUserInfoBean.getData().getUser_id());
                      params1.setAvatar(IMUserInfoBean.getData().getAvatar());
                      params1.setMobile(IMUserInfoBean.getData().getMobile());
                      if (bundleFid==null){
                          params1.setRole("0");
                          params1.setFid(fid);
                      }else {
                          params1.setRole("1");
                          params1.setFid(bundleFid);
                      }
                      params1.setUname(IMUserInfoBean.getData().getNickname());

                      listBeans.add(params1);
                      imMessageAdapter.notifyItemInserted(listBeans.size()-1);
                      uihandler.sendEmptyMessageDelayed(1,500);
                      uihandler.sendEmptyMessageDelayed(2,200);
                      et_content.setText("");
                      socketDataBean.setParams(params1);
                      client.send(com.alibaba.fastjson.JSONObject.toJSONString(socketDataBean));
                      Log.e("--sendMessage--",""+com.alibaba.fastjson.JSONObject.toJSONString(socketDataBean));

                  }else {
                      IMToastUtils.ToastShowShort(this,"不能发送空白消息！");
                  }
                  break;
              case 1:
                  SocketDataBean.Params params2=new SocketDataBean.Params();
                      params2.setContent(imgUrl);
                      params2.setType("image");
//                      params2.setStatus(1);
                      params2.setTime(System.currentTimeMillis()+"");
                      params2.setImg_path(filepath);
                      params2.setUid(IMUserInfoBean.getData().getUser_id());
                      params2.setAvatar(IMUserInfoBean.getData().getAvatar());
                      params2.setMobile(IMUserInfoBean.getData().getMobile());
                      if (bundleFid==null){//用户
                          params2.setRole("0");
                          params2.setFid(fid);
                      }else {//客服
                          params2.setRole("1");
                          params2.setFid(bundleFid);
                      }

                      params2.setUname(IMUserInfoBean.getData().getNickname());

                      listBeans.add(params2);
                      imMessageAdapter.notifyItemInserted(listBeans.size()-1);
                      uihandler.sendEmptyMessageDelayed(1,500);
                      uihandler.sendEmptyMessageDelayed(2,200);
                      et_content.setText("");
                      socketDataBean.setParams(params2);
                      client.send(com.alibaba.fastjson.JSONObject.toJSONString(socketDataBean));
                  break;
              case 2:
                  PullHistoryData pullHistoryData=new PullHistoryData();
                  pullHistoryData.setInstruct("b003");
                  PullHistoryData.Params params3=new PullHistoryData.Params();
                  params3.setPage(page+"");
                  params3.setType("history");
                  if (bundleFid==null) {//用户
                      params3.setUserid(IMUserInfoBean.getData().getUser_id());
                  }else {
                      params3.setUserid(bundleFid);
                  }

                  pullHistoryData.setParams(params3);
                  client.send(com.alibaba.fastjson.JSONObject.toJSONString(pullHistoryData));
                  Log.e(TAG,"--发送---："+com.alibaba.fastjson.JSONObject.toJSONString(pullHistoryData));
                  break;
          }

      }else {
          hideKeyboard();
          IMToastUtils.ToastShowShort(this,"已断开连接！");
      }

  }
    private class ChatMessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

                String message=intent.getStringExtra("message");
                try {
                    JSONObject jsonObject=new JSONObject(message);
                    String code=jsonObject.optString("code");
                    if (TextUtils.isEmpty(code)){

                        try {
                            SocketDataBean socketDataBean = com.alibaba.fastjson.JSONObject.parseObject(message, SocketDataBean.class);
                            SocketDataBean.Params params= socketDataBean.getParams();
                            listBeans.add(params);
                            imMessageAdapter.notifyItemInserted(listBeans.size()-1);
                            Log.e("JWebSClientService", message);
                        }catch (JSONException e){
                            Log.e(TAG, "-----接收服务端数据解析出错"+e.getMessage());
                        }

                    }else {
                        switch (code){
                            case "0":
                                JSONObject jsonmessage=jsonObject.optJSONObject("message");
                                if (jsonmessage==null)return;
                                String type=jsonmessage.optString("type");
                                if (TextUtils.isEmpty(type))return;
                                switch (type){
                                    case "system":
                                        int system=jsonmessage.optInt("system");
                                        if (system==200){ //  用户端自动推送10条历史消息
                                            try {
                                                HistoryDataBean historyDataBean= com.alibaba.fastjson.JSONObject.parseObject(message,HistoryDataBean.class);
                                                List<SocketDataBean.Params> list= historyDataBean.getMessage().getData().getHistory();
                                                Collections.reverse(list);
                                                listBeans.addAll(list);
                                                if (robot){
                                                    listBeans.add(robotBean);
                                                }
                                                imMessageAdapter.notifyDataSetChanged();
                                                if (listBeans.size()>1){
                                                    recycler_view.smoothScrollToPosition(listBeans.size()-1);
                                                }
                                                fid=historyDataBean.getMessage().getData().getFid()+"";
                                                Log.e(TAG, "-----历史消息"+com.alibaba.fastjson.JSONObject.toJSONString(list));
                                                Log.e(TAG, "-----当前列表信息"+com.alibaba.fastjson.JSONObject.toJSONString(listBeans));
                                            }catch (JSONException e){
                                                Log.e(TAG, "-----历史消息JSONException"+e.getMessage());
                                            }

                                        }

                                        break;
                                    case "image":

                                        break;

                                    case "history"://用户主动拉取历史消息
                                        mRefreshLayout.finishRefresh();
                                        ReceiveDataListBean receiveDataListBean= com.alibaba.fastjson.JSONObject.parseObject(message,ReceiveDataListBean.class);
                                        List<SocketDataBean.Params> list= receiveDataListBean.getMessage().getData();
                                        if (list.size()>1)
                                        fid=list.get(list.size()-1).getUid();
                                        Collections.reverse(list);
                                        listBeans.addAll(0,list);
                                        imMessageAdapter.notifyDataSetChanged();
                                        Log.e(TAG, "-----主动拉取历史消息 第"+page+"页:"+com.alibaba.fastjson.JSONObject.toJSONString(list));
                                        page++;
                                        break;
                                    case "service":
                                        int service=jsonmessage.optInt("service");
                                        if (service==102){//客服端 自动推送10条历史消息和用户信息
                                            try {
                                                HistoryDataBean historyDataBean= com.alibaba.fastjson.JSONObject.parseObject(message,HistoryDataBean.class);
                                                List<SocketDataBean.Params> slist= historyDataBean.getMessage().getData().getHistory();
                                                Collections.reverse(slist);
                                                listBeans.addAll(slist);
                                                if (robot){
                                                    listBeans.add(robotBean);
                                                }
                                                imMessageAdapter.notifyDataSetChanged();
                                                if (listBeans.size()>1){
                                                    recycler_view.smoothScrollToPosition(listBeans.size()-1);
                                                }
                                                fid=historyDataBean.getMessage().getData().getFid()+"";
                                                Log.e(TAG, "-----历史消息"+com.alibaba.fastjson.JSONObject.toJSONString(slist));
                                                Log.e(TAG, "-----当前列表信息"+com.alibaba.fastjson.JSONObject.toJSONString(listBeans));

                                                HistoryDataBean.MessageBean.CurUserInfoBean curUserInfoBean=historyDataBean.getMessage().getCur_user_info();
                                                uname=curUserInfoBean.getUname();
                                                ucity=curUserInfoBean.getCity_text();
                                                uavatar=curUserInfoBean.getAvatar();
                                                isshop=curUserInfoBean.getIs_shops();
                                                umobile=curUserInfoBean.getMobile();

                                            }catch (JSONException e){
                                                Log.e(TAG, "-----历史消息JSONException"+e.getMessage());
                                            }

                                        }
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
                                            robot = true;
                                            Log.e(TAG,"-----帮帮机器人在线");
                                            RobotDataBean robotDataBean= com.alibaba.fastjson.JSONObject.parseObject(message,RobotDataBean.class);
                                            robotBean = new SocketDataBean. Params();
                                            robotBean.setContent(robotDataBean.getMessage().getData().getContent().getDes() + "");
                                            robotBean.setUname(robotDataBean.getMessage().getData().getUname() + "");
                                            robotBean.setAvatar(robotDataBean.getMessage().getData().getAvatar() + "");
                                            robotBean.setType("text");
                                            robotBean.setTime(System.currentTimeMillis()+"");
                                            robotBean.setUid("0");
                                            fid="0";
                                            top_title.setText("客服不在线");
                                            top_title.setTypeface(Typeface.DEFAULT_BOLD);
                                            top_title.setTextColor(Color.parseColor("#7E7B7A"));
                                        }
                                        break;
                                    case "system":
                                        if (system2==201){//{"code":"131073","message":{"type":"system","system":201,"data":{"fid":"1757744"}}}

                                        }else if (system2==300){//其他设备登录了

                                        }

                                        break;
                                    case "text":
                                        ReceiveDataBean receiveDataBean= com.alibaba.fastjson.JSONObject.parseObject(message,ReceiveDataBean.class);
                                        if (bundleFid !=null){
                                            if (!receiveDataBean.getMessage().getData().getUid().equals(bundleFid))return;//判断当前用户id和列表带入的 是否一致
                                        }

                                        SocketDataBean. Params params1=new SocketDataBean.Params();
                                        params1.setContent(receiveDataBean.getMessage().getData().getContent() + "");
                                        params1.setUname(receiveDataBean.getMessage().getData().getUname() + "");
                                        params1.setAvatar(receiveDataBean.getMessage().getData().getAvatar() + "");
                                        params1.setType("text");
                                        params1.setTime(System.currentTimeMillis()+"");
                                        params1.setUid(receiveDataBean.getMessage().getData().getUid()+"");
                                        params1.setFid(receiveDataBean.getMessage().getData().getFid()+"");
                                        listBeans.add(params1);
                                        imMessageAdapter.notifyItemInserted(listBeans.size()-1);
                                        recycler_view.smoothScrollToPosition(listBeans.size()-1);
                                        fid=receiveDataBean.getMessage().getData().getUid()+"";
                                        break;
                                    case "image":
                                        ReceiveDataBean receiveDataBean1= com.alibaba.fastjson.JSONObject.parseObject(message,ReceiveDataBean.class);
                                        if (bundleFid !=null){
                                            if (!receiveDataBean1.getMessage().getData().getUid().equals(bundleFid))return;//判断当前用户id和列表带入的 是否一致
                                        }
                                        SocketDataBean. Params params2=new SocketDataBean.Params();
                                        params2.setContent(receiveDataBean1.getMessage().getData().getContent() + "");
                                        params2.setUname(receiveDataBean1.getMessage().getData().getUname() + "");
                                        params2.setAvatar(receiveDataBean1.getMessage().getData().getAvatar() + "");
                                        params2.setType("image");
                                        params2.setTime(System.currentTimeMillis()+"");
                                        params2.setUid(receiveDataBean1.getMessage().getData().getUid()+"");
                                        params2.setFid(receiveDataBean1.getMessage().getData().getFid()+"");
                                        listBeans.add(params2);
                                        imMessageAdapter.notifyItemInserted(listBeans.size()-1);
                                        recycler_view.smoothScrollToPosition(listBeans.size()-1);
                                        fid=receiveDataBean1.getMessage().getData().getUid()+"";
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
    /**
     * 动态注册广播
     */
    private void doRegisterReceiver() {
        chatMessageReceiver = new ChatMessageReceiver();
        IntentFilter filter = new IntentFilter("com.xbhc.servicecallback.content");
        registerReceiver(chatMessageReceiver, filter);
    }
    /**
     * 注销广播
     */
    private void unRegisterReceiver() {
        unregisterReceiver(chatMessageReceiver);
    }
    /**
     * 启动服务（websocket客户端服务）
     */
    private void startJWebSClientService() {
        Intent intent = new Intent(this, JWebSocketClientService.class);
        startService(intent);
    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //服务与活动成功绑定
            Log.e(TAG, "服务与活动成功绑定");
            binder = (JWebSocketClientService.JWebSocketClientBinder) iBinder;
            jWebSClientService = binder.getService();
            client = jWebSClientService.client;
            if (bundleFid!=null){
                Log.e(TAG, "-----连接成功发送客的信息"+com.alibaba.fastjson.JSONObject.toJSONString(fristSendSocketBean));
                client.send(com.alibaba.fastjson.JSONObject.toJSONString(fristSendSocketBean));
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //服务与活动断开
            Log.e(TAG, "服务与活动成功断开");
        }
    };

    /**
     * 绑定服务
     */
    private void bindService() {
        Intent bindIntent = new Intent(IMClientActivityIM.this, JWebSocketClientService.class);
        bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterReceiver();
        unbindService(serviceConnection);
    }












    //拍照
    private void takePhoto() {
        imgFile = new File(IMCacheUtils.getCacheDirectory(this, true, "icon")
                + "pic");
        if (imgFile.exists()) {
            imgFile.delete();
        }
        try {
            imgFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //适配android7.0 手机拍照取uri的处理
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT < 24) {
            uri = Uri.fromFile(imgFile);//7.0这里会闪退
        } else {
            uri = FileProvider.getUriForFile(IMClientActivityIM.this, IMSystem.getInstance().getAuthority(), imgFile);
            camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
        }
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(camera, FLAG_CHOOSE_CAMERA);
    }

    //选择相册
    private void choosePhoto() {
        Intent intent;
        intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, FLAG_CHOOSE_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FLAG_CHOOSE_IMG && resultCode == RESULT_OK) {         //相册页面回调

            if (data != null)
            {
                Uri uri = data.getData();
                String imgpath = IMBitmapComPressUtils.getRealFilePath(IMClientActivityIM.this, uri);
                upImage(imgpath);
            }

        } else if (requestCode == FLAG_CHOOSE_CAMERA && resultCode == RESULT_OK) {
            try{
                if (uri.getScheme() != null && "content".equalsIgnoreCase(uri.getScheme())) {
//                intent.putExtra("path", imgFile.getAbsolutePath());
                    upImage(imgFile.getAbsolutePath());
                } else {
//                intent.putExtra("path", uri.getPath());
                    upImage(uri.getPath());
                }
            }catch (Exception e){
                IMToastUtils.ToastShowShort(IMClientActivityIM.this,"拍照路径出错！");
            }


        }
//        else if (requestCode == FLAG_MODIFY_FINISH && resultCode == RESULT_OK) {      //ActivityPhoto页面回调回来
//            if (data != null)
//            {
//                final String path = data.getStringExtra("path");
//                filepath = path;
//
//                //将图片进行双重压缩后再上传
//                Bitmap imgBitmap = BitmapComPressUtils.getDecordeImage(IMClientActivityIM.this, filepath, 800f, 400f);
//
//                int degree = readPicCurDegree(filepath);             //查询图片翻转的角度
//                Bitmap bitmap = rotaingImageView(degree,imgBitmap);           //旋转图片
////                String imgpath = BitmapComPressUtils.saveBitmap(IMClientActivityIM.this, bitmap);     //保存图片
//
//
//
//                String imgpath = "data:image/png;base64,"+ BitmapUtil.bitmapToBase64(bitmap);
//                upImage(imgpath);
//                Log.e(TAG, "imgpath: "+ imgpath);
//
//
//            }
//        }
    }


    private static int readPicCurDegree(String filepath) {

        int degree = 0;

        try {

            ExifInterface exifInterface = new ExifInterface(filepath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation)
            {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return degree;
    }

    private static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Log.i("angle", "" + angle);
        Bitmap resizebitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizebitmap;
    }


//
//    private void upImage(String image_path){
//        HashMap<String, String> map=new HashMap<>();
//        map.put("image_path",image_path);
//        map.put("api_version", MyConfig.API_VERSION_1_1);
//        map.put("access_token", ProjectUrils.SP.getString("key", ""));
//        RetrofitManager.getInstance().doPost(MyConfig.QR_URL + "site/upload", map, new RetrofitManager.ICallBack() {
//            @Override
//            public void successData(String result) {
//                IMImageDataBean.DataBean imageDataBean= com.alibaba.fastjson.JSONObject.parseObject(result,IMImageDataBean.DataBean.class);
////                if (imageDataBean.getCode()==0){
//                    imgUrl = imageDataBean.getPath();
//                    sendMessage(1);
////                }
//            }
//
//            @Override
//            public void Error(String error) {
//
//            }
//        });
//    }

    private void upImage(String image_path){
        if ( IMSystem.getInstance().getExecuteImageUp()==null){
            IMToastUtils.ToastShowShort(IMClientActivityIM.this,"请实现上传图片的方法");
            return;
        }
        IMSystem.getInstance().getExecuteImageUp().upImg(image_path, "123", new UpImageCallback() {
            @Override
            public void upadd(String path, String id) {
                IMToastUtils.ToastShowShort(IMClientActivityIM.this,""+path+"  \n"+id);
            }
        });
    }
}
