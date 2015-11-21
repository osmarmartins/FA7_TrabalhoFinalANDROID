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


    public void update(Context c){

    }


    public void delete(Context c){

    }



    public Pomodoro find(Context c, Integer arg){
        Pomodoro pomodoro;
        pomodoro = new Pomodoro();

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
                        c.getString(c.getColumnIndex("titulo")) +
                                " ("+c.getInt(c.getColumnIndex("_id"))+") - " +
                                c.getInt(c.getColumnIndex("situacao")),
                        c.getString(c.getColumnIndex("descricao")),
                        c.getInt(c.getColumnIndex("qtd_pomodoro")),
                        c.getInt(c.getColumnIndex("situacao"))
                );

                pomodoros.add(pomodoro);

            }while(c.moveToNext());

        }

        return pomodoros;
    }
}
