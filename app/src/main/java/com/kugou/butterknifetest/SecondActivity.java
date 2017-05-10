package com.kugou.butterknifetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello)
    TextView tvHello;
    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        tvHello.setText("我是第二个activity");
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d("david", getClass().getName() + "--finalize");
    }

    static class ViewHolder {
        @BindView(R.id.tv_hello)
        TextView tvHello;
        @BindView(R.id.btn_jump)
        Button btnJump;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
