package com.example.xiaojun.lingdaotanchuang.beans;

/**
 * Created by chenjun on 2017/4/4.
 */

public class WBShiBieDataBean {


    /**
     * attr : {"age":32.93567657470703,"male":0.9998798966407776,"female":1.2008802150376141E-4}
     * track : 161
     * timestamp : 1493795108
     * pose : {"yaw":0.17175432,"pitch":-0.030060723}
     * feature : Ue
     * face : {"image":"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMQ//Z"}
     */

    private AttrBean attr;
    private int track;
    private int timestamp;
    private PoseBean pose;
    private String feature;
    private FaceBean face;

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

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public FaceBean getFace() {
        return face;
    }

    public void setFace(FaceBean face) {
        this.face = face;
    }

    public static class AttrBean {
        /**
         * age : 32.93567657470703
         * male : 0.9998798966407776
         * female : 1.2008802150376141E-4
         */

        private double age=30.0;
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
         * yaw : 0.17175432
         * pitch : -0.030060723
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

    public static class FaceBean {
        /**
         * image : /9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChMQ//Z
         */

        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
