package com.example.notepadplus;

import androidx.cardview.widget.CardView;

import com.example.notepadplus.Models.Notes;

// Interface for handling click events on Notes items
public interface NotesClickListener {

    // Method to be called when a note is clicked
    void onClick(Notes notes);

    // Method to be called when a note is long-clicked
    void onLongClick(Notes notes, CardView cardView);
}
