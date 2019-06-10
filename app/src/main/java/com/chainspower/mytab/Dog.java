package com.chainspower.mytab;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description:
 * Date：2019/5/31-11:18
 * Author: cwh
 */
public class Dog implements Parcelable {

    String nama;
    int age;

    protected Dog(Parcel in) {
        nama = in.readString();
        age = in.readInt();
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeInt(age);
    }
}
