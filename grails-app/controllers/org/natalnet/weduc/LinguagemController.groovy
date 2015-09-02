package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class LinguagemController {

	def springSecurityService

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def index() {
		redirect action: "listar"
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def nova() {}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def salvar() {

		// Início do cadastro de linguagem

		// Linguagem propriamente dita
        def linguagem = new Linguagem()
        linguagem.name = params.nome.toString()
        linguagem.description = params.descricao.toString()
        linguagem.robot = params.robo.toString()
        linguagem.extension = params.extensao.toString()
        linguagem.compileCode = params.codigoDeCompilacao.toString()
        linguagem.compilerFile = params.arquivo.toString()
        linguagem.sendCode = params.codigoDeEnvio.toString()
        linguagem.sentExtension = params.extensaoDoArquivo.toString()
        linguagem.header = params.cabecalho.toString()
        linguagem.footnote = params.rodape.toString()
        linguagem.mainFunction = params.funcaoPrincipal.toString()
        linguagem.otherFunctions = params.funcao.toString()
        linguagem.callFunction = params.chamadaDeFuncao.toString()
        linguagem.autor = springSecurityService.getCurrentUser()

        // Tipos da linguagem
        linguagem.types = new Tipos()
        linguagem.types.name = ""
        linguagem.types.declareFalse = params.falso.toString()
        linguagem.types.declareTrue = params.verdadeiro.toString()
        linguagem.types.declareFloat = params.numero.toString()
        linguagem.types.declareString = params.texto.toString()
        linguagem.types.declareBoolean = params.booleano.toString()
        linguagem.types.save(flush: true, failOnError: true)

        // Operadores da linguagem
        linguagem.operators = new Operadores()
        linguagem.operators.equalTo = params.igual.toString()
        linguagem.operators.notEqualTo = params.diferente.toString()
        linguagem.operators.greaterThan = params.maior.toString()
        linguagem.operators.lessThan = params.menor.toString()
        linguagem.operators.greaterThanOrEqualTo = params.maiorOuIgual.toString()
        linguagem.operators.lessThanOrEqualTo = params.menorOuIgual.toString()
        linguagem.operators.logicalAnd = params.e.toString()
        linguagem.operators.logicalOr = params.ou.toString()
        linguagem.operators.logicalNot = params.nao.toString()
        linguagem.operators.name = params.nome.toString()
        linguagem.operators.save(flush: true, failOnError: true)

        // Controles de fluxo da linguagem
        linguagem.controlFlow = new ControleDeFluxo()
        linguagem.controlFlow.languageName = params.nome.toString()
        linguagem.controlFlow.breakCode = params.sair.toString()
        linguagem.controlFlow.doCode = params.farei.toString()
        linguagem.controlFlow.forCode = params.para.toString()
        linguagem.controlFlow.ifCode = params.se.toString()
        linguagem.controlFlow.repeatCode = params.repita.toString()
        linguagem.controlFlow.whileCode = params.enquanto.toString()
        linguagem.controlFlow.switchCode = params.teste.toString()
        linguagem.controlFlow.save(flush: true, failOnError: true)

        // Salvando a linguagem
        linguagem.save(flush: true, failOnError: true)

        // Definições
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
        
        println "Cadastro da linguagem " + linguagem.name + " concluído por " + springSecurityService.getCurrentUser()

        
	// Se a pasta de destino do arquivo não existir, cria	
	File pastadaLinguagem = new File("/tmp/weduc/compilador/" + linguagem.id)
        
	if (!pastadaLinguagem.exists()) {
           pastadaLinguagem.mkdirs()
        }


        // Fim do cadastro de linguagem

        if(linguagem.id != null) {
        	flash.message = "Linguagem " + linguagem.name + " cadastrada com sucesso."
        	redirect action: "editar", id: linguagem.id
        } else {
        	flash.message = "Erro ao cadastrar a linguagem " + linguagem.name + "."
        	redirect action: "nova" //, params: params
        }

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def editar() {
		def linguagem = Linguagem.get(params.id)
		[linguagem: linguagem]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def atualizar() {

		// Início do cadastro de linguagem

		// Linguagem propriamente dita
        def linguagem = Linguagem.get(params.id)
        linguagem.name = params.nome.toString()
        linguagem.description = params.descricao.toString()
        linguagem.robot = params.robo.toString()
        linguagem.extension = params.extensao.toString()
        linguagem.compileCode = params.codigoDeCompilacao.toString()
        linguagem.compilerFile = params.arquivo.toString()
        linguagem.sendCode = params.codigoDeEnvio.toString()
        linguagem.sentExtension = params.extensaoDoArquivo.toString()
        linguagem.header = params.cabecalho.toString()
        linguagem.footnote = params.rodape.toString()
        linguagem.mainFunction = params.funcaoPrincipal.toString()
        linguagem.otherFunctions = params.funcao.toString()
        linguagem.callFunction = params.chamadaDeFuncao.toString()
        linguagem.autor = springSecurityService.getCurrentUser()

        // Tipos da linguagem
        linguagem.types.name = ""
        linguagem.types.declareFalse = params.falso.toString()
        linguagem.types.declareTrue = params.verdadeiro.toString()
        linguagem.types.declareFloat = params.numero.toString()
        linguagem.types.declareString = params.texto.toString()
        linguagem.types.declareBoolean = params.booleano.toString()
        linguagem.types.save(flush: true, failOnError: true)

        // Operadores da linguagem
        linguagem.operators.equalTo = params.igual.toString()
        linguagem.operators.notEqualTo = params.diferente.toString()
        linguagem.operators.greaterThan = params.maior.toString()
        linguagem.operators.lessThan = params.menor.toString()
        linguagem.operators.greaterThanOrEqualTo = params.maiorOuIgual.toString()
        linguagem.operators.lessThanOrEqualTo = params.menorOuIgual.toString()
        linguagem.operators.logicalAnd = params.e.toString()
        linguagem.operators.logicalOr = params.ou.toString()
        linguagem.operators.logicalNot = params.nao.toString()
        linguagem.operators.name = params.nome.toString()
        linguagem.operators.save(flush: true, failOnError: true)

        // Controles de fluxo da linguagem
        linguagem.controlFlow.languageName = params.nome.toString()
        linguagem.controlFlow.breakCode = params.sair.toString()
        linguagem.controlFlow.doCode = params.farei.toString()
        linguagem.controlFlow.forCode = params.para.toString()
        linguagem.controlFlow.ifCode = params.se.toString()
        linguagem.controlFlow.repeatCode = params.repita.toString()
        linguagem.controlFlow.whileCode = params.enquanto.toString()
        linguagem.controlFlow.switchCode = params.teste.toString()
        linguagem.controlFlow.save(flush: true, failOnError: true)

        // Salvando a linguagem
        linguagem.save(flush: true, failOnError: true)
        
        println "Cadastro da linguagem " + linguagem.name + " atualizado por " + springSecurityService.getCurrentUser()

        // Fim do cadastro de linguagem

        if(linguagem.id != null) {
        	flash.message = "Linguagem " + linguagem.name + " atualizada com sucesso."
        	redirect action: "editar", id: linguagem.id
        } else {
        	flash.message = "Erro ao atualizar a linguagem " + linguagem.name + "."
        	redirect action: "editar" //, params: params
        }

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def listar() {
		def usuario = springSecurityService.getCurrentUser()
		[linguagens: Linguagem.findAllByAutor(usuario)]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def procurar() {
		[linguagens: Linguagem.findAll()]
	}

        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def uploadCompilacao() {
                [linguagem: Linguagem.get(params.id)]
        }

        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def uploadIncludes() {
                [linguagem: Linguagem.get(params.id)]
        }


        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def uploadEnvio() {
                [linguagem: Linguagem.get(params.id)]
        }

	//Salva compilador
        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def salvarCompilacao() {
                // Captura o arquivo enviado através do formulário
                def arquivo = request.getFile("arquivo")

		//Apaga os arquivos enviados anteriormente
		File fDell = new File("/weduc/arquivos-de-compilacao/" + params.id + "/")
	        fDell.deleteDir()
			

                // Se a pasta de destino do arquivo não existir, cria
                File pastaDeDestino = new File("/weduc/arquivos-de-compilacao/" + params.id + "/")
                if (!pastaDeDestino.exists()) {
                        pastaDeDestino.mkdirs()
                }

                // Transfere o arquivo enviado para a a pasta de destino
                // e fornece um nome específico a ele
                File destinoDoArquivo = new File("/weduc/arquivos-de-compilacao/" + params.id + "/arquivo")
                arquivo.transferTo(destinoDoArquivo)

		//Copia os arquivos de include e extrai na pasta	
/*	   	def ant = new AntBuilder()   
	   	def origem = "/weduc/arquivos-de-compilacao/" + params.id + "/arquivo"	
	   	def destino = "/weduc/arquivos-de-compilacao/" + params.id + "/" 
	   	ant.unzip(  src:origem,
           	dest: destino,
           	overwrite:"false",)

		println "Dando permissao!"
		Process proc;
                 proc = Runtime.getRuntime().exec("chmod -R 777 /weduc/arquivos-de-compilacao");
                 proc.waitFor();
		println "Terminando!"*/
			
                [linguagem: Linguagem.get(params.id)]
        }

	//Salva os arquivos necessários para compilar
        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def salvarIncludes() {
                // Captura o arquivo enviado através do formulário
                def arquivo = request.getFile("arquivo")
		
		//Apaga os arquivos enviados anteriormente
		File fDell = new File("/weduc/arquivos-de-include/" + params.id + "/")
	        fDell.deleteDir()
			
		
                // Se a pasta de destino do arquivo não existir, cria
                File pastaDeDestino = new File("/weduc/arquivos-de-include/" + params.id + "/")
                if (!pastaDeDestino.exists()) {
                        pastaDeDestino.mkdirs()
                }

                // Transfere o arquivo enviado para a a pasta de destino
                // e fornece um nome específico a ele
                File destinoDoArquivo = new File("/weduc/arquivos-de-include/" + params.id + "/arquivo")
                arquivo.transferTo(destinoDoArquivo)

                [linguagem: Linguagem.get(params.id)]
        }

        @Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
        def salvarEnvio() {
                // Captura o arquivo enviado através do formulário
                def arquivo = request.getFile("arquivo")

                // Se a pasta de destino do arquivo não existir, cria
                File pastaDeDestino = new File("/weduc/arquivos-de-envio/" + params.id + "/")
                if (!pastaDeDestino.exists()) {
                        pastaDeDestino.mkdirs()
                }


                // Transfere o arquivo enviado para a a pasta de destino
                // e fornece um nome específico a ele
                File destinoDoArquivo = new File("/weduc/arquivos-de-envio/" + params.id + "/arquivo")
                arquivo.transferTo(destinoDoArquivo)     

                [linguagem: Linguagem.get(params.id)]  
        }
}
