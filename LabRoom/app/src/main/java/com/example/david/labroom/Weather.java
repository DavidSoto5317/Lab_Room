package com.example.david.labroom;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Reference: http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Author David Soto 17551
 */

@Entity(tableName = "weather"
//        ,
//        foreignKeys = {
//                @ForeignKey(
//                        entity = User.class,
//                        parentColumns = "id",
//                        childColumns = "userId",
//                        onDelete = ForeignKey.CASCADE
//                )},
//        indices = { @Index(value = "id")}
)
public class Weather {

    @PrimaryKey
    private final int id;
    private String description;

    public Weather(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
