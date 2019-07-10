package com.lingc.zhihudaily.view.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lingc.zhihudaily.R;

public class ExceptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exception);
        Intent intent = getIntent();
        final String message = intent.getStringExtra("message");
        TextView textView = findViewById(R.id.exception_message_text);
        textView.setText(message);

        findViewById(R.id.exception_copy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Label", message);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(ExceptionActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
