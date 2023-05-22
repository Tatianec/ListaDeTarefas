package com.example.app_listadetarefas.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_listadetarefas.R;
import com.example.app_listadetarefas.model.entities.Task;
import com.example.app_listadetarefas.mvp.MainMVP;
import com.example.app_listadetarefas.view.RecyclerViewItemClickListener;

import java.util.Collections;
import java.util.List;

public class ItemTaskRecyclerAdapter extends RecyclerView.Adapter<ItemTaskRecyclerAdapter.ViewHolder> {

    private Context context;
    private MainMVP.Presenter presenter;
    private List<Task> data;
    private RecyclerViewItemClickListener clickListener;

    public ItemTaskRecyclerAdapter(Context context, List<Task> data, MainMVP.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    public void setClickListener(RecyclerViewItemClickListener listener) {
        clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = data.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.descTextView.setText(task.getDescription());

        if ( task.isFavorite()) {
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.yellow));
        } else {
            holder.favoriteImageView.setColorFilter(context.getColor(R.color.gray));
        }

        holder.favoriteImageView.setOnClickListener(v -> starClick(task));
        holder.btnDelete.setOnClickListener(v -> deleteClick(task));
        holder.btnEdit.setOnClickListener(v -> presenter.openDetails(task));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void starClick(Task task) {
        presenter.favoriteTask(task);
        sortDataByFavorite();
    }

    private void deleteClick(Task task) {
        presenter.deleteTask(task);
        notifyDataSetChanged();
    }


    public void sortDataByFavorite() {
        Collections.sort(data, (task1, task2) -> {
            boolean favorite1 = task1.isFavorite();
            boolean favorite2 = task2.isFavorite();

            if (favorite1 && !favorite2) {
                return -1;
            } else if (!favorite1 && favorite2) {
                return 1;
            } else {
                return 0;
            }
        });

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView;
        public TextView descTextView;
        public ImageView favoriteImageView;
        public ImageView btnEdit;
        public ImageView btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_title_listitem);
            descTextView = itemView.findViewById(R.id.text_desc_listitem);
            favoriteImageView = itemView.findViewById(R.id.image_favorite_listitem);
            btnEdit = itemView.findViewById(R.id.image_edit_listitem);
            btnDelete = itemView.findViewById(R.id.image_delete_listitem);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
