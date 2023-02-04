package com.example.demo.util;

public class Result<T> {
    private final T resultado;
    private final String mensajeError;
    private final boolean exito;

    private Result(T resultado, String mensajeError, boolean exito) {
        this.resultado = resultado;
        this.mensajeError = mensajeError;
        this.exito = exito;
    }

    public static <T> Result<T> exito(T resultado) {
        return new Result<>(resultado, null, true);
    }

    public static <T> Result<T> fallo(String mensajeError) {
        return new Result<>(null, mensajeError, false);
    }

    public T getResultado() {
        return resultado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public boolean fueExitoso() {
        return exito;
    }
}
