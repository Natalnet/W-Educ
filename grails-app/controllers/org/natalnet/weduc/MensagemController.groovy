package org.natalnet.weduc;

class MensagemController {
	def index() {}
	def escrever() {
		def usuario = Usuario.get(params.id)
		[destinatario: usuario.username]
	}
}