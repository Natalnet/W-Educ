package org.natalnet.weduc

import grails.plugin.springsecurity.annotation.Secured

class LinguagemController {

	@Secured(['ROLE_ADMIN', 'ROLE_PROFESSOR'])
	def nova() {}
}