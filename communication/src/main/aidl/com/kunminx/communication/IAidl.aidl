// IAidl.aidl
package com.kunminx.communication;

// Declare any non-default types here with import statements
import com.kunminx.communication.LoginBean;
interface IAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    /*void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);*/
            boolean checkLogin(String name,long psw);//写完这里在build，这样可以生成对应的代码
            boolean login(in LoginBean data);
}
