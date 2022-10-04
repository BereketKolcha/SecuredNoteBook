package com.firstapp.securednotebook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Qoute.class},version = 1)
public abstract class QouteRoomDatabase extends RoomDatabase{

    public abstract QouteDao qouteDao();
    private static volatile QouteRoomDatabase INSTANT;
    static QouteRoomDatabase getInstance(Context context){
    if(INSTANT==null){
        synchronized (QouteRoomDatabase.class){
            if(INSTANT==null){
                INSTANT= Room.databaseBuilder(context.getApplicationContext(),QouteRoomDatabase.class,"Qoute_Database").build();
            }
        }
    }
    return INSTANT;
}}
