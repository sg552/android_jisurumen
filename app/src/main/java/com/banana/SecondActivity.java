package com.banana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);

    // 这一行代码,
    findViewById(R.id.go_to_main_activity).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()) {
      case R.id.go_to_main_activity:

        Log.i("SecondActivity", "== go to main");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        break;
      default:
        break;

    }

  }
}