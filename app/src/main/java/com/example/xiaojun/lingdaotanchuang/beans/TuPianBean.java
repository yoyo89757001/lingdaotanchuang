package com.example.xiaojun.lingdaotanchuang.beans;

/**
 * Created by chenjun on 2017/4/21.
 */

public class TuPianBean {

    /**
     * company_id : 1
     * id : 112
     * quality : 0.795969
     * subject_id : null
     * url : /static/upload/photo/2017-04-21/8d3b782031f1bed84c34ac3f449fb2595d0a4286.jpg
     * version : 3
     */

    private int company_id;
    private long id;
    private double quality;
    private Object subject_id;
    private String url;
    private int version;

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public Object getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Object subject_id) {
        this.subject_id = subject_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
