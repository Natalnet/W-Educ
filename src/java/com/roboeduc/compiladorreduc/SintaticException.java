/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

/**
 *
 * @author sarah
 */
public class SintaticException extends Exception {
    private String type;
    private int line;
    private String errorMessage;

    public SintaticException(String type, int line, String message){
        this.type = type;
        this.line = line + 1;
        this.errorMessage = message;
    }

    public String getType(){
        return this.type;
    }

    public int getLine(){
        return this.line;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

}