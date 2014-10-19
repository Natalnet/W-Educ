package org.natalnet.weduc

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
			reduc: reduc
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

		// Define o código
		programa.codigo = params.codigo

		render "OK"

	}
}