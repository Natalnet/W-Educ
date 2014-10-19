/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.roboeduc.compiladorreduc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Language language;
        language = new Language();
        language.setId(new Long(1));
        language.setName("NXC");
        language.setDescription("Linguagem baseada em C utilizada para programação de robôs Lego Mindstorms NXT.");
        language.setRobot("Lego Mindstorms NXT");
        language.setExtension("nxc");
        
        language.setCompileCode("diretorio/nbc.exe diretorio/nomedoprograma.nxc -O=diretorio/nomedoprograma.rxe");
        language.setCompilerFile("nbc.exe");
        language.setSendCode("diretorio/nbc.exe -d diretorio/nomedoprograma.nxc");
        language.setSentExtension("nxc");
        
        language.setHeader("");
        language.setFootnote("");
        
        language.setMainFunction("task main(){comandos}");
        language.setOtherFunctions("task funcao(){comandos}");
        language.setCallFunction("funcao();");
        
        Types types;
        types = new Types();
        types.setId(new Long(1));
        types.setName("");
        types.setDeclareFalse("false");
        types.setDeclareTrue("true");
        types.setDeclareFloat("float variavel = valor;");
        types.setDeclareString("string variavel = valor;");
        types.setDeclareBoolean("bool variavel = valor;");
        language.setTypes(types);
        
        Long tempId;
        
        LFunction function = new LFunction();
        List<LFunction> functions = new ArrayList();
        function.setId(new Long(1));
        function.setName("definirtoque");
        function.setType("Outros");
        function.setReturnType("Void");
        function.setQntParameters("1");
        function.setCode("SetSensorTouch(IN_var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(tempId + new Long(1));
        function.setName("definircor");
        function.setType("Outros");
        function.setReturnType("Void");
        function.setQntParameters("1");
        function.setCode("SetSensorColorFull(IN_var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(tempId + new Long(1));
        function.setName("definirluz");
        function.setType("Outros");
        function.setReturnType("Void");
        function.setQntParameters("1");
        function.setCode("SetSensorColorRed(IN_var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("definirultra");
        function.setType("Outros");
        function.setReturnType("Void");
        function.setQntParameters("1");
        function.setCode("SetSensorLowspeed(IN_var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("ultra");
        function.setType("Leitura");
        function.setReturnType("Float");
        function.setQntParameters("1");
        function.setCode("SensorUS(IN_var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("cor");
        function.setType("Leitura");
        function.setReturnType("Float");
        function.setQntParameters("1");
        function.setCode("Sensor(IN_var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("toque");
        function.setType("Leitura");
        function.setReturnType("Float");
        function.setQntParameters("1");
        function.setCode("SENSOR_var1(int);");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("espere");
        function.setType("Outros");
        function.setReturnType("Void");
        function.setQntParameters("1");
        function.setCode("Wait(var1(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("frente");
        function.setType("Movimentação");
        function.setReturnType("Void");
        function.setQntParameters("3");
        function.setCode("OnFwd(var1(String), var2(int));\n" +
                         "Wait(var3(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("tras");
        function.setType("Movimentação");
        function.setReturnType("Void");
        function.setQntParameters("3");
        function.setCode("OnRev(var1(String), var2(int));\n" +
                         "Wait(var3(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("girar");
        function.setType("Movimentação");
        function.setReturnType("Void");
        function.setQntParameters("5");
        function.setCode("OnFwd(var1(String), var2(int));\n" +
                         "OnRev(var3(String), var4(int));\n" +
                         "Wait(var5(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("parar");
        function.setType("Movimentação");
        function.setReturnType("Void");
        function.setQntParameters("1");
        function.setCode("Off(var1(String));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("tocar");
        function.setType("Outros");
        function.setReturnType("Void");
        function.setQntParameters("2");
        function.setCode("PlayTone(var1(int), var2(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("frenterotacao");
        function.setType("Movimentação");
        function.setReturnType("Void");
        function.setQntParameters("3");
        function.setCode("RotateMotor(var1(String), var2(int), var3(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("trasrotacao");
        function.setType("Movimentação");
        function.setReturnType("Void");
        function.setQntParameters("3");
        function.setCode("RotateMotor(var1(String), var2(int), -var3(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("escrever");
        function.setType("Escrita");
        function.setReturnType("Void");
        function.setQntParameters("3");
        function.setCode("TextOut(var1(int), LCD_LINEvar2(int), var3(String));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        tempId = function.getId();
        
        function = new LFunction();
        function.setId(new Long(tempId.intValue() + 1));
        function.setName("escrevernumero");
        function.setType("Escrita");
        function.setReturnType("Void");
        function.setQntParameters("3");
        function.setCode("NumOut(var1(int), LCD_LINEvar2(int), var3(int));");
        function.setDescription("");
        function.setTypeAliases("");
        function.setImageURL("");
        functions.add(function);
        
        language.setFunctions(functions);
        
        Operators operators;
        operators = new Operators();
        operators.setId(new Long(1));
        operators.setEqualTo("==");
        operators.setNotEqualTo("!=");
        operators.setGreaterThan(">");
        operators.setLessThan("<");
        operators.setLessThanOrEqualTo("<=");
        operators.setGreaterThanOrEqualTo(">=");
        operators.setLogicalAnd("&&");
        operators.setLogicalOr("||");
        operators.setLogicalNot("!");
        operators.setName("");
        
        ControlFlow controlFlow;
        controlFlow = new ControlFlow();
        controlFlow.setId(new Long(1));
        controlFlow.setLanguageName("NXC");
        controlFlow.setBreakCode("break;");
        controlFlow.setDoCode("do {comandos} while (condicao);");
        controlFlow.setForCode("for (int variavel = valor1; variavel < valor2; variavel+=passo) {comandos}");
        controlFlow.setIfCode("if(condicao){comandos1}else{comandos2}");
        controlFlow.setRepeatCode("repeat(var){comandos}");
        controlFlow.setSwitchCode("switch (variavel) {\n" +
                                  "//teste1\n" +
                                  "case (valor1): comandos1\n" +
                                  "break;\n" +
                                  "//teste2\n" +
                                  "default: comandos2\n" +
                                  "break;\n" +
                                  "//fim\n" +
                                  "}");
        controlFlow.setWhileCode("while(condicao){comandos}");
        
        
        // TODO code application logic here
        System.out.println("Compilador R-Educ 0.1");
        // Mapeamento mapeamento = new Mapeamento("C:\\Users\\Victor\\Documents\\a.rob");
        analisadorLexico lexico = new analisadorLexico();
        lexico.readFile("C:\\Users\\Victor\\Documents\\a.rob");
        analisadorSintatico sintatico = new analisadorSintatico(lexico, "C:\\Users\\Victor\\Documents\\", "c", "NXC", "nxc");
        //Modifiquei e por isso tem que ser feitas modificacoes aqui tmb...
        //sintatico.getMapeamento().defineValues(language, types, functions, operators, controlFlow);
        sintatico.startCompile();
        sintatico.closeFile();
        System.out.print(sintatico.isError());
    }
    
}
