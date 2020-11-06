package com.kunminx.communication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ClassName LoginBean
 * @Description TODO
 * @Author ${孙伟豪}
 * @Date 2020/11/5 14:59
 * @Version 1.0
 */
public class LoginBean implements Parcelable {
    private String loginName;
    private String loginPsw;
    private int loginSucc;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.loginName);
        dest.writeString(this.loginPsw);
        dest.writeInt(this.loginSucc);
    }

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.loginName = in.readString();
        this.loginPsw = in.readString();
        this.loginSucc = in.readInt();
    }

    public static final Parcelable.Creator<LoginBean> CREATOR = new Parcelable.Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}
