package com.kunminx.communication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IAidl mIAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent mIntent=new Intent(MainActivity.this,CheckLoginService.class);
        bindService(mIntent,mConnection,BIND_AUTO_CREATE);
        try {
            boolean sunweihao = mIAidl.checkLogin("sunweihao", 123);//输入值，根据是否正确传回一个值
            if (sunweihao){
                Toast.makeText(this, "账号，密码正确", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIAidl = IAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIAidl=null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
