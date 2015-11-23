package br.edu.fa7.trabalhofinal.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.fragment.PomodoroFragment;
import br.edu.fa7.trabalhofinal.service.ServiceTimer;

public class MainActivity extends AppCompatActivity {

    private TextView tempoPomodoro;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getSupportActionBar().setTitle("Técnica Pomodoro");
        this.getSupportActionBar().setSubtitle("Lista de Tarefas");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        this.tempoPomodoro = (TextView) findViewById(R.id.tempoPomodoro);
        tempoPomodoro.setText("Waiting...");

        threadTempo();

/*
        // Broadcast (notificação)
        Intent it = new Intent("POMODORO");
        PendingIntent pit = PendingIntent
                .getBroadcast(MainActivity.this, 0, it, 0);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.set(Calendar.SECOND, 15);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pit);
*/

        // Fragment da Lista de Tarefas
        PomodoroFragment pomodoroFragment = (PomodoroFragment) getSupportFragmentManager().findFragmentByTag("myPomodoroFragment");
        if (pomodoroFragment == null) {
            pomodoroFragment = new PomodoroFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_lista_tarefa, pomodoroFragment, "myPomodoroFragment");
            ft.commit();
        }


    }

    private void getTempoPomodoro(Activity act, TextView tv) {
        ServiceTimer.getTimer(act, tv);
    }

    private void threadTempo() {
        final Activity act = this;
        final TextView tv = tempoPomodoro;
        Log.i("log", "Entrou em  threadTempo");

        new Thread() {
            @Override
            public void run() {
                try{
                    Log.i("log", "new Thread. Executou getTempoPomodoro");
                    getTempoPomodoro(act, tv);
                    handler.postDelayed(getRunnable(), 1000);

                }catch (Exception e){
                    Log.i("log", "ERRO: "+e.getMessage());

                }

            }
        }.start();
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                threadTempo();
                Log.i("log", "Executou getRunnable");
            }
        };
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("log", "onResume MainActivity");
        threadTempo();


    }

    public void btAdicionarTarefaOnClick(View view) {
        Intent it = new Intent(this, CadastroActivity.class);
        it.putExtra("id_pomodoro", (int) 0);
        startActivity(it);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
