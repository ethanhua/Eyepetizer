package com.ethanhua.data.datasource.local.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ethanhua on 2017/12/16.
 */
@Database(entities = {WatchHistoryRecord.class}, version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static volatile AppDataBase INSTANCE;

    public abstract WatchHistoryRecordDao watchHistoryRecordDao();

    public static AppDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "Eyepetizer.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
