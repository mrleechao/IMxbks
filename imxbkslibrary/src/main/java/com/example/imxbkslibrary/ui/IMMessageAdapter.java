package com.example.imxbkslibrary.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.imxbkslibrary.IMPhotoViewActivity;
import com.example.imxbkslibrary.R;
import com.example.imxbkslibrary.bean.SocketDataBean;
import com.example.imxbkslibrary.util.IMDateUtils;

import java.util.List;

/**
 * Time:2020/5/11
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class IMMessageAdapter extends RecyclerView.Adapter<IMMessageAdapter.MyHolder> {
private final int ITEM_LEFT=0;
private final int ITEM_RIGHT=1;
private Context context;
private String uid;

private List<SocketDataBean.Params> listBeans;

    public IMMessageAdapter(List<SocketDataBean.Params> list, Context context,String uid) {
        this.listBeans = list;
        this.context=context;
        this.uid=uid;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        switch (viewType){
            case ITEM_LEFT:
                view= LayoutInflater.from(context).inflate(R.layout.im_chat_list_left,parent,false);
                break;
            case ITEM_RIGHT:
                view= LayoutInflater.from(context).inflate(R.layout.im_chat_list_right,parent,false);
                break;
        }
        return new MyHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        try {
             if (!listBeans.get(position).getUid().equals(uid)){
                holder.tv_name_left.setText(""+listBeans.get(position).getUname());
                 Glide.with(context).load(listBeans.get(position).getAvatar())
                        .into(holder.iv_head_left);
                if(!TextUtils.isEmpty(listBeans.get(position).getTime())){
                    int timelength=listBeans.get(position).getTime().length();
                    if (timelength==10){
                        holder.tv_time_left.setText(""+ IMDateUtils.getDateToString(Long.parseLong(listBeans.get(position).getTime()+"000")));
                    }else {
                        holder.tv_time_left.setText(""+ IMDateUtils.getDateToString(Long.parseLong(listBeans.get(position).getTime())));
                    }
                    holder.tv_time_left.setVisibility(View.VISIBLE);
                }else {
                    holder.tv_time_left.setVisibility(View.GONE);
                }



                if (listBeans.get(position).getType().equals("text")){
                    holder.message_left.setVisibility(View.VISIBLE);
                    holder.iv_message_left.setVisibility(View.GONE);
                    holder.message_left.setText(""+listBeans.get(position).getContent());

                }else {
                    holder.message_left.setVisibility(View.GONE);
                    holder.iv_message_left.setVisibility(View.VISIBLE);
                    Glide.with(context).load(listBeans.get(position).getContent()).into(holder.iv_message_left);

                    holder.iv_message_left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(context, IMPhotoViewActivity.class);
                            intent.putExtra("photo", listBeans.get(position).getContent());
                            context.startActivity(intent);
                        }
                    });
                }
                 holder.iv_head_left.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (imOnClickListener!=null){
                             imOnClickListener.onClickheadleft(position);
                         }
                     }
                 });

            }else {
                holder.tv_name_right.setText(""+listBeans.get(position).getUname());
                 Glide.with(context).load(listBeans.get(position).getAvatar())
                 .into(holder.iv_head_right);
                 if(!TextUtils.isEmpty(listBeans.get(position).getTime())){
                     int timelength=listBeans.get(position).getTime().length();
                     if (timelength==10){
                         holder.tv_time_right.setText(""+ IMDateUtils.getDateToString(Long.parseLong(listBeans.get(position).getTime()+"000")));
                     }else {
                         holder.tv_time_right.setText(""+ IMDateUtils.getDateToString(Long.parseLong(listBeans.get(position).getTime())));
                     }
                     holder.tv_time_right.setVisibility(View.VISIBLE);

                 }else {
                     holder.tv_time_right.setVisibility(View.GONE);
                 }


                if (listBeans.get(position).getType().equals("text")) {
                    holder.message_right.setVisibility(View.VISIBLE);
                    holder.iv_message_right.setVisibility(View.GONE);
                    holder.message_right.setText("" + listBeans.get(position).getContent());

                }else {
                    holder.message_right.setVisibility(View.GONE);
                    holder.iv_message_right.setVisibility(View.VISIBLE);
                    Glide.with(context).load(listBeans.get(position).getContent()).into(holder.iv_message_right);
                    holder.iv_message_right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setClass(context, IMPhotoViewActivity.class);
                                intent.putExtra("photo", listBeans.get(position).getContent());
                                context.startActivity(intent);

                        }
                    });
                }
            }

        }catch (Exception e){

            Log.e("------Adpater--",""+e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return listBeans==null?0:listBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listBeans.get(position).getUid().equals(uid)){
            return ITEM_RIGHT;
        }else {
            return ITEM_LEFT;
        }

    }

    class MyHolder extends RecyclerView.ViewHolder{
    private TextView message_left,message_right,tv_time_left,tv_time_right,tv_name_left,tv_name_right;
    private ImageView iv_message_left,iv_message_right;
    private IMCircleImageView iv_head_left,iv_head_right;
        public MyHolder(View itemView,int itemType) {
            super(itemView);
            switch (itemType){
                case ITEM_LEFT:
                    message_left=itemView.findViewById(R.id.tv_message);
                    tv_time_left=itemView.findViewById(R.id.tv_time);
                    iv_message_left=itemView.findViewById(R.id.iv_message);
                    tv_name_left=itemView.findViewById(R.id.tv_name);
                    iv_head_left=itemView.findViewById(R.id.iv_head);
                    break;
                case ITEM_RIGHT:
                    message_right=itemView.findViewById(R.id.tv_message);
                    tv_time_right=itemView.findViewById(R.id.tv_time);
                    iv_message_right=itemView.findViewById(R.id.iv_message);
                    tv_name_right=itemView.findViewById(R.id.tv_name);
                    iv_head_right=itemView.findViewById(R.id.iv_head);
                    break;
            }
        }
    }

    public interface IMOnClickListener{
        void onClickheadleft(int i);
    }

    public IMOnClickListener imOnClickListener;

    public void setImOnClickListener(IMOnClickListener imOnClickListener) {
        this.imOnClickListener = imOnClickListener;
    }
}
