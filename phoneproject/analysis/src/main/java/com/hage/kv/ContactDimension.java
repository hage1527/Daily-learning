package com.hage.kv;

import com.hage.kv.base.BaseDimension;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ContactDimension extends BaseDimension {

    private String phoneNum;
    private String name;

    public ContactDimension() {
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return phoneNum +'\t'
               + name;
    }

    @Override
    public int compareTo(BaseDimension o) {
        ContactDimension another = (ContactDimension) o;
        return this.phoneNum.compareTo(another.phoneNum);
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(phoneNum);
        out.writeUTF(name);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.phoneNum = in.readUTF();
        this.name=in.readUTF();
    }

    public static class CountDuration {
    }
}
