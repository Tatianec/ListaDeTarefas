package com.example.app_listadetarefas.presenter;

import android.os.Bundle;

import com.example.app_listadetarefas.model.dao.TaskDaoSingleton;
import com.example.app_listadetarefas.model.dao.ITaskDao;
import com.example.app_listadetarefas.model.entities.Task;
import com.example.app_listadetarefas.mvp.TaskDetailsMVP;
import com.example.app_listadetarefas.utils.Constant;

public class TaskDetailsPresenter implements TaskDetailsMVP.Presenter {

    private TaskDetailsMVP.View view;
    private Task task;
    private ITaskDao dao;

    public TaskDetailsPresenter(TaskDetailsMVP.View view) {
        this.view = view;
        task = null;
        dao = TaskDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void verifyUpdate() {
        String title;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            title = bundle.getString(Constant.ATTR_TITLE);
            task = dao.findByTitle(title);
            view.updateUI(task.getTitle(), task.getDescription());
        }
    }

    @Override
    public void saveTask(String title, String desc) {

        if( task == null){
            task = new Task(desc, title);
            dao.create(task);
            view.showToast("Nova tarefa adicionada com sucesso.");
            view.close();
        }else{
            String oldTitle = task.getTitle();
            Task newTask = new Task(desc, title, task.isFavorite());
            if(dao.update(oldTitle, newTask)){
                view.showToast("Tarefa atualizada com sucesso.");
                view.close();
            }else{
                view.showToast("Erro ao atualizar a tarefa.");
            }
        }
    }
}
