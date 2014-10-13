package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class LinguagemController {

	def springSecurityService

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def nova() {}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def salvar() {

		// Linguagem propriamente dita
        def linguagem = new Linguagem()
        linguagem.name = params.nome
        linguagem.description = params.descricao
        linguagem.robot = params.robo
        linguagem.extension = params.extensao
        linguagem.compileCode = params.codigoDeCompilacao
        linguagem.compilerFile = params.arquivo
        linguagem.sendCode = params.codigoDeEnvio
        linguagem.sentExtension = params.extensaoDoArquivo
        linguagem.header = params.cabecalho
        linguagem.footnote = params.rodape
        linguagem.mainFunction = params.funcaoPrincipal
        linguagem.otherFunctions = params.funcao
        linguagem.callFunction = params.chamadaDeFuncao
        linguagem.autor = springSecurityService.getCurrentUser()

        // Tipos da linguagem
        linguagem.types = new Tipos()
        linguagem.types.name = ""
        linguagem.types.declareFalse = params.falso
        linguagem.types.declareTrue = params.verdadeiro
        linguagem.types.declareFloat = params.numero
        linguagem.types.declareString = params.texto
        linguagem.types.declareBoolean = params.booleano
        linguagem.types.save(flush: true, failOnError: true)

        // Operadores da linguagem
        linguagem.operators = new Operadores()
        linguagem.operators.equalTo = params.igual
        linguagem.operators.notEqualTo = params.diferente
        linguagem.operators.greaterThan = params.maior
        linguagem.operators.lessThan = params.menor
        linguagem.operators.greaterThanOrEqualTo = params.maiorOuIgual
        linguagem.operators.lessThanOrEqualTo = params.menorOuIgual
        linguagem.operators.logicalAnd = params.e
        linguagem.operators.logicalOr = params.ou
        linguagem.operators.logicalNot = params.nao
        linguagem.operators.name = params.nome
        linguagem.operators.save(flush: true, failOnError: true)

        // Controles de fluxo da linguagem
        linguagem.controlFlow = new ControleDeFluxo()
        linguagem.controlFlow.languageName = params.nome
        linguagem.controlFlow.breakCode = params.sair
        linguagem.controlFlow.doCode = params.farei
        linguagem.controlFlow.forCode = params.para
        linguagem.controlFlow.ifCode = params.se
        linguagem.controlFlow.repeatCode = params.repeat
        linguagem.controlFlow.whileCode = params.enquanto
        linguagem.controlFlow.switchCode = params.teste
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
        
        // Funções
        println "Iniciando cadastro das funcoes da linguagem " + linguagem.name
        
        
        
        //
	}
}