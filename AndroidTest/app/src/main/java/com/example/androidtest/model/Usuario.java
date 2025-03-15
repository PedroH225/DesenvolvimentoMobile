package com.example.androidtest.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;

    private Integer idade;

    private String uf;

    private String cidade;

    private String telefone;

    private  String email;

    private String tamanho;

    private ArrayList<String> cores;

    public Usuario() {
    }

    public Usuario(String nome, Integer idade,  String uf, String cidade, String telefone, String email, String tamanho, ArrayList<String> cores) {
        this.idade = idade;
        this.nome = nome;
        this.uf = uf;
        this.cores = cores;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", cores=" + cores +
                '}';
    }
}
