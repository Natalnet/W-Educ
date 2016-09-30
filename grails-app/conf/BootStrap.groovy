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
    		usuario.email = "admin@natalnet.br"
    		usuario.save flush: true, failOnError: true

    		def usuarioPrivilegio = new UsuarioPrivilegio()
    		usuarioPrivilegio.usuario = usuario
    		usuarioPrivilegio.privilegio = privilegio
    		usuarioPrivilegio.save flush: true, failOnError: true
                
    	}
        
        
        //Cadastros dos tópicos do fórum
        def section = Section.findAll(); 
        def section1
        if (!section) {
            
            section = new Section()
            section.title = "Discussão Geral"
            section.save(flush: true, failOnError: true)
            
            section1 = new Section()
            section1.title = "Robótica"
            section1.save(flush: true, failOnError: true)
            
        }
        
        def topic = Topic.findAll()
        if (!topic ) {
       
            topic = new Topic()
            topic.section = section
            topic.title = "Conversa Fiada"
            topic.description = "Fale aqui sobre assuntos gerais relacionados ou não à robótica"
            topic.save(flush: true, failOnError: true)
        
            def artigos = new Topic()
            artigos.section = section
            artigos.title = "Artigos e Notícias"
            artigos.description = "Veja aqui artigos e notícias do mundo da robótica"
            artigos.save(flush: true, failOnError: true)
            
            def sugestoes
            sugestoes = new Topic()
            sugestoes.section = section
            sugestoes.title = "Sugestões e Reclamações"
            sugestoes.description = "Coloque aqui suas sugestões e/ou reclamações para/sobre o sistema"
            sugestoes.save(flush: true, failOnError: true)
            
            def duvidas
            duvidas = new Topic()
            duvidas.section = section
            duvidas.title = "Dúvidas diversas"
            duvidas.description = "Poste aqui suas dúvidas relacionadas ou não à robótica"
            duvidas.save(flush: true, failOnError: true)
            
            def apostilas
            apostilas = new Topic()
            apostilas.section = section
            apostilas.title = "E-books, Apostilas, Livros"
            apostilas.description = "Veja ou poste aqui livros, e-books e apostilas sobre robótica"
            apostilas.save(flush: true, failOnError: true)
            
            def competicoes
            competicoes = new Topic()
            competicoes.section = section1
            competicoes.title = "Competições de Robótica"
            competicoes.description ="Veja ou poste assuntos relacionados a competições de robótica"
            competicoes.save(flush: true, failOnError: true)
            
            def codigos
            codigos = new Topic()
            codigos.section = section1
            codigos.title = "Códigos de Robótica"
            codigos.description = "Poste aqui códigos que você deseja compartilhar"
            codigos.save(flush: true, failOnError: true)
            
            def video
            video = new Topic()
            video.section = section1
            video.title = "Vídeo Aulas"
            video.description = "Veja ou poste aqui vídeo aulas sobre robótica"
            video.save(flush: true, failOnError: true)
            
            def projetos
            projetos = new Topic()
            projetos.section = section1
            projetos.title = "Projetos e Ideias"
            projetos.description = "Poste aqui projetos que você deseja compartilhar com a comunidade"
            projetos.save(flush: true, failOnError: true)
            
            def programacao
            programacao = new Topic()
            programacao.section = section1
            programacao.title = "Programação de Robôs"
            programacao.description = "Tópico relacionado a programação de robôs em geral"
            programacao.save(flush: true, failOnError: true)
            
            def montagem
            montagem = new Topic()
            montagem.section = section1
            montagem.title = "Montagem de Robôs"
            montagem.description = "Tópico destinado a falar cobre montagem de robôs"
            montagem.save(flush: true, failOnError: true) 
        
            def thread1
            thread1 = new DiscussionThread()
            thread1.topic = artigos
            thread1.subject = "OBR 2016"
            thread1.opener = usuario
            thread1.save(flush: true, failOnError: true)
            
                
            
            def comment1
            comment1 = new Comment()
            comment1.thread = thread1 
            comment1.commentBy = usuario
            comment1.body = "A etapa nacional da OBR ocorrerá em outubro!"
            comment1.save(flush: true, failOnError: true)
        }
        // Cadastro de linguagem - NXC
        
        if(!Linguagem.findWhere(name: "NXC")) {
            
            println "Linguagem NXC nao encontrada no banco de dados."
            println "Iniciando cadastro da linguagem NXC"
            
            // Linguagem propriamente dita
            def linguagem = new Linguagem()
            linguagem.name = "NXC"
            linguagem.description = "Linguagem baseada em C utilizada para programação de robôs Lego Mindstorms NXT."
            linguagem.robot = "Lego Mindstorms NXT"
            linguagem.extension = "nxc"
            linguagem.compileCode = "localdocompilador/nbc diretorio/nomedoprograma.nxc -O=diretorio/nomedoprograma.rxe"
            linguagem.compilerFile = "nbc"
            linguagem.sendCode = "nbc.exe -d nomedoprograma.nxc"
            linguagem.sentExtension = "nomedoprograma.nxc"
            linguagem.header = "/*\n* W-Educ\n* www.natalnet.br/weduc\n* (C) Copyright 2014\n*/ \n"
            linguagem.footnote = ""
            linguagem.mainFunction = "task main(){\n comandos \n}\n"
            linguagem.otherFunctions = "task funcao(){\n comandos \n}\n"
            linguagem.callFunction = "funcao(); \n"
            linguagem.autor = usuario

            // Tipos da linguagem
            linguagem.types = new Tipos()
            linguagem.types.name = ""
            linguagem.types.declareFalse = "false"
            linguagem.types.declareTrue = "true"
            linguagem.types.declareFloat = "float variavel = valor; \n"
            linguagem.types.declareString = "string variavel = \"valor\"; \n"
            linguagem.types.declareBoolean = "bool variavel = valor; \n"
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
            linguagem.controlFlow.breakCode = "break;\n"
            linguagem.controlFlow.doCode = "do {\n comandos \n }\n while (condicao); \n"
            linguagem.controlFlow.forCode = "for (int variavel = valor1; variavel < valor2; variavel+=passo) {\n comandos \n} \n"
            linguagem.controlFlow.ifCode = "if(condicao){\n comandos1 \n}else{ \n comandos2 \n} \n"
            linguagem.controlFlow.repeatCode = "repeat(var){\n comandos \n}\n"
            linguagem.controlFlow.whileCode = "while(condicao){\n comandos \n}\n"
            linguagem.controlFlow.switchCode = "switch (variavel) {\n" +
                                               "//teste1\n" +
                                               "case (valor1): comandos1\n" +
                                               "break;\n" +
                                               "//teste2\n" +
                                               "default: comandos2\n" +
                                               "break;\n" +
                                               "//fim\n" +
                                               "}\n"
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
            funcao.code = "SetSensorTouch(IN_var1(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definircor"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorFull(IN_var1(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirluz"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorRed(IN_var1(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirultra"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorLowspeed(IN_var1(int));\n"
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
            funcao.code = "Wait(var1(int));\n"
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
                             "Wait(var3(int));\n"
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
                             "Wait(var3(int));\n"
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
                             "Wait(var5(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "parar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "Off(var1(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tocar"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "PlayTone(var1(int), var2(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frenterotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(int), var2(int), var3(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "trasrotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(int), var2(int), -var3(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrever"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "TextOut(var1(int), LCD_LINEvar2(int), var3(String));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrevernumero"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "NumOut(var1(int), LCD_LINEvar2(int), var3(int));\n"
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
            
            println "Cadastro da linguagem " + linguagem.name + " concluído."
            
        } else {
            println "Linguagem NXC jÁ existe no banco de dados."
        }
        
        // Fim do cadastro de linguagem

        // Cadastro de linguagem - CV3
        
        if(!Linguagem.findWhere(name: "CV3")) {
            
            println "Linguagem CV3 nao encontrada no banco de dados."
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
            linguagem.sentExtension = "cv3"
            linguagem.header = "/*\n* W-Educ\n* www.natalnet.br/weduc\n* (C) Copyright 2014\n* */\n#include \"CV3.h\"\n#define a OUT_A\n#define b OUT_B\n#define c OUT_C\n#define d OUT_D\n#define ab OUT_AB\n#define ac OUT_AC\n#define ad OUT_AD\n#define ba OUT_BA\n#define bc OUT_BC\n#define bd OUT_BD\n#define ca OUT_CA\n#define cb OUT_BC\n#define cd OUT_CD\n#define da OUT_DA\n#define db OUT_DB\n#define dc OUT_DC\n#define abcd OUT_ALL\n#define abc OUT_ABC\n"
            linguagem.footnote = ""
            linguagem.mainFunction = "int main(){\nOutputInit();\nButtonLedInit();\nLCD tela;\nLcdInit(tela.Lcd);\nLcdClear(tela.Lcd);\nSoundInit();\nSoundOpen();\ncomandos\nButtonLedExit();\nSoundClose();\nSoundExit();\nLcdExit();\nOutputClose();\nOutputExit();\n}"
            linguagem.otherFunctions = "void funcao(){\n comandos \n} \n"
            linguagem.callFunction = "funcao(); \n"
            linguagem.autor = usuario

            // Tipos da linguagem
            linguagem.types = new Tipos()
            linguagem.types.name = ""
            linguagem.types.declareFalse = "false"
            linguagem.types.declareTrue = "true"
            linguagem.types.declareFloat = "float variavel = valor; \n"
            linguagem.types.declareString = "char variavel[] = \"valor\"; \n"
            linguagem.types.declareBoolean = "bool variavel = valor; \n"
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
            linguagem.controlFlow.languageName = "CV3"
            linguagem.controlFlow.breakCode = "break;\n"
            linguagem.controlFlow.doCode = "do {\n comandos \n} \n while (condicao); \n"
            linguagem.controlFlow.forCode = "for (int variavel = valor1; variavel < valor2; variavel+=passo) {\n comandos} \n"
            linguagem.controlFlow.ifCode = "if(condicao){ \n comandos1 \n }else{ \n comandos2 \n} \n"
            linguagem.controlFlow.repeatCode = "repeat(var){\n comandos \n}\n"
            linguagem.controlFlow.whileCode = "while(condicao){\n comandos \n} \n"
            linguagem.controlFlow.switchCode = "switch (variavel) {\n" +
                                               "//teste1\n" +
                                               "case (valor1): comandos1\n" +
                                               "break;\n" +
                                               "//teste2\n" +
                                               "default: comandos2\n" +
                                               "break;\n" +
                                               "//fim\n" +
                                               "} \n"
            linguagem.controlFlow.save(flush: true, failOnError: true)

            // Salvando a linguagem
            linguagem.save(flush: true, failOnError: true)
            
            // Funções
            println "Iniciando cadastro das funcoes da linguagem " + linguagem.name + "."
            
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
            funcao.code = "SetSensorTouch(IN_var1(int)); \n" 
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminartoque"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseTouch(); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminarultra"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseUS(); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminarcor"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseColor(); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "terminarluz"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "CloseColor(); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definircor"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorFull(IN_var1(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirluz"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorColorRed(IN_var1(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "definirultra"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetSensorUS(IN_var1(int)); \n"
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
            funcao.code = "Wait(var1(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frente"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "OnFwd(var1(int), var2(int)); \n Wait(var3(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tras"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "OnRev(var1(int), var2(int)); \n Wait(var3(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "girar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "5"
            funcao.code = "OnFwd(var1(int), var2(int)); OnRev(var3(int), var4(int)); Wait(var5(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "giraresquerda"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "4"
            funcao.code = "RotateMotorEx(var1(int), var2(int), var3(int), var4(int), true, true); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "girardireita"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "4"
            funcao.code = "RotateMotorEx(var1(int), var2(int), var3(int), -var4(int), true, true); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "tocar"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "PlayTone(var1(int), var2(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "frenterotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(int), var2(int), var3(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "trasrotacao"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "RotateMotor(var1(int), -var2(int), var3(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrever"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "LcdText(tela.Lcd, var1(int), var2(int), var3(String));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "escrevernumero"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "LcdNum(tela.Lcd, var1(int), var2(int), var3(int));\n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "limpar"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "LcdClear(tela.Lcd); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            funcao = new Funcao()
            funcao.name = "alerta"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "SetLedWarning(true); \n Wait(var1(int)); \n SetLedWarning(false); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            println "Cadastro da linguagem " + linguagem.name + " concluido"
            
        } else {
            println "Linguagem CV3 ja existe no banco de dados"
        }
        
        //Cadastro da Linguagem Ino - Arduino UNO
        
        if(!Linguagem.findWhere(name: "INO")) {
            
            println "Linguagem INO nao encontrada no banco de dados."
            println "Iniciando cadastro da linguagem INO"
            
            // Linguagem propriamente dita
            def linguagem = new Linguagem()
            linguagem.name = "INO"
            linguagem.description = "Linguagem baseada em C utilizada para programação de robôs com Arduino UNO."
            linguagem.robot = "Arduino Uno"
            linguagem.extension = "ino"
            linguagem.compileCode = "arduino --pref build.path=diretorio --verify diretorio/nomedoprograma.ino"
            linguagem.compilerFile = "ArduinoUploader.exe"
            linguagem.sendCode = "ArduinoUploader.exe nomedoprograma.cpp.hex 1 porta"
            linguagem.sentExtension = "nomedoprograma.cpp.hex"
            linguagem.header = "/*\n* W-Educ\n* www.natalnet.br/weduc\n* (C) Copyright 2014\n*" +
                               "void setup(){\n  }\n"     
            linguagem.footnote = ""
            linguagem.mainFunction = "void loop(){\n comandos} \n"
            linguagem.otherFunctions = "void funcao(){\n comandos \n } \n"
            linguagem.callFunction = "funcao(); \n"
            linguagem.autor = usuario

            // Tipos da linguagem
            linguagem.types = new Tipos()
            linguagem.types.name = ""
            linguagem.types.declareFalse = "false"
            linguagem.types.declareTrue = "true"
            linguagem.types.declareFloat = "float variavel = valor; \n"
            linguagem.types.declareString = "char variavel[] = \"valor\"; \n"
            linguagem.types.declareBoolean = "bool variavel = valor; \n"
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
            linguagem.controlFlow.languageName = "INO"
            linguagem.controlFlow.breakCode = "break;"
            linguagem.controlFlow.doCode = "do {\n comandos \n} \n while (condicao); \n"
            linguagem.controlFlow.forCode = "for (int variavel = valor1; variavel < valor2; variavel+=passo) {\n comandos \n} \n"
            linguagem.controlFlow.ifCode = "if(condicao){comandos1}else{comandos2}"
            linguagem.controlFlow.repeatCode = "repeat(var){\n comandos \n} \n"
            linguagem.controlFlow.whileCode = "while(condicao){\n comandos \n}\n"
            linguagem.controlFlow.switchCode = "switch ((int)variavel) {\n" +
                                               "//teste1\n" +
                                               "case (valor1): comandos1\n" +
                                               "break;\n" +
                                               "//teste2\n" +
                                               "default: comandos2\n" +
                                               "break;\n" +
                                               "//fim\n" +
                                               "} \n"
            linguagem.controlFlow.save(flush: true, failOnError: true)

            // Salvando a linguagem
            linguagem.save(flush: true, failOnError: true)
            
            // Funções
            println "Iniciando cadastro das funcoes da linguagem " + linguagem.name
            
//            def defines = new Definicao()
//            defines.name = "a"
//            defines.alreadyExists = true
//            defines.text="OUT_A"
//            defines.type="int"
//            linguagem.addToDefines(defines).save(flush: true, failOnError: true)

            def funcao = new Funcao()
            funcao.name = "comentario"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "/* var1(String) */"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            println "Cadastro da linguagem " + linguagem.name + " concluido"
            
        } else {
            println "Linguagem INO ja existe no banco de dados"
        }
        
        //Cadastro da Linguagem One - Bot'Roll One A
        
        if(!Linguagem.findWhere(name: "ONE")) {
            
            println "Linguagem ONE nao encontrada no banco de dados."
            println "Iniciando cadastro da linguagem ONE"
            
            // Linguagem propriamente dita
            def linguagem = new Linguagem()
            linguagem.name = "ONE"
            linguagem.description = "Linguagem baseada em C, semelhante a INO, com bibliotecas específicas para programação de robôs Bot'N Roll One A."
            linguagem.robot = "Bot'N Roll One A"
            linguagem.extension = "ino"
            linguagem.compileCode = "arduino --pref build.path=diretorio --verify diretorio/nomedoprograma.ino"
            linguagem.compilerFile = "ArduinoUploader.exe"
            linguagem.sendCode = "ArduinoUploader.exe nomedoprograma.cpp.hex 1 porta"
            linguagem.sentExtension = "nomedoprograma.cpp.hex"
            linguagem.header = "/*\n* W-Educ\n* www.natalnet.br/weduc\n* (C) Copyright 2014\n*/ \n" +
                               "#include <BnrOneA.h> \n #include <SPI.h> \n" +
                               "BnrOneA one; \n" +          
                               "#define SSPIN 2 \n"  +
                               "void setup(){\n one.spiConnect(SSPIN); \n" +
                                "one.minBat(8.5); \n delay(5); \n" +
                                "one.obstacleEmitters(ON); \n}\n "
            linguagem.footnote = ""
            linguagem.mainFunction = "void loop(){\n comandos} \n"
            linguagem.otherFunctions = "void funcao(){\n comandos \n } \n"
            linguagem.callFunction = "funcao(); \n"
            linguagem.autor = usuario

            // Tipos da linguagem
            linguagem.types = new Tipos()
            linguagem.types.name = ""
            linguagem.types.declareFalse = "false"
            linguagem.types.declareTrue = "true"
            linguagem.types.declareFloat = "float variavel = valor; \n"
            linguagem.types.declareString = "char variavel[] = \"valor\"; \n"
            linguagem.types.declareBoolean = "bool variavel = valor; \n"
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
            linguagem.controlFlow.languageName = "ONE"
            linguagem.controlFlow.breakCode = "break;\n"
            linguagem.controlFlow.doCode = "do {\n comandos \n} \n while (condicao); \n"
            linguagem.controlFlow.forCode = "for (int variavel = valor1; variavel < valor2; variavel+=passo){ \n comandos \n} \n"
            linguagem.controlFlow.ifCode = "if(condicao){\n comandos1 \n} \n else{ \n comandos2 \n} \n"
            linguagem.controlFlow.repeatCode = "repeat(var){\n comandos}\n"
            linguagem.controlFlow.whileCode = "while(condicao){\ncomandos}\n"
            linguagem.controlFlow.switchCode = "switch ((int)variavel) {\n" +
                                               "//teste1\n" +
                                               "case (valor1): comandos1\n" +
                                               "break;\n" +
                                               "//teste2\n" +
                                               "default: comandos2\n" +
                                               "break;\n" +
                                               "//fim\n" +
                                               "}\n"
            linguagem.controlFlow.save(flush: true, failOnError: true)

            // Salvando a linguagem
            linguagem.save(flush: true, failOnError: true)
            
            // Funções
            println "Iniciando cadastro das funções da linguagem " + linguagem.name + "."   
            
            def funcao = new Funcao()
            funcao.name = "obstaculoinfra"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "0"
            funcao.code = "one.obstacleSensors()"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "botao"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "0"
            funcao.code = "one.readButton()"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "bateria"
            funcao.type = "Leitura"
            funcao.returnType = "float"
            funcao.qntParameters = "0"
            funcao.code = "one.readBattery()"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "posicaomotor1"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "one.servo1(var1(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "posicaomotor2"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "one.servo2(var1(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "acenderled"
            funcao.type = "Escrita"
            funcao.returnType = "float"
            funcao.qntParameters = "0"
            funcao.code = "one.led(HIGH); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "apagarled"
            funcao.type = "Escrita"
            funcao.returnType = "float"
            funcao.qntParameters = "0"
            funcao.code = "one.led(LOW); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "frente"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "one.move(var1(int), var2(int)); delay(var3(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "tras"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "one.move(-var1(int), -var2(int)); delay(var3(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "direita"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "one.move(var1(int), -var2(int)); delay(var3(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "esquerda"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "3"
            funcao.code = "one.move(-var1(int), var2(int)); delay(var3(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "parar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "0"
            funcao.code = "one.stop(); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "travar"
            funcao.type = "Movimentação"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "one.brake(var1(int),var2(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "escrever"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "one.lcdvar1(int)(var2(String)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "escrevernumero"
            funcao.type = "Escrita"
            funcao.returnType = "Void"
            funcao.qntParameters = "2"
            funcao.code = "one.lcdvar1(int)(var2(int)); \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "seguidorinfra"
            funcao.type = "Leitura"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "one.readAdc(var1(int)); \n"
            funcao.description = "Sensor varia de 0 a 7."
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)
            
            funcao = new Funcao()
            funcao.name = "comentario"
            funcao.type = "Outros"
            funcao.returnType = "Void"
            funcao.qntParameters = "1"
            funcao.code = "/* var1(String) */ \n"
            funcao.description = ""
            funcao.typeAliases = ""
            funcao.imageURL = ""
            linguagem.addToFunctions(funcao).save(flush: true, failOnError: true)

            println "Cadastro da linguagem " + linguagem.name + " concluído"
            
        } else {
            println "Linguagem ONE já existe no banco de dados."
        }
        
        // Fim do cadastro de linguagem
        
        
    }

    def destroy = {
    }
}

