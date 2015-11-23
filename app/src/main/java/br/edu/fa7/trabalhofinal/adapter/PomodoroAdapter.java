package br.edu.fa7.trabalhofinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.activity.CadastroActivity;
import br.edu.fa7.trabalhofinal.activity.CadastroExcluirActivity;
import br.edu.fa7.trabalhofinal.dao.PomodoroDao;
import br.edu.fa7.trabalhofinal.model.ContextMenuItem;
import br.edu.fa7.trabalhofinal.model.Pomodoro;
import br.edu.fa7.trabalhofinal.service.ServiceTimer;

/**
 * Created by Osmar on 22/11/2015.
 */
public class PomodoroAdapter extends RecyclerView.Adapter<PomodoroAdapter.PomodoroViewHolder> {

    private List<Pomodoro> pomodoros;
    private Context context;
    private LayoutInflater layoutInflater;
    private ServiceTimer pomodoroService = new ServiceTimer();


    public PomodoroAdapter(Context context, List<Pomodoro> pomodoros) {
        this.context = context;
        this.pomodoros = pomodoros;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public PomodoroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.item_pomodoro, parent, false);
        PomodoroViewHolder pomodoroViewHolder = new PomodoroViewHolder(v);
        return pomodoroViewHolder;
    }

    @Override
    public void onBindViewHolder(PomodoroViewHolder holder, int position) {

        Pomodoro p = pomodoros.get(position);

        holder.tarefaTitulo.setText(p.getTitulo()+" ("+p.getId_pomodoro().toString()+")");
        holder.tarefaDescricao.setText(p.getDescricao());
        holder.tarefaPomodoro.setText("Qtd. Pomodoro: " + p.getQtd_pomodoro().toString());

        // Controle dos botões Iniciar e Concluir de acordo com a situação da tarefa
        holder.btIniciar.setEnabled(p.getSituacao()==0);
        holder.btConcluir.setEnabled(p.getSituacao()==1);

        if (p.getSituacao()==2){
            holder.itemView.setBackgroundColor(R.color.colorSecondaryText);
            holder.tarefaTitulo.setText(p.getTitulo() + " - CONCLUIDO");
        }

    }

    @Override
    public int getItemCount() {
        return pomodoros.size();
    }


    public Boolean tarefaEmExecucao(){
        PomodoroDao pomodoroDao = new PomodoroDao(context);
        int i = pomodoroDao.findStart();
        return (i>0);
    }


    public void atualizaSituacao(int position, int situacao){
        Pomodoro p = pomodoros.get(position);
        p.setSituacao(situacao);

        PomodoroDao pomodoroDao = new PomodoroDao(context);
        pomodoroDao.update(p);

    }


    public void editarPomodoro(int position){
        Pomodoro p = pomodoros.get(position);

        Intent it = new Intent(context, CadastroActivity.class);
        it.putExtra("id_pomodoro", (int) p.getId_pomodoro());
        it.putExtra("titulo", p.getTitulo());
        it.putExtra("descricao", p.getDescricao());
        it.putExtra("qtd_pomodoro", (int) p.getQtd_pomodoro());
        it.putExtra("situacao", (int) p.getSituacao());

        context.startActivity(it);
    }

    public void excluirPomodoro(int position){

        Pomodoro p = pomodoros.get(position);

        // Confirmar exclusão
        Intent it = new Intent(context, CadastroExcluirActivity.class);
        it.putExtra("id_pomodoro", (int) p.getId_pomodoro());
        it.putExtra("titulo", p.getTitulo());
        it.putExtra("descricao", p.getDescricao());

        context.startActivity(it);
    }


    public class PomodoroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tarefaTitulo;
        private TextView tarefaDescricao;
        private TextView tarefaPomodoro;
        private Button btIniciar;
        private Button btConcluir;
        private ImageView imgContextMenu;
        private static final int EDITAR = 0;
        private static final int EXCLUIR = 1;


        public PomodoroViewHolder(View itemView) {
            super(itemView);

            tarefaTitulo = (TextView) itemView.findViewById(R.id.tarefa_titulo);
            tarefaDescricao = (TextView) itemView.findViewById(R.id.tarefa_descricao);
            tarefaPomodoro = (TextView) itemView.findViewById(R.id.tarefa_qtd_pomodoro);
            btIniciar = (Button) itemView.findViewById(R.id.bt_iniciar);
            btConcluir = (Button) itemView.findViewById(R.id.bt_concluir);
            imgContextMenu = (ImageView) itemView.findViewById(R.id.img_context_menu);

            imgContextMenu.setOnClickListener(this);
            btIniciar.setOnClickListener(this);
            btConcluir.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.bt_iniciar:
                    if(tarefaEmExecucao()){
                        Toast.makeText(context, "Aguarde concluir a tarefa em andamento.", Toast.LENGTH_LONG).show();
                        break;
                    }
                    this.btConcluir.setEnabled(true);
                    this.btIniciar.setEnabled(false);
                    atualizaSituacao(getAdapterPosition(), 1);
                    pomodoroService.start(pomodoros.get(getAdapterPosition()).getQtd_pomodoro());
                    break;

                case R.id.bt_concluir:
                    this.btConcluir.setEnabled(false);
                    this.itemView.setBackgroundColor(R.color.colorSecondaryText);
                    atualizaSituacao(getAdapterPosition(), 2);
                    pomodoroService.stop();
                    break;

                case R.id.img_context_menu:
                    float scale = context.getResources().getDisplayMetrics().density;

                    List<ContextMenuItem> items = new ArrayList<>();
                    items.add(new ContextMenuItem(R.drawable.pencil, "Editar"));
                    items.add(new ContextMenuItem(R.drawable.delete, "Excluir"));
                    items.add(new ContextMenuItem(R.drawable.localizar, "Localizar"));
                    final ContextMenuAdapter adapter = new ContextMenuAdapter(context, items);

                    final ListPopupWindow listPopupWindow = new ListPopupWindow(context);
                    listPopupWindow.setAdapter(adapter);
                    listPopupWindow.setAnchorView(imgContextMenu);
                    listPopupWindow.setWidth((int)(240 * scale + 0.5f));
                    listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            switch (position) {

                                case EDITAR:
                                    editarPomodoro(getAdapterPosition());
                                    listPopupWindow.dismiss();
                                    break;

                                case EXCLUIR:
                                    excluirPomodoro(getAdapterPosition());
                                    listPopupWindow.dismiss();
                                    break;


                            }

                        }
                    });

                    listPopupWindow.setModal(true);
                    listPopupWindow.getBackground().setAlpha(0);
                    listPopupWindow.show();

                    break;

            }

        }
    }
}


