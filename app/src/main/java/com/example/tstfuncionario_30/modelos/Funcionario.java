package com.example.tstfuncionario_30.modelos;

public class Funcionario {

    private String uid;
    private String email;
    private String pontos;
    private String imagem;
    private String nome;
    private String profissao;
    private boolean valido;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", pontos='" + pontos + '\'' +
                ", imagem='" + imagem + '\'' +
                ", nome='" + nome + '\'' +
                ", profissao='" + profissao + '\'' +
                ", valido=" + valido +
                '}';
    }
}
