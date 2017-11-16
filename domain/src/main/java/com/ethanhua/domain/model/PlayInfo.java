package com.ethanhua.domain.model;

import java.util.List;

/**
 * Created by ethanhua on 2017/9/14.
 */

public class PlayInfo {
    /**
     * height : 360
     * width : 640
     * urlList : [{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=low&source=qcloud","size":9816972},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=low&source=ucloud","size":9816972}]
     * name : 流畅
     * type : low
     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=7362&editionType=low&source=qcloud
     */

    public static final String TYPE_HIGH = "high";
    public static final String TYPE_NORMAL = "normal";
    public static final String TYPE_LOW = "low";

    public int height;
    public int width;
    public String name;
    public String type;
    public String url;
    public List<VideoUrl> urlList;
}
