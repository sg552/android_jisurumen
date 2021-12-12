package com.banana;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

// 增加这些, 用于获取http请求
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 这一行代码,
    findViewById(R.id.go_to_second_activity).setOnClickListener(this);
    findViewById(R.id.get_blogs).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()) {
      case R.id.go_to_second_activity:

        Log.i("MainActivity", "go to second activity");

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        break;
      case R.id.get_blogs:
        getBlogs();
      default:
        break;

    }

  }


  public void getBlogs(){
    OkHttpClient client = new OkHttpClient();
    String url = "https://siwei.me/interface/blogs/show?id=2347";
    final Activity that = this;
    Log.i("MainActivity", "== in validateLogin, urL " + url);
    Request request = new Request.Builder()
            .url(url)
            .get()
            .build();

    client.newCall(request)
            .enqueue(new Callback() {
              @Override
              public void onFailure(Call call, IOException e) {
                e.printStackTrace();
              }

              @Override
              public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();

                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Log.i("MainActivity", "== response: " + result);
                    Intent intent = new Intent(that, ShowBlogActivity.class);
                    startActivity(intent);
                  }
                });
              }
            });
  }
}