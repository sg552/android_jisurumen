package com.banana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ShowBlogActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_blog);

    //新页面接收数据
    Bundle bundle = this.getIntent().getExtras();
    //接收name值
    String blog_text_from_main_activity = bundle.getString("blog_text");

    TextView blog_text = (TextView)findViewById(R.id.blog_text);
    blog_text.setText(blog_text_from_main_activity);
  }


}