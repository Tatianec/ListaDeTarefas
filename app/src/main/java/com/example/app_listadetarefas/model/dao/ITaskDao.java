package com.example.app_listadetarefas.model.dao;

import com.example.app_listadetarefas.model.entities.Task;
import com.example.app_listadetarefas.model.entities.Tag;

import java.util.List;

public interface ITaskDao {
    void create(Task task);

    boolean update(String oldTitle, Task task);

    boolean delete(Task task);

    Task findByTitle(String title);

    List<Task> findByTag(Tag tag);

    List<Task> findAll();
}
