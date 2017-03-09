package org.natalnet.weduc

class Professor extends Usuario {
    
    //static hasOne = [accessCode: AccessCode]
    static hasMany = [alunos: Aluno, enabledLanguages: Linguagem]
    static mappedBy = [enabledLanguages: 'acessors']
    
}