package org.natalnet.weduc

class Professor extends Usuario {

	static hasMany = [alunos: Aluno]
	
}