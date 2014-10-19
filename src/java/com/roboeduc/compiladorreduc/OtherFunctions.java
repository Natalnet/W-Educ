/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

/**
 * 
 * @author carlafernandes
 */
public class OtherFunctions {
    analisadorSintatico sintatico;
    private int loopFunc = 0;
    
    public OtherFunctions(analisadorSintatico sintatico) {
        this.sintatico = sintatico;
    }

    public int getLoopFunc() {
        return loopFunc;
    }

    public void setLoopFunc(int loopFunc) {
        this.loopFunc = loopFunc;
    }
    
    private void writeOnFile(String name) {
        sintatico.writeOnFile(name);
    }
    
    private int getPosition() {
        return sintatico.getPosition();
    }
    
    private void setPosition(int position) {
        sintatico.setPosition(getPosition() + position);
    }
    
    private int getBracketsCounter() {
        return sintatico.getBracketsCounter();
    }
    
    private void setBracketsCounter(int bc) {
        sintatico.setBracketsCounter(getBracketsCounter() + bc);
    }
    
    private String getName(int position) {
        return sintatico.getName(position);
    }
    
    private int getLine(int position) {
        return sintatico.getLine(position);
    }
    
    private int getListSize() {
        return sintatico.getFileListSize();
    }
    
    private boolean isNumberList(String name) {
        return sintatico.numberListContains(name);
    }
    
    private boolean isNameList(String name) {
        return sintatico.nameListContains(name);
    }
    
    private boolean isBooleanList(String name) {
        return sintatico.booleanListContains(name);
    }
    
    private boolean getError() {
        return sintatico.isError();
    }
    
    private void errorFunction(int line, String name) {
        sintatico.errorFunction(line, name);
    }
    
    private boolean isNumber(String name) {
        return sintatico.isNumber(name);
    }
    
    private boolean isBoolean(String name) {
        return sintatico.isBoolean(name);
    }
    
    private void writeString(boolean declaring) {
        sintatico.writeString(declaring);
    }
    
    private boolean keywords(String name) {
        return sintatico.keywords(name);
    }
    
    private void addNumber(String name) {
        sintatico.numberListAdd(name);
    }
    
    private void addBoolean(String name) {
        sintatico.booleanListAdd(name);
    }
    
    private void addName(String name) {
        sintatico.nameListAdd(name);
    }
    
    private String[] getDeclareString() {
        return sintatico.getDeclareString();
    }
    
    private String[] getDeclareNumber() {
        return sintatico.getDeclareNumber();
    }
    
    private String[] getDeclareBoolean() {
        return sintatico.getDeclareBoolean();
    }
    
    private Mapeamento getMapeamento() {
        return sintatico.getMapeamento();
    }
    
    public void sair() {
        if (loopFunc == 0) {
            errorFunction(getLine(getPosition()), "Função Sair está fora de um laço de repetição.");
        }
        else {
            writeOnFile(getMapeamento().breakFunction()+"\n");
            loopFunc--;
            setPosition(1);
        }
    }
    
    public void numero(boolean declaring, int index) {
        if (declaring) {
            writeOnFile(getDeclareNumber()[0]);
        }
        if (!keywords(getName(getPosition()+index)) && getName(getPosition()+index+1).equals("=") &&
            !sintatico.getMapeamento().defines().get(1).contains(getName(getPosition()+index)) &&
            !sintatico.getMapeamento().defines().get(2).contains(getName(getPosition()+index))) {

            writeOnFile(getName(getPosition()+index));
            writeOnFile(getDeclareNumber()[1]);
            if (isNumberList(getName(getPosition()+index+2)) || isNumber(getName(getPosition()+index+2))||
                    sintatico.getMapeamento().getFunctionsNumber().contains(getName(getPosition()+index+2))) {
                if (declaring) {
                    addNumber(getName(getPosition()+index));
                }
                if (sintatico.getMapeamento().getFunctionsNumber().contains(getName(getPosition()+index+2))) {
                    sintatico.getCheckParameters(getName(getPosition()+index+2), 4);
                }
                else {
                    writeOnFile(getName(getPosition()+index+2));
                    setPosition(index+3);
                }
                System.out.println("Cheguei na adição de numeros!");
                writeOnFile(getDeclareNumber()[2]);
            }
            else {
                errorFunction(getLine(getPosition()+index), "Erro na declaração.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+index), "Erro na declaração.");
        }
    }
    
    public void booleano(boolean declaring, int index) {
        if (declaring) {
            writeOnFile(getDeclareBoolean()[0]);
        }
        if (!keywords(getName(getPosition()+index)) && getName(getPosition()+index+1).equals("=") &&
            !sintatico.getMapeamento().defines().get(1).contains(getName(getPosition()+index)) &&
            !sintatico.getMapeamento().defines().get(2).contains(getName(getPosition()+index))) {
            writeOnFile(getName(getPosition()+index));
            writeOnFile(getDeclareBoolean()[1]);
            if (isBooleanList(getName(getPosition()+index+2)) || isBoolean(getName(getPosition()+index+2))||
                    sintatico.getMapeamento().getFunctionsBoolean().contains(getName(getPosition()+index+2))) {
                if (declaring) {
                    addBoolean(getName(getPosition()+index));
                }
                if (sintatico.getMapeamento().getFunctionsBoolean().contains(getName(getPosition()+index+2))) {
                    sintatico.getCheckParameters(getName(getPosition()+index+2), 4);
                }
                else {
                    writeOnFile(getName(getPosition()+index+2));
                    setPosition(index+3);
                }
                writeOnFile(getDeclareBoolean()[2]);
            }
            else {
                errorFunction(getLine(getPosition()+index), "Erro na declaração.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+index), "Erro na declaração.");
        }
    }
    
    public void nome(boolean declaring, int index) {
        if (declaring) {
            writeOnFile(getDeclareString()[0]);
        }
        if (!keywords(getName(getPosition()+index)) && getName(getPosition()+index+1).equals("=") &&
            !sintatico.getMapeamento().defines().get(1).contains(getName(getPosition()+index)) &&
            !sintatico.getMapeamento().defines().get(2).contains(getName(getPosition()+index))) {
            writeOnFile(getName(getPosition()+index));
            writeOnFile(getDeclareString()[1]);
            if (getName(getPosition()+index+2).equals("\"")) {
                if (declaring) {
                    addName(getName(getPosition()+index));
                }
                setPosition(index+3);
                writeString(true);
            }
            else {
                errorFunction(getLine(getPosition()+index), "Tipo incorreto.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+index), "Erro na declaração.");
        }
    }
}
