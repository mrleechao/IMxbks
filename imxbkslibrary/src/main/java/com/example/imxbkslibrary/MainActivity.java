package com.example.imxbkslibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.java_websocket.WebSocket;
public class MainActivity extends AppCompatActivity {
  private WebSocket webSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_activity_main);
        Toast.makeText(this, "hahaha", Toast.LENGTH_SHORT).show();
    }
}
