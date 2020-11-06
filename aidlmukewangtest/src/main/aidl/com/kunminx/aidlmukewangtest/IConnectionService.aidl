// IConnectionService.aidl
package com.kunminx.aidlmukewangtest;
// 连接服务

interface IConnectionService {
    void connect();//连接
    void disconnect();//断开连接
    boolean isConnection();//获取连接状态
}
