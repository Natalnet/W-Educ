package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class AmbienteController {
	def index() {}

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def programar() {
		[linguagem: Linguagem.get(params.id)]
	}
}