package com.kugou.butterknifetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import android.support.annotation.WorkerThread;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.annotation.BdView;
import com.kugou.mybind.MyBind;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends AppCompatActivity {


    @BdView(R.id.tv_hello) TextView tvHello;
    @BdView(R.id.btn_jump) Button btnJump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        InjectView.init(this);
        MyBind.bind(this);
        tvHello.setText("你好");
        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        getString(R.string.app_name);
        findViewById(R.id.btn_jump);
//        Toast.makeText(this,"111", Toast.LENGTH_LONG).show();
//        getSystemService(POWER_SERVICE);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getName("da");
//            }
//        });
//        setAlpha(21);
    }

    @WorkerThread
//    @CheckResult(suggest = "name")
    private String getName(@NonNull String str){
        if (str!=null){
            return "1";
        }
        return null;
    }
    @CallSuper
    protected void setAlpha(@IntRange(from = 0,to=255) int alpha){

    }


//    int func(State state){
//        switch (state){
//            case INIT:
//                return 0;
//            case WAITING:
//                return 1;
//            case UPLOADING:
//                return 2;
//            case PAUSE:
//                return 3;
//            case UPLOADINGFAIL:
//                return 4;
//            case SUCESS:
//                return 5;
//            case DEAD:
//                return 6;
//        }
//        return 0;
//    }

//    public static final int INIT = 0;
//    public static final int WAITING = 1;
//    public static final int UPLOADING = 2;
//    public static final int PAUSE = 3;
//    public static final int UPLOADINGFAIL = 4;
//    public static final int SUCESS = 5;
//    public static final int DEAD = 6;
//
//    @IntDef({INIT,WAITING,UPLOADING,PAUSE,UPLOADINGFAIL,SUCESS,DEAD})
//    public @interface State{}
//
//    public void setCurrState(@State int state){
//
//    }

//    public enum State {
//        INIT, WAITING, UPLOADING, PAUSE, UPLOADINGFAIL, SUCESS, DEAD
//    }

}
