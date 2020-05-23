package com.example.imxbkslibrary.ui;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.imxbkslibrary.IMClientActivity;
import com.example.imxbkslibrary.R;
import com.example.imxbkslibrary.servicebean.ConversationListData;
import com.example.imxbkslibrary.util.DateUtils;

import java.util.List;

/**
 * Time:2020/5/18
 * <p>
 * Author:lichao
 * <p>
 * Description:会话列表
 */
public class IMConversationListAdpater extends RecyclerView.Adapter<IMConversationListAdpater.MyHolder> {
    private Context context;
    private List<ConversationListData.MessageBean.DataBean > dataBeanList;
    private boolean flagRead;

    public IMConversationListAdpater(Context context, List<ConversationListData.MessageBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.im_conversation_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(final MyHolder holder,final int position) {
        holder.tv_name.setText(""+dataBeanList.get(position).getUname());
        if (dataBeanList.get(position).getType().equals("")){
            holder.tv_message.setText(""+dataBeanList.get(position).getLastmsg());
        }else {
            if (dataBeanList.get(position).getType().equals("image")){
                holder.tv_message.setText("[图片]");
            }else {
                holder.tv_message.setText(""+dataBeanList.get(position).getLastmsg());
            }

        }

        if (dataBeanList.get(position).getLasttime()!=null&&dataBeanList.get(position).getLasttime().length()>0)
        holder.tv_time.setText(DateUtils.getDateToString(Long.parseLong(dataBeanList.get(position).getLasttime())).substring(5));
        holder.parent_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context, IMClientActivity.class);
                intent.putExtra("bundleFid",dataBeanList.get(position).getUid());
                intent.putExtra("uname",dataBeanList.get(position).getUname());
                context.startActivity(intent);
                holder.tv_num.setVisibility(View.GONE);
            }
        });
        Glide.with(context).load(dataBeanList.get(position).getAvatar())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.iv_head);
//        ImagLoadUtils.loadImg(dataBeanList.get(position).getAvatar(),holder.iv_head,context,0);
        if (position==0){
            if (flagRead){
                holder.tv_num.setVisibility(View.VISIBLE);
            }
            else{
                holder.tv_num.setVisibility(View.GONE);
                flagRead=true;
            }

        }else {
            holder.tv_num.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return dataBeanList==null?0:dataBeanList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
    ConstraintLayout parent_click;
    TextView tv_name,tv_message,tv_time,tv_num;
        ImageView iv_head;
        public MyHolder(View itemView) {
            super(itemView);
            parent_click=itemView.findViewById(R.id.parent_click);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_message=itemView.findViewById(R.id.tv_message);
            iv_head=itemView.findViewById(R.id.iv_head);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_num=itemView.findViewById(R.id.tv_num);
        }
    }
}
