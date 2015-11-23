package br.edu.fa7.trabalhofinal.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.activity.MainActivity;

/**
 * Created by bruno on 07/11/2015.
 */
public class BroadcastReceiver extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if("POMODORO".equals(intent.getAction())){

            Notification.Builder builder = new Notification.Builder(context)
                    .setSmallIcon(R.mipmap.pomodoro)
                    .setContentTitle("Tarefa Concluida!")
                    .setContentText("Notificação de conclução de tarefa")
                    .setAutoCancel(true)
                    .setStyle(
                            new Notification.BigPictureStyle()
                                    .bigPicture(BitmapFactory
                                            .decodeResource(context.getResources(), R.drawable.tarefa_concluida)));

            Intent resultIntent = new Intent(context, MainActivity.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);

            PendingIntent pit = stackBuilder
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pit);

            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, builder.build());

        }

    }

}
