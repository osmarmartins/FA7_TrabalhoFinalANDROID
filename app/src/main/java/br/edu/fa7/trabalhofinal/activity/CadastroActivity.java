package br.edu.fa7.trabalhofinal.activity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.dao.PomodoroDao;
import br.edu.fa7.trabalhofinal.model.Pomodoro;

/**
 * Created by Osmar on 20/11/2015.
 */
public class CadastroActivity extends AppCompatActivity {

    private Integer id_pomodoro;
    private Integer situacao;

    private EditText titulo;
    private EditText descricao;
    private EditText qtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        this.titulo = (EditText) findViewById(R.id.edTitulo);
        this.descricao = (EditText) findViewById(R.id.edDescricao);
        this.qtd = (EditText) findViewById(R.id.edQtd);
        this.getSupportActionBar().setTitle("Técnica Pomodoro");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        id_pomodoro = b.getInt("id_pomodoro");

        // Incluir ou Editar Tarefa
        if(id_pomodoro>0){

            this.getSupportActionBar().setSubtitle("Editar de Tarefa");

            Integer iQtd = b.getInt("qtd_pomodoro");
            titulo.setText(b.getString("titulo"));
            descricao.setText(b.getString("descricao"));
            qtd.setText(iQtd.toString());
            situacao = b.getInt("situacao");


        }else{

            id_pomodoro = null;
            this.getSupportActionBar().setSubtitle("Cadastro de Tarefa");

        }

    }

    public void btSalvarOnClick(View view){

        try {
            Pomodoro pomodoro = new Pomodoro(id_pomodoro, titulo.getText().toString(), descricao.getText().toString(), Integer.parseInt(qtd.getText().toString()), situacao );
            PomodoroDao pomodoroDao = new PomodoroDao(getApplicationContext());

            if(id_pomodoro==null){
                pomodoroDao.insert(pomodoro);
            }else{
                pomodoroDao.update(pomodoro);
            }

            finish();
        }catch (Exception e){
            Toast.makeText(this, "Dados incorretos! Verifique se todos os campos estão preenchidos.", Toast.LENGTH_LONG).show();
        }

    }
}
