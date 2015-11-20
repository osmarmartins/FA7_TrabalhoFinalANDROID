package br.edu.fa7.trabalhofinal.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.fa7.trabalhofinal.model.Pomodoro;

/**
 * Created by Osmar on 20/11/2015.
 */
public class PomodoroDao {

    public List<Pomodoro> findAll(){

        Integer[] id = {1, 2, 3, 4};
        String[] titulo = {"Lavar Carro", "Fazer Compras", "Consertar TV", "Preparar Churrasco"};
        String[] descrição = {"Lavar o carro, asprirar, limpar pneus", "Comprar para churrasco com amigos",
                              "Preparar a instalação da TV no deck", "Fazer o fogo, preparar carnes"};
        Integer[] qtd = {2, 3, 1, 6};
        Integer[] situacao = {0, 0, 0, 0};


        List<Pomodoro> lista = new ArrayList<>();
        Pomodoro pomodoro;
        for(int i=0; i<4; i++){
            pomodoro = new Pomodoro(id[i], titulo[i], descrição[i], qtd[i], situacao[i]);
            lista.add(pomodoro);
        }

        for(int i=0; i<4; i++){
            pomodoro = new Pomodoro(id[i]+4, titulo[i], descrição[i], qtd[i], situacao[i]);
            lista.add(pomodoro);
        }

        return lista;
    }
}
