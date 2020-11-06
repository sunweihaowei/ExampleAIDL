package com.kunminx.aidlmukewangtest;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.widget.Toast;

public class RemoteService extends Service {
    public RemoteService() {
    }
    //设置远程服务状态,默认为false（不连接）
    private boolean isConnected=false;
    //下面三个方法都是在新进程里的子线程中进行的，所以Toast要用handle来进行线程通讯
    private Handler handler=new Handler(Looper.getMainLooper());
    private IConnectionService connectionService=new IConnectionService.Stub() {
        @Override
        public void connect() throws RemoteException {
            //模拟新进程耗时操作
            try {
                Thread.sleep(3000);
                isConnected=true;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RemoteService.this, "connect", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void disconnect() throws RemoteException {
            isConnected=false;
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RemoteService.this, "disConnect", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public boolean isConnection() throws RemoteException {
            //获取连接状态
            return isConnected;
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return connectionService.asBinder();
    }
}
