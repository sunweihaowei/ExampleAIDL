package com.kunminx.learningaidlbind;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * @ClassName Student
 * @Description TODO
 * @Author ${孙伟豪}
 * @Date 2020/11/5 10:18
 * @Version 1.0
 */
public class Student implements Parcelable {
    private static final String TAG = "Student";
    private int id;
    private String name;
    private double price;

    protected Student(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
    }

    public Student(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    //打包：将当前对象的属性数据打包：写到包对象中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d(TAG, "writeToParcel: 打包");
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
    }
    //解包：解读包中的数据，并封装成对象
    public static final Creator<Student> CREATOR = new Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel in) {
            Log.d("TAG", "createFromParcel:解包 ");
//            return new Student(in);//这个是自带的
            int id=in.readInt();
            String name = in.readString();
            double price = in.readDouble();
            return new Student(id,name,price);

        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
