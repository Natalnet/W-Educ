/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

/**
 *
 * @author carlafernandes
 */
public class TestCondition {
    private analisadorSintatico sintatico;
    private boolean enableComparison = false;
    public TestCondition(analisadorSintatico sintatico) {
        this.sintatico = sintatico;
    }
    
    public void testVariableCondition(int type) {
        boolean repeatDefault = false;
        if (getName(getPosition()+1).equals(")")) {
            if (getName(getPosition()+2).equals("{")) {
                if (getMapeamento().isBooleanFunction(getName(getPosition())) ||
                        getMapeamento().isNameFunction(getName(getPosition())) ||
                        getMapeamento().isNumberFunction(getName(getPosition()))) {
                    checkParameters(getName(getPosition()), 3);
                }
                else if (getMapeamento().isNameDefine(getName(getPosition())) ||
                        getMapeamento().isNumberDefine(getName(getPosition())) ||
                        getMapeamento().isBooleanDefine(getName(getPosition()))) {
                    writeOnFile(getMapeamento().getDefineText(getName(getPosition())));
                }
                else {
                    writeOnFile(getName(getPosition()));
                    setPosition(3);
                }
                writeOnFile(switchCondition()[1]+"\n");
                while ((getPosition()-1) < getListSize() && !getError() && !getName(getPosition()).equals("}")) {
                    if (getName(getPosition()).equals("se")) {
                        writeOnFile(switchCondition()[2]);
                        switch (type) {
                            case (1):
                                if (isNumber(getName(getPosition()+1)) || getMapeamento().isNumberDefine(getName(getPosition()+1))) {
                                    writeOnFile(getName(getPosition()+1));
                                    setPosition(2);
                                }
                                else {
                                    errorFunction(getLine(getPosition()+1),"Tipo incorreto.");
                                }
                                break;
                            case (2):
                                if (isName(getPosition()+1)>0) {
                                    writeOnFile(getName(getPosition()+1));
                                    setPosition(2);
                                    writeString(false);
                                }
                                else {
                                    errorFunction(getLine(getPosition()+1),"Está faltando '\"'.");
                                }
                                break;
                            case (3):
                                if (isBoolean(getName(getPosition()+1))) {
                                    writeBoolean(getName(getPosition()+1));
                                    setPosition(2);
                                }
                                break;
                        }
                        if (!getError()) {
                            if (getName(getPosition()).equals(":")) {
                                if (getName(getPosition()+1).equals("{")) {
                                    writeOnFile(switchCondition()[3]+"\n");
                                    setPosition(2);
                                    while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}") && !getError()) {
                                        wordTest();
                                    }
                                    if (!getError()) {
                                        setPosition(1);
                                        writeOnFile("\n"+switchCondition()[4]+"\n");
                                    }
                                }
                                else {
                                    errorFunction(getLine(getPosition()+1),"Está faltando '{'.");
                                }
                            }
                            else {
                                errorFunction(getLine(getPosition()),"Está faltando ':'.");
                            }
                        }
                    }
                    else if (getName(getPosition()).equals("senao")) {
                        if (!repeatDefault) {
                            repeatDefault = true;
                            writeOnFile(switchCondition()[5]);
                            setPosition(1);
                            if (getName(getPosition()).equals(":")) {
                                if (getName(getPosition()+1).equals("{")) {
                                    setPosition(2);
                                    while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}") && !getError()) {
                                        wordTest();
                                    }
                                    if (!getError()) {
                                        setPosition(1);
                                        writeOnFile("\n"+switchCondition()[6]+"\n");
                                    }
                                }
                                else {
                                    errorFunction(getLine(getPosition()+1),"Está faltando '{'.");
                                }
                            }
                            else {
                                errorFunction(getLine(getPosition()),"Está faltando ':'.");
                            }
                        }
                        else {
                            errorFunction(getLine(getPosition()),"'Senão' em duplicidade.");
                        }
                    }
                    else {
                        setPosition(1);
                        if (getPosition() == getListSize()) {
                            errorFunction(getLine(getPosition()-1),"Está faltando '}'.");
                            break;
                        }
                    }
                }
                if (!getError()) {
                    writeOnFile("\n"+switchCondition()[7]+"\n");
                    setPosition(1);
                }
            }
            else {
                errorFunction(getLine(getPosition()+2),"Está faltando '{'.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1),"Está faltando ')'.");
        }
    }

    public void writeNumberCondition() {
        if (sintatico.isValidNumberExpression(0)) {
            if (getMapeamento().isNumberFunction(getName(getPosition()))) {
                checkParameters(getName(getPosition()), 2);
                if (sintatico.writeNumericalOperator(0)) {
                    writeOnFile(getName(getPosition()));
                    setPosition(1);
                    writeNumberCondition();
                }
            }
            else if (getMapeamento().isNumberDefine(getName(getPosition()))) {
                writeOnFile(getMapeamento().getDefineText(getName(getPosition())));
                if (sintatico.writeNumericalOperator(1)) {
                    setPosition(1);
                    writeOnFile(getName(getPosition()));
                    setPosition(1);
                    writeNumberCondition();
                }
                else {
                    setPosition(1);
                }
            }
            else {
                writeOnFile(getName(getPosition()));
                System.out.println("Entrei1");
                if (sintatico.writeNumericalOperator(1)) {
                    System.out.println("Entrei2");
                    setPosition(1);
                    writeOnFile(getName(getPosition()));
                    setPosition(1);
                    writeNumberCondition();
                }
                else {
                    setPosition(1);
                }
            }
        }
    }
    
    public void testCondition() {
        if (getName(getPosition()).equals(")")) {
            if (getBracketsCounter() > 0) {
                if (getBracketsCounter() != 1) {
                    writeOnFile(")");
                }
                setBracketsCounter(-1);
                setPosition(1);
            }
            else {
                errorFunction(getLine(getPosition()),"Erro na expressão.");
            }
        }
        else if (getBracketsCounter() > 0) {
//            if (senses(getName(getPosition()))) {
//                analyzeSensor();
//                enableComparison = true;
//            }
            System.out.println("NOME:: " + getName(getPosition()));
            for (int l = 0; l < sintatico.getNumberList().size(); l++) {
                System.out.println("nlist: " + l + " - " + sintatico.getNumberList().get(l));
            }
            for (int l = 0; l < sintatico.getTaskList().size(); l++) {
                System.out.println("tlist: " + l + " - " + sintatico.getTaskList().get(l));
            }
            /*if (isNumberList(getName(getPosition())) || isNumber(getName(getPosition())) ||
                    getMapeamento().isNumberFunction(getName(getPosition())) ||
                    getMapeamento().isNumberDefine(getName(getPosition()))) {*/
            

            // Daqui pra baixo é antigo!!!

            // else if (sintatico.isValidNumberExpression(0)) {
            //     writeNumberCondition();
            //     //setPosition(1);
            //     writeNumberOperand();
            //     setPosition(1);
            //     writeNumberCondition();
            //     enableComparison = true;
            // }
            if (isNameList(getName(getPosition())) || isName(getPosition())>0 ||
                    getMapeamento().isNameFunction(getName(getPosition())) || getMapeamento().isNameDefine(getName(getPosition()))) {
                if (getMapeamento().isNameFunction(getName(getPosition()))) {
                    checkParameters(getName(getPosition()), 2);
                }
                else if (getMapeamento().isNameDefine(getName(getPosition()))) {
                    writeOnFile(getMapeamento().getDefineText(getName(getPosition())));
                    setPosition(1);
                }
                else if (isName(getPosition())>0) {
                    writeOnFile(getName(getPosition()));
                    setPosition(1);
                    writeString(false);
                }
                else {
                    writeOnFile(getName(getPosition()));
                    setPosition(1);
                }
                if (getName(getPosition()).equals("=")) {
                    writeOnFile(getMapeamento().writeOperator(getName(getPosition()), getName(getPosition()+1)));
                    if (isName(getPosition()+1)>0) {
                        writeOnFile("\"");
                        setPosition(2);
                        writeString(false);
                    }
                    else if (getMapeamento().isNameDefine(getName(getPosition()+1))) {
                        writeOnFile(getMapeamento().getDefineText(getName(getPosition()+1)));
                        setPosition(3);
                    }
                    else if (isNameList(getName(getPosition()+1))) {
                        writeOnFile(getName(getPosition()+1));
                        setPosition(3);
                    }
                    else if (getMapeamento().isNameFunction(getName(getPosition()+1))) {
                        checkParameters(getName(getPosition()+1), 3);
                    }
                    else {
                        errorFunction(getLine(getPosition()+1),"2: Argumento incorreto.");
                    }
                    enableComparison = true;
                }
                else {
                    errorFunction(getLine(getPosition()),"Erro na expressão.");
                }
            }
            else if (getName(getPosition()).equals("(")) {
                writeOnFile("(");
                setBracketsCounter(1);
                setPosition(1);
            }
            else if (getName(getPosition()).equals("e") || getName(getPosition()).equals("ou")) {
                System.out.println("Entrei no E");
                if (enableComparison) {
                    writeOnFile(getMapeamento().writeRelationalOperator(getName(getPosition())));
                    enableComparison = false;
                    setPosition(1);
                }
                else {
                    errorFunction(getLine(getPosition()),"Erro no início da expressão.");
                }
            }
            else if (getName(getPosition()).equals("nao")) {
                if (!enableComparison) {
                    writeOnFile(getMapeamento().writeRelationalOperator(getName(getPosition())));
                    enableComparison = false;
                    setPosition(1);
                }
                else {
                    errorFunction(getLine(getPosition()),"Erro no início da expressão.");
                }
            }
            else if (sintatico.isMathOperation(0, false, false, false)) {
                sintatico.writeMathOperation(0, false);
                if (sintatico.isLogicalOperator(getName(getPosition()))) {
                    writeNumberOperand();
                    setPosition(1);
                    if (sintatico.isMathOperation(0, true, false, false)) {
                        sintatico.writeMathOperation(0, true);
                        //setPosition(1);
                        enableComparison=true;
                    }
                    else {
                        errorFunction(getLine(getPosition()),"Erro na expressão.");
                    }
                }
                else {
                    errorFunction(getLine(getPosition()),"Erro no operador.");
                }
            }
            else if (isBoolean(getName(getPosition())) ||
                    isBooleanList(getName(getPosition())) ||
                    getMapeamento().isBooleanFunction(getName(getPosition())) || getMapeamento().isBooleanDefine(getName(getPosition()))) {
                if (getMapeamento().isBooleanFunction(getName(getPosition()))) {
                    checkParameters(getName(getPosition()), 2);
                }
                else if (getMapeamento().isBooleanDefine(getName(getPosition()))) {
                    writeOnFile(getMapeamento().getDefineText(getName(getPosition())));
                    setPosition(1);
                }
                else if (isBooleanList(getName(getPosition()))) {
                    writeOnFile(getName(getPosition()));
                    setPosition(1);
                }
                else {
                    writeBoolean(getName(getPosition()));
                    setPosition(1);
                }
            }
            else {
                
                // Escreveu uma coisa diferente dentro do parênteses.
                errorFunction(getLine(getPosition()),"Erro na expressão.");
            }
        }
        else {
            // Fechou os parenteses e depois escreveu alguma coisa
            errorFunction(getLine(getPosition()+1),"Erro na expressão.");
        }
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
    
    private void wordTest() {
        sintatico.wordTest();
    }
    
    private void writeString(boolean declaring) {
        sintatico.writeString(declaring);
    }
    
    private void writeNumberOperand() {
        sintatico.writeNumberOperand();
    }
    
    private boolean isNumber(String name) {
        return sintatico.isNumber(name);
    }
    
    private int isName(int index) {
        return sintatico.isName(index);
    }
    
    private boolean isBoolean(String name) {
        return sintatico.isBoolean(name);
    }
    
    private void writeBoolean(String name) {
        sintatico.writeBoolean(name);
    }
    
    private String[] switchCondition() {
        return sintatico.getSwitchCondition();
    }
    
    private Mapeamento getMapeamento() {
        return sintatico.getMapeamento();
    }
    
    private void checkParameters(String functionName, int checkPosition) {
        sintatico.getCheckParameters(functionName, checkPosition);
    }
}
