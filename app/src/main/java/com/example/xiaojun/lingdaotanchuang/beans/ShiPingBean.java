package com.example.xiaojun.lingdaotanchuang.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/7/13.
 */
@Entity
public class ShiPingBean {

    /**
     * videoTitle : 视频
     * video : /commonfile/commonFile/download?id=8ede59a5cff94ec7847cd72d8b86b70b
     * timeOf : 13:22:00-20:22:47
     * timeDays : 2017-07-12
     * timeEnd : 2017-07-15
     */

    @Id@NotNull
    private String id;
    private String videoTitle;
    private String video;
    private String timeOf;
    private String timeDays;
    private String timeEnd;
    private boolean isRemove;
    private String videoType;
   private boolean isDownload;
@Generated(hash = 1613015686)
public ShiPingBean(@NotNull String id, String videoTitle, String video,
        String timeOf, String timeDays, String timeEnd, boolean isRemove,
        String videoType, boolean isDownload) {
    this.id = id;
    this.videoTitle = videoTitle;
    this.video = video;
    this.timeOf = timeOf;
    this.timeDays = timeDays;
    this.timeEnd = timeEnd;
    this.isRemove = isRemove;
    this.videoType = videoType;
    this.isDownload = isDownload;
}
@Generated(hash = 1582709193)
public ShiPingBean() {
}
public String getId() {
    return this.id;
}
public void setId(String id) {
    this.id = id;
}
public String getVideoTitle() {
    return this.videoTitle;
}
public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
}
public String getVideo() {
    return this.video;
}
public void setVideo(String video) {
    this.video = video;
}
public String getTimeOf() {
    return this.timeOf;
}
public void setTimeOf(String timeOf) {
    this.timeOf = timeOf;
}
public String getTimeDays() {
    return this.timeDays;
}
public void setTimeDays(String timeDays) {
    this.timeDays = timeDays;
}
public String getTimeEnd() {
    return this.timeEnd;
}
public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
}
public boolean getIsRemove() {
    return this.isRemove;
}
public void setIsRemove(boolean isRemove) {
    this.isRemove = isRemove;
}
public String getVideoType() {
    return this.videoType;
}
public void setVideoType(String videoType) {
    this.videoType = videoType;
}
public boolean getIsDownload() {
    return this.isDownload;
}
public void setIsDownload(boolean isDownload) {
    this.isDownload = isDownload;
}


}
