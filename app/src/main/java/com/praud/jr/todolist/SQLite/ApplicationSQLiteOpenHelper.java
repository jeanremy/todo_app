package com.praud.jr.todolist.SQLite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ApplicationSQLiteOpenHelper extends SQLiteOpenHelper {

    /** Name of database. */
    public static final String DATABASE_CLIENT = "DBCLIENT";
    /** Name of database. */
    public static final Integer VERSION_DB = 1;

    public ApplicationSQLiteOpenHelper(final Context context,
                                       final String name,
                                       final SQLiteDatabase.CursorFactory factory,
                                       final int version) {
        super(context, name, factory, version);
    }

    public ApplicationSQLiteOpenHelper(final Context context) {
        super(context, DATABASE_CLIENT, null, VERSION_DB);
    }

    public static ApplicationSQLiteOpenHelper connexionDataBase(
            final Context ctx) {
        ApplicationSQLiteOpenHelper helper = new ApplicationSQLiteOpenHelper(
                ctx,
                ApplicationSQLiteOpenHelper.DATABASE_CLIENT,
                null,
                VERSION_DB);
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creationClient = DAOTask.getSchema();
        db.execSQL(creationClient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO :Mise à jour base de données
    }
}