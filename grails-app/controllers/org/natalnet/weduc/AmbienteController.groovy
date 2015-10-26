package org.natalnet.weduc

import com.roboeduc.compiladorreduc.*
import java.io.File
import groovy.io.FileType

import grails.plugin.springsecurity.annotation.Secured

class AmbienteController {

	def springSecurityService
        
        def saveCompile = true;
      
    
	def index() {}
        
        //programar
	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def programar() {
                
                // Funções
            def funcoes = Funcao.findAllWhere(linguagem: Linguagem.get(params.id))
            
            [linguagem: Linguagem.get(params.id), funcoes: funcoes]    
        }

        //salvarPrograma
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

                if (saveCompile)
                    render "OK"
	}

        //compilarPrograma
	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def compilarPrograma() {
                
                //salva o programa antes de compilar
                saveCompile = false;
                salvarPrograma();
                saveCompile = true;
                
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
        String fName = programa.nome;
   
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
            sintatico.startCompile();
            sintatico.closeFile();
            // println(sintatico.isError());
            // render sintatico.isError();



            //Apaga todos os arquivos da pasta da linguagem para realizar nova compilação
	    File fDell = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName)
	    fDell.deleteDir()
      	    

	   //Copia os arquivos de include e extrai na pasta
	   try {		 
		def origem = "/weduc/arquivos-de-include/" + linguagem.id +  "/arquivo"	
		def destino = "/tmp/weduc/compilador/" + usuario?.username + "/" + fName        
                CommandShellToString.execute("unzip "+origem+" -d "+ destino);              
	   }
	   catch (Exception e) {
            	println e
            }

            // Deixa o arquivo com a extensão e identificação desejadas
            File fSource = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + programa.extensao + "." + linguagem.extension)
            File fTarget = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + "/" + fName + "." + linguagem.extension)
            org.apache.commons.io.FileUtils.copyFile(fSource, fTarget);

            // Define o arquivo onde ficarão os comandos do Make
            File fShell = new File("/tmp/weduc/compilador/" + usuario?.username +"/"+ fName +"/weduc.sh")
	    def comando = "" + linguagem.compileCode
	    println comando;
            comando = comando.replace("diretorio", "/tmp/weduc/compilador/" + usuario?.username +"/"+ fName)
            comando = comando.replace("localdocompilador", "/weduc/arquivos-de-compilacao/" + linguagem.id )
            comando = comando.replace("nomedoprograma",  fName )
            org.apache.commons.io.FileUtils.writeStringToFile(fShell, comando, null)
               
            println comando;  
            // Prepara o comando Make
            comando = "/bin/bash /tmp/weduc/compilador/" 
            comando += usuario?.username + "/" + fName + "/weduc.sh"


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
                programa.compilacoesMalSucedidas = programa.compilacoesMalSucedidas + 1;
                programa.save(flush: true)
                render "Linha: " + sintatico.getErrorInt() + " Erro: " + sintatico.getErrorStr();
            }
	} //Termina Compilação em R-Educ

	// Se não for a linguagem alvo:
	else {
            
            //Apaga todos os arquivos da pasta da linguagem para realizar nova compilação
	    File fDell = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + linguagem.id)
	    fDell.deleteDir()
            
            //Copia os arquivos de include e extrai na pasta
            try {		 
                 def origem = "/weduc/arquivos-de-include/" + linguagem.id +  "/arquivo"	
                 def destino = "/tmp/weduc/compilador/" + usuario?.username + "/" + fName        
                 CommandShellToString.execute("unzip "+origem+" -d "+destino);              
            }
            catch (Exception e) {
                 println e
             }
             
            File fTarget = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + "/" + fName + "." + linguagem.extension);
            org.apache.commons.io.FileUtils.writeStringToFile(fTarget, programa.codigo, null)
            
            // Deixa o arquivo com a extensão e identificação desejadas
            //File fSource = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + programa.extensao + "." + linguagem.extension)
            //File fTarget = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + linguagem.id + "/" + fName + "." + linguagem.extension)
            //org.apache.commons.io.FileUtils.copyFile(fSource, fTarget);

            // Define o arquivo onde ficarão os comandos do Make
            File fShell = new File("/tmp/weduc/compilador/" + usuario?.username +"/"+ fName +"/weduc.sh")
	    def comando = "" + linguagem.compileCode
	    println comando;
            comando = comando.replace("diretorio", "/tmp/weduc/compilador/" + usuario?.username +"/"+ fName)
            comando = comando.replace("localdocompilador", "/weduc/arquivos-de-compilacao/" + linguagem.id )
            comando = comando.replace("nomedoprograma",  fName )
            org.apache.commons.io.FileUtils.writeStringToFile(fShell, comando, null)
               
            println comando;  
            // Prepara o comando Make
            comando = "/bin/bash /tmp/weduc/compilador/" 
            comando += usuario?.username + "/" + fName + "/weduc.sh"


            println "------>"
            println comando;
            println "------>"
            
            def retorno;

            try {

		System.out.println("Iniciei a compilacao na linguagem " + linguagem.id);
                retorno = CommandShellToString.execute(comando);
		 System.out.println(retorno);
                
		 System.out.println("Finalizei a compilacao na linguagem " + linguagem.id);

            }
            catch (IOException ex) {
                System.out.println("Erro ao compilar o programa.");
            }
            catch (Exception e) {
            	println e
            }

            
            if(!(retorno.find("erro")||retorno.find("Erro")|| retorno.find("Error: "))) {
                programa.compilacoesBemSucedidas = programa.compilacoesBemSucedidas + 1;
                programa.save(flush: true)
                render "Compilação bem sucedida!"
            } else {
                programa.compilacoesMalSucedidas = programa.compilacoesMalSucedidas + 1;
                programa.save(flush: true)
                render "Ocorreram erros durante a compilação: \n" + retorno; 
            }
            
	}
	
	}//Termina compilar programa!

    //enviarCliente
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def enviarCliente() {
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
        
        String fName = programa.nome;
        
        def programaCompilado = linguagem.sentExtension
        programaCompilado = programaCompilado.replace("nomedoprograma",  params.nome)
        
        File fDell = new File("/tmp/weduc/envio/" + usuario?.username + "/")
	fDell.deleteDir()
        
       
        File d = new File("/tmp/weduc/envio/" + usuario?.username)
        d.mkdirs()
        File weducClient = new File("/tmp/weduc/envio/"+ usuario?.username +"/WeducClient.java")
       
        def origem = "/home/sarah/W-Educ/grails-app/services/weducClient.zip"	
        def destino = "/tmp/weduc/envio/" + usuario?.username + "/"     
        CommandShellToString.execute("unzip " + origem + " -d " + destino); 

        def comando = linguagem.sendCode
        comando = comando.replace("nomedoprograma",  params.nome)
        
        String enviar = ""
        
        try {
            def copiarArquivoEnvio = "cp /weduc/arquivos-de-envio/" + linguagem.id + "/arquivo" 
            copiarArquivoEnvio += " /tmp/weduc/envio/"+ usuario?.username

            System.out.println(CommandShellToString.execute(copiarArquivoEnvio))

            origem = "/tmp/weduc/envio/" + usuario?.username + "/arquivo" 	
            destino = "/tmp/weduc/envio/" + usuario?.username + "/"     
            CommandShellToString.execute("unzip " + origem + " -d " + destino);  

            def zipper = new Zipper();
            def arquivos = zipper.listarEntradasZip(new File("/tmp/weduc/envio/"+ usuario?.username + "/arquivo"))
            System.out.println(arquivos)

            enviar = arquivos.toString()
            System.out.println(enviar)
            enviar = enviar.replace('[','')      
            enviar = enviar.replace(']','')
            enviar = enviar.replace(',','')
            System.out.println(enviar)
        }  
        catch (Exception e) {
                 println e
        }
         
        def copiarArquivoCompilado = "cp /tmp/weduc/compilador/" + usuario?.username + "/" + fName + "/" + programaCompilado 
        copiarArquivoCompilado += " /tmp/weduc/envio/"+ usuario?.username
        
        System.out.println(CommandShellToString.execute(copiarArquivoCompilado))
        
        
        def codigo = "import javax.swing.JOptionPane; \n import java.io.*;"
            codigo += "public class WeducClient {\n "
            codigo += "public static void main(String[] args) { \n"
            codigo += "try {Runtime.getRuntime().exec(\"jar xf W-Educ.jar " + programaCompilado +" " + enviar + " jssc.jar\");} catch(IOException e){ } \n"
            codigo += "String comando = \" " + comando + "\"; \n"
            codigo += "if (comando.contains(\"porta\")) { \n String portName = (String)JOptionPane.showInputDialog(null, \"Selecione a porta em que seu dispositivo está conectado:\", \"W-Educ - Seletor de Portas\","
            codigo += "JOptionPane.QUESTION_MESSAGE, null,SerialPortList.getPortNames(),null); \n \n"
            codigo +=  "if (portName != null){ \n comando = comando.replace(\"porta\",  portName); \n}\n} \n"
            codigo += "try {Runtime.getRuntime().exec(comando);} catch(IOException e){ } \n   \n"  
            codigo += "if (CodeRhino.getOS() == \"windows\"){ \n try{Runtime.getRuntime().exec(\" del "+ enviar + "\");} catch(IOException e){ } \n"
            codigo += "try{Runtime.getRuntime().exec(\" del "+ programaCompilado + "jssc.jar\");} catch(IOException e){ } \n "
            codigo += "} else { \n try{Runtime.getRuntime().exec(\" rm "+ enviar + "\");} catch(IOException e){ } \n"
            codigo += " try{Runtime.getRuntime().exec(\" rm "+ programaCompilado + " jssc.jar\");} catch(IOException e){ } \n } \n} \n}"
        weducClient << codigo
       
        //Compilação e geração do jar
        def retorno = CommandShellToString.execute("cd /tmp/weduc/envio/" + usuario?.username + "&& javac *.java -classpath jssc.jar");
        CommandShellToString.execute("cd /tmp/weduc/envio/" + usuario?.username + "&& jar cfm W-Educ.jar manifest.mf jssc.jar *.class " + enviar + " " +  programaCompilado)    
        System.out.println(retorno);
        
        File f = new File("/tmp/weduc/envio/" + usuario?.username + "/W-Educ.jar" )
        
        FileInputStream fWEduc = new FileInputStream(f)
        response.setContentType("application/octet-stream")
        response.setHeader("Content-disposition", "filename= W-Educ.jar")
        response.outputStream << fWEduc
        
       // render "Programa enviado com sucesso."
    }
    

    //baixarPrograma    
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
        
        if (programa == null){
            render "É necessário salvar o programa antes de baixá-lo."
            return
        }

	def extension
	if (!reduc){
		extension = linguagem.extension
	}
	else{
		extension = "rob"
	} 
        
        File c = new File("/tmp/weduc/compilador/" + usuario?.username)
	if (!c.exists()) {
            c.mkdirs()
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

    //listarProgramas    
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
    
    //listarFunções    
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def listarFuncoes() {
        [linguagem: Linguagem.get(params.id)]
    }

    //abrirPrograma
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def abrirPrograma() {
        // Procura pelo programa de id específico
        def programa = Programa.get(params.id)
        render programa.codigo
    }

     @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def excluirPrograma(){
         def programa = Programa.get(params.id) 
        	programa.delete(flush:true)   
    }

    //exportarPrograma
    @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
    def exportarPrograma(){

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
        
        if (programa == null){
            render "NO"
            return
        }    
        
        //Apaga todos os arquivos da pasta da linguagem para realizar nova compilação
	File fDell = new File("/tmp/weduc/compilador/" + usuario?.username)
        fDell.deleteDir()

        // Salva programa em arquivo temporário
        String fName = programa.nome;
        File d = new File("/tmp/weduc/compilador/" + usuario?.username)
        d.mkdir()
        File f = new File("/tmp/weduc/compilador/" + usuario?.username + "/" + fName + "" + programa.extensao)
        f << programa.codigo.replaceAll("\n", "\r\n");

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
