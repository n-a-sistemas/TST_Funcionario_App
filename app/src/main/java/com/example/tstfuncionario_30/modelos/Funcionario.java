package com.example.tstfuncionario_30.modelos;

public class Funcionario {

    private String uuid;
    private String email;
    private String pontos;
    private String imgScr;
    private String nome;
    private String profissao;
    private String valido;
    private String epiValidade;

    public Funcionario() {
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                ", pontos='" + pontos + '\'' +
                ", imgScr='" + imgScr + '\'' +
                ", nome='" + nome + '\'' +
                ", profissao='" + profissao + '\'' +
                ", valido='" + valido + '\'' +
                ", epiValidade='" + epiValidade + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImgScr() {
        return imgScr;
    }

    public void setImgScr(String imgScr) {
        this.imgScr = imgScr;
    }

    public String getValido() {
        return valido;
    }

    public void setValido(String valido) {
        this.valido = valido;
    }

    public String getEpiValidade() {
        return epiValidade;
    }

    public void setEpiValidade(String epiValidade) {
        this.epiValidade = epiValidade;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPontos() {
        return pontos;
    }

    public void setPontos(String pontos) {
        this.pontos = pontos;
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

}
