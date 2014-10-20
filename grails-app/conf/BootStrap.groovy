import org.natalnet.weduc.*

class BootStrap {

    def init = { servletContext ->
    	// Registra privilégios, caso não existam
    	def privilegios = ["ADMIN", "PROFESSOR", "ALUNO"]
    	privilegios.each {
    		Privilegio.findOrSaveWhere(authority: "ROLE_" + it)
    	}

    	// Registra usuário padrão
    	def privilegio = Privilegio.findWhere(authority: "ROLE_ADMIN")
    	def usuario = Professor.findWhere(username: "admin")
    	if(!usuario) {
    		usuario = new Professor()
    		usuario.username = "admin"
    		usuario.password = "admin"
    		usuario.email = "admin@email.com"
    		usuario.save flush: true, failOnError: true

    		def usuarioPrivilegio = new UsuarioPrivilegio()
    		usuarioPrivilegio.usuario = usuario
    		usuarioPrivilegio.privilegio = privilegio
    		usuarioPrivilegio.save flush: true, failOnError: true
    	}

        // Cadastro de linguagem - NXC
        
        if(!Linguagem.findWhere(name: "NXC")) {
            
            println "Linguagem NXC nao encontrada no banco de dados"
            println "Iniciando cadastro da linguagem NXC"
            
            // Linguagem propriamente dita
            def linguagem = new Linguagem()
            linguagem.name = "NXC"
            linguagem.description = "Linguagem baseada em C utilizada para programação de robôs Lego Mindstorms NXT."
            linguagem.robot = "Lego Mindstorms NXT"
            linguagem.extension = "nxc"
            linguagem.compileCode = "diretorio/nbc.exe diretorio/nomedoprograma.nxc -O=diretorio/nomedoprograma.rxe"
            linguagem.compilerFile = "nbc.exe"
            linguagem.sendCode = "diretorio/nbc.exe -d diretorio/nomedoprograma.nxc"
            linguagem.sentExtension = "nxc"
            linguagem.header = ""
            linguagem.footnote = ""
            linguagem.mainFunction = "task main(){comandos}"
            linguagem.otherFunctions = "task funcao(){comandos}"
            linguagem.callFunction = "funcao();"
            linguagem.autor = usuario

            // Tipos da linguagem
            linguagem.types = new Tipos()
            linguagem.types.name = ""
            linguagem.types.declareFalse = "false"
            linguagem.types.declareTrue = "true"
            linguagem.types.declareFloat = "float variavel = valor;"
            linguagem.types.declareString = "string variavel = \"valor\";"
            linguagem.types.declareBoolean = "bool variavel = valor;"
            linguagem.types.save(flush: true, failOnError: true)

            // Operadores da linguagem
            linguagem.operators = new Operadores()
            linguagem.operators.equalTo = "=="
            linguagem.operators.notEqualTo = "!="
            linguagem.operators.greaterThan = ">"
            linguagem.operators.lessThan = "<"
            linguagem.operators.greaterThanOrEqualTo = ">="
            linguagem.operators.lessThanOrEqualTo = "<="
            linguagem.operators.logicalAnd = "&&"
            linguagem.operators.logicalOr = "||"
            linguagem.operators.logicalNot = "!"
            linguagem.operators.name = ""
            linguagem.operators.save(flush: true, failOnError: true)

            // Controles de fluxo da linguagem
            linguagem.controlFlow = new ControleDeFluxo()
            linguagem.controlFlow.languageName = "NXC"
            linguagem.controlFlow.breakCode = "break;"
            linguagem.controlFlow.doCode = "do {comandos} while (condicao);"
            linguagem.controlFlow.forCode = "for (int variavel = valor1; variavel < valor2; variavel+=passo) {comandos}"
            linguagem.controlFlow.ifCode = "if(condicao){comandos1}else{comandos2}"
            linguagem.controlFlow.repeatCode = "repeat(var){comandos}"
            linguagem.controlFlow.whileCode = "while(condicao){comandos}"
            linguagem.controlFlow.switchCode = "switch (variavel) {\n" +
                                               "//teste1\n" +
                                               "case (valor1): comandos1\n" +
                                               "break;\n" +
                                               "//teste2\n" +
                                               "default: comandos2\n" +
                                               "break;\n" +
                                               "//fim\n" +
                                               "}"
            linguagem.controlFlow.save(flush: true, failOnError: true)

            // Salvando a linguagem
            linguagem.save(flush: true, failOnError: true)
            
            // Funções
            println "Iniciando cadastro das funcoes da linguagem " + linguagem.name
            
            def defines = new Definicao()
            defines.name = "a"
            defines.alreadyExists = true
            defines.text="OUT_A"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "b"
            defines.alreadyExists = true
            defines.text="OUT_B"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "c"
            defines.alreadyExists = true
            defines.text="OUT_C"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "ab"
            defines.alreadyExists = true
            defines.text="OUT_AB"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "ac"
            defines.alreadyExists = true
            defines.text="OUT_AC"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "bc"
            defines.alreadyExists = true
            defines.text="OUT_BC"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "abc"
            defines.alreadyExists = true
            defines.text="OUT_ABC"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "meio"
            defines.alreadyExists = true
            defines.text="BTNCENTER"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "baixo"
            defines.alreadyExists = true
            defines.text="BTNEXIT"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "setadireita"
            defines.alreadyExists = true
            defines.text="BTNRIGHT"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "setaesquerda"
            defines.alreadyExists = true
            defines.text="BTNLEFT"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "preto"
            defines.alreadyExists = false
            defines.text="1"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "azul"
            defines.alreadyExists = false
            defines.text="2"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "verde"
            defines.alreadyExists = false
            defines.text="3"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "amarelo"
            defines.alreadyExists = false
            defines.text="4"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "vermelho"
            defines.alreadyExists = false
            defines.text="5"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "branco"
            defines.alreadyExists = false
            defines.text="6"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            def funcao = new Funcao()
            funcao.name = "definirtoque"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorTouch(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definircor"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorFull(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirluz"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorRed(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirultra"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorLowspeed(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "ultra"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "1"
            funcao.code = "SensorUS(IN_var1(int))"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "cor"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "1"
            funcao.code = "Sensor(IN_var1(int))"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "toque"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "1"
            funcao.code = "SENSOR_var1(int)"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "espere"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "Wait(var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frente"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "OnFwd(var1(int), var2(int));\n" +
                             "Wait(var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tras"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "OnRev(var1(int), var2(int));\n" +
                             "Wait(var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "girar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "5"
            funcao.code = "OnFwd(var1(int), var2(int));\n" +
                             "OnRev(var3(int), var4(int));\n" +
                             "Wait(var5(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "parar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "Off(var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tocar"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "PlayTone(var1(int), var2(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frenterotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(int), var2(int), var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "trasrotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(int), var2(int), -var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrever"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "TextOut(var1(int), LCD_LINEvar2(int), var3(String));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrevernumero"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "NumOut(var1(int), LCD_LINEvar2(int), var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "botao"
            funcao.type = "Leitura"
            funcao.returnType = "int"
            funcao.qntParameters = "1"
            funcao.code = "ButtonPressed(var1(int), false)"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            println "Cadastro da linguagem " + linguagem.name + " concluido"
            
        } else {
            println "Linguagem NXC ja existe no banco de dados"
        }
        
        // Fim do cadastro de linguagem

        // Cadastro de linguagem - CV3
        
        if(!Linguagem.findWhere(name: "CV3")) {
            
            println "Linguagem CV3 nao encontrada no banco de dados"
            println "Iniciando cadastro da linguagem CV3"
            
            // Linguagem propriamente dita
            def linguagem = new Linguagem()
            linguagem.name = "CV3"
            linguagem.description = "Linguagem baseada em C utilizada para programação de robôs Lego Mindstorms EV3."
            linguagem.robot = "Lego Mindstorms EV3"
            linguagem.extension = "c"
            linguagem.compileCode = "make -C diretorio"
            linguagem.compilerFile = ""
            linguagem.sendCode = "dll -r ProgramaCV3.rbf & dll -d cv3"
            linguagem.sentExtension = ""
            linguagem.header = "/*\n* W-Educ\n* www.natalnet.br/w-educ\n* (C) Copyright 2014\n* Programa 1 - Andar em sentido direto por 4 segundos\n*/\n#include \"CV3.h\"\n#define a OUT_A\n#define b OUT_B\n#define c OUT_C\n#define d OUT_D\n#define ab OUT_AB\n#define ac OUT_AC\n#define ad OUT_AD\n#define ba OUT_BA\n#define bc OUT_BC\n#define bd OUT_BD\n#define ca OUT_CA\n#define cb OUT_BC\n#define cd OUT_CD\n#define da OUT_DA\n#define db OUT_DB\n#define dc OUT_DC\n#define abcd OUT_ALL\n#define abc OUT_ABC\n"
            linguagem.footnote = ""
            linguagem.mainFunction = "int main(){\nOutputInit();\nButtonLedInit();\nLCD tela;\nLcdInit(tela.Lcd);\nLcdClear(tela.Lcd);\nSoundInit();\nSoundOpen();\ncomandos\nButtonLedExit();\nSoundClose();\nSoundExit();\nLcdExit();\nOutputClose();\nOutputExit();\n}"
            linguagem.otherFunctions = "void funcao(){comandos}"
            linguagem.callFunction = "funcao();"
            linguagem.autor = usuario

            // Tipos da linguagem
            linguagem.types = new Tipos()
            linguagem.types.name = ""
            linguagem.types.declareFalse = "false"
            linguagem.types.declareTrue = "true"
            linguagem.types.declareFloat = "float variavel = valor;"
            linguagem.types.declareString = "char variavel[] = \"valor\";"
            linguagem.types.declareBoolean = "bool variavel = valor;"
            linguagem.types.save(flush: true, failOnError: true)

            // Operadores da linguagem
            linguagem.operators = new Operadores()
            linguagem.operators.equalTo = "=="
            linguagem.operators.notEqualTo = "!="
            linguagem.operators.greaterThan = ">"
            linguagem.operators.lessThan = "<"
            linguagem.operators.greaterThanOrEqualTo = ">="
            linguagem.operators.lessThanOrEqualTo = "<="
            linguagem.operators.logicalAnd = "&&"
            linguagem.operators.logicalOr = "||"
            linguagem.operators.logicalNot = "!"
            linguagem.operators.name = ""
            linguagem.operators.save(flush: true, failOnError: true)

            // Controles de fluxo da linguagem
            linguagem.controlFlow = new ControleDeFluxo()
            linguagem.controlFlow.languageName = "NXC"
            linguagem.controlFlow.breakCode = "break;"
            linguagem.controlFlow.doCode = "do {comandos} while (condicao);"
            linguagem.controlFlow.forCode = "for (int variavel = valor1; variavel < valor2; variavel+=passo) {comandos}"
            linguagem.controlFlow.ifCode = "if(condicao){comandos1}else{comandos2}"
            linguagem.controlFlow.repeatCode = "repeat(var){comandos}"
            linguagem.controlFlow.whileCode = "while(condicao){comandos}"
            linguagem.controlFlow.switchCode = "switch (variavel) {\n" +
                                               "//teste1\n" +
                                               "case (valor1): comandos1\n" +
                                               "break;\n" +
                                               "//teste2\n" +
                                               "default: comandos2\n" +
                                               "break;\n" +
                                               "//fim\n" +
                                               "}"
            linguagem.controlFlow.save(flush: true, failOnError: true)

            // Salvando a linguagem
            linguagem.save(flush: true, failOnError: true)
            
            // Funções
            println "Iniciando cadastro das funcoes da linguagem " + linguagem.name
            
            def defines = new Definicao()
            defines.name = "a"
            defines.alreadyExists = true
            defines.text="OUT_A"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "b"
            defines.alreadyExists = true
            defines.text="OUT_B"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "c"
            defines.alreadyExists = true
            defines.text="OUT_C"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "ab"
            defines.alreadyExists = true
            defines.text="OUT_AB"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "ac"
            defines.alreadyExists = true
            defines.text="OUT_AC"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "bc"
            defines.alreadyExists = true
            defines.text="OUT_BC"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "abc"
            defines.alreadyExists = true
            defines.text="OUT_ABC"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "meio"
            defines.alreadyExists = true
            defines.text="BTNCENTER"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "baixo"
            defines.alreadyExists = true
            defines.text="BTNEXIT"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "setadireita"
            defines.alreadyExists = true
            defines.text="BTNRIGHT"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "setaesquerda"
            defines.alreadyExists = true
            defines.text="BTNLEFT"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "preto"
            defines.alreadyExists = false
            defines.text="1"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "azul"
            defines.alreadyExists = false
            defines.text="2"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "verde"
            defines.alreadyExists = false
            defines.text="3"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "amarelo"
            defines.alreadyExists = false
            defines.text="4"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "vermelho"
            defines.alreadyExists = false
            defines.text="5"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            defines = new Definicao()
            defines.name = "branco"
            defines.alreadyExists = false
            defines.text="6"
            defines.type="int"
            linguagem.addToDefines(defines).save(flush: true, failOnError: true)
            
            def funcao = new Funcao()
            funcao.name = "definirtoque"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorTouch(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminartoque"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseTouch();"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminarultra"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseUS();"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminarcor"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseColor();"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminarluz"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseColor();"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definircor"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorFull(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirluz"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorRed(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirultra"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorUS(IN_var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "ultra"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "1"
            funcao.code = "SensorUS(IN_var1(int))"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "cor"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "3"
            funcao.code = "SensorColor(IN_var1(int))"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "luz"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "5"
            funcao.code = "SensorColor(IN_var1(int))"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "toque"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "1"
            funcao.code = "SensorTouch(IN_var1(int))"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "espere"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "Wait(var1(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frente"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "OnFwd(var1(String), var2(int)); Wait(var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tras"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "OnRev(var1(String), var2(int)); Wait(var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "girar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "5"
            funcao.code = "OnFwd(var1(String), var2(int)); OnRev(var3(String), var4(int)); Wait(var5(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "giraresquerda"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "4"
            funcao.code = "RotateMotorEx(var1(string), var2(int), var3(int), var4(int), true, true);"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "girardireita"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "4"
            funcao.code = "RotateMotorEx(var1(string), var2(int), var3(int), -var4(int), true, true);"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tocar"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "PlayTone(var1(int), var2(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frenterotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(String), var2(int), var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "trasrotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(String), -var2(int), var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrever"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "LcdText(tela.Lcd, var1(int), var2(int), var3(String));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrevernumero"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "LcdNum(tela.Lcd, var1(int), var2(int), var3(int));"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "limpar"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "LcdClear(tela.Lcd);"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "alerta"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetLedWarning(true); Wait(var1(int)); SetLedWarning(false);"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            println "Cadastro da linguagem " + linguagem.name + " concluido"
            
        } else {
            println "Linguagem NXC ja existe no banco de dados"
        }
        
        // Fim do cadastro de linguagem
    }

    def destroy = {
    }
}
