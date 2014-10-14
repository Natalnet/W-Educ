package org.natalnet.weduc

class Login {
	Usuario usuario
	Boolean primeiro
	Date data

	static constraints = {
		usuario nullable: false
		data nullable: false
		primeiro nullable: true
	}

	static mapping = {
		primeiro defaultValue: false
	}
}