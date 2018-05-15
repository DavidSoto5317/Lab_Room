package com.example.david.labroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Author David Soto 17551
 */
@Dao
public interface WeatherDaoClass {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertWeather(Weather descripcion);

    @Query("SELECT * FROM weather WHERE id=:id")
        List<Weather> getDescriptionById(int id);

//        @Update(onConflict = OnConflictStrategy.REPLACE)
//        void updateTrophy(Trophy trophy);

//        @Query("delete from trophy where id = :id")
//        void delete(long id);
}
