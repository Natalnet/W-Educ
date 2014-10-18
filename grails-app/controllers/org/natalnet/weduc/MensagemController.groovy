package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class MensagemController {

	def springSecurityService

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def index() {}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def escrever() {
		def usuario = Usuario.get(params.id)
		[destinatario: usuario]
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def enviar() {
		def mensagem = new Mensagem()
		mensagem.destinatario = Usuario.get(params.id)
		mensagem.autor = springSecurityService.getCurrentUser()
		mensagem.data = new Date()
		mensagem.mensagem = params.mensagem
		mensagem.save(flush: true)

		flash.message = "Mensagem a " + mensagem.destinatario.username + " enviada com sucesso."
		redirect controller: "professor", action: "gerenciarAlunos"
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def todas() {
		def usuario = springSecurityService.getCurrentUser()
		def mensagens = Mensagem.findAllWhere(destinatario: usuario)
		[mensagens: mensagens]
	}

}