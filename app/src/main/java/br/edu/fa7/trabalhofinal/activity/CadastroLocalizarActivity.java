package br.edu.fa7.trabalhofinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.dao.PomodoroDao;
import br.edu.fa7.trabalhofinal.model.Pomodoro;

/**
 * Created by Osmar on 23/11/2015.
 */
public class CadastroLocalizarActivity extends AppCompatActivity {

    private EditText idPomodoro;
    private TextView tvTitulo;
    private TextView tvDescricao;
    private Button btOk;
    private Button btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizar_pomodoro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        idPomodoro = (EditText) findViewById(R.id.id_tarefa);
        tvTitulo = (TextView) findViewById(R.id.localizar_titulo);
        tvDescricao = (TextView) findViewById(R.id.localizar_descricao);
        btOk = (Button) findViewById(R.id.bt_ok);
        btSair = (Button) findViewById(R.id.bt_sair);

        tvTitulo.setText("");
        tvDescricao.setText("");
    }

    public void btOkOnClick(View view){
        PomodoroDao pomodoroDao = new PomodoroDao(getApplicationContext());
        Pomodoro pomodoro = pomodoroDao.findId(Integer.parseInt(idPomodoro.getText().toString()));

        if(pomodoro!=null){
            tvTitulo.setText(pomodoro.getTitulo().toString());
            tvDescricao.setText(pomodoro.getDescricao().toString());
        }else{
            Toast.makeText(this, "Tarefa não localizada!", Toast.LENGTH_SHORT).show();
        }


    }

    public void btSairOnClick(View view){
        finish();
    }
}
