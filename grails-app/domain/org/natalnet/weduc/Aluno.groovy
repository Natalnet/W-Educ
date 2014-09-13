package org.natalnet.weduc

class Aluno extends Usuario {

	Professor professor

	static constraints = {
		professor nullable: true
	}
	
}