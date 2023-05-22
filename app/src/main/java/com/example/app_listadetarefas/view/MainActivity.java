package com.example.app_listadetarefas.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_listadetarefas.R;
import com.example.app_listadetarefas.mvp.MainMVP;
import com.example.app_listadetarefas.presenter.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private MainMVP.Presenter presenter;
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private static final String SWITCH_STATE_KEY = "switch_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        presenter = new MainPresenter(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populateList(recyclerView);
        boolean switchState = sharedPreferences.getBoolean(SWITCH_STATE_KEY, false);
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void findViews(){
        actionButton = findViewById(R.id.fab_add_task);
        recyclerView = findViewById(R.id.recyclerview_task);
    }

    private void setListener(){
        actionButton.setOnClickListener(v -> {
            presenter.openDetails();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(SWITCH_STATE_KEY, true);
            editor.apply();
        });
    }

}
