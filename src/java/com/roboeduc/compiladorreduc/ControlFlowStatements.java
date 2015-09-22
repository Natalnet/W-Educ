/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

/**
 *
 * @author carlafernandes
 */
public class ControlFlowStatements {
    analisadorSintatico sintatico;
    public ControlFlowStatements(analisadorSintatico sintatico) {
        this.sintatico = sintatico;
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
    
    private boolean isBooleanList(String name) {
        return sintatico.booleanListContains(name);
    }
    
    private void addNumber(String name) {
        sintatico.numberListAdd(name);
    }
    
    private void removeNumber(String name) {
        sintatico.numberListRemove(name);
    }
    
    private boolean isNameList(String name) {
        return sintatico.nameListContains(name);
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
    
    private boolean isBoolean(String name) {
        return sintatico.isBoolean(name);
    }
    
    private int isName(int index) {
        return sintatico.isName(index);
    }
    
    private void testCondition() {
        sintatico.testCondition();
    }
    
    private void testVariableCondition(int type) {
        sintatico.testVariableCondition(type);
    }
    
    private boolean keywords(String name) {
        return sintatico.keywords(name);
    }
    
    private String[] ifCondition() {
        return sintatico.getIfCondition();
    }
    
    private String[] whileCondition() {
        return sintatico.getWhileCondition();
    }
    
    private String[] doCondition() {
        return sintatico.getDoCondition();
    }
    
    private String[] repeatCondition() {
        return sintatico.getRepeatCondition();
    }
    
    private String[] switchCondition() {
        return sintatico.getSwitchCondition();
    }
    
    private Mapeamento getMapeamento() {
        return sintatico.getMapeamento();
    }
    
    private void incrementLoopFunc() {
        sintatico.getFunctions().setLoopFunc(sintatico.getFunctions().getLoopFunc()+1);
    }
    
    private void decrementLoopFunc() {
        sintatico.getFunctions().setLoopFunc(sintatico.getFunctions().getLoopFunc()-1);
    }
    
    public void se() {
        if (getName(getPosition()+1).equals("(")) {
            if (!getName(getPosition()+2).equals(")")) {
                writeOnFile(ifCondition()[0]);
                setPosition(2);
                setBracketsCounter(1);
                while((getPosition()-1)<getListSize()) {
                    if (getError()) {
                        break;
                    }
                    if (getName(getPosition()).equals("entao")) {
                        if (getBracketsCounter() != 0) {
                            errorFunction(getLine(getPosition()+2),"16 - Confira os parênteses.");
                        }
                        setPosition(1);
                        break;
                    }
                    testCondition();
                }
                if ((getPosition()-1)==getListSize()) {
                    errorFunction(getLine(getPosition()+1),"6 - FIM não encontrado.");
                }
                if (!getError()) {
                    if (getName(getPosition()).equals("{")) {
                        writeOnFile(ifCondition()[1]);
                        setPosition(1);
                        while (!getName(getPosition()).equals("}") && !getError()) {
                            wordTest();
                        }
                        if (!getError()) {
                            System.out.println("position " + getPosition() + getName(getPosition()));
                            setPosition(1);
                            System.out.println("position " + getPosition() + getName(getPosition()));
                            // Senao
                            if (getName(getPosition()).equals("senao")) {
                                if (getName(getPosition()+1).equals("{")) {
                                    writeOnFile(ifCondition()[2]);
                                    setPosition(2);
                                    while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}")
                                            && !getError()) {
                                        wordTest();
                                    }
                                    if (!getError()) {
                                        writeOnFile("\n"+ ifCondition()[3] +"\n");
                                        setPosition(1);
                                    }
                                }
                                else {
                                    errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                                }
                            }
                            else {
                                writeOnFile("\n"+ifCondition()[3]+"\n");
                            }
                        }
                    }
                    else {
                        errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                    }
                }
            }
            else {
                errorFunction(getLine(getPosition()+2),"17 - Erro na condição.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
        }
    }
    
    public void enquanto() {
        if (getName(getPosition()+1).equals("(")) {
            if (!getName(getPosition()+2).equals(")")) {
                writeOnFile(whileCondition()[0]);
                setPosition(2);
                setBracketsCounter(1);
                while((getPosition()-1)<getListSize()) {
                    if (getError()) {
                        break;
                    }
                    if (getName(getPosition()).equals("farei")) {
                        if (getBracketsCounter() != 0) {
                            errorFunction(getLine(getPosition()+2),"16 - Confira os parênteses.");
                        }
                        setPosition(1);
                        break;
                    }
                    testCondition();
                }
                if ((getPosition()-1)==getListSize()) {
                    errorFunction(getLine(getPosition()+1),"6 - FIM não encontrado.");
                }
                if (!getError()) {
                    if (getName(getPosition()).equals("{")) {
                        incrementLoopFunc();
                        writeOnFile(whileCondition()[1]+"\n");
                        setPosition(1);
                        while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}") && !getError()) {
                            wordTest();
                        }
                        decrementLoopFunc();
                        if (!getError()) {
                            writeOnFile("\n"+whileCondition()[2]+"\n");
                            setPosition(1);
                        }
                    }
                    else {
                        errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                    }
                }
            }
            else {
                errorFunction(getLine(getPosition()+2),"17 - Erro na condição.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
        }
    }
    
    public void farei() {
        if (getName(getPosition()+1).equals("{")) {
            incrementLoopFunc();
            writeOnFile(doCondition()[0]+"\n");
            setPosition(2);
            while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}") && !getError()) {
                wordTest();
            }
            decrementLoopFunc();
            if (!getError()) {
                setPosition(1);
            }
            if (getName(getPosition()).equals("enquanto")) {
                if (getName(getPosition()+1).equals("(")) {
                    writeOnFile(doCondition()[1]);
                    setPosition(2);
                    setBracketsCounter(1);
                    while((getPosition()-1)<getListSize()) {
                        if (getError()) {
                            break;
                        }
                        if (getBracketsCounter() == 0) {
//                            setPosition(1);
                            break;
                        }
                        testCondition();
                        writeOnFile(doCondition()[2]);
                    }
                    if ((getPosition()-1)==getListSize()) {
                        errorFunction(getLine(getPosition()+1),"6 - FIM não encontrado.");
                    }
                }
                else {
                    errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
                }
            }
            else {
                errorFunction(getLine(getPosition()),"18 - ENQUANTO não encontrado.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1),"4 - Falta '{'.");
        }
    }
    
    public void repita() {
        if (isNumber(getName(getPosition()+1)) || isNumberList(getName(getPosition()+1)) || getMapeamento().isNumberDefine(getName(getPosition()+1))) {
            if ((getName(getPosition()+1).equals("1") && getName(getPosition()+2).equals("vez")) ||
                    (!getName(getPosition()+1).equals("1") && getName(getPosition()+2).equals("vezes"))) {
                if (getName(getPosition()+3).equals("{")) {
                    incrementLoopFunc();
                    writeOnFile(repeatCondition()[0]);
                    writeOnFile(getName(getPosition()+1));
                    writeOnFile(repeatCondition()[1]+"\n");
                    setPosition(4);
                    while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}") && !getError()) {
                        wordTest();
                    }
                    decrementLoopFunc();
                    if (!getError()) {
                        writeOnFile("\n"+repeatCondition()[2]+"\n");
                        setPosition(1);
                    }
                }
                else {
                    errorFunction(getLine(getPosition()+1),"4 - Falta '{'.");
                }
            }
            else {
                errorFunction(getLine(getPosition()+2),"19 - Confira a gramática da sua expressão.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1),"20 - É exigido um número nesta expressão.");
        }
    }
    
    public void para() {
        String variable = "";
        String[] mapFor = new String[2];
        mapFor = getMapeamento().forCondition(getName(getPosition()+1), 
                            getName(getPosition()+3), getName(getPosition()+5),
                            getName(getPosition()+7));
        if (!isNumberList(getName(getPosition()+1)) && !keywords(getName(getPosition()+1)) &&
                !isNameList(getName(getPosition()+1)) && !getMapeamento().isNumberDefine(getName(getPosition()+1)) &&
                !getMapeamento().isNameDefine(getName(getPosition()+1))) {
            if (isNumber(getName(getPosition()+3)) && isNumber(getName(getPosition()+5))  && isNumber(getName(getPosition()+7))) {
                if (getName(getPosition()+2).equals("de") && getName(getPosition()+4).equals("ate") &&
                        getName(getPosition()+6).equals("passo") && getName(getPosition()+8).equals("farei")) {
                    addNumber(getName(getPosition()+1));
                    writeOnFile(mapFor[0]);
                    variable = getName(getPosition()+1);
                    setPosition(9);
                    if (getName(getPosition()).equals("{")) {
                        incrementLoopFunc();
                        setPosition(1);
                        while ((getPosition()-1) < getListSize() && !getName(getPosition()).equals("}") && !getError()) {
                            wordTest();
                        }
                        decrementLoopFunc();
                        if (!getError()) {
                            removeNumber(variable);
                            writeOnFile(mapFor[1]);
                            setPosition(1);
                        }
                    }
                    else {
                        errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                    }
                }
                else {
                    errorFunction(getLine(getPosition()),"21 - Erro na sintaxe do PARA.");
                }
            }
            else {
                errorFunction(getLine(getPosition()), "20 - É exigido um número nesta expressão.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1), "3 - Utilização de nome inválido.");
        }
    }
    
    public void teste() {
        System.out.println("sc: " + switchCondition()[0]);
        writeOnFile(switchCondition()[0]);
        if (getName(getPosition()+1).equals("(")) {
            if (!getName(getPosition()+2).equals(")")) {
                setPosition(2);
                if (isNameList(getName(getPosition())) || isName(getPosition())>0) {
                    testVariableCondition(2);
                }
                else if (isNumberList(getName(getPosition())) || isNumber(getName(getPosition())) || getMapeamento().isNumberDefine(getName(getPosition()))) {
                    testVariableCondition(1);
                }
                else if (isBooleanList(getName(getPosition())) || isBoolean(getName(getPosition()))) {
                    testVariableCondition(3);
                }
                else if (getMapeamento().isNumberFunction(getName(getPosition()))) {
                    testVariableCondition(2);
                }
                else if (getMapeamento().isNameFunction(getName(getPosition()))) {
                    testVariableCondition(1);
                }
                else if (getMapeamento().isBooleanFunction(getName(getPosition()))) {
                    testVariableCondition(3);
                }
                else if (getMapeamento().isNumberDefine(getName(getPosition()))) {
                    testVariableCondition(2);
                }
                else if (getMapeamento().isNameDefine(getName(getPosition()))) {
                    testVariableCondition(1);
                }
                else if (getMapeamento().isBooleanDefine(getName(getPosition()))) {
                    testVariableCondition(3);
                }
                else {
                    errorFunction(getLine(getPosition()),"14 - Parâmetro com valor inválido.");
                }
            }
            else {
                errorFunction(getLine(getPosition()+2),"17 - Erro na condição.");
            }
        }
        else {
            errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
        }
    }
}
