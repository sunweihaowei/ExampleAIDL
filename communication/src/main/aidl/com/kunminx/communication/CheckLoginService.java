package com.kunminx.communication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

/**
 * @ClassName CheckLoginService
 * @Description TODO
 * @Author ${孙伟豪}
 * @Date 2020/11/5 14:31
 * @Version 1.0
 */
public class CheckLoginService extends Service {
    private static final String UserName="sunweihao";
    private static final String UserPsw="110";
    private IAidl.Stub mBinder=new IAidl.Stub() {
        @Override
        public boolean checkLogin(String name, long psw) throws RemoteException {
            return UserName.equals(name)&&UserPsw.equals(psw);//返回一个boolean这，账号密码是否正确
        }

    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
