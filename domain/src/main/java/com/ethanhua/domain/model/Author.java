package com.ethanhua.domain.model;

/**
 * Created by ethanhua on 2017/9/14.
 */

public class Author {

    /**
     * id : 1757
     * icon : http://img.kaiyanapp.com/cf717ab7e314807c9b5ed698c98975ee.jpeg?imageMogr2/quality/60/format/jpg
     * name : FF 时尚在现场
     * description : 呈现最新时尚秀场，带你看世界。
     * link :
     * latestReleaseTime : 1505297361000
     * videoNum : 7
     * adTrack : null
     * follow : {"itemType":"author","itemId":1757,"followed":false}
     * shield : {"itemType":"author","itemId":1757,"shielded":false}
     * approvedNotReadyVideoCount : 0
     * ifPgc : true
     */

    public int id;
    public String icon;
    public String name;
    public String description;
    public String link;
    public long latestReleaseTime;
    public int videoNum;
    public Object adTrack;
    public Follow follow;
    public Shield shield;
    public int approvedNotReadyVideoCount;
    public boolean ifPgc;

}
