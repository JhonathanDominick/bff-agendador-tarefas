package com.dominick.bff_agendador.infrastructure.exceptions;

public class BusinessExceptions extends RuntimeException {

    public BusinessExceptions(String mensagem){
        super(mensagem);
    }

    public BusinessExceptions(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
