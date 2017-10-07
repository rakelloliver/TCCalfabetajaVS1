package com.com.exemple.raquel.entidade;

/**
 * Created by raquel on 17/09/17.
 */

public class DadosCrianca {
    String nomeCrianca;
    int idade;
    int codigoCrianca;




    public  DadosCrianca(String nomeCrianca){

    }
    public  DadosCrianca (int codigoCrianca, String nome, int idade){
        this.codigoCrianca = codigoCrianca;
        this.nomeCrianca = nome;
        this.idade = idade;
    }
    public  DadosCrianca (String nome, int idade){
        this.nomeCrianca = nome;
        this.idade = idade;
    }

    public DadosCrianca() {

    }


    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getCodigoCrianca() {
        return codigoCrianca;
    }

    public void setCodigoCrianca(int codigoCrianca) {
        this.codigoCrianca = codigoCrianca;
    }
}
