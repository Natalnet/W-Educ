package org.natalnet.weduc;

class MensagemController {

	def springSecurityService

	def index() {}

	def escrever() {
		def usuario = Usuario.get(params.id)
		[destinatario: usuario]
	}

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

	def ultimas() {
		def usuario = springSecurityService.getCurrentUser()
		def mensagens = Mensagem.list(max: 3, fetch: [destinatario: usuario])
		[mensagens: mensagens]
	}

	def todas() {
		def usuario = springSecurityService.getCurrentUser()
		def mensagens = Mensagem.findAllWhere(destinatario: usuario)
		[mensagens: mensagens]
	}

}