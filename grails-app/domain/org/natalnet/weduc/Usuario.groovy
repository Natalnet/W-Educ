package org.natalnet.weduc

class Usuario {

	transient springSecurityService

	String username
        String name
	String password
        String institution
        String dateofbirth
        String gender
        String address
	String email
        String telefone
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
<<<<<<< HEAD
                programas blank: true, nullable: true
                name blank: true, nullable:true
                institution blank: true, nullable:true
                dateofbirth blank: true, nullable:true
                gender blank: true, nullable:true
                address blank: true, nullable:true
                telefone blank: true, nullable:true
=======
                name blank: true, nulable:true
                institution blank: true, nulable:true
                dateofbirth blank: true, nulable:true
                gender blank: true, nulable:true
                address blank: true, nulable:true
                telefone blank: true, nulable:true
>>>>>>> 075c239feb4f9f765718ea0efb0d7159f10cc2b3
                
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
