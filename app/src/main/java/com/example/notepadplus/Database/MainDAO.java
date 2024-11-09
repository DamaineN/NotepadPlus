package com.example.notepadplus.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notepadplus.Models.Notes;

import java.util.List;

// Data Access Object (DAO) interface for accessing and managing Notes data in the database.
// Defines the methods for interacting with the database, such as inserting, updating, deleting, and querying notes.
@Dao
public interface MainDAO {

    // Inserts a note into the database. If a conflict occurs (e.g., a note with the same ID already exists), it will be replaced.
    @Insert(onConflict = REPLACE)
    void insert(Notes notes);

    // Queries and returns a list of all notes from the database, ordered by ID in descending order.
    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Notes> getAll();

    // Updates the title and content of a note with the specified ID.
    @Query("UPDATE notes SET title = :title, notes = :notes WHERE ID = :ID")
    void update(int ID, String title, String notes);

    // Deletes a specified note from the database.
    @Delete
    void delete(Notes notes);

    // Updates the pinned status of a note with the specified ID.
    @Query("UPDATE notes SET pinned = :pin WHERE ID = :id")
    void pin(int id, boolean pin);

}
