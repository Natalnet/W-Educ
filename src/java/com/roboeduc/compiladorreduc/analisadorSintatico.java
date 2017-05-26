/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roboeduc.compiladorreduc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

// Falta fazer:
// problema no ESCREVA com variáveis

        class SintaticException extends Exception {
            private String type;
            private int line;
            private String message;
            
            public SintaticException(String type, int line, String message){
                this.type = type;
                this.line = line + 1;
                this.message = message;
            }
            
            public String getType(){
                return this.type;
            }
            
            public int getLine(){
                return this.line;
            }
            
            @Override
            public String getMessage(){
                return this.message;
            }
            
        }

/**
 *
 * @author carlafernandes
 */
public class analisadorSintatico {
    
    private ControlFlowStatements control = new ControlFlowStatements(this);
    private TestCondition tests = new TestCondition(this);
    private OtherFunctions functions = new OtherFunctions(this);

    analisadorSintatico() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class fileListStruct {
        public int line;
        public String name;
    }
    private List<fileListStruct> fileList = new ArrayList();
    private int position = 0;
    private boolean error = false;
    private boolean mainFunc = false;
//    private int loopFunc = 0;
    
    private int bracketsCounter = 0;
//    private boolean enableComparison = false;
    
    private List<String> taskList = new ArrayList();
    private List<String> numberList = new ArrayList();
    private List<String> nameList = new ArrayList();
    private List<String> booleanList = new ArrayList();
    
    private Writer writer;
    private File file;
    
    private String errorStr = "";
    private int errorInt = 0;

    private String errorType = "";
    
    private Mapeamento mapeamento;
    private String otherFunction[]=new String[3];
    private String mainFunction[]=new String[2];
    private String callFunction[]=new String[2];
    private String declareString[]=new String[3];
    private String declareNumber[]=new String[3];
    private String declareBoolean[]=new String[3];
    private String declareTrue="";
    private String declareFalse="";
    
    private String ifCondition[]=new String[4];
    private String whileCondition[]=new String[3];
    private String doCondition[]=new String[3];
    private String repeatCondition[]=new String[3];
    private String forCondition[]=new String[2];
    private String switchCondition[]=new String[8];
    
    public analisadorSintatico(analisadorLexico lexico, String folder, String fileName, String languageName, String extension) {
        mapeamento = new Mapeamento(fileName);
        //diretório dos programas gerados
        file = new File(folder+fileName+"."+extension);
        createFile();
        for (int i = 0; i < lexico.getFileList().size(); i++) {
            fileListStruct auxStruct = new fileListStruct();
            auxStruct.line = lexico.getFileListLine(i);
            auxStruct.name = lexico.getFileListName(i);
            fileList.add(auxStruct);
        }
        
        //System.out.println(fileList.size());
        
        addNumberVariables();
    }
     
    public void startCompile() throws Exception {
        
        databaseData();

        System.out.println("tamanho: " + mapeamento.defines().size());
        for (int i = 0; i < mapeamento.defines().get(1).size(); i++) {
            boolean isUsed = false;
            for (int j = 0; j < fileList.size(); j++) {
                if (getName(j).equals(mapeamento.defines().get(1).get(i))) {
                    isUsed = true;
                    break;
                }
            }
            if (isUsed) {
                writeOnFile(mapeamento.defines().get(0).get(i));
            }
        }
        
        writeOnFile(mapeamento.header());
        while (position < fileList.size()) {
            if (error) {
                writeOnFile("Error");
                break;
            }
            analyze();
        }
        
        writeOnFile(mapeamento.footnote());
        

        
        if (!mainFunc && !error) {
//            setErrorType("sintaxe");
//            errorFunction(0, "1 - INICIO não encontrado.");
            throw new SintaticException("sintaxe", 0, "1 - INICIO não encontrado.");
        }
    }
    
    private void databaseData() {
        //definesList = mapeamento.getDefinesList();
        otherFunction = mapeamento.otherFunction();
        mainFunction = mapeamento.mainFunction();
        callFunction = mapeamento.callFunction();
        declareString = mapeamento.declareString();
        declareNumber = mapeamento.declareNumber();
        declareBoolean = mapeamento.declareBoolean();
        declareTrue = mapeamento.declareTrue();
        declareFalse = mapeamento.declareFalse();
        ifCondition = mapeamento.ifCondition();
        whileCondition = mapeamento.whileCondition();
        doCondition = mapeamento.doCondition();
        repeatCondition = mapeamento.repeatCondition();
        switchCondition = mapeamento.switchCondition();
    }
    
    private void addNumberVariables() {
    //    numberList.add("bateria");
    //    numberList.add("memoria");
    }
    
    public String getName(int index) throws SintaticException {
        if(index >= fileList.size()){
            throw new SintaticException("sintaxe", getLine(fileList.size()-1), "31 - Confira a sintaxe da estrutura.");
        }
        
        return fileList.get(index).name;
    }
    
    public int getLine(int index) throws SintaticException {
        if(index >= fileList.size()){
            throw new SintaticException("sintaxe", getLine(fileList.size()-1), "31 - Confira a sintaxe da estrutura.");
        }
        
        return fileList.get(index).line;
    }
    
    private void createFile() {
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
    
    public void closeFile() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
        }
    }
    
    public void writeOnFile(String text) {
        try {
            //createFile();
            writer.write(text);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public boolean keywords(String name) {
        List <String> keywordsList = new ArrayList<String>();
        keywordsList.add("inicio");
        keywordsList.add("fim");
        keywordsList.add("tarefa");
        keywordsList.add("se");
        keywordsList.add("entao");
        keywordsList.add("senao");
        keywordsList.add("enquanto");
        keywordsList.add("farei");
        keywordsList.add("repita");
        keywordsList.add("vezes");
        keywordsList.add("vez");
        keywordsList.add("teste");
        keywordsList.add("espere");
        keywordsList.add("para");
        keywordsList.add("segundo");
        keywordsList.add("segundos");
        keywordsList.add("rotacao");
        keywordsList.add("rotacoes");
        keywordsList.add("escreva");
        keywordsList.add("toque");
        keywordsList.add("numero");
        keywordsList.add("texto");
        keywordsList.add("sair");
        keywordsList.add("limpartela");
        keywordsList.add("e");
        keywordsList.add("ou");
        keywordsList.add("(");
        keywordsList.add(")");
        keywordsList.add("{");
        keywordsList.add("}");
        keywordsList.add("<");
        keywordsList.add(">");
        keywordsList.add("=");
        keywordsList.add(">=");
        keywordsList.add("<=");
        keywordsList.add("!=");
        keywordsList.add("verdadeiro");
        keywordsList.add("memoria");
        keywordsList.add("bateria");
        if (keywordsList.contains(name)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void errorFunction(int line, String errorName) {
        error = true;
        errorStr = errorName;
        errorInt = line+1;
        //System.out.println("Line: " + (line+1) + " " + errorName);
    }
    
    private void analyze() throws SintaticException {
       
        if (getName(position).equals(" ")) {
            position++;
        }
        else if (getName(position).equals("tarefa")) {
            if (mainFunc) {
//                setErrorType("tarefa");
//                errorFunction(getLine(position), "2 - Não é possível declarar TAREFAS dentro da função INICIO.");
                throw new SintaticException("tarefa", getLine(position), "2 - Não é possível declarar TAREFAS dentro da função INICIO.");
            }
            else {
                writeOnFile(otherFunction[0]);
                if (keywords(getName(position+1)) || mapeamento.defines().get(1).contains(getName(position+1)) ||
                    mapeamento.defines().get(2).contains(getName(position+1))) {
//                    setErrorType("nome");
//                    errorFunction(getLine(position+1), "3 - Utilização de nome inválido.");
                    throw new SintaticException("nome", getLine(position+1), "3 - Utilização de nome inválido.");
                }
                else {
                    writeOnFile(getName(position+1));
                    writeOnFile(otherFunction[1]);
                    if (getName(position+2).equals("{")) {
                        taskList.add(getName(position+1));
                        position+=3;
                        while (!getName(position).equals("}") && !error) {
                            wordTest();
                        }
                        if (!error) {
                            writeOnFile(otherFunction[2]+"\n");
                            position++;
                        }
                    }
                    else {
//                        setErrorType("sintaxe");
//                        errorFunction(getLine(position+2), "4 - Falta '{'.");
                        throw new SintaticException("sintaxe", getLine(position+2), "4 - Falta '{'.");
                    }
                }
            }
        }
        else if (getName(position).equals("inicio")) {
            if (mainFunc) {
//                setErrorType("sintaxe");
//                errorFunction(getLine(position), "5 - Função INICIO em duplicidade.");
                throw new SintaticException("sintaxe", getLine(position), "5 - Função INICIO em duplicidade.");
            }
            else {
                writeOnFile(mainFunction[0]+"\n");
                position++;
                mainFunc = true;
                
                while (position < fileList.size() && !error && !getName(position).equals("fim")) {
                    wordTest();
                    
                }
                if(position >= fileList.size() && !error && !getName(position-1).equals("fim")){
//                    setErrorType("sintaxe");
//                    errorFunction(getLine(position-1), "6 - FIM não encontrado.");
                    throw new SintaticException("sintaxe", getLine(position-1), "6 - FIM não encontrado.");
                }
                if (!error) {
                    writeOnFile("\n"+mainFunction[1]+"\n");
                    position++;
                }
            }
        }
        else if (getName(position).equals("numero")) {
            //if (mainFunc) {
            //    errorFunction(getLine(position), "Declaração inválida.");
            //}
            //else {
                functions.numero(true,1);
            //}
        }
        else if (getName(position).equals("texto")) {
            //if (mainFunc) {
            //    errorFunction(getLine(position), "Declaração inválida.");
            //}
            //else {
                functions.nome(true,1);
            //}
        }
        else {
//            setErrorType("sintaxe");
//            errorFunction(getLine(position), "7 - Esta declaração deve ser feita dentro do INICIO.");
            throw new SintaticException("sintaxe", getLine(position), "7 - Esta declaração deve ser feita dentro do INICIO.");
        }
    }
    
    public void wordTest() throws SintaticException {
        if (getName(position).equals("sair")){
            functions.sair();
        }
        else if (getName(position).equals("se")){
            control.se();
        }
        else if (getName(position).equals("enquanto")){
            control.enquanto();
        }
        else if (getName(position).equals("farei")){
            control.farei();
        }
        else if (getName(position).equals("repita")){
            control.repita();
        }
        else if (getName(position).equals("para")){
            control.para();
        }
        else if (getName(position).equals("teste")){
            control.teste();
        }

        // Adicionando variaveis dentro da funcao principal
        else if (getName(position).equals("numero")){
            functions.numero(true,1);
        }
        else if (getName(position).equals("texto")){
            functions.nome(true,1);
        }


        // O que fazer com as variaveis???
            // Tem que add acoes aqui tmb!
        else if (getName(position).equals("senao")){
//            setErrorType("sintaxe");
//            errorFunction(getLine(position), "8 - Está faltando um SE.");
            throw new SintaticException("sintaxe", getLine(position), "8 - Está faltando um SE.");
        }
        else if (getName(position).equals("fim")){
//            setErrorType("sintaxe");
//            errorFunction(getLine(position), "10 - Expressão inexistente.");
            throw new SintaticException("sintaxe", getLine(position), "10 - Expressão inexistente.");
        }
        /*else if (getName(position).equals("numero")){
            errorFunction(getLine(position), "Está faltando um ')'.");
        }*/
        else if (taskList.contains(getName(position))) {
            writeOnFile(callFunction[0]);
            writeOnFile(getName(position));
            writeOnFile(callFunction[1]+"\n");
            position++;
        }
        else if (numberList.contains(getName(position))) {
            functions.numero(false,0);
        }
        else if (nameList.contains(getName(position))) {
            functions.nome(false,0);
        }
        else if (mapeamento.getFunctionsVoid().contains(getName(position))) {
            //checkParameters(getName(position), 2);
            if (checkFunction(getName(position), 2, true)) {
                
                writeFunctionOnFile(getName(position), 2);
                      
            }
            else {
//                setErrorType("funcao");
//                errorFunction(getLine(position), "9 - Erro nos parâmetros da função " + getName(position) + ".");
                throw new SintaticException("funcao", getLine(position), "9 - Erro nos parâmetros da função " + getName(position) + ".");
            }
        }
        else {
            //System.out.println("Nome que aparece " + getName(position));
            //System.out.println("qual o nome: " + getName(position));
//            setErrorType("sintaxe");
//            errorFunction(getLine(position), "10 - Expressão inexistente.");
            throw new SintaticException("sintaxe", getLine(position), "10 - Expressão inexistente.");
        }
    }
    
    public void testVariableCondition(int type) throws SintaticException {
        tests.testVariableCondition(type);
    }
    
    public void testCondition() throws SintaticException {
        tests.testCondition();
    }
    
    public void writeString(boolean declaring) throws SintaticException {
        while (position < fileList.size() && !getName(position).equals("\"")) {
            writeOnFile(getName(position));
            if (!getName(position+1).equals("\"")) {
                writeOnFile(" ");
            }
            position++;
        }
        if (position < fileList.size() && getName(position).equals("\"")) {
            if (declaring) {
                writeOnFile(declareString[2]+"\n");
            }
            else {
                writeOnFile("\"");
            }
            position++;
        }
        else {
//            setErrorType("sintaxe");
//            errorFunction(getLine(position-1), "11 - Está faltando \".");
            throw new SintaticException("sintaxe", getLine(position-1), "11 - Está faltando \".");
        }
    }
    
    public void writeBoolean(String booleanName) {
        if (booleanName.equals("verdadeiro")) {
            writeOnFile(declareTrue);
        }
        else {
            writeOnFile(declareFalse);
        }
    }
    
    public void writeNumberOperand() throws SintaticException {
        if (mapeamento.writeOperator(getName(position), getName(position+1)).equals("error")) {
//            setErrorType("sintaxe");
//            errorFunction(getLine(position),"30 - Operador inválido.");
            throw new SintaticException("sintaxe", getLine(position), "30 - Operador inválido.");
        }
        else {
            writeOnFile(mapeamento.writeOperator(getName(position), getName(position+1)));
            if (getName(position+1).equals("=")) {
                position++;
            }
        }
        /*if (!error) {
            if (isNumber(getName(position+1)) || numberList.contains(getName(position+1)) 
                    || mapeamento.getFunctionsNumber().contains(getName(position+1)) || mapeamento.isNumberDefine(getName(getPosition()+1))) {
                if (mapeamento.getFunctionsNumber().contains(getName(position+1))) {
                    checkParameters(getName(position+1), 3);
                }
                else if (getMapeamento().isNumberDefine(getName(getPosition()+1))) {
                    writeOnFile(getMapeamento().getDefineText(getName(getPosition()+1)));
                    position +=2;
                }
                else {
                    writeOnFile(getName(position+1));
                    position+=2;
                }
            }
            else {
                errorFunction(getLine(position+1), "1: Argumento incorreto.");
            }
        }*/
    }

    private void checkParameters(String functionName, int checkPosition) throws SintaticException {
        boolean newParameter = true;
        String[] writeFunction;
        writeFunction = mapeamento.writeFunction(functionName);
        writeOnFile(writeFunction[0]);
        List<String> functionParameters = mapeamento.getFunctionParameters();
        if (getName(position+checkPosition-1).equals("(")) {
            int i = checkPosition;
            int param = 0;
            while (!getName(position+i).equals(")")) {
                if (position+i == fileList.size()-1) {
                    break;
                }
                if (param >= functionParameters.size()) {
                    errorFunction(getLine(position+i),"5Erro de parâmetros.");
                    break;
                }
                /*if (newParameter && (isNumber(getName(position+i)) || numberList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsNumber().contains(getName(position+i)) || 
                        mapeamento.isNumberDefine(getName(getPosition()+i))) &&
                        (functionParameters.get(param).equalsIgnoreCase("int") ||
                        functionParameters.get(param).equalsIgnoreCase("float") ||
                        functionParameters.get(param).equalsIgnoreCase("double"))) {*/
                if (newParameter && isValidNumberExpression(i) &&
                        (functionParameters.get(param).equalsIgnoreCase("int") ||
                        functionParameters.get(param).equalsIgnoreCase("float") ||
                        functionParameters.get(param).equalsIgnoreCase("double"))) {
                    if (mapeamento.getFunctionsNumber().contains(getName(position+i))) {
                        checkParameters(getName(position+i), i+2);
                        i=0;
                        if (!writeNumericalOperator(i)) {
                            writeOnFile(writeFunction[param+1]);
                            param++;
                        }
                        else {
                            writeOnFile(getName(position+i));
                            i++;
                            newParameter=true;
                        }
                        //param++;
                        writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (mapeamento.isNumberDefine(getName(getPosition()+i))) {
                        writeOnFile(mapeamento.getDefineText(getName(getPosition()+i)));
                        i++;
                        if (!writeNumericalOperator(i)) {
                            writeOnFile(writeFunction[param+1]);
                            param++;
                            newParameter=false;
                        }
                        else {
                            writeOnFile(getName(position+i));
                            i++;
                            newParameter=true;
                        }
                        //param++;
                    }
                    else {
                        writeOnFile(getName(position+i));
                        i++;
                        if (!writeNumericalOperator(i)) {
                            writeOnFile(writeFunction[param+1]);
                            param++;
                            newParameter=false;
                        }
                        else {
                            writeOnFile(getName(position+i));
                            i++;
                            newParameter=true;
                        }
                        //param++;
                    }
                    /*if (writeNumericalOperator(i)) {
                        //param--;
                        writeOnFile(getName(position+i));
                        System.out.println("eh um operador numerico::: "+getName(position+i));
                        i++;
                        newParameter=true;
                    }
                    else {
                        newParameter=false;
                    }*/
                    //newParameter=false;
                }
                else if (newParameter && (isName(position+i)>0 || nameList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsName().contains(getName(position+i)) || 
                        mapeamento.isNameDefine(getName(getPosition()+i))) &&
                        functionParameters.get(param).equalsIgnoreCase("String")) {
                    if (mapeamento.getFunctionsName().contains(getName(position+i))) {
                        checkParameters(getName(position+i), i+2);
                        writeOnFile(writeFunction[param+1]);
                        i=0;
                        param++;
                        writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (isName(position+i)>0) {
                        writeName(position+i);
                        writeOnFile(writeFunction[param+1]);
                        i = isName(position+i)-position;
                        param++;
                    }
                    else if (mapeamento.isNameDefine(getName(getPosition()+i))) {
                        writeOnFile(mapeamento.getDefineText(getName(getPosition()+i)));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else {
                        writeOnFile(getName(position+i));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    newParameter=false;
                }
                else if (newParameter && (isBoolean(getName(position+i)) || booleanList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsBoolean().contains(getName(position+i)) || mapeamento.isBooleanDefine(getName(getPosition()+i))) &&
                        functionParameters.get(param).equalsIgnoreCase("boolean")) {
                    if (mapeamento.getFunctionsBoolean().contains(getName(position+i))) {
                        checkParameters(getName(position+i), i+2);
                        writeOnFile(writeFunction[param+1]);
                        i=0;
                        param++;
                        writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (mapeamento.isBooleanDefine(getName(getPosition()+i))) {
                        writeOnFile(getMapeamento().getDefineText(getName(getPosition()+i)));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else if (isBoolean(getName(position+i))) {
                        writeBoolean(getName(position+i));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else {
                        writeOnFile(getName(position+i));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    newParameter=false;
                }
                else if (getName(position+i).equals(",")) {
                    newParameter=true;
                    //System.out.println(getName(position+i+1));
                    i++;
                    //System.out.println(getName(position+i));
                    if (getName(position+i).equals(")")) {
                        errorFunction(getLine(position+i+1),"1Erro de parâmetros.");
                        break;
                    }
                }
                else {
                    errorFunction(getLine(position+i),"2Erro de parâmetros.");
                    break;
                }
            }
            if (param < functionParameters.size()) {
                errorFunction(getLine(position+i),"3Erro de parâmetros.");
            }
            if (i == fileList.size()-1) {
                errorFunction(getLine(checkPosition-1),"Está faltando '('.");
            }
            position += i+1;
        }
        else {
            errorFunction(getLine(checkPosition-1),"Está faltando '('.");
        }
    }
    
    private boolean checkFunctionParameters(String functionName, int checkPosition) {

        return true;
    }

    private void writeFunctionOnFile(String functionName, int checkPosition) throws SintaticException {
        boolean newParameter = true;
        String[] writeFunction;
        writeFunction = mapeamento.writeFunction(functionName);
        writeOnFile(writeFunction[0]);
        List<String> functionParameters = mapeamento.getFunctionParameters();
        if (getName(position+checkPosition-1).equals("(")) {
            int i = checkPosition;
            int param = 0;
            while (!getName(position+i).equals(")")) {
                if (position+i == fileList.size()-1) {
                    break;
                }
                if (param >= functionParameters.size()) {
//                    setErrorType("funcao");
//                    errorFunction(getLine(position+i),"12 - A quantidade de parâmetros é maior do que a necessária.");
                    throw new SintaticException("funcao", getLine(position+i), "12 - A quantidade de parâmetros é maior do que a necessária.");
//                    break;
                }
                if (newParameter && isValidNumberExpression(i) &&
                        (functionParameters.get(param).equalsIgnoreCase("int") ||
                        functionParameters.get(param).equalsIgnoreCase("float") ||
                        functionParameters.get(param).equalsIgnoreCase("double"))) {
                    if (mapeamento.getFunctionsNumber().contains(getName(position+i))) {
                        int positionAnt = position;

                        writeFunctionOnFile(getName(position+i), i+2);
                        i = position - positionAnt-1;
                        position = positionAnt;

                        writeFunction = mapeamento.writeFunction(functionName);
                        functionParameters = mapeamento.getFunctionParameters();
                    }
                    else {
                        writeOnFile(getName(position+i));
                    }
                    writeOnFile(writeFunction[param+1]);
                    param++;
                    i++;
                }
                else if (newParameter && (isName(position+i)>0 || nameList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsName().contains(getName(position+i)) || 
                        mapeamento.isNameDefine(getName(getPosition()+i))) &&
                        functionParameters.get(param).equalsIgnoreCase("String")) {
                    if (mapeamento.getFunctionsName().contains(getName(position+i))) {
                        writeFunctionOnFile(getName(position+i), i+2);
                        i=0;
                        param++;
                        writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (isName(position+i)>0) {
                        writeName(position+i);
                        writeOnFile(writeFunction[param+1]);
                        i = isName(position+i)-position;
                        param++;
                    }
                    else if (mapeamento.isNameDefine(getName(getPosition()+i))) {
                        writeOnFile(mapeamento.getDefineText(getName(getPosition()+i)));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else {
                        writeOnFile(getName(position+i));
                        writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    newParameter=false;
                }
                else if (newParameter && (isBoolean(getName(position+i)) || booleanList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsBoolean().contains(getName(position+i)) || mapeamento.isBooleanDefine(getName(getPosition()+i))) &&
                        functionParameters.get(param).equalsIgnoreCase("boolean")) {
                    if (mapeamento.getFunctionsBoolean().contains(getName(position+i))) {
                        checkParameters(getName(position+i), i+2);
                        //writeOnFile(writeFunction[param+1]);
                        i=0;
                        param++;
                        writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (mapeamento.isBooleanDefine(getName(getPosition()+i))) {
                        //writeOnFile(getMapeamento().getDefineText(getName(getPosition()+i)));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else if (isBoolean(getName(position+i))) {
                        //writeBoolean(getName(position+i));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else {
                        //writeOnFile(getName(position+i));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    newParameter=false;
                }
                else if (getName(position+i).equals(",")) {
                    newParameter=true;
                    i++;
                    if (getName(position+i).equals(")")) {
//                        setErrorType("funcao");
//                        errorFunction(getLine(position+i+1),"13 - Está faltando um parâmetro.");
                        throw new SintaticException("funcao", getLine(position+i+1), "13 - Está faltando um parâmetro.");
//                        break;
                    }
                }
                else {
//                    setErrorType("funcao");
//                    errorFunction(getLine(position+i),"14 - Parâmetro com valor inválido.");
                    throw new SintaticException("funcao", getLine(position+i), "14 - Parâmetro com valor inválido.");
//                    break;
                }
            }

            if (param < functionParameters.size()) {
//                setErrorType("funcao");
//                errorFunction(getLine(position+i),"13 - Está faltando um parâmetro.");
                throw new SintaticException("funcao", getLine(position+i), "13 - Está faltando um parâmetro.");
            }
            if (i == fileList.size()-1) {
//                setErrorType("sintaxe");
//                errorFunction(getLine(checkPosition-1), "15 - Está faltando '('.");
                throw new SintaticException("sintaxe", getLine(checkPosition-1), "15 - Está faltando '('.");
            }
            position += i+1;
        }
        else {
//            setErrorType("sintaxe");
//            errorFunction(getLine(checkPosition-1),"15 - Está faltando '('.");
            throw new SintaticException("sintaxe", getLine(checkPosition-1), "15 - Está faltando '('.");
        }
    }
    
    private boolean checkFunction(String functionName, int checkPosition, boolean backToBegin) throws SintaticException {
        boolean newParameter = true;
        String[] writeFunction;
        writeFunction = mapeamento.writeFunction(functionName);
        //writeOnFile(writeFunction[0]);
        List<String> functionParameters = mapeamento.getFunctionParameters();
        int positionAnt = position+checkPosition-2;
            int i = checkPosition;
        if (getName(position+checkPosition-1).equals("(")) {
            
            int param = 0;
            while (position+i < fileList.size() && !getName(position+i).equals(")")) {
                System.out.println(position+i + " < " + fileList.size() + " =>>>> " + getName(position+i));
                if (position+i == fileList.size()-1) {
//                    break;
                    throw new SintaticException("sintaxe", getLine(position), "16 - Está faltando ')'.");
                }
                
                
                if(getName(position+i).equals("fim")){
//                    setErrorType("sintaxe");
//                    errorFunction(getLine(checkPosition-1),"16 - Está faltando ')'.");
//                    break;
                    throw new SintaticException("sintaxe", getLine(position+i-1), "16 - Está faltando ')'.");
                }
                
                if (param >= functionParameters.size()) {
//                    setErrorType("funcao");
//                    errorFunction(getLine(position+i),"12 - A quantidade de parâmetros é maior do que a necessária.");
                    throw new SintaticException("funcao", getLine(position+i), "12 - A quantidade de parâmetros é maior do que a necessária.");
//                    break;
                }
                if (getName(position+i).equals(",")) {
                    newParameter=true;
                    i++;
                    if (getName(position+i).equals(")")) {
//                        setErrorType("funcao");
//                        errorFunction(getLine(position+i+1),"13 - Está faltando um parâmetro.");
//                        break;
                        throw new SintaticException("funcao", getLine(position+i+1), "13 - Está faltando um parâmetro.");
                    }
                }
                else if (newParameter && /*isValidNumberExpression(i) &&*/
                        (functionParameters.get(param).equalsIgnoreCase("int") ||
                        functionParameters.get(param).equalsIgnoreCase("float") ||
                        functionParameters.get(param).equalsIgnoreCase("double"))) {
                    if (isMathOperation(i,true, true, true)) {
                        param++;
                        i++;
                        if (getName(position).equals(")")) {
                            position-=i;
                        }
                        else if (getName(position+1).equals(",")) {
                            position-=(i-1);
                        }
                        //position-=(i+1);
                    }
                    else {
                        return false;
                    }
                }
                else if (newParameter && (isName(position+i)>0 || nameList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsName().contains(getName(position+i)) || 
                        mapeamento.isNameDefine(getName(getPosition()+i))) &&
                        functionParameters.get(param).equalsIgnoreCase("String")) {
                    if (mapeamento.getFunctionsName().contains(getName(position+i))) {
                        //checkParameters(getName(position+i), i+2);
                        checkFunction(getName(position+i), i+2, false);
                        //writeOnFile(writeFunction[param+1]);
                        i=0;
                        param++;
                        //writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (isName(position+i)>0) {
                        //writeName(position+i);
                        //writeOnFile(writeFunction[param+1]);
                        i = isName(position+i)-position;
                        param++;
                    }
                    else if (mapeamento.isNameDefine(getName(getPosition()+i))) {
                        //writeOnFile(mapeamento.getDefineText(getName(getPosition()+i)));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else {
                        //writeOnFile(getName(position+i));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    newParameter=false;
                }
                else if (newParameter && (isBoolean(getName(position+i)) || booleanList.contains(getName(position+i)) ||
                        mapeamento.getFunctionsBoolean().contains(getName(position+i)) || mapeamento.isBooleanDefine(getName(getPosition()+i))) &&
                        functionParameters.get(param).equalsIgnoreCase("boolean")) {
                    if (mapeamento.getFunctionsBoolean().contains(getName(position+i))) {
                        checkParameters(getName(position+i), i+2);
                        //writeOnFile(writeFunction[param+1]);
                        i=0;
                        param++;
                        writeFunction = mapeamento.writeFunction(functionName);
                    }
                    else if (mapeamento.isBooleanDefine(getName(getPosition()+i))) {
                        //writeOnFile(getMapeamento().getDefineText(getName(getPosition()+i)));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else if (isBoolean(getName(position+i))) {
                        //writeBoolean(getName(position+i));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    else {
                        //writeOnFile(getName(position+i));
                        //writeOnFile(writeFunction[param+1]);
                        i++;
                        param++;
                    }
                    newParameter=false;
                }
                else if (getName(position+i).equals(",")) {
                    newParameter=true;
                    i++;
                    if (getName(position+i).equals(")")) {
//                        setErrorType("funcao");
//                        errorFunction(getLine(position+i+1),"13 - Está faltando um parâmetro.");
//                        break;
                        throw new SintaticException("funcao", getLine(position+i+1), "13 - Está faltando um parâmetro.");
                    }
                }
                else {
//                    setErrorType("funcao");
//                    errorFunction(getLine(position+i),"14 - Parâmetro com valor inválido.");
//                    break;
                    throw new SintaticException("funcao", getLine(position+i), "14 - Parâmetro com valor inválido.");
                }
            }
            
            
            if (param < functionParameters.size()) {
//                setErrorType("funcao");
//                errorFunction(getLine(position+i),"13 - Está faltando um parâmetro.");
                System.out.println(getName(position+1) + " <<<<< Eu de novo");
                throw new SintaticException("funcao", getLine(position), "13 - Está faltando um parâmetro.");
            }
            if (i == fileList.size()-1) {
//                setErrorType("sintaxe");
//                errorFunction(getLine(checkPosition-1),"15 - Está faltando '('.");
                throw new SintaticException("sintaxe", getLine(checkPosition-1), "16 - Está faltando ')'.");
            }
            position += i+1;
        }
        else {
//            setErrorType("sintaxe");
//            errorFunction(getLine(checkPosition-1),"15 - Está faltando '('.");
            throw new SintaticException("sintaxe", getLine(checkPosition-1), "15 - Está faltando '('.");
        }
        System.out.println(" e agora?" );
        System.out.println(position + " == " + fileList.size());
        
        if(!(getName(checkPosition+i-1).equals(")"))){
//            setErrorType("sintaxe");
//            errorFunction(getLine(checkPosition-1),"16 - Está faltando ')'.");
            throw new SintaticException("sintaxe", getLine(checkPosition-1), "16 - Está faltando ')'.");
        }
        
        
        if (backToBegin) {
            position = positionAnt;
        }
        return true;
    }
    
    public void writeName(int index) throws SintaticException {
        writeOnFile("\"");
        writeOnFile(getName(index+1));
        int value = index+2;
        while (!getName(value).equals("\"")) {
            writeOnFile(getName(value));
            if (!getName(value+1).equals("\"")) {
                writeOnFile(" ");
            }
            value++;
        }
        writeOnFile("\"");
    }
    
    public int isName(int index) throws SintaticException {
        int value=index;
        if (getName(value).equals("\"")) {
            value++;
            while (value < fileList.size() && !getName(value).equals("\"")) {
                value++;
            }
            if (value == fileList.size()) {
//                return false;
                return -1;
            }
            else {
                return value+1;
//                return true;
            }
        }
        else {
            return -1;
//            return false;
        }
    }

    public boolean isOperator (String name) {
        if (name.equals("+") || name.equals("-") || name.equals("*") || name.equals("/")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isLogicalOperator(String name) {
        if (name.equals("=") || name.equals(">") || name.equals("<") || name.equals("!")) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isMathOperation(int index, boolean condition, boolean continueTesting, boolean functionArgument) throws SintaticException {
        
        int positionAnt = position;
        int parenteses = 0;
        boolean stop = false;
        position+=index;
        while ( (!keywords(getName(position)) || getName(position).equals("(") || getName(position).equals(")") ||
           isNumber(getName(position)) || mapeamento.isNumberDefine(getName(position)) ||
           mapeamento.getFunctionsNumber().contains(getName(position)) ||
           (numberList.contains(getName(position)) && !getName(position+1).equals("=")) ) && stop==false ) {
            if (getName(position).equals("(")) {
                if (functionArgument) {
                    return false;
                }
                parenteses++;
                position+=1;
            }
            else if (getName(position).equals(")")) {
                System.out.println("Entrei no parenteses " + getName(position));
                if ((condition == true && parenteses == 0) ||
                 (functionArgument && getName(position+1).equals(","))) {
                System.out.println("1 " + getName(position));
                    stop=true;
                }
                else {
                System.out.println("2 " + getName(position));
                    parenteses--;
                    if (isOperator(getName(position+1))) {
                        position+=2;
                    }
                    else {
                        //position+=1;
                        stop=true;
                    }
                }
                // if (isOperator(getName(position+1))) {
                //     position+=2;
                // }
                // else {
                //     position+=1;
                // }
            }
            else if (position+1 < fileList.size() && (isNumber(getName(position)) || numberList.contains(getName(position))
                 || mapeamento.isNumberDefine(getName(position)))) {
                if (getName(position+1).equals(")")) {
                    position++;
                }
                else if(getName(position+1).equals(",")) {
                    stop = true;
                }
                else if (!isOperator(getName(position+1))) {
                    stop = true;
                }
                else {
                    position+=2;
                }
            }
            else if (mapeamento.getFunctionsNumber().contains(getName(position))) {
                if (!checkFunction(getName(position), 2, false)) {
                    return false;
                }
                else {
                    position--;
                    if ((functionArgument && getName(position+1).equals(","))|| (getName(position).equals(")") && !isOperator(getName(position+1)))) {
                        //position++; // Coloquei agora
                        stop = true;
                    }
                    else if (isOperator(getName(position+1))) {
                        position+=2;
                    }
                }
                System.out.println("Depois da função " + getName(position));
            }
            else if (isOperator(getName(position))) {
                return false;
            }
            else {
                return false;
            }
        }
System.out.println(":( " + getName(position));
        if (getName(position+1).equals("(") || (!keywords(getName(position+1)) &&
            !mapeamento.getFunctionsVoid().contains(getName(position+1)) && !numberList.contains(getName(position+1)) && 
            !nameList.contains(getName(position+1)) && !booleanList.contains(getName(position+1)) &&
            (!functionArgument && getName(position+1).equals(",") || functionArgument && !getName(position+1).equals(",")) 
            && !(condition==true && getName(position).equals(")"))
            )) {
            return false;
        }

        System.out.println("Saiii " + getName(position+1));
            
        if (!continueTesting) {
            position=positionAnt;
        }
        
        if (parenteses==0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void writeMathOperation(int index, boolean condition) throws SintaticException {
        boolean stop = false;
        int parenteses = 0;
        position+=index;
        while ( (!keywords(getName(position)) || getName(position).equals("(") || getName(position).equals(")") ||
           isNumber(getName(position)) || mapeamento.isNumberDefine(getName(position)) ||
           mapeamento.getFunctionsNumber().contains(getName(position)) ||
           (numberList.contains(getName(position)) && !getName(position+1).equals("=")) ) && stop==false ) {
            if (getName(position).equals("(")) {
                writeOnFile(getName(position));
                position+=1;
                parenteses++;
            }
            else if (getName(position).equals(")")) {
                

                if (condition == true && parenteses == 0) {
                    stop=true;
                }
                else {
                    writeOnFile(getName(position));
                    parenteses--;
                    position+=1;
                }
                if (isOperator(getName(position))) {
                    writeOnFile(getName(position));
                    position++;
                }
            }
            else if (isNumber(getName(position)) || numberList.contains(getName(position)) || mapeamento.isNumberDefine(getName(position))) {
                writeOnFile(getName(position));
                if (getName(position+1).equals(")")) {

                }
                else if (!isOperator(getName(position+1))) {
                    stop = true;
                }
                else {
                    writeOnFile(getName(position+1));
                    position+=1;
                }
                position++;
            }
            else if (mapeamento.getFunctionsNumber().contains(getName(position))) {
                writeFunctionOnFile(getName(position), 2);
                System.out.println("O que tem no final de escrever1 " + getName(position));
                System.out.println("tam " + position + " final " + (fileList.size()-1));
                if (position==fileList.size()-1) {
                    stop = true;
                }
                else if (!isOperator(getName(position))) {
                    stop = true;
                }
                else {
                    writeOnFile(getName(position));
                    position+=1;
                }
                //Tirar isso!!!
                //while(!getName(position).equals("fim"))
                //    position++;
            }
            else if (isOperator(getName(position))) {
                writeOnFile(getName(position));
                position++;
            }
        }
        System.out.println("O que tem no final de escrever " + getName(position));
    }
    
    public boolean isBoolean(String name) {
        if (name.equals("verdadeiro") || name.equals("falso")) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public boolean isNumber(String name) {
        try {
            Float.parseFloat(name);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean writeNumericalOperator(int i) throws SintaticException {
        if (getName(position+i).equals("+") || getName(position+i).equals("-") || 
            getName(position+i).equals("*") || getName(position+i).equals("/")) {
            return true;
        }
        return false;
    }

    public boolean isValidNumberExpression(int i) throws SintaticException {
        if (isNumber(getName(position+i)) || numberList.contains(getName(position+i)) ||
                mapeamento.getFunctionsNumber().contains(getName(position+i)) || 
                mapeamento.isNumberDefine(getName(position+i))) {
            if (getName(position+i+1).equals("+") || getName(position+i+1).equals("-") || 
                getName(position+i+1).equals("*") || getName(position+i+1).equals("/")) {
                return isValidNumberExpression(i+2);
            }
            return true;
        }
        else {
            return false;
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getBracketsCounter() {
        return bracketsCounter;
    }

    public void setBracketsCounter(int bracketsCounter) {
        this.bracketsCounter = bracketsCounter;
    }

    public boolean isError() {
        return error;
    }
    
    public int getFileListSize() {
        return fileList.size();
    }
    
    public boolean numberListContains(String name) {
        return numberList.contains(name);
    }
    
    public void numberListAdd(String name) {
        numberList.add(name);
    }
    
    public void numberListRemove(String name) {
        numberList.remove(name);
    }
    
    public boolean nameListContains(String name) {
        return nameList.contains(name);
    }
    
    public boolean booleanListContains(String name) {
        return booleanList.contains(name);
    }

    public List<String> getTaskList() {
        return taskList;
    }

    public List<String> getNumberList() {
        return numberList;
    }
    
    public void nameListAdd(String name) {
        nameList.add(name);
    }
    
    public void booleanListAdd(String name) {
        booleanList.add(name);
    }

    public String getErrorStr() {
        return errorStr;
    }

    public int getErrorInt() {
        return errorInt;
    }

    public String[] getDeclareString() {
        return declareString;
    }

    public Mapeamento getMapeamento() {
        return mapeamento;
    }

    public String[] getDeclareNumber() {
        return declareNumber;
    }

    public String[] getIfCondition() {
        return ifCondition;
    }

    public String[] getWhileCondition() {
        return whileCondition;
    }

    public String[] getDoCondition() {
        return doCondition;
    }

    public String[] getRepeatCondition() {
        return repeatCondition;
    }

    public String[] getForCondition() {
        return forCondition;
    }

    public String[] getSwitchCondition() {
        return switchCondition;
    }
     
    public void getCheckParameters(String functionName, int checkPosition) throws SintaticException {
        checkParameters(functionName, checkPosition);
    }

    public OtherFunctions getFunctions() {
        return functions;
    }

    public String[] getDeclareBoolean() {
        return declareBoolean;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String _errorType) {
        errorType=errorType+" "+_errorType;
    }
    
    
    
}