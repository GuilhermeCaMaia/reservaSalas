package br.com.alura.reservaSalas.excepetion;

public class ValidacaoExcepetion extends RuntimeException{
    public ValidacaoExcepetion(String mensagem){
        super(mensagem);
    }
}
