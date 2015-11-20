package br.edu.fa7.trabalhofinal.model;

/**
 * Created by Osmar on 19/11/2015.
 */
public class Pomodoro {

    private Integer id_pomodoro;
    private String titulo;
    private String descricao;
    private Integer qtd_pomodoro;
    private Integer situacao; // 0-Aguardando, 1-Iniciado, 2-Concluido

    public Pomodoro(Integer id_pomodoro, String titulo, String descricao, Integer qtd_pomodoro, Integer situacao) {
        this.id_pomodoro = id_pomodoro;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtd_pomodoro = qtd_pomodoro;
        this.situacao = situacao;
    }

    public Pomodoro(String titulo, String descricao, Integer qtd_pomodoro, Integer situacao) {
        this.id_pomodoro = null;
        this.titulo = titulo;
        this.descricao = descricao;
        this.qtd_pomodoro = qtd_pomodoro;
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Pomodoro{" +
                "id_pomodoro=" + id_pomodoro +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", qtd_pomodoro=" + qtd_pomodoro +
                ", situacao=" + situacao +
                '}';
    }

    public Integer getId_pomodoro() {
        return id_pomodoro;
    }

    public void setId_pomodoro(Integer id_pomodoro) {
        this.id_pomodoro = id_pomodoro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtd_pomodoro() {
        return qtd_pomodoro;
    }

    public void setQtd_pomodoro(Integer qtd_pomodoro) {
        this.qtd_pomodoro = qtd_pomodoro;
    }

    public Integer getSituacao() { return situacao; }

    public void setSituacao(Integer situacao) { this.situacao = situacao;  }
}
