package com.example.tstfuncionario_30.modelos;

public class Posicao implements Comparable<Posicao> {

    private String nome;
    private int pontos;

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    @Override
    public int compareTo(Posicao outraPosicao) {
        if (this.pontos > outraPosicao.getPontos()){
            return 1;
        }
        if (this.pontos < outraPosicao.getPontos()){
            return -1;
        }

        return 0;
    }
}