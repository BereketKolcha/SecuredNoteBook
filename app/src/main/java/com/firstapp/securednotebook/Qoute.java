package com.firstapp.securednotebook;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "qoute_table")
public class Qoute {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "qoute_uid")
    private int uid;
    @ColumnInfo(name = "qoute")
    private String qoute;
    @ColumnInfo(name = "qoute_writer")
    private String qoutewriter;
    @ColumnInfo(name = "qoute_type")
    private String qouteType;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQoute() {
        return qoute;
    }

    public void setQoute(String qoute) {
        this.qoute = qoute;
    }

    public String getQoutewriter() {
        return qoutewriter;
    }

    public void setQoutewriter(String qoutewriter) {
        this.qoutewriter = qoutewriter;
    }

    public String getQouteType() {
        return qouteType;
    }

    public void setQouteType(String qouteType) {
        this.qouteType = qouteType;
    }

    public Qoute(String qoute, String qoutewriter, String qouteType) {
        this.qoute = qoute;
        this.qoutewriter = qoutewriter;
        this.qouteType = qouteType;
    }


}
