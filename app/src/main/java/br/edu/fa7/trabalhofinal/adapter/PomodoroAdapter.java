package br.edu.fa7.trabalhofinal.adapter;

import android.content.Context;
import android.nfc.TagLostException;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.model.Pomodoro;

/**
 * Created by Osmar on 19/11/2015.
 */
public class PomodoroAdapter extends RecyclerView.Adapter<PomodoroAdapter.MyViewHolder> {

    private List<Pomodoro> mList;
    private LayoutInflater mLayoutInflater;

    public PomodoroAdapter(Context c, List<Pomodoro> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_pomodoro, parent, false);
        final MyViewHolder mvh = new MyViewHolder(v);

        final Button btIniciar = (Button) v.findViewById(R.id.bt_iniciar);
        final Button btConcluir = (Button) v.findViewById(R.id.bt_concluir);

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = mvh.getAdapterPosition();
                mList.get(position).setSituacao(1);
                Log.i("log", "Botão Iniciar clicado.... Item: " + mList.get(position).toString());
                btConcluir.setEnabled(true);
                btIniciar.setEnabled(false);
            }
        });

        btConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mvh.getAdapterPosition();
                mList.get(position).setSituacao(2);
                Log.i("log", "Botão CONCLUIR clicado.... Item: "+mList.get(position).toString());
                btConcluir.setEnabled(false);
                mvh.itemView.setBackgroundColor(R.color.colorAccent);

            }
        });


        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tarefaTitulo.setText(mList.get(position).getTitulo());
        holder.tarefaDescricao.setText(mList.get(position).getDescricao());
        holder.tarefaPomodoro.setText("Qtd. Pomodoro: " + mList.get(position).getQtd_pomodoro());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tarefaTitulo;
        private TextView tarefaDescricao;
        private TextView tarefaPomodoro;


        public MyViewHolder(View itemView){
            super(itemView);

            tarefaTitulo = (TextView) itemView.findViewById(R.id.tarefa_titulo);
            tarefaDescricao = (TextView) itemView.findViewById(R.id.tarefa_descricao);
            tarefaPomodoro = (TextView) itemView.findViewById(R.id.tarefa_qtd_pomodoro);


        }

    }

}
