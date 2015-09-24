package org.natalnet.weduc

import com.roboeduc.compiladorreduc.*
import java.io.File
import groovy.io.FileType

import grails.plugin.springsecurity.annotation.Secured

class AmbienteController {

	def springSecurityService

	def index() {}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def programar() {
		[linguagem: Linguagem.get(params.id)]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def salvarPrograma() {   

		// Define usuário atual
		def usuario = springSecurityService.getCurrentUser()

		// Define a linguagem
		def linguagem = Linguagem.get(params.linguagem)

		// Define se é R-Educ ou não
		def reduc = params.reduc == "1" ? true : false

		// Procura um programa de mesmo nome;
		// caso não exista, o cria
		def programa = Programa.findOrCreateWhere(
			usuario: usuario, 
			linguagem: linguagem,
			reduc: reduc,
			nome: params.nome
		)	

		// Registra data de criação, 
		// caso programa seja novo
		programa.criadoEm = programa.criadoEm ? programa.criadoEm : (new Date())

		// Registra data de modificação
		programa.modificadoEm = new Date()

		// Inicialização das estatísticas
		programa.compilacoes = programa.compilacoes ? programa.compilacoes : 0
		programa.compilacoesBemSucedidas = programa.compilacoesBemSucedidas ? programa.compilacoesBemSucedidas : 0
		programa.compilacoesMalSucedidas = programa.compilacoesMalSucedidas ? programa.compilacoesMalSucedidas : 0

		// Define se é R-Educ 
		programa.reduc = reduc

		// Define extensão
		programa.extensao = reduc ? ".rob" : linguagem.extension

		// Define o nome
		programa.nome = programa.nome ? programa.nome : params.nome

		// Define o código
		programa.codigo = params.codigo 

		// Salva o programa
		programa.save(flush: true)

		render "OK"

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def compilarPrograma() {

		// Define usuário atual
		def usuario = springSecurityService.getCurrentUser()

		// Define a linguagem
		def linguagem = Linguagem.get(params.linguagem)

		// Define se é R-Educ ou não
		def reduc = params.reduc == "1" ? true : false

		// Procura um programa de mesmo nome;
		// caso não exista, o cria
		def programa = Programa.findWhere(
			usuario: usuario, 
			linguagem: linguagem,
			reduc: reduc,
			nome: params.nome
		)
	
	//Apaga todos os arquivos da pasta do usuário
	File fDelete = new File("/tmp/weduc/compilador/" + usuario?.username)
	fDelete.deleteDir()
      	    

		// Salva programa em arquivo temporário
        def t = System.currentTimeMillis().toString();
        String fName = programa.nome + t;
   
	File c = new File("/tmp/weduc/compilador/" + usuario?.username)
	if (!c.exists()) {
            c.mkdirs()
            }  

        File f = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + "" + programa.extensao)
        f << programa.codigo //.replaceAll("\n", "\r\n");


	if(reduc) { 	// Verifica se é R-Educ

	// Compila o arquivo utilizando o compilador REduc
            
            // Define o local do programa, para utilização futura
            programa.local = fName + programa.extensao + "." + linguagem.extension

            def language;
            language = new Language();
            language.setId(linguagem.id);
            language.setName(linguagem.name);
            language.setDescription(linguagem.description);
            language.setRobot(linguagem.robot);
            language.setExtension(linguagem.extension);

            language.setCompileCode(linguagem.compileCode);
            language.setCompilerFile(linguagem.compilerFile);
            language.setSendCode(linguagem.sendCode);
            language.setSentExtension(linguagem.sentExtension);

            language.setHeader(linguagem.header);
            language.setFootnote(linguagem.footnote);

            language.setMainFunction(linguagem.mainFunction);
            language.setOtherFunctions(linguagem.otherFunctions);
            language.setCallFunction(linguagem.callFunction);

            // Tipos
            def tipos = Tipos.findWhere(id: linguagem.types.id)
            def types;
            types = new Types();
            types.setId(tipos.id);
            types.setName(tipos.name);
            types.setDeclareFalse(tipos.declareFalse);
            types.setDeclareTrue(tipos.declareTrue);
            types.setDeclareFloat(tipos.declareFloat);
            types.setDeclareString(tipos.declareString);
            types.setDeclareBoolean(tipos.declareBoolean);
            language.setTypes(types);

            // Funções
            def funcoes = Funcao.findAllWhere(linguagem: linguagem)

            def functions = new ArrayList();

            funcoes.each() {
                obj ->

                def function = new LFunction();

                function.setId(obj.id);
                function.setName(obj.name);
                function.setType(obj.type);
                function.setReturnType(obj.returnType);
                function.setQntParameters(obj.qntParameters);
                function.setCode(obj.code);
                function.setDescription(obj.description);
                function.setTypeAliases(obj.typeAliases);
                function.setImageURL(obj.imageURL);
                functions.add(function);
            }

            // Defines
            def defines = Definicao.findAllWhere(linguagem: linguagem)

            def defines2 = new ArrayList();

            defines.each() {
                obj ->

                def defines3 = new Defines();
                
                defines3.setId(obj.id);
                defines3.setName(obj.name);
                defines3.setType(obj.type);
                defines3.setText(obj.text);
                defines3.setAlreadyExists(obj.alreadyExists);
                defines2.add(defines3);
            }

            // Operadores
            def operadores = Operadores.findWhere(id: linguagem.operators.id)
            def operators;
            operators = new Operators();
            operators.setId(operadores.id);
            operators.setEqualTo(operadores.equalTo);
            operators.setNotEqualTo(operadores.notEqualTo);
            operators.setGreaterThan(operadores.greaterThan);
            operators.setLessThan(operadores.lessThan);
            operators.setLessThanOrEqualTo(operadores.lessThanOrEqualTo);
            operators.setGreaterThanOrEqualTo(operadores.greaterThanOrEqualTo);
            operators.setLogicalAnd(operadores.logicalAnd);
            operators.setLogicalOr(operadores.logicalOr);
            operators.setLogicalNot(operadores.logicalNot);
            operators.setName(operadores.name);

            // Controle de fluxo
            def controleDeFluxo = ControleDeFluxo.findWhere(id: linguagem.controlFlow.id)
            ControlFlow controlFlow;
            controlFlow = new ControlFlow();
            controlFlow.setId(controleDeFluxo.id);
            controlFlow.setLanguageName(controleDeFluxo.languageName);
            controlFlow.setBreakCode(controleDeFluxo.breakCode);
            controlFlow.setDoCode(controleDeFluxo.doCode);
            controlFlow.setForCode(controleDeFluxo.forCode);
            controlFlow.setIfCode(controleDeFluxo.ifCode);
            controlFlow.setRepeatCode(controleDeFluxo.repeatCode);
            controlFlow.setSwitchCode(controleDeFluxo.switchCode);
            controlFlow.setWhileCode(controleDeFluxo.whileCode);

            def lexico = new analisadorLexico();
            lexico.readFile(f.path);
            def sintatico = new analisadorSintatico(lexico, "/tmp/weduc/compilador/" + usuario?.username + "/" + fName, programa.extensao, linguagem.name, language.extension);
            sintatico.getMapeamento().defineValues(language, types, functions, operators, controlFlow, defines2);
            lexico.defineUsedStructures(sintatico)
            sintatico.startCompile();
            sintatico.closeFile();
            // println(sintatico.isError());
            // render sintatico.isError();



            //Apaga todos os arquivos da pasta da linguagem para realizar nova compilação
	    File fDell = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + linguagem.id)
	    fDell.deleteDir()
      	    

	   //Copia os arquivos de include e extrai na pasta
	   try {		 
		def origem = "/weduc/arquivos-de-include/" + linguagem.id +  "/arquivo"	
		def destino = "/tmp/weduc/compilador/" + usuario?.username + "/" + linguagem.id        
                CommandShellToString.execute("unzip "+origem+" -d "+destino);              
	   }
	   catch (Exception e) {
            	println e
            }

            // Deixa o arquivo com a extensão e identificação desejadas
            File fSource = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + programa.extensao + "." + linguagem.extension)
            File fTarget = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + linguagem.id + "/" + fName + "." + linguagem.extension)
            org.apache.commons.io.FileUtils.copyFile(fSource, fTarget);

            // Define o arquivo onde ficarão os comandos do Make
            File fShell = new File("/tmp/weduc/compilador/" + usuario?.username +"/"+ linguagem.id +"/weduc.sh")
	    def comando = "" + linguagem.compileCode
	    println comando;
            comando = comando.replace("diretorio", "/tmp/weduc/compilador/" + usuario?.username +"/"+ linguagem.id)
            comando = comando.replace("localdocompilador", "/weduc/arquivos-de-compilacao/" + linguagem.id )
            comando = comando.replace("nomedoprograma",  fName )
            org.apache.commons.io.FileUtils.writeStringToFile(fShell, comando, null)
               
            println comando;  
            // Prepara o comando Make
            comando = "/bin/bash /tmp/weduc/compilador/" 
            comando += usuario?.username + "/" + linguagem.id + "/weduc.sh"


            println "------>"
            println comando;
            println "------>"
 

            try {

		System.out.println("Iniciei a compilacao na linguagem " + linguagem.id);
                System.out.println(CommandShellToString.execute(comando));
                
		 System.out.println("Finalizei a compilacao na linguagem " + linguagem.id);

            }
            catch (IOException ex) {
                System.out.println("Erro ao compilar o programa.");
            }
            catch (Exception e) {
            	println e
            }

            
            if(!sintatico.isError()) {
                programa.compilacoesBemSucedidas = programa.compilacoesBemSucedidas + 1;
                programa.save(flush: true)
                render "Compilação bem sucedida!"
            } else {
                //Adicionar aqui a equação das categorias.
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "1", "variavel");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "2", "funcao");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "3", "tarefa");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "4", "estrutura");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "5", "condicao");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "6", "repeticao");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "7", "nome");
                categorizarErros(sintatico.getErrorType(), lexico.getUsedStructures(), "8", "sintaxe");
                programa.compilacoesMalSucedidas = programa.compilacoesMalSucedidas + 1;
                programa.save(flush: true)
                render "Linha: " + sintatico.getErrorInt() + " -> Erro " + sintatico.getErrorStr();
            }
	} //Termina Compilação em R-Educ

	// Verifica se é a linguagem alvo
	else {
		render "Não suportado ainda."
	}
	
	}//Termina compilar programa!

    def categorizarErros(String errorType, String usedStructures, String tipo, String descricao) {
        def usuario = springSecurityService.getCurrentUser()
        if (errorType.contains(descricao)) {
            def error = new Erro()
            error.usuario = usuario
            error.data = new Date()
            error.tipo = tipo
            error.quant = 1
            error.save(flush: true)
        }
        else if (usedStructures.contains(tipo)) {
            def error = new Erro()
            error.usuario = usuario
            error.data = new Date()
            error.tipo = tipo
            error.quant = 0
            error.save(flush: true)
        }
    }

	
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def enviarPrograma() {
	// Define usuário atual
        def usuario = springSecurityService.getCurrentUser()

        // Define a linguagem
        def linguagem = Linguagem.get(params.linguagem)

        // Define se é R-Educ ou não
        def reduc = params.reduc == "1" ? true : false

        // Procura um programa de mesmo nome;
        // caso não exista, o cria
        def programa = Programa.findWhere(
            usuario: usuario, 
            linguagem: linguagem,
            reduc: reduc,
            nome: params.nome
        )

    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def baixarPrograma() {
        // Define usuário atual
        def usuario = springSecurityService.getCurrentUser()

        // Define a linguagem
        def linguagem = Linguagem.get(params.linguagem)

        // Define se é R-Educ ou não
        def reduc = params.reduc == "1" ? true : false

        // Procura um programa de mesmo nome;
        // caso não exista, o cria
        def programa = Programa.findWhere(
            usuario: usuario, 
            linguagem: linguagem,
            reduc: reduc,
            nome: params.nome
        )

	def extension
	if (!reduc){
		extension = linguagem.extension
	}
	else{
		extension = "rob"
	} 

	//Procura se já existe o arquivo, delete e gera novamente para fazer download
	File fDelete = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + programa.nome + "." + extension)
	fDelete.delete()
	
	File f = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + programa.nome + "." + extension)
        f << programa.codigo //.replaceAll("\n", "\r\n");

        FileInputStream fPrograma = new FileInputStream(f)
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename=" + params.nome + "."+ extension)
        response.outputStream << fPrograma
        return
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def listarProgramas() {

        // Define usuário atual
        def usuario = springSecurityService.getCurrentUser()

        // Define a linguagem
        def linguagem = Linguagem.get(params.linguagem)

        // Define se é R-Educ ou não
        def reduc = params.reduc == "1" ? true : false

        // Procura um programa de mesmo nome;
        // caso não exista, o cria
        def programas = Programa.findAllWhere(
            usuario: usuario, 
            linguagem: linguagem,
            reduc: reduc
        )

        [programas: programas]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def abrirPrograma() {

        // Procura pelo programa de id específico
        def programa = Programa.get(params.id)

        render programa.codigo
    }

    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def exportarPrograma() {

        // Define usuário atual
        def usuario = springSecurityService.getCurrentUser()

        // Define a linguagem
        def linguagem = Linguagem.get(params.linguagem)

        // Define se é R-Educ ou não
        def reduc = params.reduc == "1" ? true : false

        // Procura um programa de mesmo nome;
        // caso não exista, o cria
        def programa = Programa.findWhere(
            usuario: usuario, 
            linguagem: linguagem,
            reduc: reduc,
            nome: params.nome
        )

        // Salva programa em arquivo temporário
        def t = System.currentTimeMillis().toString();
        String fName = programa.nome + t;
        File d = new File("/tmp/weduc/compilador/" + usuario?.username)
        d.mkdir()
        File f = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + "" + programa.extensao)
        f << programa.codigo //.replaceAll("\n", "\r\n");

        // Compila o arquivo utilizando o compilador REduc
        
        // Define o local do programa, para utilização futura
        programa.local = fName + programa.extensao + "." + linguagem.extension

        def language;
        language = new Language();
        language.setId(linguagem.id);
        language.setName(linguagem.name);
        language.setDescription(linguagem.description);
        language.setRobot(linguagem.robot);
        language.setExtension(linguagem.extension);

        language.setCompileCode(linguagem.compileCode);
        language.setCompilerFile(linguagem.compilerFile);
        language.setSendCode(linguagem.sendCode);
        language.setSentExtension(linguagem.sentExtension);

        language.setHeader(linguagem.header);
        language.setFootnote(linguagem.footnote);

        language.setMainFunction(linguagem.mainFunction);
        language.setOtherFunctions(linguagem.otherFunctions);
        language.setCallFunction(linguagem.callFunction);

        // Tipos
        def tipos = Tipos.findWhere(id: linguagem.types.id)
        def types;
        types = new Types();
        types.setId(tipos.id);
        types.setName(tipos.name);
        types.setDeclareFalse(tipos.declareFalse);
        types.setDeclareTrue(tipos.declareTrue);
        types.setDeclareFloat(tipos.declareFloat);
        types.setDeclareString(tipos.declareString);
        types.setDeclareBoolean(tipos.declareBoolean);
        language.setTypes(types);

        // Funções
        def funcoes = Funcao.findAllWhere(linguagem: linguagem)

        def functions = new ArrayList();

        funcoes.each() {
            obj ->

            def function = new LFunction();

            function.setId(obj.id);
            function.setName(obj.name);
            function.setType(obj.type);
            function.setReturnType(obj.returnType);
            function.setQntParameters(obj.qntParameters);
            function.setCode(obj.code);
            function.setDescription(obj.description);
            function.setTypeAliases(obj.typeAliases);
            function.setImageURL(obj.imageURL);
            functions.add(function);
        }

        // Defines
        def defines = Definicao.findAllWhere(linguagem: linguagem)

        def defines2 = new ArrayList();

        defines.each() {
            obj ->

            def defines3 = new Defines();
            
            defines3.setId(obj.id);
            defines3.setName(obj.name);
            defines3.setType(obj.type);
            defines3.setText(obj.text);
            defines3.setAlreadyExists(obj.alreadyExists);
            defines2.add(defines3);
        }

        // Operadores
        def operadores = Operadores.findWhere(id: linguagem.operators.id)
        def operators;
        operators = new Operators();
        operators.setId(operadores.id);
        operators.setEqualTo(operadores.equalTo);
        operators.setNotEqualTo(operadores.notEqualTo);
        operators.setGreaterThan(operadores.greaterThan);
        operators.setLessThan(operadores.lessThan);
        operators.setLessThanOrEqualTo(operadores.lessThanOrEqualTo);
        operators.setGreaterThanOrEqualTo(operadores.greaterThanOrEqualTo);
        operators.setLogicalAnd(operadores.logicalAnd);
        operators.setLogicalOr(operadores.logicalOr);
        operators.setLogicalNot(operadores.logicalNot);
        operators.setName(operadores.name);

        // Controle de fluxo
        def controleDeFluxo = ControleDeFluxo.findWhere(id: linguagem.controlFlow.id)
        ControlFlow controlFlow;
        controlFlow = new ControlFlow();
        controlFlow.setId(controleDeFluxo.id);
        controlFlow.setLanguageName(controleDeFluxo.languageName);
        controlFlow.setBreakCode(controleDeFluxo.breakCode);
        controlFlow.setDoCode(controleDeFluxo.doCode);
        controlFlow.setForCode(controleDeFluxo.forCode);
        controlFlow.setIfCode(controleDeFluxo.ifCode);
        controlFlow.setRepeatCode(controleDeFluxo.repeatCode);
        controlFlow.setSwitchCode(controleDeFluxo.switchCode);
        controlFlow.setWhileCode(controleDeFluxo.whileCode);

        def lexico = new analisadorLexico();
        lexico.readFile(f.path);
        def sintatico = new analisadorSintatico(lexico, "/tmp/weduc/compilador/" + usuario?.username + "/" + fName, programa.extensao, linguagem.name, language.extension);
        sintatico.getMapeamento().defineValues(language, types, functions, operators, controlFlow, defines2);
        sintatico.startCompile();
        sintatico.closeFile();
        // println(sintatico.isError());
        // render sintatico.isError();
            
        if(!sintatico.isError()) {
            programa.compilacoesBemSucedidas = programa.compilacoesBemSucedidas + 1;
            programa.save(flush: true)
            
            // Abre o arquivo compilado
            FileInputStream fPrograma = new FileInputStream("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + programa.extensao + "." + linguagem.extension)

            response.setContentType("application/octet-stream")
            // response.setHeader("Content-disposition", "filename=cv3")
            response.outputStream << fPrograma
            return
        } else {
            programa.compilacoesMalSucedidas = programa.compilacoesMalSucedidas + 1;
            programa.save(flush: true)
            render "Linha: " + sintatico.getErrorInt() + " Erro: " + sintatico.getErrorStr();
        }

    }

}
