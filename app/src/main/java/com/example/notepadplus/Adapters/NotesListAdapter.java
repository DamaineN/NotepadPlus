package com.example.notepadplus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepadplus.Models.Notes;
import com.example.notepadplus.NotesClickListener;
import com.example.notepadplus.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Adapter class to manage and display a list of notes in a RecyclerView.
// It acts as a bridge between the data source and the RecyclerView,
// creating views for each note item, binding data to these views,
// and handling user interactions such as clicks and long clicks.



//Adapter class for displaying a list of notes in a RecyclerView
public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder>{
    Context context; // Context for inflating layout
    List<Notes> list; // List of notes to display
    NotesClickListener listener; // Listener for handling note click events

    // Constructor to initialize context, list of notes, and a click listener
    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    // Method to create new views (invoked by the layout manager)
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    // Method to replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        // Bind the data to the ViewHolder elements
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getNotes());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);

        // Check if the note is pinned and set the pin icon accordingly
        if (list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.ic_pin);
        }
        else {
            holder.imageView_pin.setImageResource(0);
        }

        // Set a random background color for the note
        int color_code = getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));

        // Set click listener for the note container
        holder.notes_container.setOnClickListener(view -> listener.onClick(list.get(holder.getAdapterPosition())));

        // Set long click listener for the note container
        holder.notes_container.setOnLongClickListener(view -> {
            listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_container);
            return true;
        });
    }

    // Method to get a random color from a predefined list of colors
    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }

    @Override
    // Method to return the size of the data set (invoked by the layout manager)
    public int getItemCount() {
        return list.size();
    }

    // Method to update the list of notes with a filtered list and refresh the RecyclerView
    public void filterList(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}

// ViewHolder class to hold the views for each note item
class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notes_container;
    TextView textView_title, textView_notes, textView_date;
    ImageView imageView_pin;

    // Constructor to initialize the ViewHolder elements
    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notes_container = itemView.findViewById(R.id.notes_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_notes = itemView.findViewById(R.id.textView_notes);
        textView_date = itemView.findViewById(R.id.textView_date);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);

    }
}
