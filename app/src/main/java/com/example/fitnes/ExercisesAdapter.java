package com.example.fitnes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {

    private List<Exercise> data;
    private ListItemClickListener mOnClickListener;

    public ExercisesAdapter(final List<Exercise> data, ListItemClickListener listener){
        this.data = data;
        mOnClickListener = listener;

    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_exercise,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = data.get(position);
        holder.name.setText(exercise.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Exercise> data){
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View view;
        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
            Log.d("MYLOG", "OnCLICKset");
        }

    }
}
