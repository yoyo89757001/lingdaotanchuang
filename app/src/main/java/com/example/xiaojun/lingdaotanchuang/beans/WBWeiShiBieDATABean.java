package com.example.xiaojun.lingdaotanchuang.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenjun on 2017/4/4.
 */

public class WBWeiShiBieDATABean {
    @Override
    public String toString() {
        return "WBWeiShiBieDATABean{" +
                "status='" + status + '\'' +
                ", blurness=" + blurness +
                ", attr=" + attr +
                ", track=" + track +
                ", timestamp=" + timestamp +
                ", pose=" + pose +
                ", feature='" + feature + '\'' +
                ", face=" + face +
                ", track_groups=" + track_groups +
                ", quality=" + quality +
                '}';
    }

    /**
     * status : unrecognized
     * blurness : {"motion":0.08079696,"gaussian":0.09961776}
     * attr : {"age":28.965023040771484,"male":0.999999463558197,"female":5.230931492405944E-7}
     * track : 66
     * timestamp : 1492492916
     * pose : {"yaw":0.1216202,"pitch":0.033680253}
     * feature : Jd8/ywIkS3huOU/NWe
     * face : {"image":"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChUqHkVAtTRcmgRoQ9BVyKqcPQVbiqWUi1F1FaNt1rOi61o2vWsplmmvQU6mr0FOrke53UfhCiiipNwooooENbpWTfHBrVbpWRf8Aeple2h5uIfvGczZpEUk8UY5qe3TnmvOqpylY5CaGHPOKtLbj0qqRchrWicA1pxRg9RRRVKVh8pIIgenSkaMAcUUVvFXVznlUs7WGGMfjQEFFFQ5WHCXO7H/9k=","rect":{"top":342,"right":1037,"bottom":939,"left":440}}
     * track_groups : {"short":16,"long":11}
     * quality : 0.85094666
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
         * motion : 0.08079696
         * gaussian : 0.09961776
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
         * age : 28.965023040771484
         * male : 0.999999463558197
         * female : 5.230931492405944E-7
         */

        private double age=0;
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
         * yaw : 0.1216202
         * pitch : 0.033680253
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
         * image : /9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAYEBQYFBAYGBQYHBwYIChAKCgkJChQODwwQFxQYGBcUFhYaHSUfGhsjHBYWICwgIyYnKSopGR8tMC0oMCUoKSj/2wBDAQcHBwoIChMKChUqHkVAtTRcmgRoQ9BVyKqcPQVbiqWUi1F1FaNt1rOi61o2vWsplmmvQU6mr0FOrke53UfhCiiipNwooooENbpWTfHBrVbpWRf8Aeple2h5uIfvGczZpEUk8UY5qe3TnmvOqpylY5CaGHPOKtLbj0qqRchrWicA1pxRg9RRRVKVh8pIIgenSkaMAcUUVvFXVznlUs7WGGMfjQEFFFQ5WHCXO7H/9k=
         * rect : {"top":342,"right":1037,"bottom":939,"left":440}
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
             * top : 342
             * right : 1037
             * bottom : 939
             * left : 440
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
         * short : 16
         * long : 11
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
