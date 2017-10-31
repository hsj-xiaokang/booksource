package com.example.hsj.beastactiontest.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author  hsj
 * @date 2017/10/31.
 */

public class Person implements Parcelable{
    private int age;
    private String name;
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(addr);

    }

    public static  final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){
        @Override
        public Person createFromParcel(Parcel source) {
            Person person = new Person();
            person.age = source.readInt();
            person.name = source.readString();
            person.addr = source.readString();
            return person;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
