package com.ethanhua.data.datasource.local;

import com.ethanhua.data.datasource.local.database.WatchHistoryRecord;
import com.ethanhua.domain.model.WatchRecord;

/**
 * Created by ethanhua on 2017/12/16.
 */

public class WatchRecordMapper {

    public static WatchRecord from(WatchHistoryRecord record) {
        WatchRecord item = new WatchRecord();
        item.userId = record.userId;
        item.position = record.position;
        item.videoId = record.videoId;
        item.actionUrl = record.actionUrl;
        item.playUrl = record.playUrl;
        item.coverUrl = record.coverUrl;
        item.blurredUrl = record.blurredUrl;
        item.title = record.title;
        item.slogan = record.slogan;
        item.category = record.category;
        item.duration = record.duration;
        item.description = record.description;
        item.collectionCount = record.collectionCount;
        item.shareCount = record.shareCount;
        item.replyCount = record.replyCount;
        item.authorName = record.authorName;
        item.authorIntro = record.authorIntro;
        item.authorAvatar = record.authorAvatar;
        item.bdPlayUrl = record.bdPlayUrl;
        item.supperPlayUrl = record.supperPlayUrl;
        item.highPlayUrl = record.highPlayUrl;
        item.normalPlayUrl = record.normalPlayUrl;
        item.lowPlayUrl = record.lowPlayUrl;
        item.updateTime = record.updateTime;
        return item;
    }

    public static WatchHistoryRecord to(WatchRecord record) {
        WatchHistoryRecord item = new WatchHistoryRecord();
        item.userId = record.userId;
        item.position = record.position;
        item.videoId = record.videoId;
        item.actionUrl = record.actionUrl;
        item.playUrl = record.playUrl;
        item.coverUrl = record.coverUrl;
        item.blurredUrl = record.blurredUrl;
        item.title = record.title;
        item.slogan = record.slogan;
        item.category = record.category;
        item.duration = record.duration;
        item.description = record.description;
        item.collectionCount = record.collectionCount;
        item.shareCount = record.shareCount;
        item.replyCount = record.replyCount;
        item.authorName = record.authorName;
        item.authorIntro = record.authorIntro;
        item.authorAvatar = record.authorAvatar;
        item.bdPlayUrl = record.bdPlayUrl;
        item.supperPlayUrl = record.supperPlayUrl;
        item.highPlayUrl = record.highPlayUrl;
        item.normalPlayUrl = record.normalPlayUrl;
        item.lowPlayUrl = record.lowPlayUrl;
        item.updateTime = record.updateTime;
        return item;
    }
}
