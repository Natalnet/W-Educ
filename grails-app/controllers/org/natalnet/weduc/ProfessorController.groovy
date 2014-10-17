package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class ProfessorController {

	def springSecurityService

	def index() {
		redirect action: "procurar"
	}

	@Secured(['ROLE_ADMIN', 'ROLE_ALUNO'])
	def procurar() {
		[professores: Professor.findAll()]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_ALUNO'])
	def selecionar() {

		// Define usuário atual
		def usuario = springSecurityService.getCurrentUser()

		// Remove antigas requisicoes
		RequisicaoDeAluno.findWhere(aluno: usuario)?.delete(flush: true)

		// Cria nova requisição
		def requisicao = new RequisicaoDeAluno()
		requisicao.aluno = usuario
		requisicao.professor = Professor.get(params.id)
		requisicao.save(flush: true)

		[professor: requisicao.professor.username]

	}


	@Secured(['ROLE_ADMIN', 'ROLE_ALUNO'])
	def aceitar() {

		def antigoProfessor = null
		def novoProfessor = null

		// Define usuário atual
		def usuario = springSecurityService.getCurrentUser()

		// Verifica se o aluno já possui algum professor
		if(usuario.professor != null) {

			// Armazena o nome
			antigoProfessor = usuario.professor.username

			// Desfaz a relação existente entre os dois
			usuario.professor.removeFromAlunos(usuario)

		}

		// Define o  novo professor do aluno
		def professor = Professor.get(params.id)

		// Salva os dados
		usuario.save flush: true

		[antigoProfessor: antigoProfessor, novoProfessor: usuario.professor.username]

	}
}