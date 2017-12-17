package com.ethanhua.data.datasource.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ethanhua on 2017/12/16.
 */
@Dao
public interface WatchHistoryRecordDao {

    /**
     * @param userId the user id
     * @return the WatchHistoryRecord select from the table
     */
    @Query("SELECT * FROM watch_history_record where user_id == :userId")
    Flowable<List<WatchHistoryRecord>> list(String userId);

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param record the record to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WatchHistoryRecord record);

    /**
     *
     */
    @Query("DELETE FROM watch_history_record")
    void deleteAll();
}
