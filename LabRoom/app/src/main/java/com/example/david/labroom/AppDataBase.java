package com.example.david.labroom;

import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

/**
 * Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Author David Soto 17551
 */

@Database(entities = {Weather.class}, version = 16)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract WeatherDaoClass dao();


    public static AppDataBase getDatabase(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context, AppDataBase.class,"weather").allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}

