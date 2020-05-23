package com.example.imxbkslibrary;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imxbkslibrary.base.BaseActivity;

/**
 * Time:2020/5/21
 * <p>
 * Author:lichao
 * <p>
 * Description:
 */
public class IMUserInfoActivity extends BaseActivity {

    private ImageView iv_head;
    private TextView tv_name,tv_table,tv_phone,tv_city_name;

    @Override
    protected int getLayoutID() {
        return R.layout.im_user_info;
    }

    @Override
    protected void initView() {
        iv_head = findViewById(R.id.iv_head);
        tv_name=findViewById(R.id.tv_name);
        tv_table=findViewById(R.id.tv_table);
        tv_phone=findViewById(R.id.tv_phone);
        tv_city_name=findViewById(R.id.tv_city_name);
    }

    @Override
    protected void initData() {
        String avatar=getIntent().getStringExtra("avatar");
        String city_text=getIntent().getStringExtra("city_text");
        String mobile=getIntent().getStringExtra("mobile");
        String name=getIntent().getStringExtra("name");
        String is_shops=getIntent().getStringExtra("is_shops");
        Glide.with(this).load(avatar).into(iv_head);
        tv_name.setText(""+name);
        tv_phone.setText(""+mobile);
        tv_city_name.setText(""+city_text==null?"--":city_text);
        if (is_shops!=null){
            if (is_shops.equals("0")){
                tv_table.setText("帮帮用户");
            }else {
                tv_table.setText("骑手");
            }

        }

    }

    @Override
    protected void listener() {

    }
}
