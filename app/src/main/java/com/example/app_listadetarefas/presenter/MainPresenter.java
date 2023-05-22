package com.example.app_listadetarefas.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_listadetarefas.model.dao.TaskDaoSingleton;
import com.example.app_listadetarefas.model.dao.ITaskDao;
import com.example.app_listadetarefas.model.entities.Task;
import com.example.app_listadetarefas.mvp.MainMVP;
import com.example.app_listadetarefas.utils.Constant;
import com.example.app_listadetarefas.view.TaskDetailsActivity;
import com.example.app_listadetarefas.view.adapter.ItemTaskRecyclerAdapter;

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private ITaskDao dao;
    Task task;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TaskDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), TaskDetailsActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails(Task task) {
        Intent intent = new Intent(view.getContext(), TaskDetailsActivity.class);
        intent.putExtra(Constant.ATTR_TITLE, task.getTitle());
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemTaskRecyclerAdapter adapter = new
                ItemTaskRecyclerAdapter(view.getContext(), dao.findAll(), this);
        adapter.setClickListener(position -> {
            task = dao.findAll().get(position);
            openDetails(task);
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void favoriteTask(Task task) {
        task.setFavorite(! task.isFavorite());
        dao.update(task.getTitle(), task);
    }

    public void deleteTask(Task task) {
        dao.delete(task);
    }
}
