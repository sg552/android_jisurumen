package com.banana;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // 这一行代码,
    findViewById(R.id.go_to_second_activity).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()) {
      case R.id.go_to_second_activity:

        Log.i("MainActivity", "go to second activity");

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        break;
      default:
        break;

    }

  }


  public void getBlogs(String email, String password){
    OkHttpClient client = new OkHttpClient();
    String url = Constants.URL_VALIDATE_LOGIN + "?email="+email+"&password="+password;
    final Activity that = this;
    Log.i(TAG, "== in validateLogin, urL " + url);
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
                Gson gson = new Gson();
                final LoginResult loginResult = gson.fromJson(result, LoginResult.class);

                runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    if(loginResult.result.equals("success") ){
                      saveUserId(loginResult.user_id);
                      Intent intent = new Intent(that, MainActivity.class);
                      startActivity(intent);
                    }else{
                      Toast.makeText(that, "用户名与密码不匹配", Toast.LENGTH_SHORT).show();
                    }
                  }
                });
              }
            });
  }
}