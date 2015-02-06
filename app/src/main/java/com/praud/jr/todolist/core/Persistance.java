package com.praud.jr.todolist.core;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Process;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Persistance
{
    public static void killProcess() {
        Process.killProcess(Process.myPid());
    }

    public static void alertQuit(
            final Context Ctx,
            String title,
            String msg,
            String btYes,
            String btNo) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Ctx);

        alertDialogBuilder.setTitle(title);

        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(btNo,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        killProcess();
                    }
                })
                .setNegativeButton(btYes,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void writeData(String name, String data, Context ctx) {
        try {
            FileOutputStream fOut = ctx.openFileOutput(name, 0);
            fOut.write(data.getBytes());
            fOut.close();
            Toast.makeText(ctx, name, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String checkValue(String value) {
        return value.matches("") ? "" : value;
    }

    public static boolean checkFile(final Context Ctx,String path) {
        File file = new File(Ctx.getFilesDir(), path);
        if(file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}

