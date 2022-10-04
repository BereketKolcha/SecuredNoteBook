package com.firstapp.securednotebook;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QouteDao {
   @Insert
    void insertQoute(Qoute qoute);

   @Query("SELECT * FROM qoute_table")
   List<Qoute> getAllQoute();

    @Query("SELECT * FROM qoute_table where qoute_uid=:id")
    Qoute findQouteById(int id);

    @Query("SELECT * FROM qoute_table where qoute=:q")
    Qoute findQouteByQoute(String q);

    @Delete
    void deleteQoute(Qoute qoute);

    @Update
    void updateQoute(Qoute qoute);
}
