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

		// Salva programa em arquivo temporário
        def t = System.currentTimeMillis().toString();
        String fName = programa.nome + t;
        File d = new File("/tmp/weduc/compilador/" + usuario?.username)
        d.mkdir()
        File f = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + "" + programa.extensao)
        f << programa.codigo //.replaceAll("\n", "\r\n");

		// Verifica se é R-Educ
		if(reduc) {

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

            // Copia os arquivos para a pasta temporária
            File fSource = new File("/tmp/weduc/compilador/CV3")
            File fTarget = new File("/tmp/weduc/compilador/" + usuario?.username + "/CV3")
            org.apache.commons.io.FileUtils.copyDirectory(fSource, fTarget);

            // Deixa o arquivo com a extensão e identificação desejadas
            fSource = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + programa.extensao + "." + linguagem.extension)
            fTarget = new File("/tmp/weduc/compilador/" + usuario?.username + "/CV3/cv3.c")
            org.apache.commons.io.FileUtils.copyFile(fSource, fTarget);

            // Prepara o comando Make
            def comando = "/bin/sh -c \"/usr/bin/make -C /tmp/weduc/compilador/" 
            	comando += usuario?.username + "/CV3\""

            println "------>"
            println comando
            println "------>"

            //comando = "/bin/sh -c \"/bin/free -m\""

            // Executa o comando Make
            Process proc;
            String saida = "";
            String s;

            try {

                 System.out.println("entrei na compilacao");
                 proc = Runtime.getRuntime().exec(comando);
                 System.out.println("sai na compilacao");

                 BufferedReader stdInput = new BufferedReader(new
                      InputStreamReader(proc.getInputStream()));

                 BufferedReader stdError = new BufferedReader(new
                      InputStreamReader(proc.getErrorStream()));

                 while ((s = stdInput.readLine()) != null) {
                     saida += s + "\n";
                 }
                 
                 //System.out.println("proc: " + proc);
                 //System.out.println("saida: " + saida);

            }
            catch (IOException ex) {
                System.out.println("Erro ao compilar o programa com o NBC");
            }
            catch (Exception e) {
            	println e
            }

            println saida
            
            if(!sintatico.isError()) {
                programa.compilacoesBemSucedidas = programa.compilacoesBemSucedidas + 1;
                programa.save(flush: true)
                render "Compilação bem sucedida!"
            } else {
                programa.compilacoesMalSucedidas = programa.compilacoesMalSucedidas + 1;
                programa.save(flush: true)
                render "Linha: " + sintatico.getErrorInt() + " Erro: " + sintatico.getErrorStr();
            }
		}
		// Verifica se é a linguagem alvo
		else {
			render "Não suportado ainda."
		}
	}

}