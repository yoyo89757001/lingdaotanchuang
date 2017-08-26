package com.example.xiaojun.lingdaotanchuang.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenjun on 2017/5/16.
 */

public class WeiShiBieBean {


    /**
     * status : unrecognized
     * blurness : {"motion":0.12329182,"gaussian":0.06325183}
     * attr : {"age":31.184730529785156,"male":0.9999841451644897,"female":1.58331331476802E-5}
     * track : 307
     * timestamp : 1494901874
     * pose : {"yaw":-0.09962569,"pitch":0.053999677}
     * feature : HlhkhVM0xiy4GMrC6kEgJ2VsCLujG9jj8XWTkMbWbRW956fpkcgPv3WOgwcZv7dpdkAy/MbLmmVN6YPT8PrXBpuanoesgmhOZeAzOyr+iVlN/EbBscfk2QUyr7/y6ge7buQUgmhnCRJ9dsXZq4f
     * face : {"image":"/9j/4AAQSkZdgOw3oaKKKfOwP/9k=","rect":{"top":510,"right":1215,"bottom":722,"left":1003}}
     * person : {"feature_id":0,"confidence":22.469938,"tag":"{\"subject_type\": 0, \"description\": \"\", \"start_time\": 0, \"birthday\": null, \"id\": 3, \"remark\": \"\", \"name\": \"\\u5154\\u5154\", \"title\": \"\", \"job_number\": \"\", \"entry_date\": null, \"end_time\": 0, \"department\": \"\", \"avatar\": \"/static/upload/photo/2017-05-16/02a0e944051a2c11b7987146bad9c84947e8a920.jpg\"}","id":"3"}
     * track_groups : {"short":93,"long":72}
     * quality : 0.8648006
     */

    private String status;
    private BlurnessBean blurness;
    private AttrBean attr;
    private Long track;
    private int timestamp;
    private PoseBean pose;
    private String feature;
    private FaceBean face;

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

    public Long getTrack() {
        return track;
    }

    public void setTrack(Long track) {
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
         * motion : 0.12329182
         * gaussian : 0.06325183
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
         * age : 31.184730529785156
         * male : 0.9999841451644897
         * female : 1.58331331476802E-5
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
         * yaw : -0.09962569
         * pitch : 0.053999677
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
         * image : /9j/4AAQSkZdgOw3oaKKKfOwP/9k=
         * rect : {"top":510,"right":1215,"bottom":722,"left":1003}
         */

        private String image;
        private RectBean rect;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public RectBean getRect() {
            return rect;
        }

        public void setRect(RectBean rect) {
            this.rect = rect;
        }

        public static class RectBean {
            /**
             * top : 510
             * right : 1215
             * bottom : 722
             * left : 1003
             */

            private int top;
            private int right;
            private int bottom;
            private int left;

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }

            public int getBottom() {
                return bottom;
            }

            public void setBottom(int bottom) {
                this.bottom = bottom;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }
        }
    }

    public static class TrackGroupsBean {
        /**
         * short : 93
         * long : 72
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
