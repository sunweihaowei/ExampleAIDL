package com.kunminx.learningaidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyRemoteService extends Service {
    public MyRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return new StudentService();
    }
    //处理student相关的业务类
    class StudentService extends IStudentService.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d(TAG, "basicTypes: Service getStudentById()"+anInt);
        }
    }
}
