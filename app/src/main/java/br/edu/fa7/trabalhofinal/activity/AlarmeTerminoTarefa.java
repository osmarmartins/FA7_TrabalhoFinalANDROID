package br.edu.fa7.trabalhofinal.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Osmar on 23/11/2015.
 */
public class AlarmeTerminoTarefa extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Tempo esgotado!", Toast.LENGTH_LONG).show();
    }
}
