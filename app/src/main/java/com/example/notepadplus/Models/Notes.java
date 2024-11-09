package com.example.notepadplus.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Entity class representing a Note in the Room database.
// This class defines the structure of the notes table in the database.
@Entity(tableName = "notes")
public class Notes implements Serializable {

    // Primary key for the notes table. The ID is auto-generated.
    @PrimaryKey(autoGenerate = true)
    int ID = 0;

    // Column for the title of the note.
    @ColumnInfo(name = "title")
    String title = "";

    // Column for the content of the note.
    @ColumnInfo(name = "notes")
    String notes = "";

    // Column for the date the note was created or modified.
    @ColumnInfo(name = "date")
    String date = "";

    // Column to indicate if the note is pinned.
    @ColumnInfo(name = "pinned")
    boolean pinned = false;

    //Getter and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }
}
