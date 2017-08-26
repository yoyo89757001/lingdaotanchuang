package com.example.xiaojun.lingdaotanchuang.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chenjun on 2017/5/16.
 */

public class ShiBieBean {


    /**
     * data : {"status":"recognized","blurness":{"motion":0.13436717,"gaussian":0.09232962},"attr":{"age":25.372713088989258,"male":0.938287079334259,"female":0.06171289458870888},"track":330,"timestamp":1494906636,"pose":{"yaw":-0.104917675,"pitch":0.12876546},"track_groups":{"short":123,"long":88},"quality":0.79744726}
     * screen : {"camera_address":"rtsp://192.166.2.56/user=admin&password=&channel=1&stream=0.sdp","allowed_subject_ids":[],"network_switcher_status":null,"box_token":"a031736c-5081-4e58-9dcb-b60e2bcde3f9","description":null,"box_heartbeat":1494902390,"network_switcher":"","camera_name":"","camera_status":0,"allow_visitor":true,"screen_token":"f57c017b-824b-455c-b9ce-44052832be8c","network_switcher_token":null,"box_status":"0","allow_all_subjects":true,"type":1,"id":1,"camera_position":"一体机","box_address":"192.166.2.65"}
     * person : {"src":"data:image/jpeg;base64,/9j/4AAQSkZJ//Z","remark":"","subject_type":0,"description":"","title":"","timestamp":1494906636,"start_time":0,"avatar":"/static/upload/photo/2017-05-16/fda50b09d94f46b12ad2654a83571dfe1b933c77.jpg","job_number":"","birthday":null,"entry_date":null,"department":"","end_time":0,"id":3,"name":"兔兔"}
     * error : 允许进入
     * open_door : true
     * type : recognized
     */

    private DataBeanSB data;
    private ScreenBeanSB screen;
    private PersonBeanSB person;
    private String error;
    private boolean open_door;
    private String type;

    public DataBeanSB getData() {
        return data;
    }

    public void setData(DataBeanSB data) {
        this.data = data;
    }

    public ScreenBeanSB getScreen() {
        return screen;
    }

    public void setScreen(ScreenBeanSB screen) {
        this.screen = screen;
    }

    public PersonBeanSB getPerson() {
        return person;
    }

    public void setPerson(PersonBeanSB person) {
        this.person = person;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isOpen_door() {
        return open_door;
    }

    public void setOpen_door(boolean open_door) {
        this.open_door = open_door;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBeanSB {
        /**
         * status : recognized
         * blurness : {"motion":0.13436717,"gaussian":0.09232962}
         * attr : {"age":25.372713088989258,"male":0.938287079334259,"female":0.06171289458870888}
         * track : 330
         * timestamp : 1494906636
         * pose : {"yaw":-0.104917675,"pitch":0.12876546}
         * track_groups : {"short":123,"long":88}
         * quality : 0.79744726
         */

        private String status;
        private BlurnessBean blurness;
        private AttrBean attr;
        private int track;
        private int timestamp;
        private PoseBean pose;
        private TrackGroupsBean track_groups;
        private double quality;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public BlurnessBean getBlurness() {
            return blurness;
        }

        public void setBlurness(BlurnessBean blurness) {
            this.blurness = blurness;
        }

        public AttrBean getAttr() {
            return attr;
        }

        public void setAttr(AttrBean attr) {
            this.attr = attr;
        }

        public int getTrack() {
            return track;
        }

        public void setTrack(int track) {
            this.track = track;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public PoseBean getPose() {
            return pose;
        }

        public void setPose(PoseBean pose) {
            this.pose = pose;
        }

        public TrackGroupsBean getTrack_groups() {
            return track_groups;
        }

        public void setTrack_groups(TrackGroupsBean track_groups) {
            this.track_groups = track_groups;
        }

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public static class BlurnessBean {
            /**
             * motion : 0.13436717
             * gaussian : 0.09232962
             */

            private double motion;
            private double gaussian;

            public double getMotion() {
                return motion;
            }

            public void setMotion(double motion) {
                this.motion = motion;
            }

            public double getGaussian() {
                return gaussian;
            }

            public void setGaussian(double gaussian) {
                this.gaussian = gaussian;
            }
        }

        public static class AttrBean {
            /**
             * age : 25.372713088989258
             * male : 0.938287079334259
             * female : 0.06171289458870888
             */

            private double age;
            private double male;
            private double female;

            public double getAge() {
                return age;
            }

            public void setAge(double age) {
                this.age = age;
            }

            public double getMale() {
                return male;
            }

            public void setMale(double male) {
                this.male = male;
            }

            public double getFemale() {
                return female;
            }

            public void setFemale(double female) {
                this.female = female;
            }
        }

        public static class PoseBean {
            /**
             * yaw : -0.104917675
             * pitch : 0.12876546
             */

            private double yaw;
            private double pitch;

            public double getYaw() {
                return yaw;
            }

            public void setYaw(double yaw) {
                this.yaw = yaw;
            }

            public double getPitch() {
                return pitch;
            }

            public void setPitch(double pitch) {
                this.pitch = pitch;
            }
        }

        public static class TrackGroupsBean {
            /**
             * short : 123
             * long : 88
             */

            @SerializedName("short")
            private int shortX;
            @SerializedName("long")
            private int longX;

            public int getShortX() {
                return shortX;
            }

            public void setShortX(int shortX) {
                this.shortX = shortX;
            }

            public int getLongX() {
                return longX;
            }

            public void setLongX(int longX) {
                this.longX = longX;
            }
        }
    }

    public static class ScreenBeanSB {
        /**
         * camera_address : rtsp://192.166.2.56/user=admin&password=&channel=1&stream=0.sdp
         * allowed_subject_ids : []
         * network_switcher_status : null
         * box_token : a031736c-5081-4e58-9dcb-b60e2bcde3f9
         * description : null
         * box_heartbeat : 1494902390
         * network_switcher :
         * camera_name :
         * camera_status : 0
         * allow_visitor : true
         * screen_token : f57c017b-824b-455c-b9ce-44052832be8c
         * network_switcher_token : null
         * box_status : 0
         * allow_all_subjects : true
         * type : 1
         * id : 1
         * camera_position : 一体机
         * box_address : 192.166.2.65
         */

        private String camera_address;
        private Object network_switcher_status;
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

        public Object getNetwork_switcher_status() {
            return network_switcher_status;
        }

        public void setNetwork_switcher_status(Object network_switcher_status) {
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

    public static class PersonBeanSB {
        /**
         * src : data:image/jpeg;base64,/9j/4AAQSkZJ//Z
         * remark :
         * subject_type : 0
         * description :
         * title :
         * timestamp : 1494906636
         * start_time : 0
         * avatar : /static/upload/photo/2017-05-16/fda50b09d94f46b12ad2654a83571dfe1b933c77.jpg
         * job_number :
         * birthday : null
         * entry_date : null
         * department :
         * end_time : 0
         * id : 3
         * name : 兔兔
         */

        private String src;
        private String remark;
        private int subject_type;
        private String description;
        private String title;
        private int timestamp;
        private int start_time;
        private String avatar;
        private String job_number;
        private Object birthday;
        private Object entry_date;
        private String department;
        private int end_time;
        private Long id;
        private String name;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSubject_type() {
            return subject_type;
        }

        public void setSubject_type(int subject_type) {
            this.subject_type = subject_type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getJob_number() {
            return job_number;
        }

        public void setJob_number(String job_number) {
            this.job_number = job_number;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getEntry_date() {
            return entry_date;
        }

        public void setEntry_date(Object entry_date) {
            this.entry_date = entry_date;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
