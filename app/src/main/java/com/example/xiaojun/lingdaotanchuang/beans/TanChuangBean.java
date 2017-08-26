package com.example.xiaojun.lingdaotanchuang.beans;

/**
 * Created by chenjun on 2017/5/16.
 */

public class TanChuangBean {
    private String name;
    private String touxiang;
    private byte[] bytes;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
