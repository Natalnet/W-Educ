package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class LinguagemController {

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
        
        //
	}
}