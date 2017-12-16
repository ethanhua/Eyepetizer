package com.ethanhua.data.datasource.local.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

/**
 * Created by ethanhua on 2017/12/16.
 */
@Dao
public interface VideoDao {

    /**
     * @param id the user id
     * @return the user select from the table by user id
     */
    @Query("SELECT * FROM video WHERE id == :id")
    Video get(long id);

    /**
     * Insert a user in the database. If the user already exists, replace it.
     *
     * @param video the video to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Video video);

    /**
     *
     */
    @Query("DELETE FROM video")
    void deleteAll();
}
