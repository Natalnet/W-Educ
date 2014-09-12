package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class AdminController {

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def index() {}

}