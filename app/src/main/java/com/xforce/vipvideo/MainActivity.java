package com.xforce.vipvideo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView_youku = (ImageView) findViewById(R.id.image_view_youku);
        ImageView imageView_iqiyi = (ImageView) findViewById(R.id.image_view_iqiyi);
        ImageView imageView_tecent = (ImageView) findViewById(R.id.image_view_tencent);
        imageView_youku.setOnClickListener(this);
        imageView_iqiyi.setOnClickListener(this);
        imageView_tecent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
        switch (v.getId()) {

            case R.id.image_view_youku:
                //setContentView(R.layout.activity_view);
                intent.putExtra("url", "http://www.youku.com/");
                startActivity(intent);
                break;
            case R.id.image_view_iqiyi:
                //setContentView(R.layout.activity_view);
                intent.putExtra("url", "http://www.iqiyi.com/");
                startActivity(intent);
                break;
            case R.id.image_view_tencent:
                //setContentView(R.layout.activity_view);
                intent.putExtra("url", "http://v.qq.com/");
                startActivity(intent);
                break;
        }
    }
}
