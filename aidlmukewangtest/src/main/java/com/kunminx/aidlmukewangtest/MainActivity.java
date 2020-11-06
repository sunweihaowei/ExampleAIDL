package com.kunminx.aidlmukewangtest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtConnect;
    private Button mBtDisConnect;
    private Button mBtIsConnected;

    private IConnectionService mConnectionService;
    private boolean connection=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Intent mIntent = new Intent(this, RemoteService.class);
        bindService(mIntent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //初始化aidl创建的接口
                mConnectionService = IConnectionService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);
    }

    private void initView() {
        mBtConnect = (Button) findViewById(R.id.bt_connect);
        mBtDisConnect = (Button) findViewById(R.id.bt_disConnect);
        mBtIsConnected = (Button) findViewById(R.id.bt_isConnected);

        mBtConnect.setOnClickListener(this);
        mBtDisConnect.setOnClickListener(this);
        mBtIsConnected.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_connect:
                //连接
                try {
                    mConnectionService.connect();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_disConnect:
                //断开
                try {
                    mConnectionService.disconnect();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_isConnected:
                try {
                    connection = mConnectionService.isConnection();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, String.valueOf(connection), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
