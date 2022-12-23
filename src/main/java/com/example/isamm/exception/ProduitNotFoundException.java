package com.example.isamm.exception;

public class ProduitNotFoundException  extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ProduitNotFoundException() {
        super();
    }

    public ProduitNotFoundException(String customMessage) {
        super(customMessage);
    }
}
