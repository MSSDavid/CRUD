package br.ufg.inf.es.integracao.dswww;

public class Conta {
    int numero;
    String cliente;
    double saldo;
    public Conta(int numero, String cliente, double saldo) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
    }
    
    @Override
    public String toString() {
        return numero +", "+ cliente +", "+saldo;
    }
}