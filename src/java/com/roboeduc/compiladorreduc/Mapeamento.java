package com.roboeduc.compiladorreduc;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Mapeamento {
    
    String languageName;
    String fileName;
    Language language;
    Types types;
    Operators operators;
    ControlFlow controlFlow;
    
    List<LFunction> functions = new ArrayList();
    List<String> functionsNumber = new ArrayList();
    List<String> functionsName = new ArrayList();
    List<String> functionsBoolean = new ArrayList();
    List<String> functionsVoid = new ArrayList();
    List<String> functionParameters = new ArrayList();
    
    List<Defines> defines = new ArrayList();
    List<String> definesNumber = new ArrayList();
    List<String> definesName = new ArrayList();
    List<String> definesBoolean = new ArrayList();
    
    List languageList;
    List typesList;
    List operatorsList;
    List controlFlowList;
    
    public Mapeamento(String fileName) {
        this.languageName = "NXC";
        this.fileName = fileName;
        
        //System.out.println("Nome da linguagem: " + languageName);
        
    }
    
    public void defineValues(Language languageIt, Types typesIt, List<LFunction> functionsIt, Operators operatorsIt, ControlFlow controlFlowIt, List<Defines> definesIt) {
        
        language = languageIt;
        
        types = typesIt;
        language.setTypes(types);
        
        functions = functionsIt;
        language.setFunctions(functions);
        
        operators = operatorsIt;
        
        controlFlow = controlFlowIt;
        
        defines = definesIt;
        
        LFunction elemFunction = null;
        Defines elemDefines = null;
        
        for (ListIterator it = functions.listIterator(); it.hasNext();) {
            elemFunction = (LFunction) it.next();
            if (elemFunction.getReturnType().equalsIgnoreCase("int") || elemFunction.getReturnType().equalsIgnoreCase("float") || elemFunction.getReturnType().equalsIgnoreCase("double")) {
                //System.out.println("getName: " + elemFunction.getName());
                //System.out.println("getName: " + elemFunction.getCode());
                //System.out.println("getName: " + elemFunction.getQntParameters());
                //System.out.println("getName: " + elemFunction.getReturnType());
                // functionsNumber.add("Carlinha");
                functionsNumber.add(elemFunction.getName());
            }
            else if (elemFunction.getReturnType().equalsIgnoreCase("String")) {
                functionsName.add(elemFunction.getName());
            }
            else if (elemFunction.getReturnType().equalsIgnoreCase("boolean")) {
                functionsBoolean.add(elemFunction.getName());
            }
            else {
                functionsVoid.add(elemFunction.getName());
            }
        }
        
        System.out.println("aeeeeeee " + defines.size());
        
        for (ListIterator it = defines.listIterator(); it.hasNext();) {
            elemDefines = (Defines) it.next();
            System.out.println("elemfed " + elemDefines.getName() + " type: " + elemDefines.getType());
            if (elemDefines.getType().equalsIgnoreCase("int") || elemDefines.getType().equalsIgnoreCase("float") || elemDefines.getType().equalsIgnoreCase("double")) {
                definesNumber.add(elemDefines.getName());
                //System.out.println("DefinesName: " + elemDefines.getName());
            }
            else if (elemDefines.getType().equalsIgnoreCase("String")) {
                definesName.add(elemDefines.getName());
            }
            else if (elemDefines.getType().equalsIgnoreCase("boolean")) {
                definesBoolean.add(elemDefines.getName());
            }
        }
    }
    
    public String getDefineText(String defineName) {
        Defines elemDefines = null;
        for (ListIterator it = defines.listIterator(); it.hasNext();) {
            elemDefines = (Defines) it.next();
            if (elemDefines.getName().equals(defineName)) {
                if (elemDefines.isAlreadyExists()) {
                    return elemDefines.getText();
                }
                else {
                    return elemDefines.getName();
                }
            }
        }
        return "";
    }
    
    public String getExtension() {
        return language.getExtension();
    }

    public List<List<String>> defines() {
        Defines elemDefines = null;
        List<String> definesText = new ArrayList();
        List<String> definesNameNE = new ArrayList();
        List<String> definesNameE = new ArrayList();
        List<List<String>> definesList = new ArrayList();
        for (ListIterator it = defines.listIterator(); it.hasNext();) {
            elemDefines = (Defines) it.next();
            //if (!elemDefines.isAlreadyExists()) {
                definesText.add("#define " + elemDefines.getName() + " " + elemDefines.getText() + "\n");
                definesNameNE.add(elemDefines.getName());
            //}
            //else {
            //    definesNameE.add(elemDefines.getName());
            //}
        }
        definesList.add(definesText);
        definesList.add(definesNameNE);
        definesList.add(definesNameE);
        return definesList;
    }
    
    public String header() {
        String header = language.getHeader();
        header = header.replace("nomedoprograma", fileName);
        return header;
    }
    
    public String footnote() {
        String footnote = language.getFootnote();
        footnote = footnote.replace("nomedoprograma", fileName);
        return footnote;
    }
    
    public String[] otherFunction() {
        String otherFunction = language.getOtherFunctions();
        otherFunction = otherFunction.replace("nomedoprograma", fileName);
        String returnString[] = new String[3];
        returnString[0] = otherFunction.substring(0, otherFunction.indexOf("funcao"));
        returnString[1] = otherFunction.substring(otherFunction.indexOf("funcao")+6, otherFunction.indexOf("comandos"));
        returnString[2] = otherFunction.substring(otherFunction.indexOf("comandos")+8);
        return returnString;
    }
    
    public String[] mainFunction() {
        String mainFunction = language.getMainFunction();
        mainFunction = mainFunction.replace("nomedoprograma", fileName);
        String returnString[] = new String[2];
        returnString[0] = mainFunction.substring(0, mainFunction.indexOf("comandos"));
        returnString[1] = mainFunction.substring(mainFunction.indexOf("comandos")+8);
        return returnString;
    }
    
    public String[] callFunction() {
        String callFunction = language.getCallFunction();
        String returnString[] = new String[2];
        returnString[0] = callFunction.substring(0, callFunction.indexOf("funcao"));
        returnString[1] = callFunction.substring(callFunction.indexOf("funcao")+6);
        return returnString;
    }
    
    public String[] declareString() {
        String declareString = types.getDeclareString();
        String returnString[] = new String[3];
        returnString[0] = declareString.substring(0, declareString.indexOf("variavel"));
        returnString[1] = declareString.substring(declareString.indexOf("variavel")+8, declareString.indexOf("valor"));
        returnString[2] = declareString.substring(declareString.indexOf("valor")+5);
        return returnString;
    }
    
    public String[] declareBoolean() {
        String declareBoolean = types.getDeclareBoolean();
        String returnString[] = new String[3];
        returnString[0] = declareBoolean.substring(0, declareBoolean.indexOf("variavel"));
        returnString[1] = declareBoolean.substring(declareBoolean.indexOf("variavel")+8, declareBoolean.indexOf("valor"));
        returnString[2] = declareBoolean.substring(declareBoolean.indexOf("valor")+5);
        return returnString;
    }
    
    public String declareTrue() {
        return types.getDeclareTrue();
    }
    
    public String declareFalse() {
        return types.getDeclareFalse();
    }
    
    public String[] writeFunction(String functionName) {
        functionParameters.clear();
        
        LFunction elemFunction=null;
        for (ListIterator it = functions.listIterator(); it.hasNext();) {
            elemFunction = (LFunction) it.next();
            if (elemFunction.getName().equals(functionName)) {
                break;
            }
        }
        String writeFunction = elemFunction.getCode();
        String returnString[] = new String[Integer.parseInt(elemFunction.getQntParameters())+1];
        int aux=0;
        for (int i = 1; i < Integer.parseInt(elemFunction.getQntParameters())+1; i++) {
            returnString[i-1] = writeFunction.substring(aux, writeFunction.indexOf("var"+i));
            aux = writeFunction.substring(writeFunction.indexOf("var"+i)).indexOf(")")+1+returnString[i-1].length() + aux;
            functionParameters.add(writeFunction.substring(writeFunction.indexOf("var"+i)+("var"+i).length()+1,aux-1));
        }
        returnString[Integer.parseInt(elemFunction.getQntParameters())] = writeFunction.substring(aux);
        return returnString;
    }
    
    public String writeOperator(String operator1, String operator2) {
        if ("=".equals(operator1)) {
            System.out.println("Entrei aqui!!!");
            return operators.getEqualTo();
        }
        else if (">".equals(operator1)) {
            if ("=".equals(operator2)) {
                return operators.getGreaterThanOrEqualTo();
            }
            else {
                return operators.getGreaterThan();
            }
        }
        else if ("<".equals(operator1)) {
            if ("=".equals(operator2)) {
                return operators.getLessThanOrEqualTo();
            }
            else {
                return operators.getLessThan();
            }
        }
        else if ("!".equals(operator1)) {
            if ("=".equals(operator2)) {
                return operators.getNotEqualTo();
            }
            else {
                return "error";
            }
        }
        return "error";
    }
    
    public String writeRelationalOperator(String operator) {
        if ("e".equals(operator)) {
            return operators.getLogicalAnd();
        }
        else if ("ou".equals(operator)) {
            return operators.getLogicalOr();
        }
        else if ("nao".equals(operator)) {
            return operators.getLogicalNot();
        }
        return "error";
    }
    
    public String[] declareNumber() {
        String declareNumber = types.getDeclareFloat();
        String returnString[] = new String[3];
        returnString[0] = declareNumber.substring(0, declareNumber.indexOf("variavel"));
        returnString[1] = declareNumber.substring(declareNumber.indexOf("variavel")+8, declareNumber.indexOf("valor"));
        returnString[2] = declareNumber.substring(declareNumber.indexOf("valor")+5);
        return returnString;
    }

    public String breakFunction() {
        return controlFlow.getBreakCode();
    }
    
    public String[] ifCondition() {
        String ifCondition = controlFlow.getIfCode();
        String returnString[] = new String[4];
        returnString[0] = ifCondition.substring(0, ifCondition.indexOf("condicao"));
        returnString[1] = ifCondition.substring(ifCondition.indexOf("condicao")+8, ifCondition.indexOf("comandos1"));
        returnString[2] = ifCondition.substring(ifCondition.indexOf("comandos1")+9, ifCondition.indexOf("comandos2"));
        returnString[3] = ifCondition.substring(ifCondition.indexOf("comandos2")+9);
        return returnString;
    }
    
    public String[] whileCondition() {
        String whileCondition = controlFlow.getWhileCode();
        String returnString[] = new String[3];
        
        returnString[0] = whileCondition.substring(0, whileCondition.indexOf("condicao"));
        returnString[1] = whileCondition.substring(whileCondition.indexOf("condicao")+8, whileCondition.indexOf("comandos"));
        returnString[2] = whileCondition.substring(whileCondition.indexOf("comandos")+8);
        
//        returnString[0] = whileCondition.substring(0, whileCondition.indexOf("var"));
//        returnString[1] = whileCondition.substring(whileCondition.indexOf("var")+3, whileCondition.indexOf("comandos"));
//        returnString[2] = whileCondition.substring(whileCondition.indexOf("comandos")+8);
        
        return returnString;
    }
    
    public String[] doCondition() {
        String doCondition = controlFlow.getDoCode();
        String returnString[] = new String[3];
        returnString[0] = doCondition.substring(0, doCondition.indexOf("comandos"));
        returnString[1] = doCondition.substring(doCondition.indexOf("comandos")+8, doCondition.indexOf("condicao"));
        returnString[2] = doCondition.substring(doCondition.indexOf("condicao")+8);
        return returnString;
    }
    
    public String[] repeatCondition() {
        String repeatCondition = controlFlow.getRepeatCode();
        String returnString[] = new String[3];
        returnString[0] = repeatCondition.substring(0, repeatCondition.indexOf("var"));
        returnString[1] = repeatCondition.substring(repeatCondition.indexOf("var")+3, repeatCondition.indexOf("comandos"));
        returnString[2] = repeatCondition.substring(repeatCondition.indexOf("comandos")+8);
        return returnString;
    }
    
    public String[] forCondition(String variable, String lim1, String lim2, String step) {
        String forCondition = controlFlow.getForCode();
        String returnString[] = new String[2];
        forCondition = forCondition.replace("variavel", variable);
        forCondition = forCondition.replace("valor1", lim1);
        forCondition = forCondition.replace("valor2", lim2);
        forCondition = forCondition.replace("passo", step);
        returnString[0] = forCondition.substring(0, forCondition.indexOf("comandos"));
        returnString[1] = forCondition.substring(forCondition.indexOf("comandos")+8);
        return returnString;
    }
    
    public String[] switchCondition() {
        String switchCondition = controlFlow.getSwitchCode();
        String returnString[] = new String[8];
        returnString[0] = switchCondition.substring(0, switchCondition.indexOf("variavel"));
        returnString[1] = switchCondition.substring(switchCondition.indexOf("variavel")+8, switchCondition.indexOf("//"));
        returnString[2] = switchCondition.substring(switchCondition.indexOf("teste1")+6, switchCondition.indexOf("valor1"));
        returnString[3] = switchCondition.substring(switchCondition.indexOf("valor1")+6, switchCondition.indexOf("comandos1"));
        returnString[4] = switchCondition.substring(switchCondition.indexOf("comandos1")+9, switchCondition.indexOf("//",switchCondition.indexOf("comandos1")));
        returnString[5] = switchCondition.substring(switchCondition.indexOf("teste2")+6, switchCondition.indexOf("comandos2"));
        returnString[6] = switchCondition.substring(switchCondition.indexOf("comandos2")+9, switchCondition.indexOf("//",switchCondition.indexOf("comandos2")));
        returnString[7] = switchCondition.substring(switchCondition.indexOf("fim")+3);
        return returnString;
    }
    
    public boolean isNameFunction(String name) {
        for (int i = 0; i < functionsName.size(); i++) {
            if (functionsName.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isNumberFunction(String name) {
        for (int i = 0; i < functionsNumber.size(); i++) {
            if (functionsNumber.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isBooleanFunction(String name) {
        for (int i = 0; i < functionsName.size(); i++) {
            if (functionsBoolean.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isNameDefine(String name) {
        for (int i = 0; i < definesName.size(); i++) {
            if (definesName.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isNumberDefine(String name) {
        for (int i = 0; i < definesNumber.size(); i++) {
            if (definesNumber.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isBooleanDefine(String name) {
        for (int i = 0; i < definesName.size(); i++) {
            if (definesBoolean.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isVoidFunction(String name) {
        for (int i = 0; i < functionsName.size(); i++) {
            if (functionsVoid.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public List<String> getFunctionsNumber() {
        return functionsNumber;
    }

    public List<String> getFunctionsName() {
        return functionsName;
    }

    public List<String> getFunctionsBoolean() {
        return functionsBoolean;
    }

    public List<String> getFunctionParameters() {
        return functionParameters;
    }

    public List getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List languageList) {
        this.languageList = languageList;
    }

    public List getTypesList() {
        return typesList;
    }

    public void setTypesList(List typesList) {
        this.typesList = typesList;
    }

    public List getOperatorsList() {
        return operatorsList;
    }

    public void setOperatorsList(List operatorsList) {
        this.operatorsList = operatorsList;
    }

    public List getControlFlowList() {
        return controlFlowList;
    }

    public void setControlFlowList(List controlFlowList) {
        this.controlFlowList = controlFlowList;
    }

    public void setFunctions(List<LFunction> functions) {
        this.functions = functions;
    }

    public Language getLanguage() {
        return language;
    }

    public List<String> getFunctionsVoid() {
        return functionsVoid;
    }

    public void setFunctionsVoid(List<String> functionsVoid) {
        this.functionsVoid = functionsVoid;
    }
    
    
    
}
