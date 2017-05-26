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
    
    public void se() throws SintaticException {
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
//                            setErrorType("sintaxe condicao");
//                            errorFunction(getLine(getPosition()+2),"16 - Confira os parênteses.");
                            throw new SintaticException("sintaxe condicao",getLine(getPosition()+2), "16 - Confira os parênteses.");
                        }
                        setPosition(1);
                        break;
                    }
                    else if (getBracketsCounter() == 0) {
//                        setErrorType("sintaxe condicao");
//                        errorFunction(getLine(getPosition()+2),"31 - Confira a sintaxe da estrutura.");
//                        break;
                        throw new SintaticException("sintaxe condicao",getLine(getPosition()+2), "31 - Confira a sintaxe da estrutura.");
                    }
                    testCondition();
                }
                if ((getPosition()-1)==getListSize()) {
//                    setErrorType("sintaxe condicao");
//                    errorFunction(getLine(getPosition()+1),"6 - FIM não encontrado.");
                    throw new SintaticException("sintaxe condicao",getLine(getPosition()+1), "6 - FIM não encontrado.");
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
//                                    setErrorType("sintaxe condicao");
//                                    errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                                    throw new SintaticException("sintaxe condicao",getLine(getPosition()), "4 - Falta '{'.");
                                }
                            }
                            else {
                                writeOnFile("\n"+ifCondition()[3]+"\n");
                            }
                        }
                    }
                    else {
//                        setErrorType("sintaxe condicao");
//                        errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                        throw new SintaticException("sintaxe condicao",getLine(getPosition()), "4 - Falta '{'.");
                    }
                }
            }
            else {
//                setErrorType("estrutura condicao");
//                errorFunction(getLine(getPosition()+2),"17 - Erro na condição.");
                throw new SintaticException("estrutura condicao",getLine(getPosition()+2), "17 - Erro na condição.");
            }
        }
        else {
//            setErrorType("sintaxe condicao");
//            errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
            throw new SintaticException("sintaxe condicao",getLine(getPosition()+1), "15 - Está faltando '('.");
        }
    }
    
    public void enquanto() throws SintaticException {
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
//                            setErrorType("sintaxe repeticao");
//                            errorFunction(getLine(getPosition()+2),"16 - Confira os parênteses.");
                            throw new SintaticException("sintaxe repeticao",getLine(getPosition()+2), "16 - Confira os parênteses.");
                        }
                        setPosition(1);
                        break;
                    }
                    testCondition();
                }
                if ((getPosition()-1)==getListSize()) {
//                    setErrorType("sintaxe repeticao");
//                    errorFunction(getLine(getPosition()+1),"6 - FIM não encontrado.");
                    throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "6 - FIM não encontrado.");
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
//                        setErrorType("sintaxe repeticao");
//                        errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                        throw new SintaticException("sintaxe repeticao",getLine(getPosition()), "4 - Falta '{'.");
                    }
                }
            }
            else {
//                setErrorType("estrutura repeticao");
//                errorFunction(getLine(getPosition()+2),"17 - Erro na condição.");
                throw new SintaticException("sintaxe repeticao",getLine(getPosition()+2), "17 - Erro na condição.");
            }
        }
        else {
//            setErrorType("sintaxe repeticao");
//            errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
            throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "15 - Está faltando '('.");
        }
    }
    
    public void farei() throws SintaticException {
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
//                        setErrorType("sintaxe repeticao");
//                        errorFunction(getLine(getPosition()+1),"6 - FIM não encontrado.");
                        throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "6 - FIM não encontrado.");
                    }
                }
                else {
//                    setErrorType("sintaxe repeticao");
//                    errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
                    throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "15 - Está faltando '('.");
                }
            }
            else {
//                setErrorType("sintaxe repeticao");
//                errorFunction(getLine(getPosition()),"18 - ENQUANTO não encontrado.");
                throw new SintaticException("sintaxe repeticao",getLine(getPosition()), "18 - ENQUANTO não encontrado.");
            }
        }
        else {
//            setErrorType("sintaxe repeticao");
//            errorFunction(getLine(getPosition()+1),"4 - Falta '{'.");
            throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "4 - Falta '{'.");
        }
    }
    
    public void repita() throws SintaticException {
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
//                    setErrorType("sintaxe repeticao");
//                    errorFunction(getLine(getPosition()+1),"4 - Falta '{'.");
                    throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "4 - Falta '{'.");
                }
            }
            else {
//                setErrorType("sintaxe repeticao");
//                errorFunction(getLine(getPosition()+2),"19 - Confira a gramática da sua expressão.");
                throw new SintaticException("sintaxe repeticao",getLine(getPosition()+2), "19 - Confira a gramática da sua expressão.");
            }
        }
        else {
//            setErrorType("repeticao");
//            errorFunction(getLine(getPosition()+1),"20 - É exigido um número nesta expressão.");
            throw new SintaticException("repeticao",getLine(getPosition()+1), "20 - É exigido um número nesta expressão.");
        }
    }
    
    public void para() throws SintaticException {
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
//                        setErrorType("sintaxe repeticao");
//                        errorFunction(getLine(getPosition()),"4 - Falta '{'.");
                        throw new SintaticException("sintaxe repeticao",getLine(getPosition()+1), "4 - Falta '{'.");
                    }
                }
                else {
//                    setErrorType("sintaxe repeticao");
//                    errorFunction(getLine(getPosition()),"21 - Erro na sintaxe do PARA.");
                    throw new SintaticException("sintaxe repeticao",getLine(getPosition()), "21 - Erro na sintaxe do PARA.");
                }
            }
            else {
//                setErrorType("sintaxe repeticao");
//                errorFunction(getLine(getPosition()), "20 - É exigido um número nesta expressão.");
                throw new SintaticException("sintaxe repeticao",getLine(getPosition()), "20 - É exigido um número nesta expressão.");
            }
        }
        else {
//            setErrorType("nome repeticao");
//            errorFunction(getLine(getPosition()+1), "3 - Utilização de nome inválido.");
            throw new SintaticException("nome repeticao",getLine(getPosition()+1), "3 - Utilização de nome inválido.");
        }
    }
    
    public void teste() throws SintaticException {
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
//                    setErrorType("condicao");
//                    errorFunction(getLine(getPosition()),"14 - Parâmetro com valor inválido.");
                    throw new SintaticException("condicao",getLine(getPosition()), "14 - Parâmetro com valor inválido.");
                }
            }
            else {
//                setErrorType("estrutura condicao");
//                errorFunction(getLine(getPosition()+2),"17 - Erro na condição.");
                throw new SintaticException("condicao",getLine(getPosition()+2), "17 - Erro na condição.");
            }
        }
        else {
//            setErrorType("sintaxe condicao");
//            errorFunction(getLine(getPosition()+1),"15 - Está faltando '('.");
            throw new SintaticException("sintaxe condicao",getLine(getPosition()+1), "15 - Está faltando '('.");
        }
    }

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
    
    private String getName(int position) throws SintaticException {
        return sintatico.getName(position);
    }
    
    private int getLine(int position) throws SintaticException {
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
    
    private void wordTest() throws SintaticException {
        sintatico.wordTest();
    }
    
    private void writeString(boolean declaring) throws SintaticException {
        sintatico.writeString(declaring);
    }
    
    private void writeNumberOperand() throws SintaticException {
        sintatico.writeNumberOperand();
    }
    
    private boolean isNumber(String name) {
        return sintatico.isNumber(name);
    }
    
    private boolean isBoolean(String name) {
        return sintatico.isBoolean(name);
    }
    
    private int isName(int index) throws SintaticException {
        return sintatico.isName(index);
    }
    
    private void testCondition() throws SintaticException {
        sintatico.testCondition();
    }
    
    private void testVariableCondition(int type) throws SintaticException {
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

    private void setErrorType(String _errorType) {
        sintatico.setErrorType(_errorType);
    }
    
    private void decrementLoopFunc() {
        sintatico.getFunctions().setLoopFunc(sintatico.getFunctions().getLoopFunc()-1);
    }
} 
