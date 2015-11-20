package br.edu.fa7.trabalhofinal.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.fragment.PomodoroFragment;

public class MainActivity extends AppCompatActivity {

    private TextView tempoPomodoro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tempoPomodoro = (TextView) findViewById(R.id.tempoPomodoro);
        this.tempoPomodoro.setText("00:00");

        // Fragment da Lista de Tarefas
        PomodoroFragment pomodoroFragment = (PomodoroFragment) getSupportFragmentManager().findFragmentByTag("myPomodoroFragment");
        if(pomodoroFragment == null) {
            pomodoroFragment = new PomodoroFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_lista_tarefa, pomodoroFragment, "myPomodoroFragment");
            ft.commit();
        }


    }

    public void btAdicionarTarefaOnClick(View view){
        this.tempoPomodoro.setText("25:00");
        Toast.makeText(this, "Adicionar Tarefa foi acionado....", Toast.LENGTH_LONG).show();
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
