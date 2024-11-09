package com.example.notepadplus.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notepadplus.Models.Notes;

// Room Database class for the Notepad Plus app.
// It defines the database configuration and serves as the main access point for the underlying connection to the app's persisted data.
@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    // Singleton instance of the database to prevent multiple instances of the database opened at the same time.
    private static RoomDB database;

    // Name of the database.
    private static String DATABASE_NAME = "Notepad Plus";

    // Method to get the singleton instance of the database.
    // It ensures that only one instance of the database is created.
    public synchronized static RoomDB getInstance(Context context) {
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)

                    // Allows database operations on the main thread. (Not recommended for production use)
                    .allowMainThreadQueries()

                    // Recreates the database if no migration object is provided.
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    // Abstract method to get the DAO.
    // This method needs to be implemented to provide access to the DAO.
    public abstract MainDAO mainDAO();
}