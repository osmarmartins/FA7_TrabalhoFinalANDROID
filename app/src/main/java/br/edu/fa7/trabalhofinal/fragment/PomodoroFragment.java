package br.edu.fa7.trabalhofinal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.edu.fa7.trabalhofinal.R;
import br.edu.fa7.trabalhofinal.adapter.PomodoroAdapter;
import br.edu.fa7.trabalhofinal.dao.PomodoroDao;
import br.edu.fa7.trabalhofinal.model.Pomodoro;

/**
 * Created by Osmar on 19/11/2015.
 */
public class PomodoroFragment extends Fragment {

    private List<Pomodoro> mList;
    private RecyclerView mRecyclerView;


    // Lista de tarefas (pomodoros)
    private void recyclerViewItens(){
        mList = new PomodoroDao(getContext()).findAll();
        PomodoroAdapter pomodoroAdapter = new PomodoroAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(pomodoroAdapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pomodoro, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.lista);
        mRecyclerView.setHasFixedSize(true);


        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        recyclerViewItens();


        // Paginação (NÃO DEU TEMPO CONCLUIR)
//        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                Log.i("log", "onScrollStateChanged");
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Log.i("log", "onScrolled");
//
//            }
//        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerViewItens();

        Log.i("log", "onResumed");
    }

}
