package com.example.fitnes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder> {

    private List<Training> data;
    private ListItemClickListener mOnClickListener;

    public TrainingAdapter(final List<Training> data, ListItemClickListener listener){
        this.data = data;
        mOnClickListener = listener;

    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex);

        void onListItemImgClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_training,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Training training = data.get(position);
        holder.name.setText(training.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Training> data){
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private View view;
        private TextView name;
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.name);
            img = view.findViewById(R.id.img);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemClick(clickedPosition);
                    Log.d("MYLOG", "OnCLICKset");
                }
            });

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    mOnClickListener.onListItemImgClick(clickedPosition);
                    Log.d("MYLOG", "onIMGclick");
                }
            });
        }
    }
}
