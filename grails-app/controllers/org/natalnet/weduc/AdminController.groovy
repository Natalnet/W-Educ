package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class AdminController {

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_ALUNO'])
	def index() {
		def meses = ["setembro", "outubro"]
		def loginsUnicos = [2, 4]
		def logins = [30, 22]
		[meses: meses, loginsUnicos: loginsUnicos, logins: logins]
	}

}