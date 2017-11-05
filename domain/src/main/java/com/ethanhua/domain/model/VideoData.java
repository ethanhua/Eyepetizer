package com.ethanhua.domain.model;

import java.util.List;

/**
 * Created by ethanhua on 2017/9/14.
 */

public class VideoData {
    /**
     * dataType : VideoBeanForClient
     * id : 7362
     * title : 100 人是如何交换名片的
     * slogan : null
     * description : 交换名片在商务场合中再正常不过，可是当与你交换名片的人多了起来，这个简单动作就变得愈加复杂。名片整理分享 app 「Eight」推出这支广告，完美传达了 Eight 定位：解决多人交换名片的烦忧。From 名刺アプリ Eight
     * provider : {"name":"YouTube","alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png"}
     * category : 广告
     * author : null
     * cover : {"feed":"http://img.kaiyanapp.com/9c2b5243eab9c70292be039e4375b197.jpeg?imageMogr2/quality/100","detail":"http://img.kaiyanapp.com/9c2b5243eab9c70292be039e4375b197.jpeg?imageMogr2/quality/100","blurred":"http://img.kaiyanapp.com/38f365925752b647215d5d39076ba0ba.jpeg?imageMogr2/quality/100","sharing":null,"homepage":null}
     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=default&source=qcloud
     * thumbPlayUrl : null
     * duration : 182
     * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=7362","forWeibo":"http://wandou.im/26ju92"}
     * releaseTime : 1463760000000
     * library : DAILY
     * playInfo : [{"height":360,"width":640,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=low&source=qcloud","size":9816972},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=low&source=ucloud","size":9816972}],"name":"流畅","type":"low","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=low&source=qcloud"},{"height":480,"width":854,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=normal&source=qcloud","size":13307246},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=normal&source=ucloud","size":13307246}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=normal&source=qcloud"},{"height":720,"width":1280,"urlList":[{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=high&source=qcloud","size":23966479},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=high&source=ucloud","size":23966479}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=high&source=qcloud"}]
     * consumption : {"collectionCount":9924,"shareCount":9427,"replyCount":270}
     * campaign : null
     * waterMarks : null
     * adTrack : null
     * tags : [{"id":384,"name":"日本广告","actionUrl":"eyepetizer://tag/384/?title=%E6%97%A5%E6%9C%AC%E5%B9%BF%E5%91%8A","adTrack":null},{"id":16,"name":"广告","actionUrl":"eyepetizer://tag/16/?title=%E5%B9%BF%E5%91%8A","adTrack":null},{"id":200,"name":"酷炫","actionUrl":"eyepetizer://tag/200/?title=%E9%85%B7%E7%82%AB","adTrack":null},{"id":512,"name":"中二","actionUrl":"eyepetizer://tag/512/?title=%E4%B8%AD%E4%BA%8C","adTrack":null},{"id":66,"name":"平面设计","actionUrl":"eyepetizer://tag/66/?title=%E5%B9%B3%E9%9D%A2%E8%AE%BE%E8%AE%A1","adTrack":null}]
     * type : NORMAL
     * titlePgc : null
     * descriptionPgc : null
     * remark : null
     * idx : 0
     * shareAdTrack : null
     * favoriteAdTrack : null
     * webAdTrack : null
     * date : 1463760000000
     * promotion : null
     * label : null
     * labelList : []
     * descriptionEditor : 交换名片在商务场合中再正常不过，可是当与你交换名片的人多了起来，这个简单动作就变得愈加复杂。名片整理分享 app 「Eight」推出这支广告，完美传达了 Eight 定位：解决多人交换名片的烦忧。From 名刺アプリ Eight
     * collected : false
     * played : false
     * subtitles : []
     * lastViewTime : null
     * playlists : null
     */

    public String dataType;
    public int id;
    public String title;
    public String slogan;
    public String description;
    public Provider provider;
    public String category;
    public Author author;
    public Cover cover;
    public String playUrl;
    public String thumbPlayUrl;
    public int duration;
    public WebUrl webUrl;
    public long releaseTime;
    public String library;
    public Consumption consumption;
    public Object campaign;
    public Object waterMarks;
    public Object adTrack;
    public String type;
    public Object titlePgc;
    public Object descriptionPgc;
    public Object remark;
    public int idx;
    public Object shareAdTrack;
    public Object favoriteAdTrack;
    public Object webAdTrack;
    public long date;
    public Object promotion;
    public Object label;
    public String descriptionEditor;
    public boolean collected;
    public boolean played;
    public Object lastViewTime;
    public Object playlists;
    public List<PlayInfo> playInfo;
    public List<Tag> tags;
    public List<?> labelList;
    public List<?> subtitles;
    public String text;
    public String actionUrl;
    public String image;
}
