package com.example.xiaojun.lingdaotanchuang.beans;

import java.util.List;

/**
 * Created by chenjun on 2017/4/21.
 */

public class ChuanJianUserBean {
    private String name;
    private String subject_type;
    private List<Long> photo_ids;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(String subject_type) {
        this.subject_type = subject_type;
    }

    public List<Long> getPhoto_ids() {
        return photo_ids;
    }

    public void setPhoto_ids(List<Long> photo_ids) {
        this.photo_ids = photo_ids;
    }
}
