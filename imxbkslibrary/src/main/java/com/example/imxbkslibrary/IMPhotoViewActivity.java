package com.example.imxbkslibrary;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class IMPhotoViewActivity extends FragmentActivity {

    private static final String TAG = "PhotoPreView";
    private String path ="";
    private ImageView img_display;
    private ImageView img_login_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_photo_view);
        IMSystem.getInstance().addActivity(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        img_display = findViewById(R.id.img_display);
        img_login_close = findViewById(R.id.img_login_close);
        img_login_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        path = getIntent().getStringExtra("photo");
        Glide.with(this).load(path).into(img_display);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IMSystem.getInstance().removeActivity(this);
    }
}
