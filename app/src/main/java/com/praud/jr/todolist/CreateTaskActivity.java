package com.praud.jr.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.praud.jr.todolist.Model.Task;
import com.praud.jr.todolist.SQLite.ApplicationSQLiteOpenHelper;
import com.praud.jr.todolist.SQLite.DAOTask;

import java.util.ArrayList;


public class CreateTaskActivity extends ActionBarActivity {
    private ArrayList<Task> listTasks = new ArrayList<Task>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        setTitle(R.string.create_task);

        final Button submit = (Button) findViewById(R.id.submit_task);

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final EditText task_name = (EditText) findViewById(R.id.task_input);
                // persist la tache puis affiche la page d'accueil
                saveTask();
                Intent i = new Intent(CreateTaskActivity.this, MainActivity.class);
                startActivity(i);
                CreateTaskActivity.this.finish();
            }
        });
    }

    public void saveTask() {
        ApplicationSQLiteOpenHelper helper =
                ApplicationSQLiteOpenHelper.connexionDataBase(this);
        DAOTask daoTask = new DAOTask(helper.getWritableDatabase());

        final EditText position = (EditText) findViewById(R.id.task_position_input);
        final String t_position = (String) position.getText().toString();
        final Integer task_position = (Integer) Integer.parseInt(t_position);
        Log.i("LOCAL", "---> position :: " + task_position);

        final EditText name = (EditText) findViewById(R.id.task_input);
        final String task_name = name.getText().toString();


        Task t = new Task();
        t.setNom(task_name);
        t.setPosition(task_position);
        listTasks.add(t);

        daoTask.addTask(t);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
