package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class ProfessorController {
	def index() {
		redirect action: "procurar"
	}

	@Secured(['ROLE_ADMIN', 'ROLE_ALUNO'])
	def procurar() {
		[professores: Professor.findAll()]
	}
}