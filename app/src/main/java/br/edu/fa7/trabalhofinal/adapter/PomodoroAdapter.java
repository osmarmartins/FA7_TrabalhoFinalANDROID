package br.edu.fa7.trabalhofinal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tarefaTitulo.setText(mList.get(position).getTitulo());
        holder.tarefaDescricao.setText(mList.get(position).getDescricao());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tarefaTitulo;
        public TextView tarefaDescricao;

        public MyViewHolder(View itemView){
            super(itemView);

            tarefaTitulo = (TextView) itemView.findViewById(R.id.tarefa_titulo);
            tarefaDescricao = (TextView) itemView.findViewById(R.id.tarefa_descricao);

        }




    }

}
