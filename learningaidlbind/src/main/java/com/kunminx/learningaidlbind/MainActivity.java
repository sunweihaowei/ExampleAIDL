package com.kunminx.learningaidlbind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtBind;
    private TextView mTv3;
    private Button mBtValue;
    private Button mBtUnbind;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtBind = (Button) findViewById(R.id.bt_bind);
        mTv3 = (TextView) findViewById(R.id.tv3);
        mBtValue = (Button) findViewById(R.id.bt_value);
        mBtUnbind = (Button) findViewById(R.id.bt_unbind);
        mTvContent = (TextView) findViewById(R.id.tv_content);

        mBtBind.setOnClickListener(this);
        mBtValue.setOnClickListener(this);
        mBtUnbind.setOnClickListener(this);
    }
    private ServiceConnection conn;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bind:
                Intent mIntent=new Intent("com.atguigu.learningaidl.remote.MyRemoteService.Action");
                if (conn==null){
                    conn=new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {

                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {

                        }
                    };
                    bindService(mIntent,conn, Context.BIND_AUTO_CREATE);
                    Toast.makeText(this, "绑定service", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "已经绑定Service", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.bt_value:

                break;
            case R.id.bt_unbind:
                if (conn!=null) {
                    unbindService(conn);
                    conn=null;
                    Toast.makeText(this, "解绑service", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
