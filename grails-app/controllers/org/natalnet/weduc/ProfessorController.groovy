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

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def aceitar() {

		// Define usuário atual
		def usuario = springSecurityService.getCurrentUser()

		// Define a requisição
		def requisicao = RequisicaoDeAluno.get(params.id)

		// Define o professor do aluno
		requisicao.aluno.professor = usuario

		// Salva as alterações
		requisicao.aluno.save(flush: true)

		// Define um aluno para o professor
		usuario.addToAlunos(requisicao.aluno)

		// Salva as alteraçÕes
		usuario.save(flush: true)

		// Remove requisicao
		requisicao.delete(flush: true)

		flash.message = requisicao.aluno.username + " foi aceito"

		redirect action: "gerenciarAlunos"

	}
	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def recusar() {

		// Define a requisição
		def requisicao = RequisicaoDeAluno.get(params.id)

		// Remove requisicao
		requisicao.delete(flush: true)

		flash.message = requisicao.aluno.username + " foi recusado"

		redirect action: "gerenciarAlunos"

	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def gerenciarAlunos() {
		def usuario = springSecurityService.getCurrentUser()
		[requisicoes: RequisicaoDeAluno.findAllWhere(professor: usuario), alunos: Aluno.findAllWhere(professor: usuario)]
	}
}