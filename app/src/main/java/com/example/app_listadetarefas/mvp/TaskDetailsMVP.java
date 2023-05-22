package com.example.app_listadetarefas.mvp;

import android.os.Bundle;

public interface TaskDetailsMVP {

    interface View{
        void updateUI(String title, String description);

        Bundle getBundle();

        void showToast(String message);

        void close();
    }

    interface Presenter{
        void deatach();

        void verifyUpdate();

        void saveTask(String title, String desc);
    }
}
