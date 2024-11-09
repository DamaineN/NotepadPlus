package com.example.notepadplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notepadplus.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    // UI components for title and notes input fields, and save button
    EditText editText_title, editText_notes;
    ImageView imageView_save;
    Notes notes;

    // Flag to check if the note is being edited
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_taker);

        // Initialize UI components
        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        notes = new Notes();

        // Check if we are editing an old note
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            if (notes != null) {

                // Populate fields with existing note data
                editText_title.setText(notes.getTitle());
                editText_notes.setText(notes.getNotes());

                // Mark as editing existing note
                isOldNote = true;
            } else {
                notes = new Notes();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            notes = new Notes();
        }

        // Set up the save button click listener
        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get input from EditText fields
                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();

                // Validate that notes are not empty
                if (description.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please add some notes!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Format the current date and time
                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                Date date = new Date();

                // Set note properties
                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date));

                // Create an Intent to pass the note back to the calling activity
                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}