package br.edu.fa7.trabalhofinal.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Osmar on 20/11/2015.
 */
public class PomodoroDatabase extends SQLiteOpenHelper {

    private static final String DATABA_NAME = "pomodoros";
    private static final Integer DATABASE_VERSION = 1;

    public PomodoroDatabase(Context context) {
        super(context, DATABA_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Criação da tabela de pomodoros
        db.execSQL("CREATE TABLE IF NOT EXISTS pomodoros (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descricao TEXT," +
                "qtd_pomodoro INTEGER NOT NULL DEFAULT 1," +
                "situacao INTEGER NOT NULL DEFAULT 0); ");

        // Inserção de dados para exemplificação da app
        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('COMPRAS', 'Fazer compras de carnes e bebidas', 3);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('PREPARAR DECK', 'Limpar deck, piscina, organizar mesas e cadeiras',4);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('CHURRASCO', 'Preparar a churrasqueira', 1);  ");




        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('COMPRAS', 'Fazer compras de carnes e bebidas', 3);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('PREPARAR DECK', 'Limpar deck, piscina, organizar mesas e cadeiras',4);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('CHURRASCO', 'Preparar a churrasqueira', 1);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('COMPRAS', 'Fazer compras de carnes e bebidas', 3);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('PREPARAR DECK', 'Limpar deck, piscina, organizar mesas e cadeiras',4);  ");

        db.execSQL("INSERT INTO pomodoros (titulo, descricao, qtd_pomodoro) " +
                "VALUES ('CHURRASCO', 'Preparar a churrasqueira', 1);  ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE pomodoros; ");
        onCreate(db);

    }
}
