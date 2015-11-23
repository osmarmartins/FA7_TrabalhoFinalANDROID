package br.edu.fa7.trabalhofinal.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Osmar on 22/11/2015.
 */
import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.dao.PomodoroDao;

public class CadastroExcluirActivity extends AppCompatActivity {

    private Button btExcluirSim;
    private Button btExcluirNao;
    private TextView tvTitulo;
    private TextView tvDescricao;
    private Integer id_pomodoro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_pomodoro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle b = getIntent().getExtras();

        tvTitulo = (TextView) findViewById(R.id.excluir_titulo);
        tvDescricao = (TextView) findViewById(R.id.excluir_descricao);
        btExcluirSim = (Button) findViewById(R.id.bt_excluir_sim);
        btExcluirNao = (Button) findViewById(R.id.bt_excluir_nao);

        id_pomodoro = b.getInt("id_pomodoro");
        tvTitulo.setText(b.getString("titulo"));
        tvDescricao.setText(b.getString("descricao"));

        btExcluirNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btExcluirSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluir();
                finish();
            }
        });

    }

    private void excluir(){
        PomodoroDao pomodoroDao = new PomodoroDao(this);
        pomodoroDao.delete(id_pomodoro);
        Toast.makeText(this, "Terfa Excluída!", Toast.LENGTH_SHORT).show();
    }

}
