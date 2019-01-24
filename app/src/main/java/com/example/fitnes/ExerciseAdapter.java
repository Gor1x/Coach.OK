package com.example.fitnes;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import APIParse.Exercise;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private List<Exercise> data;
    private List<Exercise> chosen;
    private ListItemClickListener mOnClickListener;

    public ExerciseAdapter(List<Exercise> data, List<Exercise> chosen, ListItemClickListener listener) {
        this.data = data;
        this.chosen = chosen;
        mOnClickListener = listener;
    }

    public void setData(List<Exercise> chosen){
        this.chosen = chosen;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_exercise,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        Exercise exercise = data.get(position);
        holder.name.setText(exercise.getName());
        if (chosen.contains(exercise))
            holder.cardView.setBackgroundColor(0xAAC5CAE9);
        else
            holder.cardView.setBackgroundColor(0x00FFFFFF);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView name;
        private ConstraintLayout cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.name);
            cardView = view.findViewById(R.id.card_view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemClick(clickedPosition);
                }
            });
        }
    }
}
