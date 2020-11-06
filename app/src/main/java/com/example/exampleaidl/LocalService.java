package com.example.exampleaidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalService extends Service {
    private static final String TAG = "LocalService";



    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        binder = new LocalBinder();//实例化本地绑定
        if (conn==null) {
            conn = new LocalConn();
        }

    }
    private LocalBinder binder;
    //创建本地服务绑定，实现获取服务名方法
    class LocalBinder extends ProcessService.Stub{

        @Override
        public String getServiceName() throws RemoteException {
            return "LocalService";
        }
    }
    private LocalConn conn;
    class LocalConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: Local连接远程服务成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: remoteService 被干掉");
            startService(new Intent(LocalService.this,RemoteService.class));
            //绑定远程服务
            bindService(new Intent(LocalService.this,RemoteService.class), conn, Context.BIND_IMPORTANT);//重要
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
