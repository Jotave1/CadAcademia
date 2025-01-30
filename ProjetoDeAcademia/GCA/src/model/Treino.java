package model;

public class Treino {
    private int id;
    private String ordem;
    private String descricao;

    public Treino(int id, String ordem, String descricao) {
        this.id = id;
        this.ordem = ordem;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}