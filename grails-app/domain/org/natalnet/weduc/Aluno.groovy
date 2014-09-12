package org.natalnet.weduc

class Aluno extends Usuario {

	static belongsTo = [professor: Professor]
	
}