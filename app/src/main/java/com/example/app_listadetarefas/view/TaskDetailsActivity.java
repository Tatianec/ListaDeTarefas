package com.example.app_listadetarefas.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_listadetarefas.R;
import com.example.app_listadetarefas.mvp.TaskDetailsMVP;
import com.example.app_listadetarefas.presenter.TaskDetailsPresenter;

public class TaskDetailsActivity extends AppCompatActivity
        implements TaskDetailsMVP.View, View.OnClickListener {

    private TaskDetailsMVP.Presenter presenter;
    private EditText descEditText;
    private EditText titleEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        presenter = new TaskDetailsPresenter(this);
        findViews();
        setListener();
        setToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.verifyUpdate();
    }

    @Override
    protected void onDestroy() {
        presenter.deatach();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == saveButton){
            if( titleEditText.getText().toString().isEmpty() || descEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Favor verificar os campos em branco!",
                        Toast.LENGTH_LONG).show();
            }else {
                presenter.saveTask(
                        titleEditText.getText().toString(),
                        descEditText.getText().toString());
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateUI(String title, String description) {
        titleEditText.setText(title);
        descEditText.setText(description);
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        presenter.deatach();
        finish();
    }

    private void findViews(){
        descEditText = findViewById(R.id.edittext_desc_details);
        titleEditText = findViewById(R.id.edittext_title_details);
        saveButton = findViewById(R.id.button_save_task);
    }

    private void setListener(){
        saveButton.setOnClickListener(this);
    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}