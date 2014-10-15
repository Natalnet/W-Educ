package org.natalnet.weduc

class Usuario {

	transient springSecurityService

	String username
	String password
	String email
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static hasMany = [programas: Programa]

	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Privilegio> getAuthorities() {
		UsuarioPrivilegio.findAllByUsuario(this).collect { it.privilegio }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
