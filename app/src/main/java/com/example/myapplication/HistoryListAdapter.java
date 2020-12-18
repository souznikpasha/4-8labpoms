package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryItemViewHolder>{

    private ArrayList<HistoryItem> history;
    HistoryListAdapter(){
        history = new ArrayList<>();
    }

    void initialize(ArrayList<HistoryItem> history){
        this.history = history;
        notifyDataSetChanged();
    }

    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_list_item, viewGroup, false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder historyItemViewHolder, int i) {
        historyItemViewHolder.bind(history.get(i));
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    class HistoryItemViewHolder extends RecyclerView.ViewHolder {

        private TextView historyText;
        private Button historyButton;

        HistoryItemViewHolder(View itemView) {
            super(itemView);
            historyText = itemView.findViewById(R.id.history_text);
            historyButton = itemView.findViewById(R.id.history_button);
        }

        void bind(HistoryItem historyItem) {
            historyText.setText(historyItem.getTextRepresentation());
            historyButton.setOnClickListener(v ->
                    Toast.makeText(historyButton.getContext(),
                        historyItem.getTextRepresentation(),
                        Toast.LENGTH_SHORT)
                            .show());
        }
    }
}
