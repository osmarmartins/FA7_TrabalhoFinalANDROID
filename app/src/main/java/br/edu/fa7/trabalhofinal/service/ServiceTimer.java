package br.edu.fa7.trabalhofinal.service;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by Osmar on 21/11/2015.
 */
public class ServiceTimer extends Service {

    private static final int SEGUNDOS_POMODORO = 1500; // (1500s = 25:00)

    private IBinder binder;
    private Boolean stop;
    private static Integer segundos;
    private static String tempo = "00:00";


    public ServiceTimer(){
        this.segundos = 0;
        this.tempo = "00:00";
        this.binder = new LocalBinder();
        this.stop=false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    public static void getTimer(final Activity act, final TextView tv){
        act.runOnUiThread(new Runnable() {
                              @Override
                              public void run() {
                                  // Critica p/ caso de término forçado (stop)
                                  if(segundos<0){
                                      tempo="00:00";
                                  }
                                  tv.setText(tempo);

                              }
                          }
        );

    }

    public void start(final Integer qtdPomodoros){

        new Thread(new Runnable() {

            @Override
            public void run() {
                segundos=qtdPomodoros*SEGUNDOS_POMODORO;
                while( (!stop) && (segundos > 0) ){
                    try {
                        Thread.sleep(1000);
                        segundos--;

                        Integer min = segundos/60;
                        Integer seg = segundos%60;

                        if(seg<10){
                            tempo = min.toString()+":0"+seg.toString();
                        }else{
                            tempo = min.toString()+":"+seg.toString();
                        }

                        if(min<10){
                            tempo = "0"+tempo;
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                stop = false;
            }
        }).start();

    }


    public void stop(){
        this.segundos=0;
        this.tempo = "00:00";
        this.stop=true;
    }


    public class LocalBinder extends Binder {
        public ServiceTimer getService(){
            return ServiceTimer.this;
        }
    }

}
