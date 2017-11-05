package com.ethanhua.domain.model;

import java.util.List;

/**
 * Created by ethanhua on 2017/9/14.
 */

public class VideoListData extends ListData<ItemData<VideoData>>{
    public int id;
    public String dataType;
    public ItemDataHeader header;
    public ItemData<VideoData> content;
    public Object adTrack;
    public String title;
    public String description;
    public String actionUrl;
    public String icon;
    public String text;
    public String slogan;
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
    public String image;
}
