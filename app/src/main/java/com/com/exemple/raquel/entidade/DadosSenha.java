package com.com.exemple.raquel.entidade;

/**
 * Created by raquel on 27/09/17.
 */

public class DadosSenha {
    String senha;
    int codSenha;

    public DadosSenha(){
        
    }
    public DadosSenha(int codSenha, String senha){
        this.senha = senha;
        this.codSenha = codSenha;
    }
    public DadosSenha(String senha){
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCodSenha() {
        return codSenha;
    }

    public void setCodSenha(int codSenha) {
        this.codSenha = codSenha;
    }
}
