package com.example.app_listadetarefas.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_listadetarefas.model.entities.Task;

public interface MainMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();

        void openDetails();

        void openDetails(Task task);

        void populateList(RecyclerView recyclerView);

        void favoriteTask(Task task);

        void deleteTask(Task task);
    }
}
