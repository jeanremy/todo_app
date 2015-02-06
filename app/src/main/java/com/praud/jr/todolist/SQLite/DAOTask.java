package com.praud.jr.todolist.SQLite;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.praud.jr.todolist.Model.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class DAOTask implements Serializable {

    private static final long serialVersionUID = 4424516457383949626L;
    public static final String TASK = "tâche";
    public static final String TASK_ID = "id";
    public static final String TASK_NOM = "nom";
    public static final String TASK_POSITION = "position";

    /** SQLiteDatabase object. */
    private SQLiteDatabase db;

    public DAOTask(final SQLiteDatabase database) {
        this.db = database;
    }

    /**
     * Création de la table SQL 'task'.
     * @return schéma de la table task.
     */
    public static String getSchema() {
        return "create table " + TASK + " ("
                + TASK_ID + " AUTO_INCREMENT INT, "
                + TASK_POSITION + " INT, "
                + TASK_NOM + " VARCHAR(255) NOT NULL )";
    }

    public final void addTask(final Task task) {
        ContentValues item = new ContentValues();
        item.put(TASK_ID, task.getId());
        item.put(TASK_NOM, task.getNom());
        item.put(TASK_POSITION, task.getPosition());
        db.insert(TASK, null, item);
    }

    /**
     * Retourne toutes les tâches.
     * @return les tâches.
     */
    public final ArrayList<Task> getAllTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String[] cols = {TASK_POSITION, TASK_NOM};
        String orderBy = TASK_POSITION + " ASC";
        Cursor c = db.query(TASK, cols, null, null, null, null, orderBy);
        if (c.getCount() > 0) {
            c.moveToFirst();
            Task itemTask;
            do {
                itemTask = new Task();
                itemTask.setPosition(c.getInt(c.getColumnIndex(TASK_POSITION)));
                itemTask.setNom(c.getString(c.getColumnIndex(TASK_NOM)));
                tasks.add(itemTask);
            } while (c.moveToNext());
        }
        return tasks;
    }

    /**
     * Retourne une tâche par id.
     * @param task (Task) on task.
     */
    public final void getTask(final Task task) {
        String[] cols = {TASK_NOM};
        String whereClause = TASK_ID + " = ?";
        String[] values = {String.valueOf(task.getId())};
        Cursor c = db.query(TASK, cols, whereClause, values, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            //Task.setNom(c.getString(c.getColumnIndex(TASK_NOM)));
        }
    }
    /**
     * Vérifie si la tâche existe.
     * @param taskId (String) id de la tâche.
     * @return boolean.
     */
    public final Boolean getTaskExist(final String taskId) {
        String[] cols = {TASK_ID};
        String whereClause = TASK_ID + " = ?";
        String[] values = {String.valueOf(taskId)};
        Cursor c =
                db.query(TASK, cols, whereClause, values, null, null, null);
        return (c.getCount() > 0);
    }

}