package com.ethanhua.data.datasource.local.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Created by ethanhua on 2017/12/16.
 */
@Entity(tableName = "watch_history_record",
        primaryKeys = {"video_id", "user_id"})
public class WatchHistoryRecord {
    @NonNull
    @ColumnInfo(name = "video_id")
    public long videoId;
    @NonNull
    @ColumnInfo(name = "user_id")
    public String userId;

    @ColumnInfo(name = "seek_position")
    public long position;

    @ColumnInfo(name = "action_url")
    public String actionUrl;

    @ColumnInfo(name = "play_url")
    public String playUrl;

    @ColumnInfo(name = "cover_url")
    public String coverUrl;

    @ColumnInfo(name = "blurred_url")
    public String blurredUrl;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "slogan")
    public String slogan;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "duration")
    public int duration;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "collection_count")
    public int collectionCount;

    @ColumnInfo(name = "share_count")
    public int shareCount;

    @ColumnInfo(name = "reply_count")
    public int replyCount;

    @ColumnInfo(name = "author_name")
    public String authorName;

    @ColumnInfo(name = "author_intro")
    public String authorIntro;

    @ColumnInfo(name = "author_avatar")
    public String authorAvatar;

    @ColumnInfo(name = "bd_play_url")
    public String bdPlayUrl;

    @ColumnInfo(name = "supper_play_url")
    public String supperPlayUrl;

    @ColumnInfo(name = "high_play_url")
    public String highPlayUrl;

    @ColumnInfo(name = "normal_play_url")
    public String normalPlayUrl;

    @ColumnInfo(name = "low_play_url")
    public String lowPlayUrl;

    @ColumnInfo(name = "update_time")
    public long updateTime;
}
