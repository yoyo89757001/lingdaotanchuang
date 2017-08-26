package com.example.xiaojun.lingdaotanchuang.beans;

import java.util.List;

/**
 * Created by chenjun on 2017/4/4.
 */

public class WBWeiShiBieScreenBean {

    /**
     * camera_address : rtsp://192.168.2.52/user=admin&password=&channel=1&stream=0.sdp
     * allowed_subject_ids : []
     * network_switcher_status : 1
     * box_token : 95d83a45-2615-49cf-9efc-238aab4bf33a
     * description : null
     * box_heartbeat : 1491284781
     * network_switcher : 192.168.2.231
     * camera_name :
     * camera_status : 0
     * allow_visitor : true
     * screen_token : cd7b0f4c-f342-45b7-b0b2-42100626b8cc
     * network_switcher_token : null
     * box_status : 0
     * allow_all_subjects : true
     * type : 1
     * id : 1
     * camera_position : u529eu516cu5ba4u95e8u53e3
     * box_address : 192.168.2.50
     */

    private String camera_address;
    private int network_switcher_status;
    private String box_token;
    private Object description;
    private int box_heartbeat;
    private String network_switcher;
    private String camera_name;
    private int camera_status;
    private boolean allow_visitor;
    private String screen_token;
    private Object network_switcher_token;
    private String box_status;
    private boolean allow_all_subjects;
    private int type;
    private int id;
    private String camera_position;
    private String box_address;
    private List<?> allowed_subject_ids;

    public String getCamera_address() {
        return camera_address;
    }

    public void setCamera_address(String camera_address) {
        this.camera_address = camera_address;
    }

    public int getNetwork_switcher_status() {
        return network_switcher_status;
    }

    public void setNetwork_switcher_status(int network_switcher_status) {
        this.network_switcher_status = network_switcher_status;
    }

    public String getBox_token() {
        return box_token;
    }

    public void setBox_token(String box_token) {
        this.box_token = box_token;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getBox_heartbeat() {
        return box_heartbeat;
    }

    public void setBox_heartbeat(int box_heartbeat) {
        this.box_heartbeat = box_heartbeat;
    }

    public String getNetwork_switcher() {
        return network_switcher;
    }

    public void setNetwork_switcher(String network_switcher) {
        this.network_switcher = network_switcher;
    }

    public String getCamera_name() {
        return camera_name;
    }

    public void setCamera_name(String camera_name) {
        this.camera_name = camera_name;
    }

    public int getCamera_status() {
        return camera_status;
    }

    public void setCamera_status(int camera_status) {
        this.camera_status = camera_status;
    }

    public boolean isAllow_visitor() {
        return allow_visitor;
    }

    public void setAllow_visitor(boolean allow_visitor) {
        this.allow_visitor = allow_visitor;
    }

    public String getScreen_token() {
        return screen_token;
    }

    public void setScreen_token(String screen_token) {
        this.screen_token = screen_token;
    }

    public Object getNetwork_switcher_token() {
        return network_switcher_token;
    }

    public void setNetwork_switcher_token(Object network_switcher_token) {
        this.network_switcher_token = network_switcher_token;
    }

    public String getBox_status() {
        return box_status;
    }

    public void setBox_status(String box_status) {
        this.box_status = box_status;
    }

    public boolean isAllow_all_subjects() {
        return allow_all_subjects;
    }

    public void setAllow_all_subjects(boolean allow_all_subjects) {
        this.allow_all_subjects = allow_all_subjects;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCamera_position() {
        return camera_position;
    }

    public void setCamera_position(String camera_position) {
        this.camera_position = camera_position;
    }

    public String getBox_address() {
        return box_address;
    }

    public void setBox_address(String box_address) {
        this.box_address = box_address;
    }

    public List<?> getAllowed_subject_ids() {
        return allowed_subject_ids;
    }

    public void setAllowed_subject_ids(List<?> allowed_subject_ids) {
        this.allowed_subject_ids = allowed_subject_ids;
    }
}
