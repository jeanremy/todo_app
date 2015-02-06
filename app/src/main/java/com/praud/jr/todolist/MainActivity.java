package com.praud.jr.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.praud.jr.todolist.Model.Task;
import com.praud.jr.todolist.SQLite.ApplicationSQLiteOpenHelper;
import com.praud.jr.todolist.SQLite.DAOTask;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private TaskAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.todo);
        // Global list component
        ListView list = (ListView) findViewById(R.id.list_tasks);
        final ArrayList<Task> tasks = new ArrayList<Task>();
        this.adapter = new TaskAdapter(MainActivity.this, R.layout.row_task, tasks);
        list.setAdapter(adapter);

        new loadTask(this).execute();

        final ListView list_tasks = (ListView) findViewById(R.id.list_tasks);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.goto_create) {
            Intent i = new Intent(MainActivity.this, CreateTaskActivity.class);
            startActivity(i);
            MainActivity.this.finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private class loadTask extends AsyncTask<Void, Void, String> {
        /** Context of application. */
        private Context ctx;

        /**
         * Constructor LoadWebServiceF1.
         * @param ct : context of application.
         */
        public loadTask(final Context ct) {
            super();
            this.ctx = ct;
        }

        /* (non-Javadoc)
        * @see android.os.AsyncTask#onPreExecute()
        */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /* (non-Javadoc)
        * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
        */
        @Override
        protected final String doInBackground(final Void... params) {
            return null;
        }

        /* (non-Javadoc)
        * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
        */
        @Override
        protected final void onPostExecute(final String result) {
            super.onPostExecute(result);
            ApplicationSQLiteOpenHelper helper = ApplicationSQLiteOpenHelper.connexionDataBase(ctx);
            DAOTask daoClient = new DAOTask(helper.getWritableDatabase());
            ArrayList<Task> allClient = daoClient.getAllTask();
            adapter.clear();
            adapter.addAll(allClient);
            adapter.notifyDataSetChanged();

            Toast.makeText(this.ctx, getResources().getString(R.string.loading), Toast.LENGTH_LONG).show();

        }
    }


    /**
     * Class of client adapter.
     * @author edrichard.
     */
    public class TaskAdapter extends ArrayAdapter<Task> {
        /** Resource of application. */
        private int res;
        /**
         * Constructor.
         * @param context of application.
         * @param resource of application.
         * @param items of list view.
         */
        public TaskAdapter(
                final Context context,
                final int resource,
                final List<Task> items) {
            super(context, resource, items);
            this.res = resource;
        }
        @Override
        public final View getView(
                final int position,
                final View convertView,
                final ViewGroup parent) {
            Task item = this.getItem(position);
            LayoutInflater inf = LayoutInflater.from(getContext());
            View v = inf.inflate(this.res, null);
            TextView tvFn = (TextView) v.findViewById(R.id.row_name);
            tvFn.setText(item.getNom() + " " + item.getNom());
            return v;
        }
    }

}
