package br.edu.fa7.trabalhofinal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.fa7.trabalhofinal.model.Pomodoro;

/**
 * Created by Osmar on 20/11/2015.
 */
public class PomodoroDao {

    private final String TABLE_NAME = "pomodoros";
    private PomodoroDatabase myDatabase;
    private SQLiteDatabase db;
    private Pomodoro pomodoro;


    private List<Pomodoro> lista;

    public PomodoroDao(Context context){
        myDatabase = new PomodoroDatabase(context);
        db = myDatabase.getReadableDatabase();

    }

    public void insert(Pomodoro pomodoro){
        ContentValues values = new ContentValues();
        values.put("titulo", pomodoro.getTitulo());
        values.put("descricao", pomodoro.getDescricao());
        values.put("qtd_pomodoro", pomodoro.getQtd_pomodoro());
        values.put("situacao", 0);

        db.insert(TABLE_NAME, null, values);
    }


    public void update(Pomodoro pomodoro){
        ContentValues values = new ContentValues();
        values.put("_id", pomodoro.getId_pomodoro());
        values.put("titulo", pomodoro.getTitulo());
        values.put("descricao", pomodoro.getDescricao());
        values.put("qtd_pomodoro", pomodoro.getQtd_pomodoro());
        values.put("situacao", pomodoro.getSituacao());

        db.update(TABLE_NAME, values, "_id=?", new String[]{pomodoro.getId_pomodoro().toString()});
    }


    public void delete(int id){
        db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
    }



    public Pomodoro findId(Integer id){

        Pomodoro pomodoro = null;
        Cursor c = db.query(TABLE_NAME, null, "_id=?", new String[]{String.valueOf(id)}, null, null, null);

        if(c.getCount() > 0) {
            c.moveToFirst();
            pomodoro = new Pomodoro(
                    c.getInt(c.getColumnIndex("_id")),
                    c.getString(c.getColumnIndex("titulo")),
                    c.getString(c.getColumnIndex("descricao")),
                    c.getInt(c.getColumnIndex("qtd_pomodoro")),
                    c.getInt(c.getColumnIndex("situacao"))
            );
        }

        return pomodoro;
    }



    public List<Pomodoro> findAll(){

        List<Pomodoro> pomodoros = null;
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, " _id ASC ");

        if(c.getCount() > 0){
            pomodoros = new ArrayList<>();
            c.moveToFirst();

            do{
                Pomodoro pomodoro = new Pomodoro(
                        c.getInt(c.getColumnIndex("_id")),
                        c.getString(c.getColumnIndex("titulo")),
                        c.getString(c.getColumnIndex("descricao")),
                        c.getInt(c.getColumnIndex("qtd_pomodoro")),
                        c.getInt(c.getColumnIndex("situacao"))
                );

                pomodoros.add(pomodoro);

            }while(c.moveToNext());

        }

        return pomodoros;
    }

    // Método para verificar a existência de alguma tarefa em excecução
    public int findStart(){

        Cursor c = db.query(TABLE_NAME, null, "situacao=1", null, null, null, null);
        return c.getCount();

    }

}
